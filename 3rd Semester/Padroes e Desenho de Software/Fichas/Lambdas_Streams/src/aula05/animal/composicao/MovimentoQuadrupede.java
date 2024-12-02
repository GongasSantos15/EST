package aula05.animal.composicao;

import aula05.animal.anatomia.Pata;

public class MovimentoQuadrupede implements Movimento {

	protected Pata patas[] = new Pata[ 4 ];
	{
		for( int i=0; i < patas.length; i++)
			patas[ i ] = new Pata();
	}

	@Override
	public void mover( Corpo c ) {
		System.out.println( c.getNome() + " andando em " + patas.length + " patas");
	}

}
