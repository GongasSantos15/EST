package aula05.composicao;

public class PolimorfismoTeste {

	
	public static void testar( PolimorfismoComposicao p ) {
		p.metodo1();
		p.metodo2();
	}
	
	public static void main(String[] args) {
		
		PolimorfismoBase b = new PolimorfismoBase( 10 );
		testar( b );
		
		PolimorfismoDerivada d = new PolimorfismoDerivada( 30 );
		testar( d );
	}

}
