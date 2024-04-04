package teste;

import securest.recurso.Funcionario;

public class TesteFuncionario {

	public static void main(String[] args) {
		
		/* ---------------------------------------------- DECLARAÇÃO DOS FUNIONÁRIOS ----------------------------------------------- */
		Funcionario f1 = new Funcionario(1, "Inês Irreal", 1);
		System.out.println(f1);
		
		/* ------------------------------------------------------------ TESTES --------------------------------------------------------*/
		// Teste método estaPresente()
		System.out.println("\n ------ Está presente em alguma instalação? ------");
		System.out.println(f1.estaPresente());
		
		// Teste método entrar()
		System.out.println("\n ------ Entrou na instalação ------");
		//f1.entrar();
		System.out.println(f1);
		
		// Teste método sair()
		System.out.println("\n ------ Saiu da instalação ------");
		//f1.sair();
		System.out.println(f1);

	}

}
