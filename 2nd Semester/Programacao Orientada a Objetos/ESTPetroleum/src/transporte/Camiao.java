package transporte;
import java.awt.Point;
import java.util.Objects;

import edificios.Central;
import edificios.Posto;
import menu.Mapa;
import viagem.Itinerario;
import viagem.Paragem;

/** Esta classe representa um camião, que, neste contexto, se refere à capacidade de transportar
 * combústivel. Tem para isso um limite máximo de combústivel que pode transportar e a
 * quantidade de combústvel que transporta num dado momento.
 * Cada camião desloca-se a uma velocidade, que para simplificar, iremos contabilizar como a velocidade média.
 * Um camião segue um itinerário, sendo que deve terminar o itinerário dentro 
 * do limite de tempo de um turno de 14 horas )2 condutores com turnos de 7 horas cada).
 */
public class Camiao {
	// o tempo máximo de um turno, que são as 14 horas 2 condutores), dadas em segundos 
	public static final int TEMPO_TURNO = 14 * 3600;
	
	/* ------------------------------------------ VARIÁVEIS ------------------------------------------------ */
	private String matricula;
	private int capacidadeMax, quantidadeAtual, velocidadeMedia, debito;
	private Itinerario itinerario;
	
	/* ----------------------------------------- CONSTRUTOR ------------------------------------------------- */
	public Camiao(String matricula, int capacidadeMax, int quantidadeAtual, int velocidadeMedia, int debito, Itinerario itinerario) {
		this.matricula = matricula;
		
		if (capacidadeMax >= 0 && capacidadeMax >= quantidadeAtual) {
			this.capacidadeMax = capacidadeMax;
		} else {
			this.capacidadeMax = quantidadeAtual;
		}
		
		if (quantidadeAtual >= 0 && quantidadeAtual <= capacidadeMax) {
			setQuantidadeAtual(quantidadeAtual);
		} else {
			this.quantidadeAtual = capacidadeMax;
		}
		
		if (velocidadeMedia >= 0) {
			this.velocidadeMedia = velocidadeMedia;
		} else {
			this.velocidadeMedia = 0;
		}

		if (debito >= 0) {
			this.debito = debito;
		} else {
			this.debito = 0;
		}
		
		setItinerario(itinerario);
	}

	/* ------------------------------------------- MÉTODOS -------------------------------------------------- */
	
	/** indica se o Camião pode acrescentar o seguinte pedido ao seu itinerário
	 * @param posto posto que pede o abastecimento
	 * @param litros litros que o posto pretende
	 * @return ACEITE, se aceitar o pedido 
	 *         EXCEDE_CAPACIDADE_CAMIAO, se o número de litros for superior
	 *         ao que o camião tem disponível
	 *         EXCEDE_TEMPO_TURNO, se o pedido implicar um tempo maior que um turno      
	 *          
	 */
	public int podeFazerPedido(Posto posto, int litros) {
		
		if (litros > capacidadeLivre()) {
			return Central.EXCEDE_CAPACIDADE_CAMIAO;
		} else if (duracaoTurno() + tempoPercorrer(itinerario.getFim(), posto.getPosicao()) + tempoDespejar(litros) > TEMPO_TURNO) {
			return Central.EXCEDE_TEMPO_TURNO;
		}
		return Central.ACEITE;
	}

	/** adiciona um posto ao itinerário do camião, se for possível.
	 * @param p posto que pede o abastecimento
	 * @param litros litros que o posto pretende
	 * @return ACEITE, se aceitar o pedido <br>
	 *         EXCEDE_CAPACIDADE_CAMIAO, se o número de litros for superior
	 *         ao que o camião tem disponível<br>
	 *         EXCEDE_TEMPO_TURNO, se o pedido implicar um tempo maior que um turno      
	 */
	public int addPosto( Posto p, int litros ) {
		// TODO ZFEITO fazer este método
		if (podeFazerPedido(p, litros) == Central.ACEITE && !itinerario.postoExiste(p)) {
			itinerario.addParagem(new Paragem(p, litros));
			this.quantidadeAtual += litros;
		}
		return podeFazerPedido(p, litros);
	}
	
