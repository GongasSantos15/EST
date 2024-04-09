package teste;

import p2.tempo.*;

public class TesteHorario {

	/* --------------------------------------------------- CRIAÇÃO DO HORÁRIO ------------------------------------------ */ 

	public static void main(String[] args) {
		Horario h1 = new Horario();
		
	/* ---------------------------------------------------------- TESTES -------------------------------------------------- */ 
		h1.addPeriodo(new Periodo(new Hora(8, 0, 0), new Hora(12, 0, 0)));
		System.out.println("\n -------------------------------------------------------------------- Horario 1 ----------------------------------------------------------- ");
		System.out.println(h1.getPeriodos());
		
		h1.addPeriodo(new Periodo(new Hora(14, 0, 0), new Hora(20, 0, 0)));
		System.out.println("\n ------------------------------------------------------------------ Horario 1 e 2 --------------------------------------------------------- ");
		System.out.println(h1.getPeriodos());
		
		h1.addPeriodo(new Periodo(new Hora(6, 0, 0), new Hora(7, 59, 59)));
		System.out.println("\n ----------------------------------------------------------------- Horario 1, 2 e 3 ------------------------------------------------------- ");
		System.out.println(h1.getPeriodos());
		
		h1.addPeriodo(new Periodo(new Hora(12, 0, 1), new Hora(13, 59, 58)));
		System.out.println("\n ---------------------------------------------------------------- Horario 1, 2, 3 e 4 ------------------------------------------------------ ");
		System.out.println(h1.getPeriodos());
	}

}
