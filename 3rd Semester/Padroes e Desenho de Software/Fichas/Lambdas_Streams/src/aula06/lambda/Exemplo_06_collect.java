package aula06.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Exemplo_06_collect {

	public static void main(String[] args) {
		List<Aluno> alunos = AlunoFactory.getAlunos();
		
		//Filtrar aprovados usando uma lista criada pelo collect		
		List<Aluno> aprovados = alunos.stream().filter( Aluno::estaAprovado )
				                               .collect( Collectors.toList() );
		
		//Filtrar reprovados usando o tipo de lista que queremos
		ArrayList<Aluno> reprovados = alunos.stream().filter( Predicate.not( Aluno::estaAprovado) )
				                                     .collect( Collectors.toCollection( ArrayList::new) );

		// exemplo de como reutilizar a parte de imprimir o nome e a nota final
		// ver outro exemplo (ainda melhor) no exemplo 07
		Consumer<Aluno> printNomeNota = a -> System.out.println( a.getNome() + " " + a.getNotaFinal() );
		
		// agora podemos fazer o que quisermos com as listas
		// incluindo, mas não só, apresentar os resultados
		System.out.println( "Aprovados ------ ");
		aprovados.forEach( printNomeNota );

		System.out.println( "\nReprovados ------");
		reprovados.forEach( printNomeNota );
		
		// exemplo de como calcular o número de alunos sem minimos na teórica
		long numAlunosSemMinimosTeorica = alunos.stream().filter( p-> p.getNotaTeorica() < 8f).collect( Collectors.counting() );
		System.out.println( "\nHá " + numAlunosSemMinimosTeorica + " alunos sem mínimos na teórica");
		// mesmo exemplo mas usando o count como operação terminal (PREFERÍVEL)
		numAlunosSemMinimosTeorica = alunos.stream().filter( p-> p.getNotaTeorica() < 8f).count();
		System.out.println( "\nHá " + numAlunosSemMinimosTeorica + " alunos sem mínimos na teórica");

		// colocar os reprovados a estudar
		System.out.println( "\nA estudar mais ------");
		reprovados.forEach( Aluno::estudar );
	}
}
