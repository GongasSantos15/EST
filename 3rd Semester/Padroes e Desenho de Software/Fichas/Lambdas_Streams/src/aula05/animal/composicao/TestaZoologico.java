package aula05.animal.composicao;

public class TestaZoologico {

	public static void main(String[] args) {
		Zoologico zoo = new Zoologico();
		
		zoo.addAnimal( new Cao("Bobi", 2, 7) );
		zoo.addAnimal( new Cao("Max", 4, 8) );
		zoo.addAnimal( new Cao("Lassie", 35, 6) );

		zoo.addAnimal( new Cao2("Bobi 2", 2, 7) );
		zoo.addAnimal( new Cao2("Max 2", 4, 8) );
		zoo.addAnimal( new Cao2("Lassie 2", 35, 6) );
		
		zoo.addAnimal( new Cao3("Bobi 3", 2, 7) );
		zoo.addAnimal( new Cao3("Max 3", 4, 8) );
		zoo.addAnimal( new Cao3("Lassie 3", 35, 6) );
		
		zoo.addAnimal( new Gato("Tareco", 2, 4) );
		zoo.addAnimal( new Gato("Garfield", 30, 8) );
		zoo.addAnimal( new Gato("Bichano", 4, 3) );
		
		zoo.alimentarBichos();
		zoo.horasDeDormir();
		zoo.passearBichos();
		zoo.ouvirBichos();
	}	
}
