package reserva;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

import estairways.Aeroporto;
import estairways.ClasseConforto;
import estairways.Passageiro;
import estairways.Voo;

public class ReservaBasic extends ReservaDefault {

	/* -------------------------------------------------------------------------------------------------------- VARIÁVEIS ----------------------------------------------------------------------------------------------------- */
	public final static int LIMITE_MALAS_PORAO = 3;
	
	/* -------------------------------------------------------------------------------------------------------- COSNTRUTOR ----------------------------------------------------------------------------------------------------- */

	public ReservaBasic(String idReserva, String tipo, Voo voo, Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino, long custo, ClasseConforto classe) {
		super(idReserva, tipo, voo, aeroportoOrigem, aeroportoDestino, custo, classe);
	}	
	
	/* -------------------------------------------------------------------------------------------------------- VARIÁVEIS ----------------------------------------------------------------------------------------------------- */
	// Método obtém o valor da alteração de malas
	@Override
	public long AlterarMalasCusto(Voo v, Integer numMalas) {
	    // O custo é simplesmente o número de malas multiplicado pelo custo de cada mala
		long custo = 0;
		custo = v.getCustoBagagem() * numMalas;
		return custo;
	}

	// Método que verifica se o utilizador pode alterar o lugar
	@Override
	public boolean podeReservarLugar(Voo v) {
		if (v.adicionarDiaData().isAfter(LocalDate.now())) return true;
		
		// Verifica se a data do voo já passou
	    if (v.adicionarDiaData().isBefore(LocalDate.now())) return false;


		LocalTime horasAtePartida = v.getHora().minusHours(3);
		return LocalTime.now().isBefore(horasAtePartida);
	}
	
}
