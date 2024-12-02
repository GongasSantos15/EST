package aula06.lambda;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Exemplo_10b_tabelaNotas {

	public static void main(String[] args) {
		List<Aluno> alunos = AlunoFactory.getAlunos();
		
		// objetivo: listar os alunos por notas
		
		// usar o grouping para obter uma lista com os alunos indexados pela nota final
		Map<Integer,List<Aluno>> tabelaNotas = alunos.stream().collect( Collectors.groupingBy(Aluno::getNotaFinal, Collectors.toList() ));
		
		// listar a informação
		tabelaNotas.entrySet().stream().sorted( (e1,e2) -> e2.getKey().compareTo(e1.getKey()) )
		                               .map( entry -> entry.getKey() + 
		                                              entry.getValue().stream().map( Aluno::getNome )
		                                                              .collect(Collectors.joining(", ", ": ", " ---") ) ) 
		                               .forEach( System.out::println );		
	}		
}

