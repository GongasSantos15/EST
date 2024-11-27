package estairways;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import reserva.Reserva;
import reserva.ReservaDefault;

/** Classe que tem as constantes para os tipos de reserva, bem como
 * algumas outras constantes (podiam estar num ficheiro, mas já há ficheiros
 * que cheguem :-)   
 */
public class ESTAirways {
	public static final String ECONOMICA = "Económica";
	public static final String BASIC = "Basic";
	public static final String BUSINESS = "Business";
	public static final String EXECUTIVE = "Executive";
	public static final int MAX_PASSAGEIROS_RESERVA = 8;

	
	// Criação dos mapas dos aeroportos, voos e reservas
	private static HashMap<String,Aeroporto> aeroportos = new HashMap<String, Aeroporto>();
	private static HashMap<String,Voo> voos = new HashMap<String, Voo>();
	private HashMap<String,Reserva> reservas = new HashMap<String, Reserva>();	
		
	/* -------------------------------------------------------------------------------------------------------- MÉTODOS AEROPORTO --------------------------------------------------------------------------------------------- */	
	public void addAeroporto( Aeroporto a ){		
		aeroportos.put( a.getCodigoAeroporto(), a );
	}
	
	public Collection<Aeroporto> getAeroportos() {
        return Collections.unmodifiableCollection(aeroportos.values());
    }
	
	public Aeroporto getAeroportoPorCodigo(String codigoVoo) {
		return aeroportos.get(codigoVoo);
	}
	
	/* ------------------------------------------------------------------------------------------------------------ MÉTODOS VOO ----------------------------------------------------------------------------------------------- */	
	public void addVoo( Voo v ) {
		voos.put(v.getCodigoVoo(), v);
	}
	
	public void removeVoo(Voo v) {
		voos.remove(v.getCodigoVoo());
	}
	
	public Collection<Voo> getVoos() {
		return Collections.unmodifiableCollection(voos.values());
	}
	
	public static Voo getVooPorCodigo(String codigo) {
	    return voos.get(codigo);
	}
	
	/* ----------------------------------------------------------------------------------------------------------- MÉTODOS RESERVA -------------------------------------------------------------------------------------------- */	
	public void addReserva( Reserva r ) {
		reservas.put(r.getIdReserva(), r);
	}
	
	public void removeReserva( Reserva r ) {
		reservas.remove(r.getIdReserva(), r);
	}
	
	public Collection<Reserva> getReservas() {
		return Collections.unmodifiableCollection(reservas.values());
	}
	
	/** método que gera o id da reserva 
	 * @return um código de 6 caracteres para a reserva
	 */
	public static String gerarReservaId() {
		return GeradorCodigos.gerarCodigo( 6 );
	}
	
	public Reserva getReservaPorId(String id) {
		return reservas.get(id);
	}
	
	public static long custoTotalReserva( String codigoVoo, String origem, String destino, ClasseConforto classe, String tipoReserva) {
		
		// Variável para guardar as taxas de cada aeroporto somadas
		long taxas = aeroportos.get(origem).getTaxaAeroportuaria() + aeroportos.get(destino).getTaxaAeroportuaria();
		
		// Inicializar a variável precoVoo a 0
		long precoVoo = 0L;
		
		// Obter o precoVoo correspondente a cada classe
		if (classe == ClasseConforto.STANDARD)
			precoVoo = getVooPorCodigo(codigoVoo).precoClasseStandard();
		if (classe == ClasseConforto.COMFORT)
			precoVoo = getVooPorCodigo(codigoVoo).precoClasseConfort();
		if (classe == ClasseConforto.DELUXE)
			precoVoo = getVooPorCodigo(codigoVoo).precoClasseDeluxe();
				
		// Para o tipo de reserva igua a BASIC o precoVoo tem um aumento de 30%
		if (tipoReserva.equals(ESTAirways.BASIC)) {
			precoVoo = (long) (precoVoo * 1.3);
		}
		
		// Retornar os precoVoo juntamento com as taxas 
		return precoVoo + taxas;

	}
	
	/* ------------------------------------------------------------------------------------------------------------- MÉTODOS GERAIS ------------------------------------------------------------------------------------------- */	

	// Método para juntar a data e a hora obtida do voo
	public static LocalDateTime getDataHoraPartida(Voo v) {
		LocalDate data = v.adicionarDiaData();
		LocalTime hora = v.getHora();
	 
		return LocalDateTime.of(data, hora);
}

}
