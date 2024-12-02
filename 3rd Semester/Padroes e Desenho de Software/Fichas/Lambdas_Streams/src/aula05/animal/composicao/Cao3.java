package aula05.animal.composicao;

import aula05.animal.anatomia.Faro;

public class Cao3 extends AnimalSimples {

	private Faro faro = new Faro();
	
	public Cao3(String nome, int idade, int tamanho) {
		super( new CorpoSimples(nome, idade, tamanho),
			   new AlimentacaoBase(),
			   new Voz() { 
					@Override
					public void falar( Corpo c) {
						System.out.println( c.getNome() + ": AU AU AU");				
					}
		       },
			   new MovimentoQuadrupede() );
	}

	public void cheirar() {
		// usar faro
	}
}
