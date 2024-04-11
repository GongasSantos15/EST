package poo.freecell;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import poo.carta.Carta;

/**
 * Representa uma casa, local onde se podem armazenar as cartas de um naipe por 
 * ordem crescente 
 * 
 * @author F. Sérgio Barbosa
 *
 */
public class Casa {

	private ArrayList<Carta> asCartas = new ArrayList<Carta>();
	private Point topo;
	private int comprimento;
	private int altura;
	private boolean selecionado = false;
	private boolean eOrigem = true, eDestino = true;
	
	/**
	 * Cria a casa numa determinada posição do écran, definindo o seu comprimento e largura  
	 * @param t coordenada do topo da casa
	 * @param comp comprimento da casa
	 * @param alt altura da casa
	 */
	public Casa(Point t, int comp, int alt) {
		topo = t;
		comprimento = comp;
		altura = alt;		
	}
	
	
	/**
	 * indica se pode receber a carta.
	 * @param c a carta a verificar
	 * @return true se pode receber, false em caso contrário
	 */
	public boolean podeReceber(Carta c) {
		if( estaVazio() ) 
			return c.getFace() == Carta.AS;			
		else {				
			Carta ultima = getCarta();
			return ultima.getNaipe() == c.getNaipe() && ultima.getFace() + 1 == c.getFace();  
		}
	}
	
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
	public void colocar( Carta c ){
		c.setPosicao( new Point( getPosicao().x+1, getPosicao().y+1 ) );
		asCartas.add( c );
	}
	
	/**
	 * retira a carta do topo da casa e devolve-a
	 * @return a carta retirada
	 */
	public Carta retirar( ){		
		return asCartas.remove( asCartas.size()-1 );
	}

	
	/**
	 * devolve a carta no topo da casa, sem a retirar
	 * @return a carta ou null se vazia
	 */
	public Carta getCarta( ){
		return estaVazio()? null: asCartas.get( asCartas.size()-1 );
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
		// reposicionar o topo
		topo = p;
		
		// reposicionar as cartas
		for( int i = 0; i < asCartas.size(); i++ )
			asCartas.get( i ).setPosicao( new Point( topo.x+1, topo.y + 1 ) );
	}

	/** define se a casa é uma origem de cartas
	 * @param eOrigem true se for origem, false se não
	 */
	public void setComoOrigem(boolean eOrigem) {
		this.eOrigem = eOrigem;
	}

	/** indica se a casa pode ser considerada como origem
	 * @return true se for origem e false se não for
	 */
	public boolean eOrigem( ) {
		return this.eOrigem;
	}

	/** define se a casa é um destino de cartas
	 * @param eOrigem true se for destino, false se não
	 */
	public void setComoDestino(boolean eDestino) {
		this.eDestino = eDestino;
	}

	/** indica se a casa pode ser considerada como destino
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
	 * indica se uma dada coordenada está dentro do espaço da casa 
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
	 * (des)selecciona a casa
	 * @param sel true para seleccionar, false para desseleccionar 
	 */
	public void setSelecionado( boolean sel ) {
		selecionado = sel; 
		if( !estaVazio() )
			getCarta().setSelecionada( sel );
	}
	
	/** indica se a casa está selecionada ou não
	 * @return true se está selecionada, false se não estiver
	 */
	public boolean estaSelecionado() {
		return selecionado;
	}
	
	/**
	 * desenha a casa no écran
	 * @param g sistema gráfico onde se vai desenhar
	 */
	public void desenhar( Graphics g ){
		int x1 = getPosicao().x;
		int y1 = getPosicao().y;
		int x2 = getPosicao().x + getComprimento();
		int y2 = getPosicao().y + getAltura();
		
		g.setColor( Color.black );
		g.drawLine( x1, y1, x2, y1 );
		g.drawLine( x1, y1, x1, y2 );
		g.setColor( Color.green );	
		g.drawLine( x2, y1+1, x2, y2 );
		g.drawLine( x1+1, y2, x2, y2 );			
		
		for( int i=0; i < asCartas.size(); i++ ){
			Carta card = (Carta) asCartas.get( i );
			card.desenhar( g );
		}					
	}


}
