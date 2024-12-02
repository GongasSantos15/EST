package aula06.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class Exemplo_04_methodRefs {
	
	public static void main(String[] args) {
		List<Aluno> alunos = AlunoFactory.getAlunos();
		
		Comparator<Aluno> porNome = (a1,a2) -> a1.getNome().compareTo( a2.getNome() );
		Comparator<Aluno> porNotaFinal = (a1,a2) -> a2.getNotaFinal() - a1.getNotaFinal();
		
		System.out.println("\nOrdenar por nomes -----");
		alunos.sort( porNome );
		// imprimir usando um objeto especifico: System.out
		alunos.forEach(	System.out::println );

		System.out.println("\nOrdenar por nota final descendente -----");
		alunos.sort( porNotaFinal );
		alunos.forEach(	a -> System.out.println( a.getNome() + " " + a.getNotaFinal() ) );

		// adicionar todos a uma turma (usar um objeto especifico)
		Turma t1 = new Turma( 2 );
		alunos.forEach( t1::addAluno );
		//for( Aluno a : alunos )
		//	t1.addAluno( a );
		
		// por todos a estudar - usar objetos indeterminados de um tipo especifico (Aluno)
		System.out.println("\nA estudar -----");
		alunos.forEach( Aluno::estudar );
		
		
		
		List<Integer> numeros = Arrays.asList( 10, -20, 20, -30, 10, -40 );
		// comparar usando um método static
		numeros.sort( Integer::compare );
		
		// imprimir usando um objeto especifico
		numeros.forEach( System.out::println );		
	}
}

