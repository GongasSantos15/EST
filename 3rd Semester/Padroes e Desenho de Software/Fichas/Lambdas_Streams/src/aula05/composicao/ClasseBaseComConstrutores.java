package aula05.composicao;

public class ClasseBaseComConstrutores {
	
	private int x;
	
	public ClasseBaseComConstrutores( int x ) {
		this.x = x;
	}
	
	public void metodo1( ) {
		x++;
	}
	
	public int metodo2( ) {
		return x;
	}
}
