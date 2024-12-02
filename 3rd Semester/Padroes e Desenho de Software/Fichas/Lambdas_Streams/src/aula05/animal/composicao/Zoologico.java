package aula05.animal.composicao;

import java.util.ArrayList;

public class Zoologico {
	private ArrayList<Animal> animais = new ArrayList<Animal>();
	
	public void addAnimal( Animal a ){
		animais.add( a );
	}
	
	public void alimentarBichos(){
		for( Animal a : animais ){
			a.comer();
		}
	}
	
	public void horasDeDormir() {
		for( Animal a : animais )
			a.dormir();
	}
	
	public void passearBichos() {
		for( Animal a : animais )
			a.mover();
	}
	
	public void ouvirBichos() {
		for( Animal a : animais )
			a.falar();
	}
}
