package aula05.animal.composicao;

public class MovimentoSimples implements Movimento{
	
	@Override
	public void mover(  Corpo c ) {
		System.out.println( c.getNome() + ": movendo" );
	}
}
