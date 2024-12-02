package aula06.lambda;

import java.util.Arrays;
import java.util.List;

public class AlunoFactory {
	public static List<Aluno> getAlunos(){
		return Arrays.asList(
					new Aluno(2023001,"António", 18, 18.45f),
					new Aluno(2023002,"Carlos", 7.8f, 10.6f),
					new Aluno(2023003,"Evaristo", 13.4f, 12.05f),
					new Aluno(2023004,"David", 15.8f, 12.05f),
					new Aluno(2023005,"Fábio", 14.9f, 11.9f),
					new Aluno(2023006,"Baltazar", 11, 16.45f)
				);		
	}
}
