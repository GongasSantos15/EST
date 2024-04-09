package teste;

import securest.recurso.Funcionario;

public class testeFuncionario {

	public static void main(String[] args) {
		Funcionario f1 = new Funcionario(1, "Asdrubal", 1);
		System.out.println(f1);
		
		Funcionario f2 = new Funcionario(2, "Josefina", 2);
		System.out.println(f2);
		
		Funcionario f3 = new Funcionario(3, "Ambrosio", 3);
		System.out.println(f3);
		
		Funcionario f4 = new Funcionario(4, "Albertina", 4);
		System.out.println(f4);
		
		Funcionario f5 = new Funcionario(5, "Ze BigBoss", 5);
		System.out.println(f5);
		
		Funcionario f6 = new Funcionario(6, "Josefa", 3);
		System.out.println(f6);
		
		System.out.println(f1.equals(f1));
		
		System.out.println(f1.estaPresente());

	}

}
