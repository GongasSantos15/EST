package aula05.animal.heranca;

import aula05.animal.anatomia.Pata;

public abstract class Quadrupede extends AnimalDefault {
	
	protected Pata patas[] = new Pata[ 4 ];
	{
		for( int i=0; i < patas.length; i++)
			patas[ i ] = new Pata();
	}
	
	public Quadrupede(String nome, int idade, int tamanho) {
		super(nome, idade, tamanho);
	}
	
	public void andar( ){
		System.out.println( getNome() + " andando em " + patas.length + " patas");
	}
	
	@Override
	public void mover() {
		andar();
	}
}
