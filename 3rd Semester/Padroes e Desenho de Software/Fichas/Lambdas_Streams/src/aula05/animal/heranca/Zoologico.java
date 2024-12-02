package aula05.animal.heranca;

import java.util.ArrayList;

public class Zoologico {
	private ArrayList<AnimalDefault> animais = new ArrayList<AnimalDefault>();
	
	public void addAnimal( AnimalDefault a ){
		animais.add( a );
	}
	
	public void alimentarBichos(){
		for( AnimalDefault a : animais ){
			a.comer();
		}
	}
	
	public void horasDeDormir() {
		for( AnimalDefault a : animais )
			a.dormir();
	}
	
	public void passearBichos() {
		for( AnimalDefault a : animais )
			a.mover();
	}
	
	public void ouvirBichos() {
		for( AnimalDefault a : animais )
			a.falar();
	}
}
