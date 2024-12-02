package aula05.animal.heranca;

import aula05.animal.anatomia.Garra;

public class Aguia extends Ave {

	private Garra garras[] = new Garra[ 8 ];
	{
		for( int i=0; i < garras.length; i++)
			garras[ i ] = new Garra();
	}
	
	public Aguia(String nome, int idade, int tamanho) {
		super(nome, idade, tamanho);
	}	
	
	public void piar() {
		System.out.println( getNome() + ": email email");
	}
	
	@Override
	public void falar() {
		piar();
	}
}
