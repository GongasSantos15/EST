package estrada.app;

import java.awt.Point;
import java.io.IOException;
import java.util.Random;

import estrada.estrada.Faixa;
import estrada.veiculo.Carro;
import estrada.veiculo.TodoTerreno;
import estrada.veiculo.Veiculo;
import prof.jogos2D.image.*;

/**
 * Classe responsável por criar os vários veículos do jogo
 * Essa criação será feita aleatoriamente. Os parâmetros aleatórios poderão ser alterados 
 */
public class CriadorVeiculos {	
	
	// os vários veículos que se apresentam no jogo
	private static final int DESPORTIVO = 0;
	private static final int MONOVOLUME = 1;
	private static final int NORMAL = 2;
	private static final int ANTIGO = 3;
	private static final int JIPE = 4;
	private static final int PICKUP = 5;
	private static final int CAMIAO = 6;
	private static final int POLICIA = 7;
	private static final int AMBULANCIA = 8;
	private static final int LIMUSINE = 9;
	
	// criador de números aleatórios 
	private Random aleatorios = new Random( );  
	
	// as faixas onde se podem criar carros
	private Faixa []faixas;
	
	// variáveis de controlo de turnos de criação
	private int turnoActual = 0;      // número do turno actual
	private int proximoTurno = 0;     // próximo turno de criação
	private int []proximasVezes;      // próximo turno de criação por faixa
	private int minTurnos = 0;        // mínimo de turnos que terão de passar até poder criar um novo veículo, em qualquer faixa
	private int minTurnosFaixa = 0;   // mínimo de turnos que terão de passar até poder criar um novo veículo numa dada faixa
 	
	// probabilidades acumuladas para cada veículo
	private int probabilidadesCarro[] = { 90, // desportivo    9% dos carros são desportivos
										 180, // monovolume    9% dos carros são monovolumes
										 405, // normal     22,5% dos carros são normais 
										 450, // antigo      4,5% dos carros são antigos 
										 600, // jipe         15% dos carros são jipes
										 700, // pickup       10% dos carros são pickups
										 900, // camião       20% dos carros são camiões   
										 950, // policia       5% dos carros são polícias
										 970, // ambulância    2% dos carros são ambulâncias
										 1000 // limusine      3% dos carros são de estado
	}; 
	
	/**
	 * construtor do criador de veículos
	 * @param faixas faixas nas quais se podem criar veículos
	 * @param minTurnos minimo número de turnos entre a criação de um ve�culo pelo criador
	 * @param minTurnosFaixa minímo número de turnos entre a criação de um veículo numa faixa
	 */
	public CriadorVeiculos( Faixa []faixas, int minTurnos, int minTurnosFaixa ){
		this.faixas = faixas;
		proximasVezes = new int[ faixas.length ];
		this.minTurnos = minTurnos;
		this.minTurnosFaixa = minTurnosFaixa;
	}
	
	/**
	 * vai gerar aleatoriamente uma série de carros
	 * @return os carros criados neste turno
	 */
	public Veiculo[] criarVeiculos( ){
		// ver qual o turno actual e se ainda não tiver chegado ao próximo turno de 
		// criação não cria nenhum carro
		turnoActual++;
		if( proximoTurno > turnoActual )
			return new Veiculo[0]; 
		
		// ver quantos veículos pode criar no máximo, para isso
		// vai ver quantas faixas já passaram o turno de criação
		int maxCarros = 0;
		for( int prox : proximasVezes ){
			if( prox <= turnoActual ) maxCarros++;
		}	
		
		// este gerador cria no máximo 4 veículos em cada turno
		int nCarros = (int)Math.abs( aleatorios.nextGaussian() * 2 );
		nCarros = Math.min( nCarros, Math.min(maxCarros, 4));
		
		// se vai criar veiculos o próximo turno de criação será daqui a quantos turnos?
		if( nCarros > 0 )
			proximoTurno = turnoActual + minTurnos;
		
		// criar agora os veículos
		Veiculo carros[] = new Veiculo[ nCarros ];
		for( int i = 0; i < nCarros; i++ ){
			// escolher aleatoriamente a faixa
			int fidx = aleatorios.nextInt( faixas.length );
			// garantir que a faixa escolhida é elegível
			while( proximasVezes[fidx] > turnoActual )
				fidx = aleatorios.nextInt( faixas.length );
			proximasVezes[ fidx ] = turnoActual + minTurnosFaixa;
			Faixa f = faixas[ fidx ];
			
			// escolher aleatoriamente o carro
			int prob = aleatorios.nextInt( 1000 );
			int tipoCarro = 0;
			for( int k = 0; k < probabilidadesCarro.length; k++ ){
				if( prob <= probabilidadesCarro[ k ] ){
					tipoCarro = k;
					break;
				}
			}
			Veiculo c = null;
			
			// Teste do tipo de carro, pois todos os veiculos vão passar ao tipo de carro da variável
			//tipoCarro = CAMIAO;
			
			switch( tipoCarro ){
			case DESPORTIVO: c = criaDesportivo( f ); break; 
			case MONOVOLUME: c = criaMonovolume( f ); break;
			case NORMAL:     c = criaNormal( f );     break;
			case ANTIGO:     c = criaAntigo( f );     break;
			case JIPE:		 c = criaJipe( f );		  break;
			case PICKUP:	 c = criaPickup( f );	  break;
			case CAMIAO:     c = criaCamiao( f );     break;
			case POLICIA:    c = criaPolicia( f );    break;
			case AMBULANCIA: c = criaAmbulancia( f ); break;		
			case LIMUSINE:   c = criaLimusine( f ); break;		
			default: c = criaDesportivo( f ); break;
			}
			carros[i] = c;
			
		}
		return carros;
	}

