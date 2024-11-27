package reserva;

import java.util.Collection;

import estairways.Aeroporto;
import estairways.ClasseConforto;
import estairways.Passageiro;
import estairways.Voo;

public interface Reserva {
	
	/* -------------------------------------------------------------------------------------------------------- VARIÁVEIS ----------------------------------------------------------------------------------------------------- */
	long getCusto();
	void setCusto(long custo);
	String getTipo();
	Voo getVoo();
	Aeroporto getAeroportoOrigem();
	Aeroporto getAeroportoDestino();
	String getIdReserva();
	Collection<Passageiro> getPassageiros();
	void addPassageiro( Passageiro p );
	Passageiro getPassageirosPorNumero(int numero);
	
	/* --------------------------------------------------------------------------------------------------------- MÉTODOS ----------------------------------------------------------------------------------------- */
	// Método que verifica se pode alterar o nome, dependendo das horas antes da partida do avião
	boolean podeAlterarNome( Voo v );
	
	// Método que verifica se pode alterar a data, dependendo das horas antes da partida do avião
	boolean podeAlterarData(Voo v);
	
	// Método que verifica se pode alterar o lugar, dependendo das horas antes da partida do avião
	boolean podeReservarLugar( Voo v );
	
	// Método que verifica se pode cancelar o lugar, dependendo das horas antes da partida do avião
	long cancelarReserva( );
	
	// Método que verifica se pode alterar o número de malas, dependendo do tipo de reserva
	long AlterarMalasCusto(Voo v, Integer numMalas);
	
	// Método para contar o número de bilhetes disponíveis
	int contarBilhetesDisponiveis( ClasseConforto classe, Voo v);
	
	// Método para calcular quanto se gasta ao alterar a data
	long alterarDataCusto();
	
	// Método para calcular quanto se gasta ao alterar o nome
	long alterarNomeCusto();

	// Método para calcular quanto se gasta ao alterar o lugar
	long alterarLugarCusto(Voo v);
}
