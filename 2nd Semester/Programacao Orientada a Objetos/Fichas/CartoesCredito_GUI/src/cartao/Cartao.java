package cartao;

import java.awt.Image;

import banco.Conta;

/**
 * Interface que define o comportamento de um cartão de crédito
 */
public interface Cartao {

	/**
	 *  faz o levantamento de uma dada quantia
	 * @param quantia quantia a levantar
	 * @throws SaldoInsuficienteException 
	 * @throws CartaoBloqueadoException 
	 */
	public void levanta( long quantia );
	
	/**
	 *  faz uma compra
	 * @param quantia quantia a gastar
	 * @throws SaldoInsuficienteException 
	 * @throws CartaoBloqueadoException 
	 */
	public void compra( long quantia );
	
	/**
	 * retorna o plafond 
	 * @return o plafond 
	 */
	public long getPlafond();
	
	/**
	 * Altera o plafond do cartão
	 * @param plafond
	 */
	public void setPlafond( long plafond );
	
	/**
	 * Indica qual o valor que ainda está disponível no plafond
	 * @return o valor que ainda está disponível no plafond
	 */
	public long getPlafondDisponivel();
	
	/**
	 * devolve a dívida do cartão, isto é, quanto é que já gastou usando o cartão
	 * @return a dívida do cartão
	 */
	public long getDivida();
	
	/**
	 * indica o estado do cartão (true = ativo, false=desativo)
	 * @return o estado do cartão
	 */
	public boolean estaAtivo();	
	
	/**
	 * define o estado do cartão  (true = ativo, false=desativo)
	 * @param activo novo estado do cartão
	 */
	public void setAtivo( boolean ativo );
	
	/**
	 * faz as contas no final do mês
	 * @throws SaldoInsuficienteException 
	 * @throws CartaoBloqueadoException 
	 */
	public void resetPlafond();
	
	/** 
	 * devolde o número do cartão
	 * @return o número do cartão
	 */
	public int getNumero();
	
	/** devolve a conta associada ao cartão
	 * @return a conta associada ao cartão
	 */
	public Conta getConta();
	
	/** devolve a imagem do cartão
	 * @return a imagem do cartão
	 */
	public Image getImagem();
	
	/** altera a imagem do cartão
	 * @param imagem a nova imagem
	 */
	public void setImagem(Image imagem);
}
