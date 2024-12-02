package aula05.composicao;

public class PolimorfismoBase implements PolimorfismoComposicao {
	
	private int x;
	
	public PolimorfismoBase( int x ) {
		this.x = x;
	}
	
	public void metodo1( ) {
		System.out.println("Base método1 alterado " + x);
		x++;
	}
	
	public int metodo2( ) {
		System.out.println("Base método2 " + x);
		return x;
	}
}
