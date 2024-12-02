package aula05.animal.composicao;

import aula05.animal.anatomia.Faro;

public class Cao2 implements Animal {

	private AnimalSimples animal;
	private Faro faro = new Faro();
	
	public Cao2(String nome, int idade, int tamanho) {
		Corpo c = new CorpoSimples(nome, idade, tamanho);
		Alimentacao a = new AlimentacaoBase();
		Voz v = new Voz() { 
			@Override
			public void falar( Corpo c) {
				System.out.println( c.getNome() + ": AU AU AU");				
			}
		};  
		Movimento m = new MovimentoQuadrupede();
		animal = new AnimalSimples(c, a, v, m);
	}
	

	public void cheirar() {
		// usar faro
	}

	public void dormir() {
		animal.dormir();
	}

	public String getNome() {
		return animal.getNome();
	}

	public int getIdade() {
		return animal.getIdade();
	}

	public int getTamanho() {
		return animal.getTamanho();
	}

	public int getPeso() {
		return animal.getPeso();
	}

	public void comer() {
		animal.comer();
	}

	public void falar() {
		animal.falar();
	}

	public void mover() {
		animal.mover();
	}
}
