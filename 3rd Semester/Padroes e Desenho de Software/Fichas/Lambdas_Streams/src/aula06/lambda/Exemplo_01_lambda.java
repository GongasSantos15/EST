package aula06.lambda;

import java.util.Comparator;
import java.util.List;

public class Exemplo_01_lambda {

	public static void main(String[] args) {

		System.out.println("\nOrdenar por nomes sem lambdas-----");
		List<Aluno> alunos = AlunoFactory.getAlunos();
		alunos.sort( new Comparator<Aluno>(){
			@Override
			public int compare(Aluno a1, Aluno a2) {
				return a1.getNome().compareTo(a2.getNome());
			}	

		} );
		for( Aluno a : alunos )
			System.out.println( a.getNome() );

		System.out.println("\nOrdenar por nomes com lambdas -----");
		alunos = AlunoFactory.getAlunos();
		alunos.sort( (a1,a2) -> a1.getNome().compareTo( a2.getNome() ) );
		for( Aluno a : alunos )
			System.out.println( a.getNome() );

		System.out.println("\nOrdenar por nota final descendente -----");	
		alunos = AlunoFactory.getAlunos();
		alunos.sort( (a1,a2) -> a2.getNotaFinal() - a1.getNotaFinal() );
		for( Aluno a : alunos )
			System.out.println( a.getNome() + " " + a.getNotaFinal() );
	}
}
