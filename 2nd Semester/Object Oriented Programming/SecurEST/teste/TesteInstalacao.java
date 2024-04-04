package teste;

import p2.tempo.Hora;
import p2.tempo.Horario;
import securest.recurso.Funcionario;
import securest.recurso.Instalacao;

public class TesteInstalacao {

	public static void main(String[] args) {
		
		/* --------------------------------------------------- CRIAÇÃO DA INSTALAÇÃO ------------------------------------------ */ 
		Instalacao i1 = new Instalacao(1, 1, "Sala 1", new Horario());
		System.out.println("\n -------------------------------------------------------------------- Instalação --------------------------------------------------------- ");
		System.out.println("A instalação é: " + i1);
		System.out.println();
		
		/* --------------------------------------------------- CRIAÇÃO DO FUNCIONÁRIO ----------------------------------------- */
		Funcionario f1 = new Funcionario(1, "Inês Inexistente", 3);
		System.out.println("\n -------------------------------------------------------------------- Funcionário --------------------------------------------------------- ");
		System.out.println("O funcionário é: " + f1);
		
		/* ---------------------------------------------------------- TESTES -------------------------------------------------- */ 
		
		// Método podeEntrar()
		System.out.println("\n ---------------------------------------------------------------- Uso do método podeEntrar() -------------------------------------------- ");
		System.out.println("O funcionário pode entrar? " + i1.podeEntrar(f1, new Hora()));
		
		// Método entrar()
		System.out.println("\n ---------------------------------------------------------------- Uso do método entrar() -------------------------------------------- ");
		System.out.println("O funcionário entrou? " + i1.entrar(f1));
		System.out.println("Funcionários presentes: " + i1.getPresentes());
	}
	
}
