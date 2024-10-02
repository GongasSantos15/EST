package contrato;


/* ---------------------------------------------------------------------------- IMPORTS -------------------------------------------------------------------------------- */
import java.time.LocalDateTime;
import java.util.Objects;
import estacionamento.ESTacionamento;
import estacionamento.Ticket;

public abstract class ContratoDefault implements Contrato {

	/* ------------------------------------------------------------------------ VARIÁVEIS ------------------------------------------------------------------------------ */
	private String matricula;
	
	/* ------------------------------------------------------------------------ CONSTRUTOR ----------------------------------------------------------------------------- */
	public ContratoDefault(String matricula) {
		this.matricula = Objects.requireNonNull( matricula );
	}

	/* -------------------------------------------------------------------------- MÉTODOS ------------------------------------------------------------------------------ */
	@Override
	public long calcularCusto(Ticket t) {
		long tempoEstacionamento = t.tempoAposEntrada();
		return ((tempoEstacionamento % ESTacionamento.TEMPO_FRACAO == 0 ? 0 : 1) + (tempoEstacionamento / ESTacionamento.TEMPO_FRACAO)) * ESTacionamento.CUSTO_FRACAO;
	}

	@Override
	public void pagar(Ticket t) {
		if (t.estaPago() )
			throw new PagamentoTicketException();
		t.setPagamento( LocalDateTime.now() );
		t.setCusto( calcularCusto(t) );

	}
	
	// O método pagamento mensal como tem o comportamento diferente nos 3 tipos (Ocasional, ViaEST e Residente) usa-se essa mesma classe abstrata e implementa-se esse
	// método da forma correta em cada classe

	@Override
	public void sair(Ticket t) throws TempoExcedidoException {
		t.setSaida( LocalDateTime.now() );
	}

	@Override
	public String getMatricula() {
		return matricula;
	}
	
	protected boolean excedeuTempoSaida(Ticket t) {
		return t.tempoAposPagamento() > ESTacionamento.TEMPO_RETIRADA;
	}

}
