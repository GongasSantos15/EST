package aula05.animal.composicao;

public class AlimentacaoBase implements Alimentacao {

	@Override
	public void comer( Corpo c ) {
		System.out.println( c.getNome() + " está a comer com vontade");
	}
}
