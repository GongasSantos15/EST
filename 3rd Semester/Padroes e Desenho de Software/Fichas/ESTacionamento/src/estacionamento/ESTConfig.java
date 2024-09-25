package estacionamento;

import java.io.FileInputStream;
import java.util.Properties;

/** Classe que armazena as configurações do sistemas
 * Será útil na implementação das novas funcionalidades
 * 
 * @author F. Sergio Barbosa
 * 
 * P.S. Aqui podia-se aplicar uma das patterns que iremos dar. 
 */
public class ESTConfig {
	
	private static Properties props = null;
	
	private ESTConfig() {
	}
	
	/** faz a leitura do ficheiro das configurações
	 * @return as configurações lidas do ficheiro
	 */
	private static Properties getProps(){
		if( props == null ){
			props = new Properties();
			try {
				props.load( new FileInputStream("config/config.cfg" ) );
			} catch (Exception e) {
				System.err.println("Ficheiro config.cfg n�o lido");
			}
		}
		return props;
	}

	/** Devolve a configuração, em String, associada ao texto indicado 
	 * 
	 * @param cfg a configuração a retornar
	 * @return a String associada à configuração, ou null se não existir
	 */
	public static String getConfig( String cfg ){
		return getProps().getProperty(cfg); 
	}
	
	/** Devolve a configuração, convertida em inteiro, associada ao texto indicado 
	 * 
	 * @param cfg a configuração a retornar
	 * @return o valor inteiro associado à configuração
	 */
	public static int getConfigAsInt( String cfg ){
		return Integer.parseInt( getProps().getProperty(cfg) ); 
	}
	
	/** Devolve a configuração, convertida em long, associada ao texto indicado 
	 * 
	 * @param cfg a configuração a retornar
	 * @return o valor long associado à configuração
	 */
	public static long getConfigAsLong( String cfg ){
		return Long.parseLong( getProps().getProperty(cfg) ); 
	}
	
	/** Devolve a configuração, convertida em float, associada ao texto indicado 
	 * 
	 * @param cfg a configuração a retornar
	 * @return o valor float associado à configuração
	 */
	public static float getConfigAsFloat( String cfg ){
		return Float.parseFloat( getProps().getProperty(cfg) ); 
	}

	/** Devolve a configuração, convertida em double, associada ao texto indicado 
	 * 
	 * @param cfg a configuração a retornar
	 * @return o valor double associado à configuração
	 */
	public static double getConfigAsDouble( String cfg ){
		return Double.parseDouble( getProps().getProperty(cfg) ); 
	}
	
}
