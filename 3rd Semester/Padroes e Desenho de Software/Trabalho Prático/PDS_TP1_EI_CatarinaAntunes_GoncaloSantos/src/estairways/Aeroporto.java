package estairways;

import java.util.HashMap;
import java.util.Map;

public class Aeroporto {
	
	/* -------------------------------------------------------------------------------------------------------- VARIÁVEIS ----------------------------------------------------------------------------------------------------- */
	private String codigoAeroporto;
	private String nomeAeroporto;
	private int taxaAeroportuaria;
	private int taxaAlteracoes;
	private static HashMap<String, String> abreviaturas = new HashMap<>(); // Adicionar ao Mapa de abreviaturas o nome e a respetiva abreviatura
	
	/* ------------------------------------------------------------------------------------------------------- CONSTRUTOR ----------------------------------------------------------------------------------------------------- */
	public Aeroporto(String codigoAeroporto, String nomeAeroporto, int taxaAeroportuaria, int taxaAlteracoes) {
		super();
		this.codigoAeroporto = codigoAeroporto;
		this.nomeAeroporto = nomeAeroporto;
		this.taxaAeroportuaria = taxaAeroportuaria;
		this.taxaAlteracoes = taxaAlteracoes;
	}

	/* ---------------------------------------------------------------------------------------------------- GETTERS E SETTERS ------------------------------------------------------------------------------------------------- */
	public String getNomeAeroporto() {
		return nomeAeroporto;
	}

	public int getTaxaAeroportuaria() {
		return taxaAeroportuaria;
	}

	public int getTaxaAlteracoes() {
		return taxaAlteracoes;
	}

	public String getCodigoAeroporto() {
		return codigoAeroporto;
	}
	
	public void addAbreviatura() {
		abreviaturas.put(codigoAeroporto, nomeAeroporto);
	}
	
	public static String getNomeCompleto( String abreviatura) {
		return abreviaturas.get(abreviatura);
	}

	/* -------------------------------------------------------------------------------------------------------- TO STRING ------------------------------------------------------------------------------------------------------ */
	@Override
	public String toString() {
		return "Aeroporto [codigoAeroporto = " + codigoAeroporto + ", nomeAeroporto = " + nomeAeroporto
				+ ", taxaAeroportuária = " + taxaAeroportuaria + ", taxaAlteracoes = " + taxaAlteracoes + "]";
	}
	
	
	
	
	
	
	
}
