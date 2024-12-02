package aula05.composicao;

public class PolimorfismoDerivada implements PolimorfismoComposicao {

	PolimorfismoBase base;
	
	public PolimorfismoDerivada( int x ) {
		base = new PolimorfismoBase(x);
	}

	public void metodo1() {
		System.out.print( "derivada método 1  ");
		base.metodo1();
	}

	public int metodo2() {
		System.out.print( "derivada método 2  ");
		return base.metodo2();
	}
}
