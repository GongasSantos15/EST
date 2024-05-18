package teste;

import banco.Conta;
import banco.SaldoInsuficienteException;

public class TesteConta {

	public static void main(String[] args) {
		Conta c = new Conta(1, 1000);
		System.out.println(c);
		
		// Teste método deposita()
		c.depositar(200);
		
		System.out.println();
		/* ----------------------------------------------------------------------------- TESTE MÉTODO DEPOSITO ------------------------------------------------------------------------------------------- */
		System.out.println("---------------------------- DEPOSITO ---------------------------- ");
		System.out.println("Saldo após depósito: " + c.getSaldo() + "€");
		
		System.out.println();
		/* ----------------------------------------------------------------------------- TESTE MÉTODO LEVANTAR ------------------------------------------------------------------------------------------- */
		System.out.println("---------------------------- LEVANTAR ---------------------------- ");

		try {
			c.levantar(1201);
			System.out.println("Saldo após depósito: " + c.getSaldo() + "€");
		} catch (SaldoInsuficienteException e) {
			System.out.println("Não tem fundos para realizar esta operação!");
		}
		
	}

}
