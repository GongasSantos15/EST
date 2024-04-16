package teste;

import p2.tempo.Hora;
import p2.tempo.Horario;
import securest.recurso.Funcionario;
import securest.recurso.Instalacao;

public class TesteInstalacao {

	public static void main(String[] args) {
		
		/* --------------------------------------------------- CRIAÇÃO DA INSTALAÇÃO ------------------------------------------ */ 
		Instalacao i1 = new Instalacao(1, 1, "Sala 1", new Horario());
		System.out.println("\n -------------------------------------------------------------------- Instalação 1 --------------------------------------------------------- ");
		System.out.println("A instalação é: " + i1);
		System.out.println();
		
		Instalacao i2 = new Instalacao(2, 2, "Sala 2", new Horario());
		System.out.println("\n -------------------------------------------------------------------- Instalação 2 --------------------------------------------------------- ");
		System.out.println("A instalação é: " + i2);
		
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
		
		System.out.println();
		
		System.out.println("O funcionário entrou? " + i2.entrar(f1));
		System.out.println("Funcionários presentes: " + i2.getPresentes());
		
		// Método sair()
		System.out.println("\n ---------------------------------------------------------------- Uso do método sair() -------------------------------------------- ");
		i2.sair(f1);
		System.out.println("O funcionário saiu!");
		System.out.println("Funcionários presentes: " + i1.getPresentes());
		
		System.out.println();
		
		i1.sair(f1);
		System.out.println("O funcionário saiu!");
		System.out.println("Funcionários presentes: " + i1.getPresentes());
	}
	
}
