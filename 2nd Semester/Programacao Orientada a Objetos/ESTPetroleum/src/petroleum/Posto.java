package petroleum;

import java.awt.Point;

	 /** Um posto do sistema. Um posto deve ter uma capacidade
	 * máxima de combustível, assim como a quantidade de combustível que tem.
	 * Para simular o uso do posto assume-se que ele tem num gasto médio diário,
	 * o que significa que todos os dias, o posto vende esse combústivel.
	 */

public class Posto {
	// indica a capacidade (em percentagem) a partir da qual o posto não aceita novos pedidos abastecimento */
	public static final double OCUPACAO_SUFICIENTE = 0.75; 

	// indica a capacidade (em percentagem) abaixo da qual o posto precisa de fazer um pedido */
	public static final double OCUPACAO_MINIMA = 0.25; 
	
	// indica a probabilidade (em percentagem) para o posto pedir um novo pedido
	public static final double PROBABILIDADE_REABASTECIMENTO = 0.1;
	

	/* ------------------------------------------ VARIÁVEIS ------------------------------------------------ */
	private int id, quantidadeAtual, capacidade, gastoMedio;
	private Point x, y;
	
	/* ----------------------------------------- CONSTRUTOR ------------------------------------------------- */
	public Posto(int id, Point x, Point y, int capacidade, int quantidadeAtual, int gastoMedio) {
		
		this.id = id;
		this.x = x;
		this.y = y;
		setCapacidade(capacidade);
		setQuantidadeAtual(quantidadeAtual);
		setGastoMedio(gastoMedio);
	}
	
	/* ------------------------------------------- MÉTODOS -------------------------------------------------- */

	/** transferência de combústivel para o posto
	 * @param nLitros -> litros a transferir
	 * @return ACEITE -> o pedido foi adicionado ao camião<br>
	 *         POSTO_NAO_PRECISA -> se o posto não necessita de ser abastecido
	 *         EXCEDE_CAPACIDADE_POSTO -> se o posto não tem capacidade de armazenar os litros indicados      
	 */
	public int enche( int nLitros ) {
		if (temPedidoPendente()) {      
			if (quantidadeAtual + nLitros < capacidade) {
				return Central.ACEITE;
			} else if (quantidadeAtual == capacidade) {
				return Central.POSTO_NAO_PRECISA;
			}
		}
		return Central.EXCEDE_CAPACIDADE_POSTO;
	}
	
	/** retorna a capacidade livre, isto é, quantos litros ainda podem ser armazenados no posto
	 * @return -> capacidade livre
	 */
	public int capacidadeLivre() {
		return capacidade - quantidadeAtual;
	}
	
	/** retorna a percentagem de ocupação do posto, entre 0 (0%) e 1 (100%) -> @return a percentagem de ocupação
	 * do posto
	 */
	public float percentagemOcupacao() {
		return quantidadeAtual/capacidade;
	}

	
	// indica se o posto tem um pedido pendente -> @return true, se tiver um pedido */
	public boolean temPedidoPendente() {
		if (quantidadeAtual < OCUPACAO_MINIMA * capacidade || Math.random() < PROBABILIDADE_REABASTECIMENTO) {
			return addPedido();
		}
		return false;
	}

	// Laborar do posto. O posto processa os gastos e verifica se precisa de realizar um pedido de abastecimento
	public void laborar() {
		setQuantidadeAtual(quantidadeAtual-gastoMedio);
		addPedido();
	}
	
	// Adicionar o pedido, quando existe um pedido pendente
	public boolean addPedido() {
		return temPedidoPendente();
	}
	
	/* --------------------------------------- GETTERS E SETTERS --------------------------------------------- */
	public int getCapacidade() {
		return capacidade;
	}


	public void setCapacidade(int capacidade) {
		if (capacidade >= 0 && capacidade >= this.quantidadeAtual) {
			this.capacidade = capacidade;
		}
	}

	
	public int getQuantidadeAtual() {
		return quantidadeAtual;
	}


	public void setQuantidadeAtual(int quantidadeAtual) {
		if (quantidadeAtual >= 0 && quantidadeAtual <= this.capacidade)
			this.quantidadeAtual = quantidadeAtual;
	}


	public double getGastoMedio() {
		return gastoMedio;
	}


	public void setGastoMedio(int gastoMedio) {
		if (gastoMedio >= 0) {
			this.gastoMedio = gastoMedio;
		}
	}


	public int getId() {
		return id;
	}


	public Point getX() {
		return x;
	}


	public Point getY() {
		return y;
	}
	
}
