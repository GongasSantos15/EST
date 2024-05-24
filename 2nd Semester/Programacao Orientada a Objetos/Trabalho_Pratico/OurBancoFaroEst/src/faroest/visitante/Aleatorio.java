package faroest.visitante;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import faroest.util.GeradorAleatorio;
import prof.jogos2D.image.ComponenteAnimado;
import prof.jogos2D.image.ComponenteVisual;
import prof.jogos2D.util.ComponenteVisualLoader;

public class Aleatorio extends VisitanteDefault {
	
	/* ---------------------------------------------- CONSTANTES ------------------------------------ */
	private static final int ENTRAR = 10;
	private static final int ESPERA = 11;
	private static final int SAIR = 12;
	
	/* ---------------------------------------------- VARIÁVEIS ------------------------------------ */
	private int minAberto;  
	private int maxAberto; 
	private long proxFecho;
	
	private ComponenteVisual extraSai; 
	private int nExtras;
	private ComponenteAnimado extras[]; 
	private ComponenteAnimado imgSaida;
	
	private boolean vaiDepositar = false;
	
	String imgDeposita = nome + "_deposita";
	String imgMata = nome + "_mata";
	
	/* ---------------------------------------------- CONSTRUTOR ------------------------------------ */

	public Aleatorio(String nome, int pontos, int nExtras, int minAberto, int maxAberto) {
		super(nome, pontos);
		
		this.nExtras = nExtras;
		extras = new ComponenteAnimado[ this.nExtras ];
		for( int i = 0; i < this.nExtras; i++){
			extras[i] = (ComponenteAnimado)ComponenteVisualLoader.getCompVisual( nome + "_extra" + i );
		}
		System.out.println("Construtor: " + nExtras);
		
		this.minAberto = minAberto;
		this.maxAberto = maxAberto;
		
		setStatus(ENTRAR);
//		ramdomImage(nome);
		if(decideImagem()) {
			setImagem(imgDeposita);
			vaiDepositar = true;
		} else {
			setImagem(imgMata);
			vaiDepositar = false;
		}
	}
	
	private boolean decideImagem() {
		return Math.random() < 0.5;
	}
	
	private void ramdomImage(String nome) {
		
		if(decideImagem()) {
			setImagem(imgDeposita);
			vaiDepositar = true;
		} else {
			setImagem(imgMata);
			vaiDepositar = false;
		}
	}

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
		
		if( getStatus() == SAIR )
			return 0;
			
		if( temExtras() ){
			reduzExtra();
			return getPontos();
		} else if (vaiDepositar) {
			setStatus(SAIR);
			setImagem(nome + "_deposita");
			setImagemSaida("dinheiro");
		} else {
			fezAsneira("boom");
			setStatus(SAIR);
			setImagem(nome + "_mata");	
		}	
		
		return 0;
	}

	@Override
	public void atualizar() {
		
		if( getStatus() == ENTRAR && getImagem().numCiclosFeitos() > 0 ){
			setStatus( ESPERA );
		} else if (getStatus() == ESPERA && fimEspera()) {
			setStatus(SAIR);
		}
	}
	
	private boolean fimEspera(){
		return System.currentTimeMillis() >= proxFecho;
	}
	
	@Override
	public void desenhar(Graphics2D g) {
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
	}

	/** Indica se ainda tem extras
	 * @return true, se ainda tem extras
	 */
	private boolean temExtras(){
		return nExtras > 0;
	}
	
	/** define a imagem que representa o visitante
	 * @param nome o nome da imagem
	 */
	public void setImagem(String nome) {
		Point p = img != null? img.getPosicao() : null;
		img = ComponenteVisualLoader.getCompVisual( nome );
		img.setPosicao( p );
	}

	/**
	 * Define a posição do visitante no jogo. A posição é dada em pixeis.
	 * @param point a posição do visitante
	 */
	public void setPosicao(Point posicao) {
		super.setPosicao(posicao);
		if( imgSaida != null )
			imgSaida.setPosicao( (Point)posicao.clone() );
		for( int i = 0; i < nExtras; i++){
			extras[i].setPosicao( (Point)posicao.clone() );
		}
	}
	
	private void setImagemSaida( String nomeImg ){
		imgSaida = (ComponenteAnimado)ComponenteVisualLoader.getCompVisual( nomeImg );
		Rectangle r = getImagem().getBounds();
		imgSaida.setPosicao( new Point( r.x+(r.width - imgSaida.getComprimento())/2 , r.y) );
	}
	
	@Override
	public VisitanteDefault clone() {
		Aleatorio v = (Aleatorio) super.clone();
		v.extras = new ComponenteAnimado[ extras.length ];
		for( int i=0; i < extras.length; i++ ){
			v.extras[i] = extras[i].clone();
		}
		return v;
	}

}
