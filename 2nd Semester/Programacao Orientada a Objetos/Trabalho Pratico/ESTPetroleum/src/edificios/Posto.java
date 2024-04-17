package edificios;

import java.awt.Point;
import java.util.Objects;

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
	private String nome;
	private int id, quantidadeAtual, capacidade, gastoMedio;
	private Point posicao;
	private boolean pedido = false;
	
	/* ----------------------------------------- CONSTRUTOR ------------------------------------------------- */
	public Posto(int id, String nome, Point posicao, int gastoMedio, int quantidadeAtual, int capacidade) {
		
		this.id = id;
		this.posicao = posicao;
		
		if (capacidade >= 0 && capacidade >= quantidadeAtual) {
			this.capacidade = capacidade;
		} else {
			this.capacidade = quantidadeAtual;
		}
		
		if (quantidadeAtual >= 0 && quantidadeAtual <= capacidade) {
			setQuantidadeAtual(quantidadeAtual);
		} else {
			this.quantidadeAtual = capacidade;
		}
		
		if (gastoMedio >= 0) {
			this.gastoMedio = gastoMedio;
		} else {
			this.gastoMedio = 0;
		}
		
		this.nome = nome;
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
			if (podeEncher(nLitros)) {
				setQuantidadeAtual(quantidadeAtual + nLitros);
				return Central.ACEITE;
			} else {
				return Central.EXCEDE_CAPACIDADE_POSTO;
			}
		}
			return Central.POSTO_NAO_PRECISA;
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
		return (float) quantidadeAtual / capacidade;
	}
	
	// indica se o posto tem um pedido pendente -> @return true, se tiver um pedido */
	public boolean temPedidoPendente() {
		return pedido;
	}

	// Laborar do posto. O posto processa os gastos e verifica se precisa de realizar um pedido de abastecimento
	public void laborar() {	
		
		setQuantidadeAtual(quantidadeAtual - gastoMedio);
		
		if (quantidadeAtual < 0) {
			setQuantidadeAtual(0);
		}

		if (percentagemOcupacao() < OCUPACAO_MINIMA) {
			pedido = true;
		} else if ((percentagemOcupacao() < OCUPACAO_SUFICIENTE) && (Math.random() < PROBABILIDADE_REABASTECIMENTO)) {
			pedido = true;
		} else {
			pedido = false;
		}
	}
	
	public boolean podeEncher(int nLitros) {
		if (quantidadeAtual + nLitros <= capacidade) {
			return true;
		}
		return false;
	}

	/* --------------------------------------- GETTERS E SETTERS --------------------------------------------- */
	public int getCapacidade() {
		return capacidade;
	}
	
	public int getQuantidadeAtual() {
		return quantidadeAtual;
	}

	public void setQuantidadeAtual(int quantidadeAtual) {
		this.quantidadeAtual = quantidadeAtual;
	}

	public int getGastoMedio() {
		return gastoMedio;
	}

	public int getId() {
		return id;
	}

	public Point getPosicao() {
		return posicao;
	}
	
	public String getNome() {
		return nome;
	}

	/* --------------------------------------- EQUALS e HASHCODE ---------------------------------------------- */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posto other = (Posto) obj;
		return id == other.id;
	}
	
}
