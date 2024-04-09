package securest.recurso;

import java.util.Objects;

public class Funcionario {
	private int id, nivelAcesso;
	private String nome;
	private Instalacao instalacao;

	/* -------------------------------------------------------- CONSTRUTOR -------------------------------------------------------------- */
	public Funcionario(int id, String nome, int nivelAcesso) {
		this.id = id;
		setNome(nome);
		setNivelAcesso(nivelAcesso);
	}
	
	/* ---------------------------------------------------------- MÉTODOS --------------------------------------------------------- */
		
		// Método que indica se um funcionário está presente em alguma instalação
		public boolean estaPresente() {
			return instalacao != null;
		}
		
		// Método que faz com que um funcionário entre na instalação indicada
		void entrar(Instalacao inst) {
			instalacao = inst;
		}
		
		// Método que faz com que o funcionário saia da instalação onde está
		void sair() {
			instalacao = null;
		}
		
		// Métodos que indicam se um funcionário é igual ao outro

		@Override
		public int hashCode() {
			return Objects.hash(id, instalacao, nivelAcesso, nome);
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
				return id == other.id && Objects.equals(instalacao, other.instalacao)
						&& nivelAcesso == other.nivelAcesso && Objects.equals(nome, other.nome);
			}

	/* --------------------------------------------------- GETTERS E SETTERS --------------------------------------------------------- */
	public int getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(int nivelAcesso) {
		if (nivelAcesso >= CentralControlo.NIVEL_MIN && nivelAcesso <= CentralControlo.NIVEL_MAX) {
			this.nivelAcesso = nivelAcesso;
		} else {
			this.nivelAcesso = CentralControlo.NIVEL_MIN;
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public Instalacao getInstalacao() {
		return instalacao;
	}

	/* --------------------------------------------------------- TOSTRING ---------------------------------------------------------- */
	@Override
	public String toString() {
		return "["+ id + "]" + "; NOME = " + nome + "; NÍVEL = " + nivelAcesso + "; INSTALAÇÃO = " + instalacao;
	}
	
	

}
