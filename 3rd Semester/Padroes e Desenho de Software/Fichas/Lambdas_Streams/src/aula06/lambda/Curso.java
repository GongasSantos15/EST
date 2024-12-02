package aula06.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Curso {
	private List<Turma> turmas = new ArrayList<Turma>();

	public boolean addTurma(Turma t) {
		return turmas.add(t);
	}

	public boolean removeTurma(Turma t) {
		return turmas.remove(t);
	}
	
	public List<Turma> getTurmas(){
		return Collections.unmodifiableList( turmas );
	}
	
}