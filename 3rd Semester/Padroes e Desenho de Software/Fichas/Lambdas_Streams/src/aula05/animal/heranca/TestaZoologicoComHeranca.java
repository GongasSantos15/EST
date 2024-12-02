package aula05.animal.heranca;


public class TestaZoologicoComHeranca {

	public static void main(String[] args) {
		Zoologico zoo = new Zoologico();

		zoo.addAnimal( new Cao("Bobi", 2, 7) );
		zoo.addAnimal( new Cao("Max", 4, 8) );
		zoo.addAnimal( new Cao("Lassie", 35, 6) );
		
		zoo.addAnimal( new Gato("Tareco", 2, 4) );
		zoo.addAnimal( new Gato("Garfield", 30, 8) );
		zoo.addAnimal( new Gato("Bichano", 4, 3) );
		
		zoo.addAnimal( new Pardal("Piupiu", 1, 1) );
		zoo.addAnimal( new Pardal("Zipzip", 1, 3) );
		zoo.addAnimal( new Pardal("Castanho", 1, 1) );

		zoo.addAnimal( new Aguia("Vitória", 3, 5) );
		zoo.addAnimal( new Aguia("America", 6, 7) );
		zoo.addAnimal( new Aguia("Royal", 3, 5) );
		
		zoo.addAnimal( new Leao("Jubas", 5, 150 ));
		zoo.addAnimal( new Leao("Simba", 6, 250 ));
		
		zoo.alimentarBichos();
		zoo.horasDeDormir();
		zoo.passearBichos();
		zoo.ouvirBichos();
	}	
}
