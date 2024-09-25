package estacionamento;

import java.util.*;

import contrato.*;

public class ESTacionamento {
	private Map<String,Ticket> tickets = new HashMap<String, Ticket>();
	private Map<String,Ticket> ticketsAtivos = new HashMap<String, Ticket>();
	
	private int lugaresOcupados = 0;
	
	public ESTacionamento() {		
	}

	/** retorna a capacidade do parque, isto é, quantos lugares o aprque tem no total.
	 * @return a capacidade do parque
	 */
	public int capacidade() {
		// TODO nem todos os parques tem 20 lugares
		return 20;
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
		// TODO implementar este método
		return false; 
	}
	
	/** indica se o parque ainda tem lugares livres
	 * @return true, se o parque ainda tem lugares livres
	 */
	public boolean estaLivre() {
		// TODO implementar este método
		return true;
	}
	
	/** retorna quantos lugares vagos tem o parque
	 * @return quantos lugares vagos tem o parque
	 */
	public int lugaresVagos() {
		// TODO implementar este método
		return 0;
	}
	
	/** Indica se uma matrícula tem um contrato associado
	 * @param matricula a matrícula a verificar
	 * @return true, se tem contrato associado
	 */
	public boolean temContrato( String matricula ) {
		// TODO implementar este método
		return false;
	}

	/** Retorna o contrato associado a uma matrícula
	 * @param matricula a matrícula a verificar
	 * @return o contrato associado à matricula, ou null se não houver
	 */
	public Contrato getContrato( String matricula ){
		// TODO implementar este método
		return null;
	}

	/** adiciona um contrato ao parque
	 * @param c contrato a adicionar
	 */
	public void addContrato( Contrato c ){		
		// TODO implementar este método
	}

	/** retorna todos os contratos existentes
	 * @return todos os contratos existentes
	 */
	public Collection<Contrato> getContratos() {
		// TODO implementar este método
		return null;
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
		// TODO implementar este método
	}

	/** remove o ticket ativo
	 * @param t o ticket a remover
	 */
	private void removeTicketAtivo( Ticket t  ){		
		// TODO implementar este método
	}
	
	/** retorna o ticket ativo associado à matricula
	 * @param matricula a verificar
	 * @return o ticket ativo associado à matrícula ou null se não houver 
	 */
	public Ticket getTicketAtivo( String matricula  ){
		// TODO implementar este método
		return null;
	}

	/** retorna todos os tickets (ativos e já expirados)
	 * @return todos os tickets (ativos e já expirados)
	 */
	public Collection<Ticket> getTicketsAtivos() {
		// TODO implementar este método
		return null;
	}
	
	/** retorna todos os tickets ativos
	 * @return todos os tickets ativos
	 */
	public Collection<Ticket> getAllTickets() {
		// TODO implementar este método
		return null;
	}
}

