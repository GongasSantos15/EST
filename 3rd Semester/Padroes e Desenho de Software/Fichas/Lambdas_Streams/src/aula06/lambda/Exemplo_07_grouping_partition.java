package aula06.lambda;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Exemplo_07_grouping_partition {

	public static void main(String[] args) {
		List<Aluno> alunos = AlunoFactory.getAlunos();
		
		// objetivo: listar os alunos por notas
		
		// usar o grouping para obter uma lista com os alunos indexados pela nota final
		Map<Integer,List<Aluno>> tabelaNotas = alunos.stream().collect( Collectors.groupingBy(Aluno::getNotaFinal, Collectors.toList() ));
		
		// apresentar a lista de notas sem streams
		System.out.println("tabela notas forma antiga");
		for( int nota = 20; nota > 0; nota--) {
			if( tabelaNotas.containsKey(nota) ) {
				System.out.print( nota + ": " );
				for( Aluno a : tabelaNotas.get( nota ) )
					System.out.print(a.getNome() + ", " );
				System.out.println(" ---");
			}
		}
		
		// isto ainda pode ser de outra maneira! (ver exemplo 10b)
		System.out.println("\ntabela notas com streams");
		tabelaNotas.forEach( (nota,lista) -> {
			System.out.print( nota +": "  );
			lista.forEach( a -> System.out.print( a.getNome() + ", " ) );
			System.out.println();} );

		//Filtrar aprovados	e reprovados
		Map<Boolean, List<Aluno>> aprovadosReprovados = alunos.stream()
				        .collect(Collectors.partitioningBy( Aluno::estaAprovado ));
		List<Aluno> aprovados = aprovadosReprovados.get( true );
		List<Aluno> reprovados = aprovadosReprovados.get( false );
		
		// agora podemos fazer o que quisermos com as listas
		// incluindo, mas não só, apresentar os resultados
		System.out.println( "\nAprovados ------ ");
		aprovados.forEach( Exemplo_07_grouping_partition::printNomeNota );

		System.out.println( "\nReprovados ------");
		reprovados.forEach( Exemplo_07_grouping_partition::printNomeNota );
		reprovados.forEach( Aluno::estudar );
	}
	
	// outra maneira de reutilizar código (comparar com o que foi feito no exemplo 6)
	// esta versão é melhor pois colocando este método como público poderia ser
	// usado noutras classes
	private static void printNomeNota( Aluno a) {
		System.out.println( a.getNome() + " " + a.getNotaFinal() );
	}
}
