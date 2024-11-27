package estairways;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Voo {
	
	/* -------------------------------------------------------------------------------------------------------- VARIÁVEIS ----------------------------------------------------------------------------------------------------- */
	private String codigoVoo;
	private String aeroportoOrigem;
	private String aeroportoDestino;
	private int data;
	private LocalTime hora;
	private int custoLugar;
	private int custoBagagem;
	private ArrayList<Long> valoresClasseDeluxe;
	private ArrayList<Long> valoresClasseComfort;
	private ArrayList<Long> valoresClasseStandard;
	private ClasseConforto classeConforto;
	private Passageiro passageiro;
	
	/* ------------------------------------------------------------------------------------------------------- CONSTRUTOR ----------------------------------------------------------------------------------------------------- */
	public Voo(String codigoVoo, String aeroportoOrigem, String aeroportoDestino, int data, LocalTime hora, int custoLugar, int custoBagagem, ArrayList<Long> valoresClasseStandard, ArrayList<Long> valoresClasseComfort, ArrayList<Long> valoresClasseDeluxe) {
		this.codigoVoo = codigoVoo;
		this.aeroportoOrigem = aeroportoOrigem;
		this.aeroportoDestino = aeroportoDestino;
		this.data = data;
		this.hora = hora;
		this.custoLugar = custoLugar;
		this.custoBagagem = custoBagagem;
		this.valoresClasseDeluxe = valoresClasseDeluxe;
        this.valoresClasseComfort = valoresClasseComfort;
        this.valoresClasseStandard = valoresClasseStandard;
	}

	/* ---------------------------------------------------------------------------------------------------- GETTERS E SETTERS ------------------------------------------------------------------------------------------------- */
	public String getAeroportoOrigem() {
		return aeroportoOrigem;
	}

	public String getAeroportoDestino() {
		return aeroportoDestino;
	}

	public int getData() {
		return data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public int getCustoLugar() {
		return custoLugar;
	}

	public int getCustoBagagem() {
		return custoBagagem;
	}

	public String getCodigoVoo() {
		return codigoVoo;
	}
	
	public ArrayList<Long> getValoresClasseDeluxe() {
		return valoresClasseDeluxe;
	}

	public ArrayList<Long> getValoresClasseComfort() {
		return valoresClasseComfort;
	}

	public ArrayList<Long> getValoresClasseStandard() {
		return valoresClasseStandard;
	}
	
	public ClasseConforto getClasseConforto() {
		return classeConforto;
	}
	
	public Passageiro getPassageiro() {
		return passageiro;
	}
	
	/* -------------------------------------------------------------------------------------------------------- MÉTODOS ------------------------------------------------------------------------------------------------------ */
	
	// Método para obter o preco dependendo de cada classe
	public long precoClasses(ClasseConforto classe) {
		long preco = 0L;
		
		if (classe == ClasseConforto.STANDARD) 
			preco = getValoresClasseStandard().get(valoresClasseStandard.size() - 1);
		else if (classe == ClasseConforto.COMFORT)
			preco = getValoresClasseComfort().get(valoresClasseComfort.size() - 1);
		else
			preco = getValoresClasseDeluxe().get(valoresClasseDeluxe.size() - 1);
		
		return preco;
	}
	
	// Método para obter os precos da classe STANDARD
	public long precoClasseStandard() {
		return getValoresClasseStandard().get(valoresClasseStandard.size() - 1);
	}
	
	// Método para obter os precos da classe COMFORT
	public long precoClasseConfort() {
		return getValoresClasseComfort().get(valoresClasseComfort.size() - 1);
	}
	
	// Método para obter os precos da classe DELUXE
	public long precoClasseDeluxe() {
		return getValoresClasseDeluxe().get(valoresClasseDeluxe.size() - 1);
	}
	
	// Método para adicionar ao dia atual o dia obtido pelo ficheiro do voo (que é inteiro)
	public LocalDate adicionarDiaData() {
		return LocalDate.now().plusDays(getData());
	}
	
	// Método para obter quantos preços existem na classe DELUXE
	public int getNValoresClasseDeluxe() {
		return valoresClasseDeluxe.size();
	}
	
	// Método para obter quantos preços existem na classe COMFORT
	public int getNValoresClasseComfort() {
		return valoresClasseComfort.size();
	}
	
	// Método para obter quantos preços existem na classe STANDARD
	public int getNValoresClasseStandard() {
		return valoresClasseStandard.size();
	}

	/* -------------------------------------------------------------------------------------------------------- TO STRING ------------------------------------------------------------------------------------------------------ */
	@Override
	public String toString() {
		return "Voo [codigoVoo = " + codigoVoo + ", aeroportoOrigem = " + aeroportoOrigem + ", aeroportoDestino = "
				+ aeroportoDestino + ", data = " + data + ", hora = " + hora + ", custoLugar = " + custoLugar
				+ ", custoBagagem = " + custoBagagem + "]";
	}
	
}
