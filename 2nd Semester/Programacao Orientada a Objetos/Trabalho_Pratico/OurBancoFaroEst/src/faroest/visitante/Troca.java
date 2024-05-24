package faroest.visitante;

import java.awt.Point;
import java.awt.Rectangle;

import faroest.util.GeradorAleatorio;
import prof.jogos2D.image.ComponenteAnimado;
import prof.jogos2D.image.ComponenteVisual;
import prof.jogos2D.util.ComponenteVisualLoader;

public class Troca extends VisitanteDefault {
	
	/* ---------------------------------------------- CONSTANTES ------------------------------------ */
	
	/** Estados possíveis do Troca */
	private static final int MORTO = 10;
	private static final int ENTRAR = 11;
	private static final int ESPERA_APOS = 12;
	private static final int TROCAR = 13;
	private static final int MORTE_ANTES = 14;
	private static final int MORTE_APOS = 14;
	private static final int ESPERA_ANTES = 16;
		
	/* ---------------------------------------------- VARIÁVEIS ------------------------------------ */
	
	private Assaltante assaltante;
	
	String nomeAssaltante;
	private int minDisparo; 
	private int maxDisparo;
	
	private int tempoMinTroca, tempoMaxTroca;
	private ComponenteAnimado imgSaida; 
	private long proxFecho;
	
	/* ---------------------------------------------- CONSTRUTOR ------------------------------------ */

	public Troca(String nome, String nomeAssaltante, int pontos, int tempoMinTroca, int tempoMaxTroca, int minDisparo, int maxDisparo ) {
		super(nome, pontos);
		
		this.nomeAssaltante = nomeAssaltante;
		this.minDisparo = minDisparo;
		this.maxDisparo = maxDisparo;
		
		this.tempoMinTroca = tempoMinTroca;
		this.tempoMaxTroca = tempoMaxTroca;
		
		setStatus(ENTRAR);
		setImagem(nome + "_ola");
	}

	/* ---------------------------------------------- MÉTODOS ------------------------------------ */
	
	@Override
	public int fecharPorta() {
		return 0;
	}

	@Override
	public void portaAberta() {
		GeradorAleatorio.proxTempo(tempoMinTroca, tempoMaxTroca);
	}

	@Override
	public boolean podeFechar() {
		return getStatus() == MORTO && getImagem().numCiclosFeitos() > 0;
	}

	@Override
	public int baleado() {
		if( getStatus() == MORTO ) {
			return pontos;
		} else if (getStatus() == ESPERA_APOS) {
			fezAsneira("oops");
		}
		
		setImagem( nome + "_morte" );
		fezAsneira("oops");
		setStatus(MORTO);
		return 0;
	}

	@Override
	public void atualizar() {
		if( getStatus() == ENTRAR && getImagem().numCiclosFeitos() > 0 ){
			setStatus( ESPERA_ANTES );
			setImagem( nome + "_espera");
		} else if (getStatus() == ESPERA_ANTES) {
			setStatus(TROCAR);
			setImagem(nome + "_troca");
		} else if (getStatus() == TROCAR && getImagem().numCiclosFeitos() > 0 ) {
			setStatus(MORTE_ANTES);
			setImagem(nomeAssaltante + "_troca");
		}
		else if( getStatus() == ESPERA_APOS && fimEspera() ){
			fezAsneira("bang");
		}
	}
	
	private boolean fimEspera(){
		return System.currentTimeMillis() >= proxFecho;
	}
	
	@Override
	public void setImagem(String nome) {
		super.setImagem(nome);
	}
	
	private void setImagemSaida( String nomeImg ){
		imgSaida = (ComponenteAnimado)ComponenteVisualLoader.getCompVisual( nomeImg );
		Rectangle r = getImagem().getBounds();
		imgSaida.setPosicao( new Point( r.x+(r.width - imgSaida.getComprimento())/2 , r.y) );
	}
	
	@Override
	public VisitanteDefault clone() {
		return super.clone();
	}

}
