package banco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import cartao.Cartao;

/* Classe que representará uma conta
 */
public class Conta {
	
	/* ---------------------------------------------------------------------------------- VARIÁVEIS ------------------------------------------------------------------------------------------------------ */
	private int numero;
	private long saldo;
	private ArrayList<Cartao> cartoes = new ArrayList<Cartao>();
	
	/* ---------------------------------------------------------------------------------- CONSTRUTOR ----------------------------------------------------------------------------------------------------- */
	public Conta(int numero, long saldo) {
		
		if (saldo < 0) {
			// Se identificar um ERRO, atirar uma exceção
			throw new IllegalArgumentException("saldo tem de ser >= 0: saldo introduzido = " + saldo);  // Esta exceção diz que o valor do saldo da conta está errado
		}
		
		this.numero = numero;
		this.saldo = saldo;
	}
	
	/* -------------------------------------------------------------------------------------- MÉTODOS ---------------------------------------------------------------------------------------------------- */

	public void depositar(long quantia) {
		if (quantia < 0) 
			throw new IllegalArgumentException("quantia tem de ser >= 0: quantia intoduzida = " + quantia);
		
		saldo += quantia;
	}
	
	// Método para levantar dinheiro introduzindo uma quantia. Usar o throws pois a exceção criada é do tipo check
	public void levantar(long quantia) throws SaldoInsuficienteException {
		
		if (quantia > saldo) 
			throw new SaldoInsuficienteException("quantia superior ao saldo");
		
		saldo -= quantia;

	}

	/* ---------------------------------------------------------------------------------- GETTERS E SETTERS ---------------------------------------------------------------------------------------------- */

	public long getSaldo() {
		return saldo;
	}
	
	// ADD, REMOVE E GET PARA AS LISTAS
	private void addCartao(Cartao c) {
		cartoes.add(c);
	}
	
	private void removeCartao(Cartao c) {
		cartoes.remove(c);
	}

	public List<Cartao> getCartoes() {
		return Collections.unmodifiableList(cartoes);
	}

	public int getNumero() {
		return numero;
	}
	
	/* ---------------------------------------------------------------------------------------- TOSTRING ------------------------------------------------------------------------------------------------ */
	@Override
	public String toString() {
		return "Conta: " + numero + ", saldo: " + saldo + "€";
	}

}
