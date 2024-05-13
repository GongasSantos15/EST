package poo.freecell;

import java.awt.Point;
import poo.carta.Carta;

/**
 * Representa uma coluna, local onde se podem armazenar várias cartas desde que seja por 
 * ordem decrescente e de cor alternada
 * 
 * @author F. Sérgio Barbosa
 *
 */
public class Coluna extends ContentorCartasDefault {

	/**
	 * Cria a coluna numa determinada posição do écran, definindo o seu comprimento e largura  
	 * @param t coordenada do topo da coluna
	 * @param comp comprimento da coluna
	 * @param alt altura da coluna
	 */
	public Coluna(Point t, int comp, int alt) {
		super( t, comp, alt);
	}
	
	/**
	 * indica se pode receber a carta.
	 * @param c a carta a verificr
	 * @return true se pode receber, false em caso contrário
	 */
	public boolean podeReceber(Carta c) {
		// se está vazio pode receber qualquer carta
		if( estaVazio() )
			return true;
		
		// se não está vazio tem de receber de cor diferente e consecutiva
		Carta ultima = getCarta( );
		return ( ultima.getCor() != c.getCor() ) &&
		       ( ultima.getFace() - 1 == c.getFace() ); 
	}

	/**
	 * coloca a carta mesmo que não obedeça às regras
	 * @param c carta a colocar
	 */
	public void colocar( Carta c ){
		if( estaVazio() ){
			c.setPosicao( getPosicao() );			
		}
		else {			
			Carta ultima = getCarta( );
		
			Point nova = new Point( ultima.getPosicao().x, ultima.getPosicao().y + ultima.getAltura() / 3 );
			c.setPosicao( nova );
		}		
		super.colocar(c);
	}	
}
