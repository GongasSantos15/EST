package contrato;

/*----------------------------------------------------------------------------------------------------- IMPORTS ---------------------------------------------------------------------------------------*/
import estacionamento.Ticket;

public class ContratoResidente extends ContratoDefault {

	/*------------------------------------------------------------------------------------------------- VARIÁVEIS -------------------------------------------------------------------------------------*/
	private long mensalidade;
	
	/*------------------------------------------------------------------------------------------------- CONSTRUTOR ------------------------------------------------------------------------------------*/
	public ContratoResidente(String matricula, long mensalidade) {
		super(matricula);
		if( mensalidade < 0 )
			throw new IllegalArgumentException("mensalidade < 0: " + mensalidade);
		this.mensalidade = mensalidade;
	}
	
	/*-------------------------------------------------------------------------------------------------- MÉTODOS --------------------------------------------------------------------------------------*/
	@Override
	public long pagamentoMensal() {
		return mensalidade;
	}

	@Override
	public long calcularCusto(Ticket t) {
		return 0;
	}
	
	@Override
	public void sair(Ticket t) throws TempoExcedidoException {
		pagar( t );
		super.sair(t);
	}
}
