package aula05.animal.composicao;

public class CorpoSimples implements Corpo {

	private String nome;
	private int idade;
	private int tamanho;
	private int peso;

	public CorpoSimples(String nome, int idade, int tamanho) {
		this.nome = nome;
		this.idade = idade;
		this.tamanho = tamanho;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public int getIdade() {
		return idade;
	}

	@Override
	public int getTamanho() {
		return tamanho;
	}

	@Override
	public int getPeso() {
		return peso;
	}

	@Override
	public void dormir() {
		System.out.println( getNome() + ": ZZZZZZ");
	}
}
