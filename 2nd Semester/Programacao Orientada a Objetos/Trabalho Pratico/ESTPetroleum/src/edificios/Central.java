package edificios;
import java.awt.Point;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import transporte.Camiao;

/** Classe que representa a central de distribuição de combústivel.
 * Deve conter todas as informações, como os camiões, os postos, etc.
 * É ainda responsável por controlar todos os elemenots e operações
 */
public class Central {
	
	/* ------------------------------------------ CONSTANTES ------------------------------------------------ */

	// constantes para os erros que podem surgir durante a realização das operações
	/** Correu tudo bem com a operação */
	public static final int ACEITE = 0;
	/** Usada quando se pretende adicionar um pedido a um posto, mas este não precisa */
	public static final int POSTO_NAO_PRECISA = ACEITE + 1;
	/** Indica que uma quantidade de litros excede a capacidade do posto de a armazenar */
	public static final int EXCEDE_CAPACIDADE_POSTO = POSTO_NAO_PRECISA + 1;
	/** Indica que uma quantidade de litros excede a capacidade do camião de a armazenar */
	public static final int EXCEDE_CAPACIDADE_CAMIAO = EXCEDE_CAPACIDADE_POSTO + 1;
	/** Indica que o camião não pode satisfazer um pedido, pois este iria exceder o tempo de turno */ 
	public static final int EXCEDE_TEMPO_TURNO = EXCEDE_CAPACIDADE_CAMIAO + 1;
	
	/* ------------------------------------------ VARIÁVEIS ------------------------------------------------ */
	private Point localizacao;
	
	private Map<String,Camiao> camioes = new HashMap<String,Camiao>();
	private Map<Integer,Posto> postos = new HashMap<Integer,Posto>();
	/* ----------------------------------------- CONSTRUTOR ------------------------------------------------- */
	public Central(Point localizacao) {
		this.localizacao = localizacao;
	}

	/* ------------------------------------------- MÉTODOS -------------------------------------------------- */
	/* retorna o camião associado a uma matricula
	 * @param matricula matrícula a pesquisar matricula
	 * @return o camião com a matrícula indicada, ou null se não existir
	 */
	public Camiao getCamiao(String matricula ) {
		// TODO ZFEITO fazer este método
		return camioes.get(matricula);
	}
	
	/* retorna o posto que tem um dado id
	 * @param id id a pesquisar
	 * @return o posto com o id, ou null se não existir
	 */
	public Posto getPosto( int id ) {
		// TODO ZFEITO fazer este método
		return postos.get(id);
	}

	// Adicionar um posto ao HashMap
	public void addPosto( Posto p ) {
		postos.put(p.getId(), p);
	}
	
	// Adicionar um camião ao HashMap
	public void addCamiao( Camiao c ) {
		camioes.put(c.getMatricula(), c);
	}
	
	/** processa uma entrega, isto é, associa o pedido ao camião respetivo
	 * @param posto posto onde entregar o combústivel
	 * @param litros litros a entregar
	 * @param camiao camião que irá fazer a entrega
	 * @return ACEITE, o pedido foi adicionado ao camião<br>
	 *         EXCEDE_CAPACIDADE_CAMIAO, se o número de litros for superior
	 *         ao que o camião tem disponível<br>
	 *         EXCEDE_TEMPO_TURNO, se o pedido implicar um tempo maior que um turno
	 *         POSTO_NAO_PRECISA, se o posto não necessita de ser abastecido
	 *         EXCEDE_CAPACIDADE_POSTO, se o posto não tem capacidade de armazenar os litros indicados      
	 */
	public int processarEntrega(Posto posto, int litros, Camiao camiao) {
		
		// TODO ZFEITO fazer este método
		
	    // Criação das variáveis dos resultados
	    int resultadoCamiao = camiao.podeFazerPedido(posto, litros);
	    
	    // Verificar se o camião pode fazer o pedido
	    if (resultadoCamiao != Central.ACEITE) {
	        return resultadoCamiao; 
	    } 
	    
	    // Verifica se o posto pode aceitar o pedido
	    if (!posto.podeEncher(litros)) {
	        return EXCEDE_CAPACIDADE_POSTO;
	    }
	    
	    camiao.addPosto(posto, litros);
	    
 	    return Central.ACEITE;
	}
	
	/** finaliza um turno, isto é, realiza os itinerários e
	 * processa os gastos dos postos 
	 */
	public void finalizarTurno() {
		realizarItinerarios();
		processarGastosPostos();
	}
	
	/** realiza os itinerários, isto é, faz os camiões
	 * transportar o combústivel para os postos adjudicados 
	 */
	private void realizarItinerarios() {
		// TODO ZFEITO fazer este método
		for (Camiao camiao : camioes.values()) {
				camiao.transporta();
		}
	}
	
	// Processa os gastos dos postos
	private void processarGastosPostos() {
		// TODO ZFEITO fazer este método
		for (Posto posto : postos.values()) {
				posto.laborar();
		}
	}

	/*------------------------------------------ GETTERS E SETTERS ---------------------------------------- */
	public Point getLocalizacao() {
		return localizacao;
	}

	public Collection<Camiao> getCamioes(){
		return Collections.unmodifiableCollection( camioes.values() );
	}	
	
	public Collection<Posto> getPostos(){
		return Collections.unmodifiableCollection( postos.values() );
	}	
}

