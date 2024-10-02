package contrato;

/* ---------------------------------------------------------------------------- IMPORTS -------------------------------------------------------------------------------- */
import estacionamento.Ticket;

public class ContratoViaEST extends ContratoDefault {
	
	/* ------------------------------------------------------------------------ VARIÁVEIS ------------------------------------------------------------------------------ */
	private long divida = 0;

	/* ------------------------------------------------------------------------ CONSTRUTOR ----------------------------------------------------------------------------- */
	public ContratoViaEST(String matricula) {
		super(matricula);
	}

	/* -------------------------------------------------------------------------- MÉTODOS ------------------------------------------------------------------------------ */
	@Override
	public long pagamentoMensal() {
		return divida;
	}
	
	@Override
	public void sair(Ticket t) throws TempoExcedidoException {
		if (!t.estaPago()) {
			divida += calcularCusto(t);
			pagar(t);
		}
		
		if (excedeuTempoSaida(t)) {
			throw new TempoExcedidoException();
		}
		
		super.sair(t);
	}

}
