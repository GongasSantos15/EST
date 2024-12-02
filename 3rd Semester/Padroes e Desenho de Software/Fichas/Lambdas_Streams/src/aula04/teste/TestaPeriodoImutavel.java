package aula04.teste;

import aula04.tempo.immutable.*;

public class TestaPeriodoImutavel {

	
	public static void main(String[] args) {
		Periodo p1 = new Periodo( new Hora(10,20,30), new Hora(12,30,20) );
		System.out.println( p1 + "  OK");
		
		// tudo bem, mas e este?
		Hora h1 = new Hora( 10, 20, 30 );
		Hora h2 = new Hora( 12, 30, 20 );
		Periodo p2 = new Periodo( h1, h2 );		
		System.out.println( p2 + "  OK");
		
		h1.somaHoras( 10 );               // o somaHoras retorna uma nova hora, NÃO altera a h1
		System.out.println( p2 + "  OK");
		
		// ou até este?
		Periodo p3 = new Periodo( new Hora(10,20,30), new Hora(12,30,20) );
		System.out.println( p3 + "  OK");
		//p3.getFim().setHoras( 8 );      // agora Hora não tem setters, por isso não pode ser alterada
		System.out.println( p3 + "  OK");
		
		// e ainda este?
		Periodo p4 = new Periodo( new Hora(10,20,30), new Hora(12,30,20) );
		System.out.println( p4 + "  OK");  
		p4.getInicio().somaHoras( 8 );    // o somaHoras retorna uma nova hora, NÃO altera a hora do periodo
		System.out.println( p4 + "  OK");
	}
}
