package teste;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import estacionamento.Ticket;

public class TestaTicket {

	public static void main(String[] args) {
		Ticket t1 = new Ticket( "AA11AA", null, LocalDateTime.of( LocalDate.now(), LocalTime.of( 10, 20, 30)) );
		System.out.println( t1 );
		
		Ticket t2 = new Ticket( "AA22AA", null );
		System.out.println( t2 );
		
		t1.setPagamento( LocalDateTime.of( LocalDate.now(), LocalTime.of( 12, 20, 23)) );
		System.out.println( t1 );
		
		t1.setSaida( LocalDateTime.of( LocalDate.now(), LocalTime.of(12, 50, 23)) );
		System.out.println( t1 );
		
		t1.setCusto( 10 );
		System.out.println( t1 );
		
		System.out.println( t1.tempoAposEntrada() );
		System.out.println( t1.tempoAposPagamento() );
		
	}

}
