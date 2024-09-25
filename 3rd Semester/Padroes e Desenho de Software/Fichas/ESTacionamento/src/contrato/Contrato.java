package contrato;

import estacionamento.Ticket;

/**
 * Interface que especifica o contrato de um contrato :-)
 * Esta interface define tudo o que todos os contratos devem 
 * saber realizar.
 */
public interface Contrato {

	/** calcula o custo de um ticket
	 * @param t ticket do qual se calcula o custo
	 * @return o custo de um ticket
	 */
	public long calcularCusto(Ticket t);
	
	/** procedimento para pagar o ticket
	 * @param t ticket a ser pago
	 */
	public void pagar( Ticket t );
	
	/** procedimento para sair do parque
	 * @param t ticket a sair
	 */
	public void sair( Ticket t );
	
	/** indica qual o valor a pagar no final do mês
	 * @return qual o valor a pagar no final do mês
	 */
	public long pagamentoMensal();
	
	/** retorna a matrícula associada ao contrato
	 * @return  a matrícula associada ao contrato
	 */
	public String getMatricula();
}
