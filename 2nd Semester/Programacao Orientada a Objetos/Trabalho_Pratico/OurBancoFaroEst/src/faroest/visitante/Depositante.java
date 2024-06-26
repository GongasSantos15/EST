   package faroest.visitante;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import faroest.util.GeradorAleatorio;
import prof.jogos2D.image.ComponenteAnimado;
import prof.jogos2D.image.ComponenteVisual;
import prof.jogos2D.util.ComponenteVisualLoader;

/** Representa um depositante. Um depositante irá depositar o dinheiro
 * quando fechar a porta. Pode ter vários extras que podem ser removidos.
 * Os extras não precisam de ser removidos para ele depositar mas valem pontos. 
 */
public class Depositante extends VisitanteDefault implements Cloneable {
	
	/* ---------------------------------------------- CONSTANTES ------------------------------------ */

	// Estados possíveis o Depositante */
	private static final int OLA = 10;
	private static final int ESPERA = 11;
	private static final int ADEUS = 12;
	private static final int MORTO = 13;
	
	/* ---------------------------------------------- VARIÁVEIS ------------------------------------ */

	private int minAberto;  
	private int maxAberto; 
	private long proxFecho;
	
	private ComponenteVisual extraSai;  
	private ComponenteAnimado imgSaida; 
	
	private ComponenteAnimado extras[]; 
	private int nExtras;               

	/* ---------------------------------------------- CONSTRUTOR ------------------------------------ */
	
	/** Cria um visitante Depositante
	 * @param nome nome do visitante (usado para as imagens)
	 * @param pontos pontos que vale
	 * @param nExtras quantos extras tem
	 * @param minAberto mínimo de tempo que mantém a porta aberta
	 * @param maxAberto máximo de tempo que mantém a porta aberta
	 */
	public Depositante( String nome, int pontos, int nExtras, int minAberto, int maxAberto ) {
		
		super(nome, pontos);
		
		this.nExtras = nExtras;
		extras = new ComponenteAnimado[ this.nExtras ];
		for( int i = 0; i < this.nExtras; i++){
			extras[i] = (ComponenteAnimado)ComponenteVisualLoader.getCompVisual( nome + "_extra" + i );
		}
		
		setStatus( OLA );
		setImagem( nome + "_ola" );
		this.minAberto= minAberto;
		this.maxAberto = maxAberto;
	}
	
	/* ---------------------------------------------- MÉTODOS ------------------------------------ */

	/** informa o visitante que a porta abriu
	 */
	public void portaAberta() {
		proxFecho = GeradorAleatorio.proxTempo(minAberto, maxAberto);
	}
	
	/** indica se o visitante permite que a porta feche
	 * @return true se a porta pode fechar
	 */
	public boolean podeFechar() {
		return getStatus() == ADEUS && getImagem().numCiclosFeitos() > 1;
	}
	
	/** método que diz ao visitante que a porta fechou
	 * @return a pontuação por esta ação
	 */
	public int fecharPorta() {
		porta.setRecebeu( true );
		return getPontos();
	}
	
	/** o visitante foi baleado 
	 * @return a pontuação obtida
	 */
	
	// Se tem extras, reduzir o extra quando o personagem for baleado e retornar os pontos. Ao fim disto, mudar a animação para "morte" e chamar o método fezAsneira para retirar
	// uma vida e mudar o estado para MORTO
	public int baleado() {
		if( getStatus() == MORTO )
			return 0;
		
		if( temExtras() ){
			reduzExtra();
			return getPontos();
		}
		
		setImagem( nome + "_morte" );
		fezAsneira("oops");
		setStatus(MORTO);
		return 0;
	}
	
	/** efetua um ciclo do jogo
	 */
	
	// Atualiza o personagem 
		// Estado = OLA, mudando o estado para ESPERA, bem como a animação do personagem
		// Estado = ESPERA, muda o estado para ADEUS, bem como a animação para "adeus" e a imagem de saída ("dinheiro")
	public void atualizar() {
		if( getStatus() == OLA && getImagem().numCiclosFeitos() > 0 ){
			setStatus( ESPERA );
			setImagem( nome + "_espera");
		}
		else if( getStatus() == ESPERA && fimEspera() ){
			setStatus( ADEUS );
			setImagem ( nome + "_adeus" );
			setImagemSaida( "dinheiro" );
		}
	}
	
	private boolean fimEspera(){
		return System.currentTimeMillis() >= proxFecho;
	}
	
	/**
	 * desenha o visitante
	 * @param g ambiente gráfico onde desenhar
	 */
	
	// Desenhar o personagem bem como os seus extras
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

	
	/** retorna a imagem associada
	 * @return a imagem associada
	 */
	public ComponenteVisual getImagem() {
		return img;
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
	
	/** define qual a imagem de saída. A imagem de saída é um "efeito especial",
	 * que pode ser o dinheiro ou outra (num futuro) 
	 * @param nomeImg o nome da imagem de saida
	 */
	private void setImagemSaida( String nomeImg ){
		imgSaida = (ComponenteAnimado)ComponenteVisualLoader.getCompVisual( nomeImg );
		Rectangle r = getImagem().getBounds();
		imgSaida.setPosicao( new Point( r.x+(r.width - imgSaida.getComprimento())/2 , r.y) );
	}
	
	/** retorna a imagem de saída
	 * @return  a imagem de saída
	 */
	protected ComponenteAnimado getImagemSaida(){
		return imgSaida;
	}
	
	/** cria um clone do visitante
	 * @return um visitante igual ao original
	 */
	public VisitanteDefault clone() {
		Depositante v = (Depositante) super.clone();
		v.extras = new ComponenteAnimado[ extras.length ];
		for( int i=0; i < extras.length; i++ ){
			v.extras[i] = extras[i].clone();
		}
		return v;
	}
}
