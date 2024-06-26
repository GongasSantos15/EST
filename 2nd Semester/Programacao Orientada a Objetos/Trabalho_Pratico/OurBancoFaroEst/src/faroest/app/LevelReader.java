package faroest.app;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import faroest.mundo.Mundo;
import faroest.visitante.*;
import prof.jogos2D.util.ESTProperties;

/** Classe responsável pela leitura dos ficheiros de nível
 */
public class LevelReader {
	
	private static ESTProperties props;    // as propriedades a ler
	
	/**
	 * método para ler o ficheiro de nível
	 * @param level número do nivel
	 * @return o Nivel criado
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static Mundo lerNivel( int level ) throws FileNotFoundException, IOException{
		String file = "niveis/nivel" + level + ".bfe";

		// ler o ficheiro como uma sequência de propriedades
		props = new ESTProperties( new FileReader( file ) );
		
		// ler imagem do fundo e da porta
		BufferedImage imgFundo = ImageIO.read( new File( "art/" + props.getConfig("fundo") ));
		BufferedImage imgPorta = ImageIO.read( new File( "art/" + props.getConfig("porta") ));
		
		// ler as restantes infos do mundo
		int numPortas = props.getConfigAsInt("numPortas");
		int numVisitantes = props.getConfigAsInt("numVisitantes");
		int pontos = props.getConfigAsInt("pontos"); 
		
		// criar o mundo com as infos lidas
		Mundo mundo = new Mundo( imgFundo, imgPorta, numPortas, pontos );
		
		// ler os visitantes possíveis
		
		// Switch case para criar todos os possíveis visitantes do jogo
		try {
			for( int i=1; i <= numVisitantes; i++ ) {
				String p = "visitante_" + (i < 10 ? "0" : "") + i;
				String info[] = props.getConfig( p ).split(",");
					switch( info[0] ) {
						case "depositante": mundo.addPossivelVisitante( criarDepositante(info) ); break;
						case "assaltante": mundo.addPossivelVisitante( criarAssaltante(info) ); break;
						// TODO ZFEITO ler os restantes tipos de visitantes
						case "troca": mundo.addPossivelVisitante( criarTroca(info) ); break;
						case "insatisfeito": mundo.addPossivelVisitante(criarInsatisfeito(info)); break;
						case "aleatorio": mundo.addPossivelVisitante(criarAleatorio(info)); break;
						default: throw new IOException();
					}
			}
		} catch( Exception e ) {
			throw new IOException( e );
		}
		
		// criar e retornar o mundo
		return mundo;
	}

	/** Cria um depositante
	 * @param info as informações sobre o depositante
	 * @return o depositante criado
	 */
	private static Depositante criarDepositante( String info[] ) {
		String nome = info[1];
		int pontos = Integer.parseInt( info[2] );
		int extras = Integer.parseInt( info[3] );
		int minAberto = Integer.parseInt( info[4] );
		int maxAberto = Integer.parseInt( info[5] );
		return new Depositante(nome, pontos, extras, minAberto, maxAberto );
	}
	
	/** Cria um assaltante
	 * @param info as informações sobre o assaltante
	 * @return o assaltante criado
	 */
	private static Assaltante criarAssaltante( String info[] ) {
		String nome = info[1];
		int pontos = Integer.parseInt( info[2] );
		int minSacar = Integer.parseInt( info[3] );
		int maxSacar = Integer.parseInt( info[4] );
		int minAberto = Integer.parseInt( info[5] );
		int maxAberto = Integer.parseInt( info[6] );
		return new Assaltante(nome, pontos, minSacar, maxSacar, minAberto, maxAberto );
	}
	
	/** Cria um troca
	 * @param info as informações sobre o troca
	 * @return o troca criado
	 */
	private static Troca criarTroca( String info[] ) {
		String nome = info[1];
		String nomeAssaltante = info[2];
		int pontos = Integer.parseInt( info[3] );
		int tempoMinTroca = Integer.parseInt(info[4]);
		int tempoMaxTroca = Integer.parseInt(info[5]);
		int minDisparo = Integer.parseInt(info[6]);
		int maxDisparo = Integer.parseInt(info[7]);
		return new Troca(nome, nomeAssaltante, pontos, tempoMinTroca, tempoMaxTroca, minDisparo, maxDisparo);
	}
	
	/** Cria um aleatorio
	 * @param info as informações sobre o aleatorio
	 * @return o aleatorio criado
	 */
	private static Aleatorio criarAleatorio( String info[] ) {
		String nome = info[1];
		int pontos = Integer.parseInt( info[2] );
		int extras = Integer.parseInt( info[3] );
		int minAberto = Integer.parseInt( info[4] );
		int maxAberto = Integer.parseInt( info[5] );
		return new Aleatorio (nome, pontos, extras, minAberto, maxAberto);
	}
	
	/** Cria um insatisfeito
	 * @param info as informações sobre o insatisfeito
	 * @return o insatisfeito criado
	 */
	private static Insatisfeito criarInsatisfeito( String info[] ) {
		String nome = info[1];
		int pontos = Integer.parseInt( info[2] );
		int extras = Integer.parseInt( info[3] );
		int minAberto = Integer.parseInt( info[4] );
		int maxAberto = Integer.parseInt( info[5] );
		return new Insatisfeito(nome, pontos, extras, minAberto, maxAberto );
	}
}
