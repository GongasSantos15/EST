package petroleum;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Um itinerário é um conjunto de paragens.
 * O itinerário assume que o ponto de início é sempre o mesmo e,
 * no final, deve retornar sempre ao local de início.
 */
public class Itinerario {
	
	/* ------------------------------------------ VARIÁVEIS ------------------------------------------------ */
	private ArrayList<Paragem> paragens = new ArrayList<Paragem>();
	private int nLitros;
	private Point pontoInicial;

	/* ----------------------------------------- CONSTRUTOR ------------------------------------------------- */
	
	public Itinerario(int nLitros, Point pontoInicial) {
		setnLitros(nLitros);
		this.pontoInicial = pontoInicial;
	}

	/* ------------------------------------------- MÉTODOS -------------------------------------------------- */
	
	// retorna o ponto de inicio do itenerário -> @return o ponto de inicio do itenerário 
	public Point getInicio() {
		if (!paragens.isEmpty()) {
			// TODO ZFEITO Retornar a pontoInicial do posto da primeira paragem do ArrayList paragens
			return paragens.get(0).getPosto().getCoordenada();
		}
		return null;
	}
	
	
	
	// retorna o fim do itenerário -> @return o ponto terminal do itenerário 
	public Point getFim() {
		if (!paragens.isEmpty()) {
			// TODO ZFEITO Retornar a pontoInicial do posto da ultima paragem do ArrayList paragens
			return paragens.get(paragens.size() - 1).getPosto().getCoordenada();
		}
		return null;
	}

	// limpa o itinerário, isto é, remove todas as paragens do mesmo 
	public void limpar() {
		paragens.clear();
		nLitros = 0;
	}
	
	public void addParagem(Paragem p) {
		paragens.add(p);
	}
	
	public void removeParagem(Paragem p) {
		paragens.remove(p);
	}
	
	/* --------------------------------------- GETTERS E SETTERS --------------------------------------------- */
	public List<Paragem> getParagens() {
		return Collections.unmodifiableList(paragens);
	}

	public int getnLitros() {
		return nLitros;
	}

	public void setnLitros(int nLitros) {
		this.nLitros = nLitros;
	}
	
	public Point getPontoInicial() {
		return pontoInicial;
	}

	
	
	
}
