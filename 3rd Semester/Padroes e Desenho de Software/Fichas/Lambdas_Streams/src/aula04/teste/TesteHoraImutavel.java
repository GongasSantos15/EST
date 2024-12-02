package aula04.teste;

import aula04.tempo.immutable.Hora;

public class TesteHoraImutavel {

	public static void main(String[] args) {
		Hora h1 = new Hora( 10, 20, 30 );
		System.out.println( h1 );

		Hora h2 = h1.somaHoras( 10 );
		System.out.println( h1 + " + 10 horas =  " + h2);
	}

}
