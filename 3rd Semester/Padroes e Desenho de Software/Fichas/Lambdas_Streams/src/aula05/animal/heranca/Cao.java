package aula05.animal.heranca;

import aula05.animal.anatomia.Faro;

public class Cao extends Quadrupede {

	private Faro faro = new Faro();
	
	public Cao(String nome, int idade, int tamanho) {
		super( nome, idade, tamanho );
	}

	public void ladrar( ){
		System.out.println( getNome() + ": AU au au");
	}
	
	@Override
	public void falar() {
		ladrar();
	}
}
