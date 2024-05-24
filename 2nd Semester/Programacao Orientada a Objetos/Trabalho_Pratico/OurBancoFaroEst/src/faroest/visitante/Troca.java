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
	private static final int ENTRAR = 10;
	private static final int TROCAR_ANTES = 11;
	private static final int TROCAR = 12;
	private static final int TROCAR_APOS = 13;
	private static final int MORTE_ANTES = 14;
	private static final int MORTE_APOS = 15;
		
	/* ---------------------------------------------- VARIÁVEIS ------------------------------------ */
	
	private Assaltante assaltante;
	
	String nomeAssaltante;
	private int minDisparo; 
	private int maxDisparo;
	private long tempoSaque; 
	
	private int tempoMinTroca, tempoMaxTroca;
	private ComponenteAnimado imgSaida; 
	private long proxFecho;
	private long proxAto;  
	
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
		return getStatus() == MORTE_APOS && getImagem().numCiclosFeitos() > 0;
	}

	@Override
	public int baleado() {
		
		float pontuacao = 0;
		
		if( getStatus() == MORTE_APOS ) {
			return pontos;
		} else if (getStatus() == MORTE_ANTES) {
			fezAsneira("oops");
		} else if (getStatus() == MORTE_APOS) {
			setStatus(MORTE_APOS);
			setImagem(nomeAssaltante + "_morte");
			
			long tempo = (System.currentTimeMillis() - tempoSaque);
			if( tempo < 100 )
				pontuacao = getPontos();
			else if( tempo < 200 )
				pontuacao = getPontos() * 0.95f;
			else if( tempo < 400 )
				pontuacao = getPontos() * 0.8f;
			else if( tempo < 600 )
				pontuacao = getPontos() * 0.7f;
			else if( tempo < 800 )
				pontuacao = getPontos() * 0.6f;
			else if( tempo < 1200 )
				pontuacao = getPontos() * 0.5f;
			else if( tempo < 1500 )
				pontuacao = getPontos() * 0.4f;
			else
				pontuacao = getPontos() * 0.2f;
		}
		return (int)(pontuacao+0.5f);
	}
	
	@Override
	public void atualizar() {
		
		proxAto = GeradorAleatorio.proxTempo(minDisparo, maxDisparo);
		
		if( getStatus() == ENTRAR && getImagem().numCiclosFeitos() > 0 ){
			setStatus( TROCAR_ANTES );
			setImagem( nome + "_espera");
		} else if (getStatus() == TROCAR_ANTES) {
			setStatus(TROCAR);
			setImagem(nome + "_troca");
		} else if (getStatus() == TROCAR && getImagem().numCiclosFeitos() > 0 ) {
			setStatus(TROCAR_APOS);
			setImagem(nomeAssaltante + "_troca");
			setImagem(nomeAssaltante + "_sacada");
		} else if( getStatus() == TROCAR_APOS && System.currentTimeMillis() >= proxAto ){
			setStatus(MORTE_APOS);
		} else if (getStatus() == MORTE_APOS && fimEspera()) {
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
	
	@Override
	public VisitanteDefault clone() {
		return super.clone();
	}

}
