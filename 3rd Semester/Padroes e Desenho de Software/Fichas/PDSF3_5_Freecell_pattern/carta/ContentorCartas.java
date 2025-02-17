package carta;

import java.awt.Graphics;
import java.awt.Point;

public interface ContentorCartas extends Cloneable {
	
	// Método para clonar o contentor de cartas
	public ContentorCartas clone();

	public boolean estaDentro( Point pt );
	public boolean podeReceber( Carta c );	
	public boolean receber( Carta c );
	
	public void colocar( Carta c );

	public Carta getCarta( );
	public Carta retirar( );		

	public boolean estaVazio();
	public void limpar();
	
	public void setSeleccionado( boolean sel );
	public boolean getSeleccionado( );
	
	public int getComprimento();	
	public int getAltura();
	
	public Point getPosicao();
	public void setPosicao( Point pt );
	
	public void desenhar( Graphics g );
	
}
