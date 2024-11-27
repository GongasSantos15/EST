package estairways;

public class Passageiro {
	
	/* -------------------------------------------------------------------------------------------------------- VARIÁVEIS ----------------------------------------------------------------------------------------------------- */
	private int numero;
	private String nome;
	private String lugar;
	private int numMalas;
	
	/* ------------------------------------------------------------------------------------------------------- CONSTRUTOR ----------------------------------------------------------------------------------------------------- */
	public Passageiro(int numero, String nome, String lugar, int numMalas) {
		this.numero = numero;
		this.nome = nome;
		this.lugar = lugar;
		this.numMalas = numMalas;
	}

	/* ---------------------------------------------------------------------------------------------------- GETTERS E SETTERS ------------------------------------------------------------------------------------------------- */
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public int getNumMalas() {
		return numMalas;
	}

	public void setNumMalas(int numMalas) {
		this.numMalas = numMalas;
	}

	public int getNumero() {
		return numero;
	}

	/* ---------------------------------------------------------------------------------------------------- TOSTRING ------------------------------------------------------------------------------------------------- */
	@Override
	public String toString() {
		return "Passageiro [numero=" + numero + ", nome=" + nome + ", lugar=" + lugar + ", numMalas=" + numMalas + "]";
	}

	
	
	
	
	
	
	
}
