package securest.recurso;

public class Funcionario {
	private int id, nivelAcesso;
	private String nome;
	private Instalacao instalacao;

	/* Construtor */
	public Funcionario(int id, String nome, int nivelAcesso) {
		this.id = id;
		setNome(nome);
		setNivelAcesso(nivelAcesso);
	}
	
	/* Métodos */
		
		// Método que indica se um funcionário está presente em alguma instalação
		public boolean estaPresente() {
			return instalacao != null;
		}
		
		// Método que faz com que um funcionário entre na instalação indicada
		public void entrar(Instalacao inst) {
			instalacao = inst;
		}
		
		// Método que faz com que o funcionário saia da instalação onde está
		public void sair() {
			instalacao = null;
		}

	/* Getters e Setters */
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

	/* ToString */
	@Override
	public String toString() {
		return "Funcionario: ["+ id + "] " + nome + " com nível " + nivelAcesso + " está em " + instalacao;
	}

}
