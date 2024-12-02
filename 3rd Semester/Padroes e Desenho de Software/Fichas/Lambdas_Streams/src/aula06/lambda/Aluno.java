package aula06.lambda;

import java.util.Comparator;

public class Aluno {
	private int numero;
	private String nome;
	private int notaFinal;
	private float notaPratica;
	private float notaTeorica;
	private float mediaFinal;
	
	
	
	public Aluno(int numero, String nome, float notaTeorica, float notaPratica) {
		this.numero = numero;
		this.nome = nome;
		this.notaPratica = notaPratica;
		this.notaTeorica = notaTeorica;
		calcularMediaFinal( );
	}

	private void calcularMediaFinal() {
		mediaFinal = notaTeorica*0.4f + notaPratica*0.6f;
		notaFinal = (int)(mediaFinal+0.5f);
	}

	public String getNome() {
		return nome;
	}

	public int getNotaFinal() {
		return notaFinal;
	}

	public float getNotaPratica() {
		return notaPratica;
	}

	public float getNotaTeorica() {
		return notaTeorica;
	}

	public int getNumero() {
		return numero;
	}
	
	public float getMediaFinal() {
		return mediaFinal;
	}
	
	
	public boolean estaAprovado( ) {
		return notaFinal >= 10;
	}
	
	public void estudar() {
		System.out.println( nome + " a estudar" );
		notaTeorica += 0.5;
		calcularMediaFinal();
	}
}
