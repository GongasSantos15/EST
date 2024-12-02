package aula05.animal.heranca;

import aula05.animal.anatomia.Asa;

public abstract class Ave extends AnimalDefault {

	protected Asa asas[] = new Asa[ 2 ];
	{
		for( int i=0; i < asas.length; i++)
			asas[ i ] = new Asa();
	}
	
	public Ave(String nome, int idade, int tamanho) {
		super(nome, idade, tamanho);
	}

	public void voar( ){
		System.out.println( getNome() + " voando com " + asas.length + " asas");
	}
	
	@Override
	public void mover() {
		voar();
	}
}
