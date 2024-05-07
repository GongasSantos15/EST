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
public class Casa extends ContentorCartasDefault {

	/**
	 * Cria a casa numa determinada posição do écran, definindo o seu comprimento e largura  
	 * @param t coordenada do topo da casa
	 * @param comp comprimento da casa
	 * @param alt altura da casa
	 */
	public Casa(Point t, int comp, int alt) {
		super(t, comp, alt);
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
		super.colocar( c );
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
		
		super.desenhar(g);			
	}

}
