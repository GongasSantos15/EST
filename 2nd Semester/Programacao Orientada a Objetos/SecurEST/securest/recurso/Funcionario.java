package securest.recurso;

import java.util.Objects;

public class Funcionario {
	private int id;
	private String nome;
	private int nivel;
	private Instalacao instalacao;
	
	
	public Funcionario(int id, String nome, int nivel) {
		this.id = id;
		setNome( nome );
		setNivel( nivel );
	}
	
	public boolean equals(Funcionario f) {
		return id==f.id;
	}
	
	public boolean estaPresente() {
		return getInstalacao() != null;
	}
	
	public void entrar(Instalacao inst) {
		instalacao = inst;
	}
	
	public void sair() {
		instalacao = null;
	}
	
	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getNivel() {
		return nivel;
	}


	public void setNivel(int nivel) {
		if(nivel>instalacao.NIVEL_MAXIMO || nivel< instalacao.NIVEL_MINIMO)
			this.nivel = 1;
		else
			this.nivel = nivel;
	}


	public int getId() {
		return id;
	}


	public Instalacao getInstalacao() {
		return instalacao;
	}


	@Override
	public String toString() {
		return "Funcionario[" + id + "]" + "," + nome + "(" + nivel + ")" + instalacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		return id == other.id;
	}
	
	


	

	
}
