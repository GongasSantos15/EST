package aula05.animal.heranca;

import aula05.animal.anatomia.Unha;

public class Gato extends Quadrupede {

	private Unha unhas[] = new Unha[ 32 ];
	{
		for( int i=0; i < unhas.length; i++)
			unhas[ i ] = new Unha();
	}

	public Gato(String nome, int idade, int tamanho) {
		super(nome, idade, tamanho);
	}

	public void miar( ){
		System.out.println( getNome() + ": Miau miau");
	}
	
	@Override
	public void falar() {
		miar();		
	}
}
