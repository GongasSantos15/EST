package p2.tempo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Horario {
	
	/* --------------------------------------------------- VARIÁVEIS ---------------------------------------------------------- */
	private ArrayList<Periodo> periodos = new ArrayList<Periodo>();
	
	/* --------------------------------------------------- CONSTRUTOR --------------------------------------------------------- */
	// Normalmente quando usamos ArrayList não usamos construtor, porque já foi inicializado acima
	public Horario() {}
	
	/* ----------------------------------------------------- MÉTODOS ---------------------------------------------------------- */
	
	// Adiciona um período ao horário. Quando se adiciona um novo período de tempo ao horário este não fica sobreposto aos restantes, mas são todos rearranjados de modo a refletir
	// a mudança com o menor número de períodos possível
	public void addPeriodo(Periodo novo) {
		
		/* Nas listas usar o ciclo for desta maneira, devido a eficiência e simplicidade: Ao remover o elemento do meio das listas, iria-se shiftar o restante para a esquerda, logo não se iria processar
		 o elemento que passasse para o local do elemento removido, esse elemento nunca iria ser processado. Ao fazer o processo ao contrário (do último para o primeiro), torna-se mais eficiente
			 EX: Do início para o fim: A, B, C, D, E -> remover c
			 	 Ficaria: A, B, D, E -> shift dos restantes para a esquerda
			 	 Ao passar para o elemento seguinte o D nunca iria ser processado, pois ia passar do i = 2, para o i = 3, e após o shift esse i seria o E
		*/
				
		for (int i = periodos.size() - 1; i >= 0; i--) {
			
			Periodo p = periodos.get(i);
			
			// Se interseta, junta-se e remove-se o antigo
			if (novo.interseta(p)) {
				novo.junta(p);
				periodos.remove(i);
			} 
			// Senão verificar a hora de inicio do periodo a inserir, com a hora de fim do periodo já existente -> adicionar a seguir ao período existente
			else if ( novo.getInicio().compareTo(p.getFim()) > 0) {
				periodos.add(i + 1, novo);
				return;
			}
		}
		// Se chegámos ao fim do ciclo for -> periodo é o menor, logo acrescenta-se na posição 0
		periodos.add(0, novo);
	}
	
	// Remove o período com o índice idx do horário
	public void removePeriodo( int idx ){
		periodos.remove(idx);
	}
	
	// Indica se a hora h está dentro do horário
	public boolean estaDentro(Hora h) {
		return true;
	}
	
	/* ----------------------------------------------- GETTERS E SETTERS ------------------------------------------------------ */
	// Nas listas o set() -> add() e remove() e o get() -> Collections.unmodifiableList()
	public List<Periodo> getPeriodos() {
		return Collections.unmodifiableList(periodos);
	}
	
	/* --------------------------------------------------- TOSTRING ----------------------------------------------------------- */
	// Não se usa o ToString, porque para imprimir os períodos usa-se o método getPeriodos()	

}
