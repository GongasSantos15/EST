package aula04.teste;

import aula04.tempo.original.*;

public class TestaPeriodoOriginal {

	
	public static void main(String[] args) {
		Periodo p1 = new Periodo( new Hora(10,20,30), new Hora(12,30,20) );
		System.out.println( p1 + "  OK");
		
		// tudo bem, mas e este?
		Hora h1 = new Hora( 10, 20, 30 );
		Hora h2 = new Hora( 12, 30, 20 );
		Periodo p2 = new Periodo( h1, h2 );		
		System.out.println( p2 + "  OK");
		
		
		h1.setHoras( 14 );    // como Periodo usa o objecto h1, ao alterar h1 está-se a laterar o periodo!!!
		System.out.println( p2 + "  Hora inicial maior que hora final");
		
		// ou até este?
		Periodo p3 = new Periodo( new Hora(10,20,30), new Hora(12,30,20) );
		System.out.println( p3 + "  OK");
		p3.getFim().setHoras( 8 );  // p3.getFim() retorna a hora do periodo, por isso alterar esta ALTERA o Periodo
		System.out.println( p3 + "  Hora final menor que hora inicial");
		
		// e ainda este?
		Periodo p4 = new Periodo( new Hora(10,20,30), new Hora(12,30,20) );
		System.out.println( p4 + "  OK");
		p4.getInicio().somaHoras( 8 );  // p4.getInicio() retorna a hora do periodo, por isso alterar esta ALTERA o Periodo
		System.out.println( p4 + "  Hora inicial maior que hora final");
	}
}
