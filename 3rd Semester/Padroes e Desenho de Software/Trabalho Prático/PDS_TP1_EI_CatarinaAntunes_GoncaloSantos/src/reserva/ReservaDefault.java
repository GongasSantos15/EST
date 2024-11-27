package reserva;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import estairways.Aeroporto;
import estairways.ClasseConforto;
import estairways.ESTAirways;
import estairways.Passageiro;
import estairways.Voo;

public abstract class ReservaDefault implements Reserva {
	
	/*--------------------------------------------------------------------- VARIÁVEIS ---------------------------------------------------------------------*/
	private String tipo;
	private String idReserva;
	private Voo voo;
	private Aeroporto aeroportoOrigem;
	private Aeroporto aeroportoDestino;
	public HashMap<Integer, Passageiro> passageiros = new HashMap<Integer, Passageiro>();	
	private long custo;
	private ClasseConforto classe;
	
	/*--------------------------------------------------------------------- CONSTRUTOR --------------------------------------------------------------------*/
	public ReservaDefault(String idReserva, String tipo, Voo voo, Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino, long custo, ClasseConforto classe) {
		this.idReserva = idReserva;
		this.tipo = tipo;
		this.voo = voo;
		this.aeroportoOrigem = aeroportoOrigem;
		this.aeroportoDestino = aeroportoDestino;
		this.custo = custo;
		this.classe = classe;
	}
	/*------------------------------------------------------------------ GETTERS E SETTERS ----------------------------------------------------------------*/
	public long getCusto() {
		return custo;
	}

	public void setCusto(long custo) {
		this.custo = custo;
	}

	public String getTipo() {
		return tipo;
	}

	public Voo getVoo() {
		return voo;
	}
	
	public Aeroporto getAeroportoOrigem() {
		return aeroportoOrigem;
	}
	
	public Aeroporto getAeroportoDestino() {
		return aeroportoDestino;
	}

	public String getIdReserva() {
		return idReserva;
	}
	
	public Collection<Passageiro> getPassageiros() {
		return Collections.unmodifiableCollection(passageiros.values());
	}
	
	public void addPassageiro( Passageiro p ) {
		passageiros.put(p.getNumero(), p);
	}
	
	public Passageiro getPassageirosPorNumero(int numero) {
		if (passageiros.containsKey(numero)) {
		    return passageiros.get(numero);
		}
		return null;
	}

		public ClasseConforto getClasse() {
			return classe;
		}	

	/*--------------------------------------------------------------------------------------------------- MÉTODOS ---------------------------------------------------------------------*/
	
	// Método para contar os bilhetes disponíveis para cada classe
	public int contarBilhetesDisponiveis( ClasseConforto classe, Voo v) {
		int bilhetesDisponiveis = 0;
		
		if (classe == ClasseConforto.STANDARD) {
			bilhetesDisponiveis = v.getValoresClasseStandard().size();
		} else if (classe == ClasseConforto.COMFORT) {
			bilhetesDisponiveis = v.getValoresClasseComfort().size();
		} else {
			bilhetesDisponiveis = v.getValoresClasseDeluxe().size();
		}
		
		return bilhetesDisponiveis;	
		
	}

	// Método que verifica se o utilizador pode alterar o nome
	@Override
	public boolean podeAlterarNome(Voo v) {
		if (v.adicionarDiaData().isAfter(LocalDate.now())) return true;

	    // Verifica se a data do voo já passou
	    if (v.adicionarDiaData().isBefore(LocalDate.now())) return false;

	    // Verifica se o voo está a menos de 3 horas da partida
	    LocalTime horasAtePartida = v.getHora().minusHours(3);
	    return LocalTime.now().isBefore(horasAtePartida);
	}

	// Método que verifica se o utilizador pode alterar a data
	public boolean podeAlterarData(Voo v) {
		return false;
	}
	
	// Método que verifica se o utilizador pode alterar o lugar
	public boolean podeReservarLugar( Voo v ) {
		return false;
	}
	
	// Método que verifica se o utilizador pode cancelar a reserva e obter o preço da mesma
	@Override
	public long cancelarReserva() {
		
		long reembolsoTaxas = getAeroportoOrigem().getTaxaAeroportuaria() + getAeroportoDestino().getTaxaAeroportuaria();
		long precoBilhete = ESTAirways.custoTotalReserva(getVoo().getCodigoVoo(), getVoo().getAeroportoOrigem(), getVoo().getAeroportoDestino(), classe, classe.getReservaAssociadas()[0]);
		long reembolsoBilhete = 0;

	    // Verifica se o voo está a menos de 5 horas da partida
	    LocalTime horasAtePartida = getVoo().getHora().minusHours(5);
	    
	    if (LocalTime.now().isBefore(horasAtePartida)) {
	    	reembolsoBilhete = precoBilhete / 2;  // Metade do preço do bilhete
	    }
		
	    return reembolsoTaxas + reembolsoBilhete;
	}
	
	// Método que obtém o preço da alteração da data
	public long alterarDataCusto() {
		return 0L;
	}
	
	// Método que obtém o preço da alteração do nome
	public long alterarNomeCusto() {
		return getAeroportoOrigem().getTaxaAlteracoes();
	}
	
	// Método que obtém o preço da alteração do lugar
	public long alterarLugarCusto(Voo v) {
		return 0L;
	}
	
	/*--------------------------------------------------------------------------------------------------- TOSTRING ---------------------------------------------------------------------*/
	@Override
	public String toString() {
		return "ReservaDefault [tipo=" + tipo + ", idReserva=" + idReserva + ", voo=" + voo + ", aeroportoOrigem="
				+ aeroportoOrigem + ", aeroportoDestino=" + aeroportoDestino + ", passageiros=" + passageiros
				+ ", custo=" + custo + "]";
	}

}
