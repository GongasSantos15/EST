package poo.freecell;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import poo.carta.Carta;

/**
 * Representa uma célula, local onde se pode armazenar uma e uma só carta
 * 
 * @author F. Sérgio Barbosa
 *
 */
public class Celula {
	
	private Carta aCarta;
	private Point topo;
	private int comprimento;
	private int altura;
	private boolean selecionado = false;
	private boolean eOrigem = true, eDestino = true;
	
	/**
	 * Cria a célula numa determinada posição do écran, definindo o seu comprimento e largura  
	 * @param t coordenada do topo da célula
	 * @param comp comprimento da célula 
	 * @param alt altura da célula
	 */
	public Celula( Point t, int comp, int alt) {
		topo = t;
		comprimento = comp;
		altura = alt;		
	}

	/**
	 * indica se pode receber a carta
	 * @param c a carta a verificar
	 * @return true se pode receber, false em caso contrário
	 */
	public boolean podeReceber(Carta c) {		
		return estaVazio();
	}
	
	/**
	 * recebe a carta, se obedecer às regras
	 * @param c a carta a receber
	 * @return true se recebeu, false caso contr�rio
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
		aCarta = c;
	}
	
	/**
	 * retira a carta e devolve-a
	 * @return a carta retirada
	 */
	public Carta retirar( ){
		Carta old = aCarta;
		aCarta = null;
		return old;
	}

	
	/**
	 * devolve a carta 
	 * @return a carta ou null se vazia
	 */
	public Carta getCarta( ){
		return estaVazio()? null: aCarta;
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
		return aCarta == null;
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
		topo = p;
		if( !estaVazio() )		
			aCarta.setPosicao( new Point( p.x+1, p.y+1 ) );
	}
	
	/** define se a célula é uma origem de cartas
	 * @param eOrigem true se for origem, false se não
	 */
	public void setComoOrigem(boolean eOrigem) {
		this.eOrigem = eOrigem;
	}

	/** indica se a célula pode ser considerada como origem
	 * @return true se for origem e false se não for
	 */
	public boolean eOrigem( ) {
		return this.eOrigem;
	}

	/** define se a célula é um destino de cartas
	 * @param eOrigem true se for destino, false se não
	 */
	public void setComoDestino(boolean eDestino) {
		this.eDestino = eDestino;
	}

	/** indica se a célula pode ser considerada como destino
	 * @return true se for destino e false se não for
	 */
	public boolean eDestino( ) {
		return this.eDestino;
	}

	/**
	 * remove todas as cartas
	 */
	public void limpar() {
		aCarta = null;
	}
	
	
	/**
	 * indica se uma dada coordenada está dentro do espaço da célula 
	 * @param pt a coordenada a verificar
	 * @return true se a coordenada está dentro, false caso contr�rio 
	 */
	public boolean estaDentro( Point pt ) {
		// ver se existe carta na celula, se existir ver se clicou nela
		if( !estaVazio() )  
			return aCarta.estaDentro( pt );
		
		// se não, ver se clicou na área do componente
		return topo.x <= pt.x && topo.y <= pt.y && topo.x + comprimento >= pt.x && topo.y + altura >= pt.y;
	}

	/**
	 * (des)selecciona a célula
	 * @param sel true para seleccionar, false para desseleccionar 
	 */
	public void setSelecionado( boolean sel ) {
		selecionado = sel; 
		if( !estaVazio() )
			getCarta().setSelecionada( sel );
	}
	
	/** indica se a célula está selecionada ou não
	 * @return true se está selecionada, false se não estiver
	 */
	public boolean estaSelecionado() {
		return selecionado;
	}

	/**
	 * desenha a célula no écran
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
		
		if( !estaVazio() )
			aCarta.desenhar( g );				
	}		
}
