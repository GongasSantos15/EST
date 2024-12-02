package aula04.teste;

import aula04.tempo.copiadefensiva.*;

public class TestaPeriodoCopiaDefensiva {

	
	public static void main(String[] args) {
		Periodo p1 = new Periodo( new Hora(10,20,30), new Hora(12,30,20) );
		System.out.println( p1 + "  OK1");
		
		// tudo bem, mas e este?
		Hora h1 = new Hora( 10, 20, 30 );
		Hora h2 = new Hora( 12, 30, 20 );
		Periodo p2 = new Periodo( h1, h2 );		
		System.out.println( p2 + "  OK2");
		
		h1.setHoras( 14 );                   // alterar h1 NÃO altera a hora do Periodo, porque este tem a sua prória hora
		System.out.println( p2 + "  OK2");
		
		// ou até este?
		Periodo p3 = new Periodo( new Hora(10,20,30), new Hora(12,30,20) );
		System.out.println( p3 + "  OK3");
		p3.getFim().setHoras( 8 );           // p3.getFim() retorna uma hora nova e NÃO a hora do Periodo, logo alterar esta não altera o periodo
		System.out.println( p3 + "  OK3");
		
		// e ainda este?
		Periodo p4 = new Periodo( new Hora(10,20,30), new Hora(12,30,20) );
		System.out.println( p4 + "  OK4");
		p4.getInicio().somaHoras( 8 );        // p4.getInicio() retorna uma hora nova, logo alterar esta não altera o periodo
		System.out.println( p4 + "  OK4");

	}
}
