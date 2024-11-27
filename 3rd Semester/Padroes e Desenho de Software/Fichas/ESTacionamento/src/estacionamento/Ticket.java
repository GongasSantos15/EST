package estacionamento;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import contrato.Contrato;

/** Representa um ticket virtual do sistema. Este guarda informações
 * como a data de entrada, de pagamento e de saída, bem como a matrícula 
 * e o custo do estacionamento.  O contrato associado também deve constar.
 */
public class Ticket {
	
	private String matricula;
	private LocalDateTime entrada; 
	private LocalDateTime pagamento; 
	private LocalDateTime saida;
	private long custo;
	private Contrato contrato;

	public Ticket(String matricula, Contrato contrato, LocalDateTime entrada) {
		this.matricula = Objects.requireNonNull( matricula );
		// TODO FEITO Não esquecer de testar o contrato
		this.contrato = Objects.requireNonNull( contrato );
		this.entrada = Objects.requireNonNull( entrada );
	}

	public Ticket(String matricula, Contrato contrato) {
		this( matricula, contrato, LocalDateTime.now() );
	}

	/** retorna quanto tempo se passou em minutos, desde que foi emitido
	 * @return quantos minutos passaram desde que entrou
	 */
	public long tempoAposEntrada() {
		// TODO FEITO implementar este método
		return ChronoUnit.MINUTES.between(entrada, LocalDateTime.now() );
		
		//return entrada.until( LocalTime.now(), ChronoUnit.MINUTES );
	}
	
	/** Indica se o ticket já está pago
	 * @return true, se o ticket já está pago
	 */
	public boolean estaPago() {
		// TODO FEITO implementar este método
		return pagamento != null;
	}
	
	/** retorna quanto tempo se passou, em minutos, desde que foi pago. <br><strong>Este método não
	 * pode ser chamado se o ticket ainda não foi pago</strong>.
	 * @return quantos minutos passsaram desde que pagou
	 */
	public long tempoAposPagamento() {
		// TODO FEITO implementar este método
		//return estaPago()? ChronoUnit.MINUTES.between(pagamento, LocalTime.now() ): -1;
		return ChronoUnit.MINUTES.between(pagamento, LocalDateTime.now() );
	}

	public LocalDateTime getPagamento() {
		return pagamento;
	}

	public void setPagamento(LocalDateTime pagamento) {
		//Objects.requireNonNull( pagamento );
		if( pagamento.isBefore(entrada) ) 
			throw new IllegalArgumentException("pagamento antes da entrada");
		this.pagamento = pagamento;
	}

	public LocalDateTime getSaida() {
		return saida;
	}

	public void setSaida(LocalDateTime saida) {
		if( !estaPago() || saida.isBefore( pagamento) )
			throw new IllegalArgumentException( "saida antes do pagamento" );
		this.saida = saida;
	}

	public long getCusto() {
		return custo;
	}

	public void setCusto(long custo) {
		if( custo < 0 )
			throw new IllegalArgumentException("custo não pode ser < 0");
		this.custo = custo;
	}

	public String getMatricula() {
		return matricula;
	}

	public LocalDateTime getEntrada() {
		return entrada;
	}

	public Contrato getContrato() {
		return contrato;
	}

	@Override
	public String toString() {
		return "Ticket [matricula=" + matricula + ", entrada=" + entrada + ", pagamento=" + pagamento + ", saida="
				+ saida + ", custo=" + custo + "]";
	}
	
	
}
