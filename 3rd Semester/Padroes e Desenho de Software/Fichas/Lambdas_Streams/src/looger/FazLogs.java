package looger;

import java.util.logging.Logger;

public class FazLogs {

	private String nome;
	private int idade, peso;
	
	public FazLogs(String nome, int idade, int peso) {
		setNome( nome );
		setIdade( idade );
		setPeso( peso );
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if( nome == null )
			Logger.getLogger("a").warning("nome é null");
		else
			Logger.getLogger("a").fine("nome não é null");
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		if( idade < 0 )
			Logger.getLogger("b").warning("idade é negativa");
		this.idade = idade;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		if( idade < 0 )
			Logger.getLogger("c").warning("peso é negativo");
		this.peso = peso;
	}
	
	
	
}
