package faroest.visitante;

import java.awt.Graphics2D;
import java.awt.Point;

import faroest.util.GeradorAleatorio;
import prof.jogos2D.image.ComponenteAnimado;


/** Representa um assaltante. Um assaltante irá disparar sobre
 * o jogador após algum tempo. Pode aparecer com as armas empunhadas
 * ou com as armas por sacar. Só pode ser morto quando as armas estão empunhadas.
 */
public class Assaltante extends VisitanteDefault implements Cloneable {
	
	/* ---------------------------------------------- CONSTANTES ------------------------------------ */

	// Estados possíveis do Assaltante
	private static final int ESPERA = 10;
	private static final int SACANDO = 11;
	private static final int SACOU = 12;
	private static final int DISPARO = 13;
	private static final int MORTO = 14;

	/* ---------------------------------------------- VARIÁVEIS ------------------------------------ */
	
	private int minSacar;   
	private int maxSacar;   
	private int minDisparo; 
	private int maxDisparo; 
	private long proxAto;   
	
	private long tempoSaque; 
	
	private ComponenteAnimado imgSaida;     

	/* ---------------------------------------------- CONSTRUTOR ------------------------------------ */

	/** Cria um visitante Assaltante
	 * @param nome nome do visitante (usado para as imagens)
	 * @param pontos pontos que vale
	 * @param minSacar tempo mínimo que demora a sacar 
	 * @param maxSacar tempo máximo que demora a sacar (se 0, as armas já vêm empunhadas)
	 * @param minDisparo tempo mínimo que demora a disparar (após sacar)
	 * @param maxDisparo tempo máximo que demora a disparar (após sacar)
	 */
	public Assaltante( String nome, int pontos, int minSacar, int maxSacar, int minDisparo, int maxDisparo ) {
		
		super(nome, pontos);
		
		// ver se vem com as armas empunhadas ou não
		if( maxSacar > 0){  // por sacar
			setStatus( ESPERA );
			setImagem( nome + "_espera" );			
		}
		else {  // já sacadas
			setStatus( SACOU );
			setImagem( nome + "_sacada" );			
		}
		this.minSacar = minSacar;
		this.maxSacar = maxSacar;
		this.minDisparo = minDisparo;
		this.maxDisparo = maxDisparo;
	}
	
	/* ---------------------------------------------- MÉTODOS ------------------------------------ */

	/** método que diz ao visitante que a porta fechou
	 * @return a pontuação por esta ação
	 */
	public int fecharPorta() {
		return 0;
	}
	
	/** informa o visitante que a porta abriu
	 */
	public void portaAberta() {
		if( getStatus() == ESPERA )
			proxAto = GeradorAleatorio.proxTempo(minSacar, maxSacar);
		else {
			proxAto = GeradorAleatorio.proxTempo(minDisparo, maxDisparo);
			tempoSaque = System.currentTimeMillis();
		}
	}
	
	/** indica se o visitante permite que a porta feche
	 * @return true se a porta pode fechar
	 */
	public boolean podeFechar() {
		return getStatus() == MORTO && getImagem().numCiclosFeitos() > 0;
	}
	
	/** o visitante foi baleado 
	 * @return a pontuação obtida
	 */
	
	// Verificar o estado do personagem e se < SACOU, mudar a animação para "morte1" e retirar uma vida ao jogador, senão matar o personagem e retornar os pontos correpondentes
	// ao tempo demorado, bem como mudar o estado do personagem para MORTO
	public int baleado() {
		if( getStatus() == MORTO )
			return 0;
		
		float pontuacao = 0;
		if( getStatus() < SACOU ){
			setImagem( nome + "_morte1" );
			fezAsneira("oops");
		} 
		else {
			setImagem( nome + "_morte2" );
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
		setStatus( MORTO );
		// retorna a pontuação arredondada
		return (int)(pontuacao+0.5f);
	}
	
	/** efetua um ciclo do jogo
	 */
	
	// Atualizar o personagem 
		// Se ao sacar a arma, mudando o estado para ESPERA e a animação para "saca"
		// Depois dessa animação, mudar o estado para SACANDO e a animação para "sacada", bem como calcular o tempo que demora a disparar
		// Se o jogador não tiver baleado o personagem, este dispara e muda o ESTADO para DISPARO bem como a animação do jogo para "bang"
	public void atualizar() {
		if( getStatus() == ESPERA && System.currentTimeMillis() >= proxAto  ){
			setStatus( SACANDO );
			setImagem( nome + "_saca");
		}
		else if( getStatus() == SACANDO && getImagem().numCiclosFeitos() > 0 ){
			setStatus( SACOU );
			setImagem ( nome + "_sacada" );
			tempoSaque = System.currentTimeMillis();
			proxAto = GeradorAleatorio.proxTempo(minDisparo, maxDisparo);
		}
		else if( getStatus() == SACOU && System.currentTimeMillis() >= proxAto ){
			setStatus( DISPARO );
			fezAsneira("bang");
		}
	}
	
	/**
	 * desenha o visitante
	 * @param g ambiente gráfico onde desenhar
	 */
	public void desenhar( Graphics2D g ){
		super.desenhar(g);
		if( imgSaida != null )
			imgSaida.desenhar( g );
	}

	/**
	 * Define a posição do visitante no jogo. A posição é dada em pixeis.
	 * @param point a posição do visitante
	 */
	public void setPosicao(Point posicao) {
		super.setPosicao(posicao);
		if( imgSaida != null )
			imgSaida.setPosicao( (Point)posicao.clone() );
	}
	
	/** cria um clone do visitante
	 * @return um visitante igual ao original
	 */
	public VisitanteDefault clone() {
		return super.clone();
	}

}
