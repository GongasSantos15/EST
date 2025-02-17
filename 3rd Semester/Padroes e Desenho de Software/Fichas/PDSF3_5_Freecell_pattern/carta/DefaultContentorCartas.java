package carta;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class DefaultContentorCartas implements ContentorCartas {
	
	private ArrayList<Carta> asCartas = new ArrayList<Carta>();
	private Point topo;
	private int comprimento;
	private int altura;
	private boolean selecionado = false;
	
	public DefaultContentorCartas( Point t, int comp, int alt ){
		topo = t;
		comprimento = comp;
		altura = alt;
	}
	
	public void colocar( Carta c ){
		asCartas.add( c );
	}	
	
	public boolean receber( Carta c ){
		if( !podeReceber( c ) )
			return false;
		colocar( c );
		return true;
	}
	
	public void desenhar( Graphics g ){
		for( int i=0; i < asCartas.size(); i++ ){
			Carta card = (Carta) asCartas.get( i );
			card.desenhar( g );
		}			
	}
	
	public Carta retirar( ){		
		return (Carta) asCartas.remove( asCartas.size()-1 );
	}

	public Carta getCarta( ){
		return (Carta) asCartas.get( asCartas.size()-1 );
	}
	
	public boolean estaVazio() {
		return asCartas.isEmpty();
	}
	
	public boolean estaDentro( Point pt ) {
		// ver se clicou em alguma das cartas do componente
		for( int i = 0; i < asCartas.size(); i++ )
			if( ((Carta)asCartas.get( i )).estaDentro( pt ) )
				return true;
		
		// se n�o ver se clicou na �rea do componente
		return topo.x <= pt.x && topo.y <= pt.y && topo.x + comprimento >= pt.x && topo.y + altura >= pt.y;
	}
	
	public void setSeleccionado( boolean sel ) {
		selecionado = sel; 
		if( !estaVazio() )
			getCarta().setSelecionada( sel );
	}
	
	public boolean getSeleccionado( ){
		return selecionado;
	}
	
	public Point getPosicao() {
		return topo;
	}
	
	public int getComprimento() {
		return comprimento;
	}
	
	public int getAltura(){
		return altura;
	}

	public void setPosicao( Point pt ){
		int dx = pt.x - topo.x;
		int dy = pt.y - topo.y;
		topo = pt;
		
		for( Carta c : asCartas ){
			Point pc = c.getPosicao();
			pc.x += dx;
			pc.y += dy;
			c.setPosicao( pc );
		}
	}
	
	public void limpar() {
		asCartas.clear();
	}
	
	@Override
	public ContentorCartas clone() {
		try {
			// Fazer deep copy do array de cartas, pois não queremos que as mesmas se apresentem na posição inicial, e sim na posição que estava ao criar o ponto de restauro
			DefaultContentorCartas copia = (DefaultContentorCartas) super.clone();
			
			// Fazer deep copy das cartas	
				// Versão com streams
				//copia.asCartas = asCartas.stream().map(Carta::clone).collect(Collectors.toCollection(ArrayList::new));
			
				// Versão com forEach
				copia.asCartas = new ArrayList<Carta>();
				asCartas.forEach(c -> copia.asCartas.add(c.clone()));
				
			copia.topo = (Point) topo.clone();
			return copia;
		} catch (CloneNotSupportedException e) { 
			return null;
		}
	}
}
