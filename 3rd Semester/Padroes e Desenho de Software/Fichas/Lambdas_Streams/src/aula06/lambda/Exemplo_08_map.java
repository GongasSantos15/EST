package aula06.lambda;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Exemplo_08_map {

	public static void main(String[] args) {
		List<Aluno> lista = AlunoFactory.getAlunos();
		
		// criar um mapa com os alunos indexados pelo número
		Map<Integer,Aluno> alunos = lista.stream().collect( Collectors.toMap( Aluno::getNumero, a -> a) );
	
		// lista com alunos inscritos numa UC (alguns números estão errados) 
		List<Integer> numerosInscritosUC = List.of( 2023002, 100, 2023003, 2023004, 132 );
		
		// determinar quais os alunos inscritos à UC
		List<Aluno> alunosInscritos = numerosInscritosUC.stream().map( alunos::get )
				                                        .filter( a -> a != null )
				                                        .toList();

		// imprimir os alunos
		System.out.println( "inscritos");
		alunosInscritos.forEach( Exemplo_08_map::printNomeNota );
		
		// Se queremos saber quais os que deram problemas, podemos usar um método NÃO muito recomendado,
		// pois este código altera coisas fora da stream que processa,
		// mas as streams não são muito boas a lidar com erros
		List<Integer> problemas = new ArrayList<Integer>();
		List<Aluno> alunosInscritos2 = numerosInscritosUC.stream().map( n -> {if( !alunos.containsKey(n) ) problemas.add(n); return alunos.get(n);} )
				                                         .filter( a -> a != null ).toList();

		System.out.println( "\ninscritos 2");
		alunosInscritos2.forEach( Exemplo_08_map::printNomeNota );

		System.out.println( "\ndesconhecidos");
		problemas.forEach( System.out::println );
		
		// calcular o número de aprovados, reprovados
		Map<Boolean,Long> result = lista.stream().collect( Collectors.groupingBy( Aluno::estaAprovado, Collectors.counting()));
		long numAprovados = result.get( true );
		long numReprovados = result.get( false );
		System.out.println("Aprovados: " + numAprovados + "  Reprovados: " + numReprovados);
		
		// calcular estatística sobre as notas
		IntSummaryStatistics stats = lista.stream().mapToInt( Aluno::getNotaFinal ).summaryStatistics();
		System.out.println( "Núm. alunos: " + stats.getCount() + "  Média: " + stats.getAverage() + "  melhor: " + stats.getMax()
		                    + "  pior: " + stats.getMin() );
		
	}
	
	// aproveitar o conselho de exemplo 07 e colocar este método como public
	// assim podemos reaproveitar nos outros exemplo 
	// (embora devesse estar numa classe utilitária e não aqui!!!)
	public static void printNomeNota( Aluno a) {
		System.out.println( a.getNome() + " " + a.getNotaFinal() );
	}
}

