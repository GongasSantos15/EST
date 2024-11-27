package estacionamento;

/*----------------------------------------------------------------------------------------------------- IMPORTS --------------------------------------------------------------------------------------*/
import java.util.*;
import contrato.*;

public class ESTacionamento {
	
	/*------------------------------------------------------------------------------------------------ CONSTANTES ------------------------------------------------------------------------------------*/
	public static final int CUSTO_FRACAO = 10;
	public static final int TEMPO_FRACAO = 15;
	public static final int TEMPO_RETIRADA = 10;
	private static final int CAPACIDADE = 3;

	/*------------------------------------------------------------------------------------------------- VARIÁVEIS ------------------------------------------------------------------------------------*/
	private Map<String,Ticket> tickets = new HashMap<String, Ticket>();
	private Map<String,Ticket> ticketsAtivos = new HashMap<String, Ticket>();
	
	private Map<String, Contrato> contratos = new HashMap<String, Contrato>();
	
	private int lugaresOcupados = 0;
	
	/*------------------------------------------------------------------------------------------------- CONSTRUTOR -----------------------------------------------------------------------------------*/
	public ESTacionamento() {		
	}

	/*-------------------------------------------------------------------------------------------------- MÉTODOS -------------------------------------------------------------------------------------*/
	public void entrar( String matricula ) throws ParqueCheioException, MatriculaRepetidaException {
		// TODO FEITO Auto-generated method stub
		
		// 1º - Testar se o parque está cheio
		if ( estaCheio() )
			throw new ParqueCheioException();
		
		// 2º - Testar se a viatura está dentro do parque
		if ( getTicketAtivo(matricula) != null )
			throw new MatriculaRepetidaException();
		
		// 3º - Criar um ticket com a matricula e respetivo contrato
		Ticket t = new Ticket( matricula, getContratoUsar(matricula) );
		
		// 4º - Armazenar ticket
		addTicketAtivo( t );
		lugaresOcupados++;
	}
	
	// Calcula o custo com base no contrato
	public long calcularCusto( String matricula ) throws MatriculaDesconhecidaException {
		Ticket t = getTicketAtivo( matricula );
		
		// 1º - Verificar se a matricula é válida
		if (t == null)
			throw new MatriculaDesconhecidaException( matricula );
		
		// 2º - Verificar se está pago
		if ( t.estaPago() )
			throw new PagamentoTicketException();
		
		// 3º - Calcular o custo
		return t.getContrato().calcularCusto(t);
	}

	// Efetua o pagamento com base no contrato
	public void pagar( String matricula ) throws MatriculaDesconhecidaException {
		Ticket t = getTicketAtivo( matricula );
		
		// 1º - Verificar se a matricula é válida
		if (t == null)
			throw new MatriculaDesconhecidaException( matricula );
		
		// 2º - Verificar se está pago
		if ( t.estaPago() )
			throw new PagamentoTicketException();
		
		// 3º - Pagar
		t.getContrato().pagar(t);
	}
	
	/** retorna a capacidade do parque, isto é, quantos lugares o aprque tem no total.
	 * @return a capacidade do parque
	 */
	public int capacidade() {
		// TODO FEITO nem todos os parques tem 20 lugares
		return CAPACIDADE;
	}

	/** retorna o número de lugares ocupados no parque
	 * @return o número de lugares ocupados no parque
	 */
	public int lugaresOcupados() {
		return lugaresOcupados;
	}
	
	/** indica se o parque está cheio, isto é, está na capacidade total ocupada
	 * @return true, se o parque está cheio
	 */
	public boolean estaCheio() {
		// TODO FEITO implementar este método
		return lugaresOcupados() >= capacidade(); 
	}
	
	/** indica se o parque ainda tem lugares livres
	 * @return true, se o parque ainda tem lugares livres
	 */
	public boolean estaLivre() {
		// TODO FEITO implementar este método
		return !estaCheio();
	}
	
	/** retorna quantos lugares vagos tem o parque
	 * @return quantos lugares vagos tem o parque
	 */
	public int lugaresVagos() {
		// TODO FEITO implementar este método
		return capacidade() - lugaresOcupados();
	}
	
	/** Indica se uma matrícula tem um contrato associado
	 * @param matricula a matrícula a verificar
	 * @return true, se tem contrato associado
	 */
	public boolean temContrato( String matricula ) {
		// TODO FEITO implementar este método
		// return getContrato(matricula) != null;
		return contratos.containsKey( matricula );
	}

	/** Retorna o contrato associado a uma matrícula
	 * @param matricula a matrícula a verificar
	 * @return o contrato associado à matricula, ou null se não houver
	 */
	public Contrato getContrato( String matricula ){
		// TODO FEITO implementar este método
		return contratos.get( matricula );
	}
	
	// Devolve o contrato associado, mas caso ele não exista retorna um contrato ocasional
	public Contrato getContratoUsar( String matricula ) {
		// TODO FEITO Auto-generated method stub
		// return temContrato(matricula) ? getContrato(matricula) : new ContratoOcasional(matricula);
		
		// Este código só vai ao mapa uma vez buscar a informação
		Contrato c = getContrato( matricula );
		return c != null ? c : new ContratoOcasional (matricula );
	}

	/** adiciona um contrato ao parque
	 * @param c contrato a adicionar
	 */
	public void addContrato( Contrato c ){		
		// TODO FEITO implementar este método
		contratos.put( c.getMatricula(), c );
	}

	/** retorna todos os contratos existentes
	 * @return todos os contratos existentes
	 */
	public Collection<Contrato> getContratos() {
		// TODO FEITO implementar este método
		
		// Esta forma retorna os contratos, mas ao fazer alterações, altera o mapa em si
		//return contratos.values();
		return Collections.unmodifiableCollection( contratos.values() );
	}
	
	/** adiciona um ticket 
	 * @param t ticket a adicionar
	 */
	private void addTicket( Ticket t ){		
		// TODO implementar este método
	}

	/** adiciona um ticket aos tickets ativos
	 * @param t tickete a adicionar
	 */
	private void addTicketAtivo(Ticket t) {		
		// TODO FEITO implementar este método
		ticketsAtivos.put( t.getMatricula(), t );
	}

	/** remove o ticket ativo
	 * @param t o ticket a remover
	 */
	private void removeTicketAtivo( Ticket t  ){		
		// TODO FEITO implementar este método
		ticketsAtivos.remove( t.getMatricula(), t );
	}
	
	/** retorna o ticket ativo associado à matricula
	 * @param matricula a verificar
	 * @return o ticket ativo associado à matrícula ou null se não houver 
	 */
	public Ticket getTicketAtivo( String matricula  ){
		// TODO FEITO implementar este método
		return ticketsAtivos.get( matricula );
	}

	/** retorna todos os tickets ativos
	 * @return todos os tickets ativos
	 */
	public Collection<Ticket> getTicketsAtivos() {
		// TODO FEITO implementar este método
		return Collections.unmodifiableCollection( ticketsAtivos.values() );
	}
	
	/** retorna todos os tickets (ativos e já expirados)
	 * @return todos os tickets (ativos e já expirados)
	 */
	public Collection<Ticket> getAllTickets() {
		// TODO FEITO implementar este método
		return Collections.unmodifiableCollection( tickets.values() );
	}
}

