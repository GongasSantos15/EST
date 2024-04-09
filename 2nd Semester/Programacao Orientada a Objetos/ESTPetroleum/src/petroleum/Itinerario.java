package petroleum;

import java.awt.Point;
import java.util.ArrayList;

/** Um itinerário é um conjunto de paragens.
 * O itinerário assume que o ponto de início é sempre o mesmo e,
 * no final, deve retornar sempre ao local de início.
 */
public class Itinerario {
	
	/* ------------------------------------------ VARIÁVEIS ------------------------------------------------ */
	private ArrayList<Paragem> paragens;
	private int nLitros;
	private Point x, y;

	/* ----------------------------------------- CONSTRUTOR ------------------------------------------------- */
	
	public Itinerario(ArrayList<Paragem> paragens, int nLitros, Point x, Point y) {
		this.paragens = new ArrayList<Paragem>();
		setnLitros(nLitros);
		this.x = x;
		this.y = y;
	}

	/* ------------------------------------------- MÉTODOS -------------------------------------------------- */
	
	// retorna o ponto de inicio do itenerário -> @return o ponto de inicio do itenerário 
	public Point getInicio() {
		if (!paragens.isEmpty()) {
			// TODO fazer o resto
		}
		return null;
	}

	// limpa o itinerário, isto é, remove todas as paragens do mesmo 
	public void limpar() {
		paragens.clear();
		nLitros = 0;
	}
	
	/* --------------------------------------- GETTERS E SETTERS --------------------------------------------- */
	public ArrayList<Paragem> getParagens() {
		return paragens;
	}

	public void setParagens(ArrayList<Paragem> paragens) {
		this.paragens = paragens;
	}

	public int getnLitros() {
		return nLitros;
	}

	public void setnLitros(int nLitros) {
		this.nLitros = nLitros;
	}

	
	public Point getX() {
		return x;
	}

	
	public Point getY() {
		return y;
	}
	
	
	
}
