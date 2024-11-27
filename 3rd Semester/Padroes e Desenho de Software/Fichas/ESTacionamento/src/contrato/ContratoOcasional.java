package contrato;

/*----------------------------------------------------------------------------------------------------- IMPORTS ---------------------------------------------------------------------------------------*/
import estacionamento.Ticket;

public class ContratoOcasional extends ContratoDefault {

	/*------------------------------------------------------------------------------------------------- CONSTRUTOR ------------------------------------------------------------------------------------*/
	public ContratoOcasional(String matricula) {
		super(matricula);
	}

	/*-------------------------------------------------------------------------------------------------- MÉTODOS --------------------------------------------------------------------------------------*/
	@Override
	public long pagamentoMensal() {
		return 0;
	}

	@Override
	public void sair(Ticket t) throws TempoExcedidoException {
		if( !t.estaPago() ) 
			throw new PagamentoTicketException();
		
		if( excedeuTempoSaida( t ) ) 
			throw new TempoExcedidoException();

		super.sair(t);
	}

}
