package aula05.animal.composicao;

public class AnimalSimples implements Animal {

	private Corpo corpo;
	private Alimentacao alimentacao;
	private Voz voz;
	private Movimento movimento;
	
	public AnimalSimples( Corpo c, Alimentacao a, Voz v, Movimento m) {
		corpo = c;
		alimentacao = a;
		voz = v;
		movimento = m;
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

	@Override
	public void mover() {
		movimento.mover( corpo );
	}
	
}
