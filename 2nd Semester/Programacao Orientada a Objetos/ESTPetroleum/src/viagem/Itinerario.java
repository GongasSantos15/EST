package viagem;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edificios.Posto;

/** Um itinerário é um conjunto de paragens.
 * O itinerário assume que o ponto de início é sempre o mesmo e,
 * no final, deve retornar sempre ao local de início.
 */
public class Itinerario {
	
	/* ------------------------------------------ VARIÁVEIS ------------------------------------------------ */
	private ArrayList<Paragem> paragens = new ArrayList<Paragem>();
	private Point pontoInicial;

	/* ----------------------------------------- CONSTRUTOR ------------------------------------------------- */
	
	public Itinerario(Point pontoInicial) {
		this.pontoInicial = pontoInicial;
	}

	/* ------------------------------------------- MÉTODOS -------------------------------------------------- */
	
	// retorna o ponto de inicio do itenerário -> @return o ponto de inicio do itenerário 
	public Point getInicio() {
			// TODO ZFEITO Retornar o pontoInicial do posto da primeira paragem do ArrayList paragens
			return pontoInicial;
	}	
	
	// retorna o fim do itenerário -> @return o ponto terminal do itenerário 
	public Point getFim() {
		if (!paragens.isEmpty()) {
			// TODO ZFEITO Retornar a pontoInicial do posto da ultima paragem do ArrayList paragens
			return paragens.get(paragens.size() - 1).getPosto().getPosicao();
		}
		return getInicio();
	}

	// limpa o itinerário, isto é, remove todas as paragens do mesmo 
	public void limpar() {
		paragens.clear();
	}
	
	public void addParagem(Paragem p) {
			paragens.add(p);
	}
	
	public void removeParagem(Paragem p) {
		paragens.remove(p);
	}
	
	public boolean postoExiste( Posto p) {
		for (Paragem paragem : paragens) {
			if (paragem.getPosto() == p) {
				return true;
			}

		}
		return false;
	}
	
	/* --------------------------------------- GETTERS E SETTERS --------------------------------------------- */
	public List<Paragem> getParagens() {
		return Collections.unmodifiableList(paragens);
	}
	
	public Point getPontoInicial() {
		return pontoInicial;
	}

	
	
	
}
