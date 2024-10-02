package contrato;

/* ---------------------------------------------------------------------------- IMPORTS -------------------------------------------------------------------------------- */
import java.time.LocalTime;
import java.util.Objects;
import estacionamento.Ticket;

public abstract class ContratoDefault implements Contrato {

	public static final int CUSTO_FRACAO = 10;
	public static final int TEMPO_FRACAO = 15;
	/* ------------------------------------------------------------------------ VARIÁVEIS ------------------------------------------------------------------------------ */
	private String matricula;
	/* ------------------------------------------------------------------------ CONSTRUTOR ----------------------------------------------------------------------------- */
	
	public ContratoDefault(String matricula) {
		this.matricula = Objects.requireNonNull( matricula );
	}

	@Override
	public long calcularCusto(Ticket t) {
		long tempoEstacionamento = t.tempoAposEntrada();
		return ((tempoEstacionamento % TEMPO_FRACAO == 0 ? 0 : 1) + (tempoEstacionamento / TEMPO_FRACAO)) * CUSTO_FRACAO;
	}

	@Override
	public void pagar(Ticket t) {
		if (t.estaPago() )
			throw new TicketPagoException();
		t.setPagamento( LocalTime.now() );
		t.setCusto( calcularCusto(t) );

	}
	
	// O método pagamento mensal como tem o comportamento diferente nos 3 tipos (Ocasional, ViaEST e Residente) usa-se essa mesma classe abstrata e implementa-se esse
	// método da forma correta em cada classe

	@Override
	public void sair(Ticket t) {
		t.setSaida( LocalTime.now() );
	}

	@Override
	public String getMatricula() {
		return matricula;
	}

}
