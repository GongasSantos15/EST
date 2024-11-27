package reserva;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

import estairways.Aeroporto;
import estairways.ClasseConforto;
import estairways.ESTAirways;
import estairways.Passageiro;
import estairways.Voo;

public class ReservaEconomica extends ReservaDefault {
	
	/* -------------------------------------------------------------------------------------------------------- VARIÁVEIS ----------------------------------------------------------------------------------------------------- */
	public final static int LIMITE_MALAS_PORAO = 2;
	private final int MALAS_GRATUITAS = 0;
	private final int MALAS_PAGAS = LIMITE_MALAS_PORAO - MALAS_GRATUITAS;

	/* -------------------------------------------------------------------------------------------------------- CONSTRUTOR ----------------------------------------------------------------------------------------------------- */

	public ReservaEconomica(String idReserva, String tipo, Voo voo, Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino, long custo, ClasseConforto classe) {
		super(idReserva, tipo, voo, aeroportoOrigem, aeroportoDestino, custo, classe);
	}
	
	/* -------------------------------------------------------------------------------------------------------- MÉTODOS ----------------------------------------------------------------------------------------------------- */
	
	// Método que verifica se o utilizador pode alterar o nome
	@Override
	public boolean podeAlterarNome(Voo v) {
		return false;
	}
	
	// Método que verifica se o utilizador pode alterar o lugar
	@Override
	public boolean podeReservarLugar(Voo v) {
		if (v.adicionarDiaData().isAfter(LocalDate.now())) return true;
		
		// Verifica se o voo é hoje, mas a hora já passou
	    if (v.adicionarDiaData().equals(LocalDate.now()) && v.getHora().isBefore(LocalTime.now())) return false;

	    LocalTime horasAtePartida24 = v.getHora().minusHours(24);
	    return LocalTime.now().isBefore(horasAtePartida24);

	}
	
	// Método que obtém o valor da alteração das malas
	@Override
	public long AlterarMalasCusto(Voo v, Integer numMalas) {
	    // O custo é simplesmente o número de malas multiplicado pelo custo de cada mala
		long custo = 0;
		custo = v.getCustoBagagem() * numMalas;
		return custo;
	}

	
	// Método que obtém o preço da alteração do lugar
	@Override
	public long alterarLugarCusto(Voo v) {
        long horasAtePartida = ChronoUnit.HOURS.between(LocalDateTime.now(), ESTAirways.getDataHoraPartida(v));

        if (horasAtePartida >= 24) {
            return v.getCustoLugar(); // Sem agravamento
        } else if (horasAtePartida >= 3) {
            return (long) (v.getCustoLugar() * 1.5); // Agravamento de 50%
        } else {
            return 0L;
        }
	}

	// Método que verifica se é possível cancelar a reserva
	@Override
	public long cancelarReserva() {
		return 0L;
	}
	
}
