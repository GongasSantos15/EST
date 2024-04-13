package petroleum;
import java.awt.Point;

import petroleum.Posto;

/** Classe que representa a central de distribuição de combústivel.
 * Deve conter todas as informações, como os camiões, os postos, etc.
 * É ainda responsável por controlar todos os elemenots e operações
 */
public class Central {
	// constantes para os erros que podem surgir udurante a realização das operações
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

	/** retorna o camião associado a uma matricula
	 * @param matricula matrícula a pesquisar matricula
	 * @return o camião com a matrícula indicada, ou null se não existir
	 */
	public Camiao getCamiao(String matricula ) {
		// TODO fazer este método
		return null;
	}
	
	/** retorna o posto que tem um dado id
	 * @param id id a pesquisar
	 * @return o posto com o id, ou null se não existir
	 */
	public Posto getPosto( int id ) {
		// TODO fazer este método
		return null;
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
		// TODO fazer este método
		return ACEITE;
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
		// TODO fazer este método
	}
	
	/** processa os gastos dos postos
	 */
	private void processarGastosPostos() {
		// TODO fazer este método
	}
	

	
	
	
	/* ----------------------------------------- Postos ------------------------------------------------- */
		Point minastirith = new Point(1400,990);
		Posto MinasTirith = new Posto(1,minastirith, 2200, 10000, 40000);
		
		Point isengard = new Point(925,710);
		Posto Isengard = new Posto(2, isengard, 3200, 7000, 30000);
		
		Point dolguldur = new Point(1235,510);
		Posto DolGuldur = new Posto(3,dolguldur, 2300, 17000, 30000);
		
		Point rivendell = new Point(890,310);
		Posto Rivendell = new Posto(4,rivendell, 800, 15000, 20000);
		
		Point hobbiton = new Point(455,335);
		Posto Hobbiton = new Posto(5,hobbiton, 1300, 25000, 30000);
		
		Point edoras = new Point(1035,800);
		Posto Edoras = new Posto(6,edoras, 1300, 20000, 25000);
		
		Point baraddur = new Point(1690,915);
		Posto BaradDur = new Posto(7,edoras, 2300, 5000, 35000);
		
		Point amonsul = new Point(735,310);
		Posto AmonSul = new Posto(8,amonsul, 1800, 6000, 25000);
		
		Point erebor = new Point(1500,180);
		Posto Erebor = new Posto(9,erebor, 1750, 4000, 30000);
		
		Point moria = new Point(860,500);
		Posto Moria = new Posto(10,moria, 2100, 7000, 35000);
		
		Point cirithungol = new Point(1540,980);
		Posto CirithUngol = new Posto(11,cirithungol, 1200, 2000, 25000);
		
		Point emynmuil = new Point(1380,700);
		Posto EmynMuil = new Posto(12,emynmuil, 1600, 18000, 25000);
		
		Point linhir = new Point(1110,1180);
		Posto Linhir = new Posto(13,linhir, 1900, 5000, 30000);
		
		Point dombeornd = new Point(1090,240);
		Posto DomBeornd = new Posto(14,dombeornd, 1600, 5000, 30000);
		
		Point harlond = new Point(175,400);
		Posto Harlond = new Posto(15,harlond, 2000, 8000, 30000);
		
		Point nosso1 = new Point(225,700);
		Posto Nosso1 = new Posto(16,nosso1, 2800, 12000, 20000);
		
		Point nosso2 = new Point(400,835);
		Posto Nosso2 = new Posto(17,nosso2, 1000, 15000, 30000);
		
		Point nosso3 = new Point(500, 742);
		Posto Nosso3 = new Posto(18,nosso3, 3600, 20000, 35000);
		
		Point nosso4 = new Point(900,320);
		Posto Nosso4 = new Posto(19,nosso4, 2400, 6000, 40000);
		
		Point nosso5 = new Point(100,456);
		Posto Nosso5 = new Posto(20,nosso5, 2700, 5700, 35000);
}
		
		//Camiao c1 = new Camiao("11-FG-33",20000, 0, 65, 20 );
