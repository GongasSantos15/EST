package estacionamento;

/* ---------------------------------------------------------------------------- IMPORTS -------------------------------------------------------------------------------- */

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import contrato.Contrato;

/** Representa um ticket virtual do sistema. Este guarda informações como a data de entrada, de pagamento e de saída, bem como a matrícula  e o custo do estacionamento.  
 * O contrato associado também deve constar. */
public class Ticket {
	
	/* ------------------------------------------------------------------------ VARIÁVEIS ------------------------------------------------------------------------------ */
	
	private String matricula;
	private LocalTime entrada;
	private LocalTime pagamento;
	private LocalTime saida;
	private long custo;
	private Contrato contrato;
	
	/* ------------------------------------------------------------------------ CONSTRUTOR ----------------------------------------------------------------------------- */
	
	// Ticket com a hora a introduzir
	public Ticket(String matricula, Contrato contrato, LocalTime entrada) {
		
		// Verificar se as variáveis introduzidas pelo utilizador são nulas
		this.matricula = Objects.requireNonNull( matricula );
		
		// TODO NÃO ESQUECER
		// this.contrato = Objects.requireNonNull( contrato );
		
		this.entrada = Objects.requireNonNull( entrada );
	}
	
	// Ticket com a hora atual, definida pelo sistema
	public Ticket(String matricula, Contrato contrato) {
		this( matricula, contrato, LocalTime.now() );
	}

	/* -------------------------------------------------------------------------- MÉTODOS ------------------------------------------------------------------------------ */

	/** retorna quanto tempo se passou em minutos, desde que foi emitido 
	 * @return quantos minutos passaram desde que entrou
	 */
	public long tempoAposEntrada() {
		// TODO FEITO implementar este método
		// return  entrada.until(LocalTime.now(), ChronoUnit.MINUTES);
		return ChronoUnit.MINUTES.between( entrada, LocalTime.now() );
	}
	
	/** Indica se o ticket já está pago
	 * @return true, se o ticket já está pago
	 */
	public boolean estaPago() {
		// TODO FEITO implementar este método
		return pagamento != null;
	}
	
	/** retorna quanto tempo se passou, em minutos, desde que foi pago. <br>Se ainda não foi pago retorna -1.
	 * @return quantos minutos passsaram desde que pagou ou -1 se ainda não pagou
	 */
	public long tempoAposPagamento() {
		// TODO FEITO implementar este método
		return ChronoUnit.MINUTES.between( pagamento, LocalTime.now() );
	}
	
	/* ---------------------------------------------------------------------- GETTERS E SETTERS ----------------------------------------------------------------------- */
	
	public LocalTime getPagamento() {
		return pagamento;
	}

	public void setPagamento(LocalTime pagamento) {
		
		// Objects.requireNonNull( pagamento );
		if( pagamento.isBefore(entrada) )
			throw new IllegalArgumentException( "Pagamento antes da entrada" );
		this.pagamento = pagamento;
	}

	public LocalTime getSaida() {
		return saida;
	}

	public void setSaida(LocalTime saida) {
		if ( !estaPago() || saida.isBefore(pagamento) )
			throw new IllegalArgumentException( "Saída antes do pagamento" );
		this.saida = saida;
	}

	public long getCusto() {
		return custo;
	}

	public void setCusto(long custo) {
		if (custo < 0 )
			throw new IllegalArgumentException("Custo não pode ser < 0");
		this.custo = custo;
	}

	public String getMatricula() {
		return matricula;
	}

	public LocalTime getEntrada() {
		return entrada;
	}

	public Contrato getContrato() {
		return contrato;
	}

	/* ---------------------------------------------------------------------- TOSTRING ----------------------------------------------------------------------- */
	
	@Override
	public String toString() {
		return "Ticket [matricula=" + matricula + ", entrada=" + entrada + ", pagamento=" + pagamento + ", saida="
				+ saida + ", custo=" + custo + "]";
	}
	
}
