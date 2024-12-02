package aula06.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Turma {
	private int ano;
	private List<Aluno> inscritos = new ArrayList<Aluno>();

	public Turma(int ano) {
		this.ano = ano;
	}

	public int getAno() {
		return ano;
	}
	
	public boolean addAluno(Aluno a) {
		return inscritos.add(a);
	}

	public boolean removeAluno(Aluno a) {
		return inscritos.remove(a);
	}
	
	public List<Aluno> getInscritos(){
		return Collections.unmodifiableList( inscritos );
	}	
}