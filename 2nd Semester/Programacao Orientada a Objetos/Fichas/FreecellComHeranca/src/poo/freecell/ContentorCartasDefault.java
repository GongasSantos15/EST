package poo.freecell;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import poo.carta.Carta;

/**
 * Representa uma coluna, local onde se podem armazenar várias cartas desde que seja por 
 * ordem decrescente e de cor alternada
 * 
 * @author F. Sérgio Barbosa
 *
 */
public abstract class ContentorCartasDefault {

	private ArrayList<Carta> asCartas = new ArrayList<Carta>();
	private Point topo;
	private int comprimento;
	private int altura;
	private boolean selecionado = false;
	private boolean eOrigem = true, eDestino = true;

	/**
	 * Cria a coluna numa determinada posição do écran, definindo o seu comprimento e largura  
	 * @param t coordenada do topo da coluna
	 * @param comp comprimento da coluna
	 * @param alt altura da coluna
	 */
	public ContentorCartasDefault(Point t, int comp, int alt) {
		topo = t;
		comprimento = comp;
		altura = alt;		
	}
	
	/**
	 * indica se pode receber a carta.
	 * @param c a carta a verificr
	 * @return true se pode receber, false em caso contrário
	 */
	public abstract boolean podeReceber(Carta c);
	
	/**
	 * recebe a carta, se obedecer às regras
	 * @param c a carta a receber
	 * @return true se recebeu, false caso contrário
	 */	
	public boolean receber( Carta c ){
		if( !podeReceber( c ) )
			return false;
		colocar( c );
		return true;
	}	
	
	/**
	 * coloca a carta mesmo que não obedeça às regras
	 * @param c carta a colocar
	 */
	public void colocar( Carta c ) {
		asCartas.add( c );		
	}

	/**
	 * retira a carta e devolve-a
	 * @return a carta retirada
	 */
	public Carta retirar( ){		
		return (Carta) asCartas.remove( asCartas.size()-1 );
	}

	/**
	 * devolve a carta no topo da coluna, sem a retirar
	 * @return a carta ou null se vazia
	 */
	public Carta getCarta( ){
		return estaVazio()? null:  asCartas.get( asCartas.size()-1 );
	}	

	/**
	 * devolve a posição no écran
	 * @return
	 */
	public Point getPosicao() {
		return topo;
	}	

	/**
	 * indica se está vazia
	 * @return se está vazia
	 */
	public boolean estaVazio() {
		return asCartas.isEmpty();
	}

	/**
	 * devolve o comprimento
	 * @return o comprimento em pixeis
	 */
	public int getComprimento() {
		return comprimento;
	}
	
	/**
	 * devolve a altura em pixeis
	 * @return a altura em pixeis
	 */
	public int getAltura(){
		return altura;
	}
	
	/**
	 * define uma nova posição no écran
	 * @param p a nova posição
	 */
	public void setPosicao( Point p ){
		int dx = p.x - topo.x;
		int dy = p.y - topo.y;
		
		// reposicionar o topo
		topo = p;
		
		for( int i = 0; i < asCartas.size(); i++ ) {
			Point posicaoCarta = asCartas.get(i).getPosicao();
			asCartas.get( i ).setPosicao( new Point( posicaoCarta.x + dx, posicaoCarta.y + dy) );
		}
	}

	/** define se a coluna é uma origem de cartas
	 * @param eOrigem true se for origem, false se não
	 */
	public void setComoOrigem(boolean eOrigem) {
		this.eOrigem = eOrigem;
	}

	/** indica se a coluna pode ser considerada como origem
	 * @return true se for origem e false se não for
	 */
	public boolean eOrigem( ) {
		return this.eOrigem;
	}

	/** define se a coluna é um destino de cartas
	 * @param eOrigem true se for destino, false se não
	 */
	public void setComoDestino(boolean eDestino) {
		this.eDestino = eDestino;
	}

	/** indica se a coluna pode ser considerada como destino
	 * @return true se for destino e false se não for
	 */
	public boolean eDestino( ) {
		return this.eDestino;
	}
	
	/**
	 * remove todas as cartas
	 */
	public void limpar() {
		asCartas.clear();
	}
	
	/**
	 * indica se uma dada coordenada está dentro do espaço da coluna 
	 * @param pt a coordenada a verificar
	 * @return true se a coordenada está dentro, false caso contrário 
	 */
	public boolean estaDentro( Point pt ) {
		// ver se clicou em alguma das cartas do componente
		for( int i = 0; i < asCartas.size(); i++ )
			if( ((Carta)asCartas.get( i )).estaDentro( pt ) )
				return true;
		
		// se não, ver se clicou na área do componente
		return topo.x <= pt.x && topo.y <= pt.y && topo.x + comprimento >= pt.x && topo.y + altura >= pt.y;
	}
	
	/**
	 * (des)selecciona a coluna
	 * @param sel true para seleccionar, false para desseleccionar 
	 */
	public void setSelecionado( boolean sel ) {
		selecionado = sel; 
		if( !estaVazio() )
			getCarta().setSelecionada( sel );
	}

	/** indica se a coluna está selecionada ou não
	 * @return true se está selecionada, false se não estiver
	 */
	public boolean estaSelecionado() {
		return selecionado;
	}
		
	/**
	 * desenha a coluna no écran
	 * @param g sistema gráfico onde se vai desenhar
	 */
	public void desenhar( Graphics g ){
		for( int i=0; i < asCartas.size(); i++ ){
			Carta card = (Carta) asCartas.get( i );
			card.desenhar( g );
		}					
	}	
}
