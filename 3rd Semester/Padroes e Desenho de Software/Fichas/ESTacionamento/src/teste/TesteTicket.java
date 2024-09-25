package teste;

/* ---------------------------------------------------------------------------- IMPORTS -------------------------------------------------------------------------------- */

import java.time.LocalTime;
import estacionamento.Ticket;

public class TesteTicket {

	public static void main(String[] args) {
		
		/* ---------------------------------------------------------------- CRIAÇÃO DOS OBJETOS  ----------------------------------------------------------------------- */
		
		System.out.println("---------------------------------------------- OBJETOS ----------------------------------------------- ");
		
		// Criação de um ticket com matricula, sem contrato e com a hora de entrada 10:20:30
		Ticket t1 = new Ticket("AA11BB", null, LocalTime.of(10, 20, 30));
		System.out.println( t1 );
		
		// Criação de um ticket com matricula, sem contrato e com a hora de entrada atual
		Ticket t2 = new Ticket("AA22BB", null);
		System.out.println( t2 );
		System.out.println();
		
		/* ------------------------------------------------------------------- TESTE MÉTODOS  -------------------------------------------------------------------------- */
		System.out.println("----------------------------------------- setPagamento() ---------------------------------------------");
		t1.setPagamento( LocalTime.of(12, 20, 23) );
		System.out.println(t1);
		System.out.println();
		
		System.out.println("------------------------------------------- setSaida() -----------------------------------------------");
		t1.setSaida( LocalTime.of(12, 50, 23) );
		System.out.println(t1);
		System.out.println();
		
		System.out.println("------------------------------------------- setCusto() -----------------------------------------------");
		t1.setCusto( 10 );
		System.out.println(t1);
		System.out.println();
		
		System.out.println("---------------------------------------- tempoAposEntrada() ------------------------------------------");
		System.out.println(t1.tempoAposEntrada());
		System.out.println();
		
		System.out.println("---------------------------------------- tempoAposPagamento() ------------------------------------------");
		System.out.println(t1.tempoAposPagamento());
		System.out.println();
	}

}
