package reserva;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

import estairways.Aeroporto;
import estairways.ClasseConforto;
import estairways.ESTAirways;
import estairways.Passageiro;
import estairways.Voo;

public class ReservaExecutive extends ReservaDefault {
	
	/* -------------------------------------------------------------------------------------------------------- VARIÁVEIS ----------------------------------------------------------------------------------------------------- */
	public static final int LIMITE_MALAS_PORAO = 4;
	
	/* -------------------------------------------------------------------------------------------------------- CONSTRUTOR ----------------------------------------------------------------------------------------------------- */
	public ReservaExecutive(String idReserva, String tipo, Voo voo, Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino, long custo, ClasseConforto classe) {
		super(idReserva, tipo, voo, aeroportoOrigem, aeroportoDestino, custo, classe);
	}

	/* -------------------------------------------------------------------------------------------------------- MÉTODOS ----------------------------------------------------------------------------------------------------- */
	
	// Método que obtém o preço da alteração do nome
	public boolean podeAlterarNome(Voo v) {
		if (v.adicionarDiaData().isAfter(LocalDate.now())) return true;
		
	    // Verifica se a data do voo já passou
	    if (v.adicionarDiaData().isBefore(LocalDate.now())) return false;

	    // Verifica se o voo está a menos de 1 hora da partida
	    LocalTime horasAtePartida = v.getHora().minusHours(1);
	    return LocalTime.now().isBefore(horasAtePartida);
	}
	
	// Método que verifica se pode reservar o lugar
	@Override
	public boolean podeReservarLugar(Voo v) {
		if (v.adicionarDiaData().isAfter(LocalDate.now())) return true;
		
		// Verifica se a data do voo já passou
	    if (v.adicionarDiaData().isBefore(LocalDate.now())) return false;

		LocalTime horasAtePartida = v.getHora().minusHours(3);
		return LocalTime.now().isBefore(horasAtePartida);
	}
	
	// Método que verifica se pode alterar a data
	@Override
	public boolean podeAlterarData(Voo v) {
		if (v.adicionarDiaData().isAfter(LocalDate.now())) return true;
		
		// Verifica se a data do voo já passou
	    if (v.adicionarDiaData().isBefore(LocalDate.now())) return false;
		
		LocalTime horasAtePartida = v.getHora().minusHours(3);
		return LocalTime.now().isBefore(horasAtePartida);
	}
	
	// Método que obtém o preço da alteração do nome
	public long alterarNomeCusto() {
		return 0L;
	}
	
	// Método que obtém o preço da alteração das malas
	@Override
	public long AlterarMalasCusto(Voo v, Integer numMalas) {
	    // O custo é simplesmente o número de malas multiplicado pelo custo de cada mala
		long custo = 0;
		custo = v.getCustoBagagem() * numMalas;
		return custo;
	}
	
	// Método que obtém o preço do cancelamento da reserva
	@Override
	public long cancelarReserva() {
		LocalTime horasAtePartida = getVoo().getHora().minusHours(3);
		if (LocalTime.now().isBefore(horasAtePartida));
			return 0L;
	}
	
}
