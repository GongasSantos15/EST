package viagem;

import edificios.Posto;

/** Uma paragem do itinerário deve indicar
 * o posto onde se deve parar e quantos litros
 * devem ser transferidos para o posto  
 */
public class Paragem {
	
	/* ------------------------------------------ VARIÁVEIS ------------------------------------------------ */
	private Posto posto;
	private int nLitros;
	
	/* ----------------------------------------- CONSTRUTOR ------------------------------------------------- */
	public Paragem(Posto posto, int nLitros) {
		this.posto = posto;
		setNLitros(nLitros);
	}
	
	/* --------------------------------------- GETTERS E SETTERS --------------------------------------------- */
	public int getNLitros() {
		return nLitros;
	}
	
	public void setNLitros(int nLitros) {
		this.nLitros = nLitros;
	}
	
	public Posto getPosto() {
		return posto;
	}	
	
}
