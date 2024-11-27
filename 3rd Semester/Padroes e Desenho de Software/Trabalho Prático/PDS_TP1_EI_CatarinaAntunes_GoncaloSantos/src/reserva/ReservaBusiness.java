package reserva;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

import estairways.Aeroporto;
import estairways.ClasseConforto;
import estairways.Passageiro;
import estairways.Voo;

public class ReservaBusiness extends ReservaDefault {
	
	/* -------------------------------------------------------------------------------------------------------- VARIÁVEIS ----------------------------------------------------------------------------------------------------- */
	public static final int LIMITE_MALAS_PORAO = 3;
	
	/* -------------------------------------------------------------------------------------------------------- CONSTRUTOR ----------------------------------------------------------------------------------------------------- */
	public ReservaBusiness(String idReserva, String tipo, Voo voo, Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino, long custo, ClasseConforto classe) {
		super(idReserva, tipo, voo, aeroportoOrigem, aeroportoDestino, custo, classe);
	}
	
	/* -------------------------------------------------------------------------------------------------------- MÉTODOS ----------------------------------------------------------------------------------------------------- */
	
	// Método que verfiica se é possível alterar a data
	@Override
	public boolean podeAlterarData(Voo v) {
		if (v.adicionarDiaData().isAfter(LocalDate.now())) return true;
		
		// Verifica se a data do voo já passou
	    if (v.adicionarDiaData().isBefore(LocalDate.now())) return false;
		
	    // Verifica se o voo está a menos de 3 horas da partida
		LocalTime horasAtePartida = v.getHora().minusHours(3);
		return LocalTime.now().isBefore(horasAtePartida);
	}
	
	// Método que verifica se é possível reservar o lugar
	@Override
	public boolean podeReservarLugar(Voo v) {
		if (v.adicionarDiaData().isAfter(LocalDate.now())) return true;
		
		// Verifica se a data do voo já passou
	    if (v.adicionarDiaData().isBefore(LocalDate.now())) return false;

		LocalTime horasAtePartida = v.getHora().minusHours(3);
		return LocalTime.now().isBefore(horasAtePartida);
	}
	
	// Método que obtém o preço da alteração da data
	public long alterarDataCusto() {
		return getAeroportoOrigem().getTaxaAlteracoes() + getAeroportoDestino().getTaxaAlteracoes();
	}
	
	// Método que obtém o preço da alteração das malas
	@Override
	public long AlterarMalasCusto(Voo v, Integer numMalas) {
	    // O custo é simplesmente o número de malas multiplicado pelo custo de cada mala
		long custo = 0;
		custo = v.getCustoBagagem() * numMalas;
		return custo;
	}
}
