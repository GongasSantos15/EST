package aula05.animal.heranca;

import aula05.animal.anatomia.Garra;

public class Leao extends Quadrupede {

	private Garra garras[] = new Garra[ 32 ];
	{
		for( int i=0; i < garras.length; i++)
			garras[ i ] = new Garra();
	}

	public Leao(String nome, int idade, int tamanho) {
		super(nome, idade, tamanho);
	}

	public void rugir( ){
		System.out.println( getNome() + ": Rooar rooar");
	}
	
	@Override
	public void falar() {
		rugir();		
	}
}
