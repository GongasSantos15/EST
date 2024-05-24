package faroest.visitante;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import faroest.util.GeradorAleatorio;
import prof.jogos2D.image.ComponenteAnimado;
import prof.jogos2D.image.ComponenteVisual;
import prof.jogos2D.util.ComponenteVisualLoader;

public class Insatisfeito extends VisitanteDefault {
	
	/* ---------------------------------------------- CONSTANTES ------------------------------------ */
	
	private static final int ENTRAR = 10;
	private static final int ESPERA_ANTES = 11;
	private static final int PACIFICANDO = 12;
	private static final int ESPERA = 13;
	private static final int SAIR = 14;
	private static final int MORTE = 14;
	
	/* ---------------------------------------------- VARIÁVEIS ------------------------------------ */
	
	private ComponenteAnimado extras[]; // imagens dos extras
	private int nExtras;                // número de extras que ainda tem
	private ComponenteVisual extraSai;  // imagem de um extra a sair
	private ComponenteAnimado imgSaida; // imagem de saida do visitante 
	private int minAberto;  // mínimo de tempo que mantém a porta aberta
	private int maxAberto;  // máximo de tempo que mantém a porta aberta 
	private long proxFecho; // quando vai fechar

	/* ---------------------------------------------- CONSTRUTOR ------------------------------------ */
	
	public Insatisfeito(String nome, int pontos, int nExtras, int minAberto, int maxAberto) {
		super(nome, pontos);
		this.nExtras = nExtras;
		this.minAberto = minAberto;
		this.maxAberto = maxAberto;
		
		extras = new ComponenteAnimado[ this.nExtras ];
		for( int i = 0; i < this.nExtras; i++){
			extras[i] = (ComponenteAnimado)ComponenteVisualLoader.getCompVisual( nome + "_extra" + i );
		}
		setStatus(ENTRAR);
		setImagem( nome + "_zangado");
		
	}
	
	/* ---------------------------------------------- MÉTODOS ------------------------------------ */

	@Override
	public int fecharPorta() {
		porta.setRecebeu( true );
		return getPontos();
	}

	@Override
	public void portaAberta() {
		proxFecho = GeradorAleatorio.proxTempo(minAberto, maxAberto);
	}

	@Override
	public boolean podeFechar() {
		return getStatus() == SAIR && getImagem().numCiclosFeitos() > 0;
	}

	@Override
	public int baleado() {
		if( getStatus() == MORTE )
			return 0;
		
		if( getStatus() == ESPERA_ANTES) {
			setStatus(PACIFICANDO);
			setImagem(nome + "_pacificando");
		}
		return 0;
	}

	@Override
	public void atualizar() {
		if( getStatus() == ENTRAR && getImagem().numCiclosFeitos() > 0 ){
			setStatus( ESPERA_ANTES );
		}
		else if( getStatus() == PACIFICANDO && getImagem().numCiclosFeitos() > 0 ){
			setStatus( ESPERA );
			setImagem(nome + "_espera");
			setImagemSaida("dinheiro");
		}
		else if( (getStatus() == ESPERA_ANTES ) && fimEspera()) {
			fezAsneira("boom");
		}
		else if (getStatus() == ESPERA) {
			setStatus(SAIR);
			setImagem(nome + "_adeus");
		}
	}
	
	private boolean fimEspera(){
		return System.currentTimeMillis() >= proxFecho;
	}
	
	/**
	 * desenha o visitante
	 * @param g ambiente gráfico onde desenhar
	 */
	public void desenhar( Graphics2D g ){
		super.desenhar(g);
		if( imgSaida != null )
			imgSaida.desenhar( g );
		for( int i = 0; i < nExtras; i++){
			extras[i].desenhar( g );
		}
		if( extraSai != null ){
			extraSai.desenhar( g );
			if( extraSai.numCiclosFeitos() > 0 )
				extraSai = null;
		}
	}
		
		/** retorna o número de extras que ainda possui
	 * @return o número de extras que ainda possui
	 */
	public int getnExtras() {
		return nExtras;
	}
	
	/** remove um extra
	 */
	private void reduzExtra(){
		nExtras--;
		extraSai = ComponenteVisualLoader.getCompVisual( nome + "_extra"+nExtras+"_sai"); 
		extraSai.setPosicao( (Point)getImagem().getPosicao().clone() );
		getPontos();
	}

	/** Indica se ainda tem extras
	 * @return true, se ainda tem extras
	 */
	private boolean temExtras(){
		return nExtras > 0;
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