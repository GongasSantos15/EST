package aula06.lambda;

import java.util.Comparator;
import java.util.List;

public class Exemplo_02_lambdaVars {

	public static void main(String[] args) {
		List<Aluno> alunos = AlunoFactory.getAlunos();
		
		Comparator<Aluno> porNome = (a1,a2) -> a1.getNome().compareTo( a2.getNome() );
		Comparator<Aluno> porNotaFinal = (a1,a2) -> a2.getNotaFinal() - a1.getNotaFinal();
		Comparator<Aluno> porNotaFinalNome = (a1,a2) -> { int n = a2.getNotaFinal() - a1.getNotaFinal();
		                                                  return n != 0? n: a1.getNome().compareTo( a2.getNome() );		                                                  
		                                                };
				
		System.out.println("\nOrdenar por nomes -----");
		alunos.sort( porNome );
		for( Aluno a : alunos )
			System.out.println( a.getNome() );

		System.out.println("\nOrdenar por nota final descendente -----");
		alunos.sort( porNotaFinal );
		for( Aluno a : alunos )
			System.out.println( a.getNome() + " " + a.getNotaFinal() );
		
		System.out.println("\nOrdenar por nota final descendente e nome -----");
		alunos.sort( porNotaFinalNome );
		for( Aluno a : alunos )
			System.out.println( a.getNome() + " " + a.getNotaFinal() );

	}
}
