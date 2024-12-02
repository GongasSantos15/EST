package aula05.animal.composicao;

import aula05.animal.anatomia.Faro;

public class Cao implements Animal {

	private Corpo corpo;
	private Alimentacao alimentacao;
	private Voz voz;
	private Movimento movimento;
	
	private Faro faro = new Faro();
	
	public Cao(String nome, int idade, int tamanho) {
		corpo = new CorpoSimples(nome, idade, tamanho);
		alimentacao = new AlimentacaoBase();
		voz = new Voz() { 
			@Override
			public void falar( Corpo c) {
				System.out.println( c.getNome() + ": AU AU AU");				
			}
		};  
		movimento = new MovimentoQuadrupede();
	}

	public void cheirar() {
		// usar faro
	}
	
	public void dormir() {
		corpo.dormir();
	}

	public String getNome() {
		return corpo.getNome();
	}

	public int getIdade() {
		return corpo.getIdade();
	}

	public int getTamanho() {
		return corpo.getTamanho();
	}

	public int getPeso() {
		return corpo.getPeso();
	}

	public void comer( ) {
		alimentacao.comer( corpo );
	}

	public void falar() {
		voz.falar( corpo );
	}
	
	public void mover( ) {
		movimento.mover( corpo );
	}
}
