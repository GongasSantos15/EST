package aula05.animal.heranca;

public class Pardal extends Ave {
	
	public Pardal(String nome, int idade, int tamanho) {
		super(nome, idade, tamanho);
	}
	
	public void chilrear( ){
		System.out.println("Piu piu");
	}
	
	@Override
	public void falar() {
		chilrear();		
	}
}
