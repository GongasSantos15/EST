package aula06.lambda;

import java.util.List;

public class Exemplo_09_flatMap {

	public static void main(String[] args) {
		List<Aluno> alunos = AlunoFactory.getAlunos();
		
		// para simplificar vamos colocar metade dos alunos
		// para a turma do 1º ano e metade para a turma do 2º ano
		int metade = alunos.size() / 2;		
		Turma t1 = new Turma( 1 );  // turma do 1º ano
		Turma t2 = new Turma( 2 );  // turma do 2º ano
		alunos.stream().limit( metade ).forEach( t1::addAluno );
		alunos.stream().skip( metade ).forEach( t2::addAluno );

		System.out.println("inscritos na turma 1: "); // usando o map
		t1.getInscritos().stream().map( Aluno::getNome ).forEach( System.out::println );
		
		System.out.println("inscritos na turma 2: "); // imprimindo diretamente
		t2.getInscritos().forEach( a -> System.out.println( a.getNome() ) );
		
		// adicionar as turmas ao curso
		Curso curso = new Curso();
		curso.addTurma( t1 );
		curso.addTurma( t2 );
		
		// exemplo de flatMap
		// determinar quais os alunos inscritos num curso
		System.out.println("Alunos inscritos no curso");
		List<Aluno> todos = curso.getTurmas().stream().flatMap( t -> t.getInscritos().stream() )
				                             .distinct().toList();
		todos.forEach( a -> System.out.println( a.getNome() ) );
		
		// determinar quais os alunos inscritos num curso num dado ano
		int ano = 2;
		System.out.println("\nAlunos inscritos no curso no ano " + ano);
		List<Aluno> anistas = curso.getTurmas().stream().filter( t -> t.getAno() == ano)
				                               .flatMap( t -> t.getInscritos().stream() )
				                               .distinct().toList();
		anistas.forEach( a -> System.out.println( a.getNome() ) );
	}
}


