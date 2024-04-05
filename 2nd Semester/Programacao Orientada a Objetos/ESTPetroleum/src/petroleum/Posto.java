package petroleum;

/** Um posto do sistema. Um posto deve ter uma capacidade
 * máxima de combustível, assim como a quantidade de combustível que tem.
 * Para simular o uso do posto assume-se que ele tem num gasto médio diário,
 * o que significa que todos os dias, o posto vende esse combústivel.
 */
public class Posto {
	/** indica a capacidade (em percentagem) a partir da qual o posto não aceita
	 * novos pedidos abastecimento */
	public static final double OCUPACAO_SUFICIENTE = 0.75; 

	/** indica a capacidade (em percentagem) abaixo da qual o
	 * posto precisa de fazer um pedido */
	public static final double OCUPACAO_MINIMA = 0.25; 

	/** transferência de combústivel para o posto
	 * @param nLitros litros a transferir
	 * @return ACEITE, o pedido foi adicionado ao camião<br>
	 *         POSTO_NAO_PRECISA, se o posto não necessita de ser abastecido
	 *         EXCEDE_CAPACIDADE_POSTO, se o posto não tem capacidade de armazenar os litros indicados      
	 */
	public int enche( int nLitros ){
		// TODO fazer este método
		return Central.ACEITE;
	}
	
	/** retorna a capacidade livre, isto é, quantos
	 * litros ainda podem ser armazenados no posto
	 * @return a capacidade livre
	 */
	public int capacidadeLivre() {
		// TODO fazer este método
		return 0;
	}
	
	/** retorna a percentagem de ocupação do posto, entre 0 (0%) e 1 (100%)
	 * @return a percentagem de ocupação do posto
	 */
	public float percentagemOcupacao() {
		// TODO fazer este método
		return 0;
	}

	/** indica se o posto tem um pedido pendente
	 * @return true, se tiver um pedido
	 */
	public boolean temPedidoPendente() {
		// TODO fazer este método
		return false;
	}

	/** Laborar do posto. O posto processa os gastos e verifica
	 * se precisa de realizar um pedido de abastecimento
	 */
	public void laborar() {
		// TODO fazer este método
	}
}
