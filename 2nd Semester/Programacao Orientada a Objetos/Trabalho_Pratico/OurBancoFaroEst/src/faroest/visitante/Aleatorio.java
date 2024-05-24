package faroest.visitante;

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
	private boolean eDepositante = true;
	
	private ComponenteVisual extraSai; 
	private int nExtras;
	private ComponenteAnimado extras[]; 
	private ComponenteAnimado imgSaida; // imagem de saida do visitante 
	
	/* ---------------------------------------------- CONSTRUTOR ------------------------------------ */

	public Aleatorio(String nome, int pontos, int nExtras, int minAberto, int maxAberto) {
		super(nome, pontos);
		this.nExtras = nExtras;
		extras = new ComponenteAnimado[ this.nExtras ];
		for( int i = 0; i < this.nExtras; i++){
			extras[i] = (ComponenteAnimado)ComponenteVisualLoader.getCompVisual( nome + "_extra" + i );
		}
		
		this.minAberto = minAberto;
		this.maxAberto = maxAberto;
		
		setStatus(ENTRAR);
		ramdomImage(nome);
	}
	
	private boolean decideImagem() {
		return Math.random() < 0.5;
	}
	
	private void ramdomImage(String nome) {
		String imgDepositante = nome + "_deposita";
		String imgAssaltante = nome + "_mata";
		
		if(decideImagem()) {
			setImagem(imgDepositante);
		} else {
			setImagem(imgAssaltante);
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
		
		if (eDepositante) {
			fezAsneira("oops");
			setStatus(SAIR);
			setImagem("_deposita");
		} else {
			fezAsneira("boom");
			setStatus(SAIR);
			setImagem("_mata");	
		}	
			
		if( getStatus() == SAIR )
			return 0;
		
		if( temExtras() ){
			reduzExtra();
			return getPontos();
		}
		
		setImagem( nome + "_morte" );
		fezAsneira("oops");
		setStatus(SAIR);
		return 0;
	}

	@Override
	public void atualizar() {
		if( getStatus() == ENTRAR && getImagem().numCiclosFeitos() > 0 ){
			setStatus( ESPERA );
		} else if( getStatus() == ESPERA && getImagem().numCiclosFeitos() > 0 ){
			setStatus( ESPERA );
			setImagem(nome + "_deposita");
			setImagemSaida("dinheiro");
		} else if (getStatus() == ESPERA && decideImagem()) {
			setStatus(SAIR);
			setImagem(nome + "_deposita");
		}
	}
	
//	private boolean eDepositante() {
//		
//	}
	
	private boolean fimEspera(){
		return System.currentTimeMillis() >= proxFecho;
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
