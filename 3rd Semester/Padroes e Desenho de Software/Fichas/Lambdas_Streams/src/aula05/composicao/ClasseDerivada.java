package aula05.composicao;

public class ClasseDerivada {

	private ClasseBase base;

	public void metodo1() {
		base.metodo1();
	}

	public int metodo2() {
		return base.metodo2();
	}

	
	
}
