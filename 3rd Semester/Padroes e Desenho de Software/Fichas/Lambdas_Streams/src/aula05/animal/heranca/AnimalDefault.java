package aula05.animal.heranca;

public abstract class AnimalDefault implements Animal {

	private String nome;
	private int idade;
	private int tamanho;
	private int peso;

	public AnimalDefault( String nome, int idade, int tamanho) {
		this.nome = nome;
		this.idade = idade;
		this.tamanho = tamanho;
	}

	public String getNome() {
		return nome;
	}
	
	public void comer() {
		System.out.println( nome + " está a comer tudo!");
	}

	public void dormir() {
		System.out.println( nome + " ZZZZZZZ");
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
	

}