	/** retorna a capacidade livre, isto é, quantos litros ainda pode
	 * adicionar à carga
	 * @return a capacidade livre, em litros
	 */
	public int capacidadeLivre() {
		// TODO ZFEITO fazer este método     
		return capacidadeMax - quantidadeAtual;
	}

	/** retorna o tempo, em segundos, que demora a fazer o itinerário
	 * @return o tempo, em segundos, que demora a fazer o itinerário 
	 */
	public double duracaoTurno(){
		
	    // TODO ZFEITO calcular uma distância, para depois retornar o tempo		
		double tempo = 0;
		Point pontoInicial = itinerario.getInicio();
		
		for (Paragem paragem : itinerario.getParagens()) {
			Point pontoFinal = paragem.getPosto().getPosicao();
			
			tempo += tempoPercorrer(pontoInicial, pontoFinal);
			
			if (tempo > TEMPO_TURNO) {
				return TEMPO_TURNO;
			}
			
			pontoInicial = pontoFinal;
		}
		return tempo;
	}
	
	/** retorna o tempo, em segundos, que demora a fazer o itinerário acrescentando um posto extra
	 * @param extra o posto extra a processar
	 * @param nLitros oslitros que o posto extra precisa
	 * @return tempo, em segundos, que demora a fazer o itinerário mais o posto extra
	 */
	public double duracaoTurnoExtra( Posto extra, int nLitros ) {
		// TODO ZFEITO fazer este método
		return duracaoTurno() + tempoPercorrer(itinerario.getFim(), extra.getPosicao()) + tempoDespejar(nLitros);
		
	}

	// Efetua o transporte e transferência de combustível para todos os postos no itinerário
	public void transporta() {
	    
		// TODO ZFEITO fazer este método
	    for (Paragem paragem : itinerario.getParagens()) {
	    	 Posto postoParagem = paragem.getPosto();
	        
	        if (tempoPercorrer(itinerario.getInicio(), postoParagem.getPosicao()) > TEMPO_TURNO) {
	            return;
	        }
    		postoParagem.enche(paragem.getNLitros());		
    		setQuantidadeAtual(quantidadeAtual - paragem.getNLitros());
	    }
	    itinerario.limpar();
	}

	/** retorna o tempo, em segundos, que demora a percorrer o caminho entre
	 * dois pontos.
	 * @param ini o ponto inical
	 * @param fim o ponto final
	 * @return o tempo que demora a ir de ini a fim.
	 */
	private double tempoPercorrer( Point ini, Point fim ){

		// TODO ZFEITO terminar este método (distância / velocidade)
			return (Mapa.distancia(ini, fim) / this.velocidadeMedia) * 3600;
	}
	
	/** retorna quanto tempo demora, em segundos, a transferir a quantidade de liquido
	 * @param nLitros a quantidade de liquido a transferir
	 * @return o tempo que demora, em segundos, a transferir os nLitros
	 */
	private double tempoDespejar( int nLitros ){
		// TODO ZFEITO fazer este método                         
		return (double) nLitros / debito;
	}
	
	/** retorna a percentagem de ocupação do camião, entre 0 (0%) e 1 (100%)
	 * @return a percentagem de ocupação 
	 */
	public float percentagemOcupacao() {
		// TODO ZFEITO fazer este método                        
		return (float) quantidadeAtual / capacidadeMax;
	}
	
	/* --------------------------------------- GETTERS E SETTERS --------------------------------------------- */
	public int getCapacidadeMax() {
		return capacidadeMax;
	}

	public int getQuantidadeAtual() {
		return quantidadeAtual;
	}

	public void setQuantidadeAtual(int quantidadeAtual) {
		this.quantidadeAtual = quantidadeAtual;
	}

	public int getVelocidadeMedia() {
		return velocidadeMedia;
	}

	public int getDebito() {
		return debito;
	}
	
	public String getMatricula() {
		return matricula;
	}

	public Itinerario getItinerario() {
		return itinerario;
	}
	
	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}

	/* --------------------------------------- EQUALS e HASHCODE ---------------------------------------------- */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Camiao other = (Camiao) obj;
		return Objects.equals(matricula, other.matricula);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}
	
	
	
}
