package aula05.composicao;

public class ClasseDerivadaComConstrutores {

	ClasseBaseComConstrutores base;
	
	public ClasseDerivadaComConstrutores( int x ) {
		base = new ClasseBaseComConstrutores(x);
	}

	public void metodo1() {
		base.metodo1();
	}

	public int metodo2() {
		return base.metodo2();
	}
}
