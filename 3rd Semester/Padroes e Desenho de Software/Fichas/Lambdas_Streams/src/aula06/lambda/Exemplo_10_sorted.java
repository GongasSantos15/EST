package aula06.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Exemplo_10_sorted {

	public static void main(String[] args) {
		List<Aluno> alunos = AlunoFactory.getAlunos();

		// listagem 1
		System.out.println("Listagem por nota 1 - ascendente");
		alunos.stream().sorted( (a1,a2) -> a1.getNotaFinal() - a2.getNotaFinal() )
		               .forEach( Exemplo_08_map::printNomeNota );
		
		// listagem 2
		System.out.println("Listagem por nota 2 - ascendente");
		alunos.stream().sorted( Comparator.comparing( Aluno::getNotaFinal ) )
		               .forEach( Exemplo_08_map::printNomeNota );
		
		// listagem 3
		System.out.println("Listagem por nota 3 - ascendente");
		alunos.stream().sorted( Exemplo_10_sorted::comparaNotaFinal )
		               .forEach( Exemplo_08_map::printNomeNota );
		
		// listagem 4
		System.out.println("Listagem por nota 4 - descendente");
		alunos.stream().sorted( (a1,a2) -> a2.getNotaFinal() - a1.getNotaFinal() )
		               .forEach( Exemplo_08_map::printNomeNota );
		
		// listagem 5
		System.out.println("Listagem por nota 5 - descendente");
		alunos.stream().sorted( Comparator.comparing( Aluno::getNotaFinal ).reversed() )
		               .forEach( Exemplo_08_map::printNomeNota );

		// listagem 6
		System.out.println("Listagem por nota 6 - descendente");
		alunos.stream().sorted( (a1,a2)-> -comparaNotaFinal(a1, a2) )
		               .forEach( Exemplo_08_map::printNomeNota );
		
		Stream<Aluno> a2 = alunos.stream();
		Stream aprovadosS = a2.filter( Aluno::estaAprovado );
		
		List<Aluno> aprovados = aprovadosS.toList();
		
		aprovados.forEach( System.out::println );
		
	}
	
	public static int comparaNotaFinal( Aluno a1, Aluno a2) {
		return a1.getNotaFinal() - a1.getNotaFinal();
	}
}