	/*
	 * Métodos de criação para cada um dos veículos
	 * @param f faixa onde vai ficar o veículo
	 * @return p veículo criado
	 */
	
	private Carro criaDesportivo( Faixa f){
		try {
			ComponenteVisual image = new ComponenteSimples( "art/alfa.gif" );
			return new Carro( image, f, 8, 220 );
		} catch (IOException e) {
			System.out.println("Erro a ler o ficheiro art/alfa.gif");
		}			
		return null;
	}

	private Carro criaMonovolume( Faixa f){
		try {
			ComponenteVisual image = new ComponenteSimples( "art/monovolume.gif" );
			return new Carro( image, f, 5, 180);
		} catch (IOException e) {
			System.out.println("Erro a ler o ficheiro art/monovolume.gif");
		}			
		return null;
	}	
	
	private Carro criaNormal( Faixa f){
		int n = aleatorios.nextInt( 2 );
		String file = "art/normal" + n + ".gif";
		try {
			ComponenteVisual image = new ComponenteSimples( file );
			return new Carro( image, f, 6, 200 );
		} catch (IOException e) {
			System.out.println("Erro a ler o ficheiro " + file);
		}			
		return null;
	}		
	
	private Carro criaAntigo( Faixa f){
		try {
			ComponenteVisual image = new ComponenteSimples( "art/antigo.gif" );
			return new Carro( image, f, 4, 150 );
		} catch (IOException e) {
			System.out.println("Erro a ler o ficheiro art/antigo.gif");
		}			
		return null;
	}
	
	private TodoTerreno criaJipe( Faixa f){
		try {
			ComponenteVisual image = new ComponenteSimples( "art/jipe.gif" );
			return new TodoTerreno( image, f, 5, 200 );
		} catch (IOException e) {
			System.out.println("Erro a ler o ficheiro art/jipe.gif");
		}			
		return null;
	}
	
	private TodoTerreno criaPickup( Faixa f){
		try {
			ComponenteVisual image = new ComponenteSimples( "art/pickup.gif" );
			return new TodoTerreno( image, f, 7, 200 );
		} catch (IOException e) {
			System.out.println("Erro a ler o ficheiro art/pickup.gif");
		}			
		return null;
	}

	private Carro criaCamiao( Faixa f){
		try {
			ComponenteVisual image = new ComponenteSimples( "art/camiao.gif" );
			return new Carro( image, f, 4, 250 );
		} catch (IOException e) {
			System.out.println("Erro a ler o ficheiro art/camiao.gif");
		}			
		return null;
	}	
	
	private Carro criaPolicia( Faixa f){
		try {
			ComponenteVisual image = new ComponenteAnimado( new Point(), "art/policia.png", 2, 10 );
			return new Carro( image, f, 9, 200 );
		} catch (IOException e) {
			System.out.println("Erro a ler o ficheiro art/policia.png");
		}			
		return null;
	}
	
	private Carro criaAmbulancia( Faixa f){
		try {
			ComponenteVisual image = new ComponenteSimples( "art/ambulancia.gif" );
			return new Carro( image, f, 7, 160 );
		} catch (IOException e) {
			System.out.println("Erro a ler o ficheiro art/ambulancia.gif");
		}			
		return null;
	}	
	
	private Carro criaLimusine( Faixa f){
		try {
			ComponenteVisual image = new ComponenteSimples( "art/limusine.gif" );
			return new Carro( image, f, 8, 200 );
		} catch (IOException e) {
			System.out.println("Erro a ler o ficheiro art/limusine.gif");
		}			
		return null;
	}	
}
