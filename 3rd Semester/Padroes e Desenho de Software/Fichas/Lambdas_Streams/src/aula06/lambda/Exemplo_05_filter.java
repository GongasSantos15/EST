package aula06.lambda;

import java.util.List;
import java.util.function.Predicate;

public class Exemplo_05_filter {

	public static void main(String[] args) {
		List<Aluno> alunos = AlunoFactory.getAlunos();
		
		System.out.println("Filtrar aprovados -----");		
		alunos.stream().filter( Aluno::estaAprovado )
		               .forEach( a -> System.out.println( a.getNome() + " " + a.getNotaFinal() ) );
	
		System.out.println("\nFiltrar reprovados 1 -----");		
		alunos.stream().filter( a -> !a.estaAprovado() )
		               .forEach( a -> System.out.println( a.getNome() + " " + a.getNotaFinal() ) );

		System.out.println("\nFiltrar reprovados 2 -----");		
		alunos.stream().filter( Predicate.not(Aluno::estaAprovado) )
		               .forEach( a -> System.out.println( a.getNome() + " " + a.getNotaFinal() ) );
		
		System.out.println("\nFiltrar aprovados e com nota teorica superior a 15 -----");		
		alunos.stream().filter( Aluno::estaAprovado )
		               .filter( a -> a.getNotaTeorica() >= 15 )
		               .forEach( a -> System.out.println( a.getNome() + " " + a.getNotaTeorica() ) );
	}
}
