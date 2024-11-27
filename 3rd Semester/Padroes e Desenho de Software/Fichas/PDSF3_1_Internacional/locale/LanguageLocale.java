package locale;

import java.io.*;
import java.util.*;

import javax.swing.JDialog;
import javax.swing.JOptionPane;


/**
 * Esta classe tem por objectivo fornecer os nomes dos comandos e demais texto
 * presente nos controlos de uma aplica��o. Cada texto est� identificado por uma chave.
 * Assim na cria��o da aplica��o basta dar a chave que esta classe encarrega-se de
 * lhe atribuir o texto correcto na linguagem correcta.
 *  
 * @author fsergio (da parte inicial, restante deve ser feita pelos alunos)
 *
 */
public class LanguageLocale {

	private static String language;                                             // linguagem actual
	private static HashMap<String,String> words = new HashMap<String,String>(); // Mapa com as relações entre chaves e texto
	private static LanguageBrowser langBrowser = new LanguageBrowser();  // classe que carrega as várias linguagens

	private static LanguageLocale LocaleUnico;

	public static LanguageLocale getLocale() {

		if (LocaleUnico == null) {
			LocaleUnico = new LanguageLocale();
		}
		return LocaleUnico;
	}

	private LanguageLocale( ){
		try{
			JanelaProgresso jan = new JanelaProgresso(null);
			jan.setVisible( true );
			// ler o ficheiro de configura��o da linguagem
			BufferedReader br = new BufferedReader( new FileReader( new File( "linguagens/defaultlang.cfg" ) ));
			String langFileName = br.readLine();
			br.close();

			// ler o ficheiro da linguagem
			br = new BufferedReader( new FileReader( new File( "linguagens/"+langFileName ) ));

			// ler a descri��o da linguagem
			language = br.readLine();
			while( true ){
				String s = br.readLine();
				if( s == null )  // n�o tem linha? termina a leitura
					break;
				if( s.equals("") ) // linha vazia? continua para a linha seguinte
					continue;				
				if( s.charAt(0) == '#') // � uma linha de coment�rio logo passa � pr�xima linha
					continue;
				String strs[] = s.split("=");
				// n�o tem duas string? d� erro... Ignora esta entrada e passa � pr�xima
				if( strs.length != 2 ) 
					continue;
				// se tem duas strings a primeira � a chave e a segunda o texto
				words.put( strs[0], strs[1] );
			}
		}
		catch( IOException e ){
			System.err.println("Erro na leitura do ficheiro de configura��o de linguagem" );
			e.printStackTrace();
			System.exit( 0 );
		}
	}

	/**
	 * retorna o texto relacionado com um dado c�digo
	 * @param key o c�digo do texto
	 * @return o texto a colocar na aplica��o
	 */
	public String get( String key ){
		String val = words.get( key ) ;
		return val == null? "???": val;
	}

	/**
	 * m�todo que retorna os nomes das linguagens suportadas pela aplica��o
	 * @return um array com os nomes da linguagens suportadas
	 */
	public static String[] getLanguages(){

		return langBrowser.getLinguagens();

	}

	/**
	 * Retorna os nomes dos ficheiros onde est�o definidas as linguagens
	 * @return um array com os nomes dos ficheiros onde est�o definidas as linguagens
	 */
	public static String[] getFilenames() {
		return langBrowser.getFilenames();
	}

	/**
	 * Retorna o �ndice da linguagem seleccionada dentro das linguagens que est�o definidas.
	 * O �ndice deve ser visto no retorno do m�todo getLanguages()
	 * @return o �ndice da linguagem seleccionada
	 */
	public int getSelectedLanguageIndex(){
		String [] availableLang = getLanguages();
		for( int i=0; i < availableLang.length; i++ )
			if( availableLang[i].equals( language ))
				return i;

		return -1;
	}

	/**
	 * Retorna o nome da linguagem que est� a ser usada 
	 * @return o nome da linguagem que est� a ser usada
	 */
	public String getSelectecLanguage(){
		return language;
	}

	/**
	 * m�todo que define qual a nova linguagem por defeito
	 * Ou seja, qual a linguagem que deve ser carregada quando a aplica��o come�a
	 * Vai gravar o nome dessa linguagem no ficheiro "defaultlang.cfg"
	 * @param lang o nome da nova linguagem por defeito
	 */
	public static void setDefaultLanguage(String lang){

		// Get the filename
		String [] availableLang = getLanguages();
		String [] availableFiles = getFilenames();

		String strFilename = null;

		for(int i = 0 ; i < availableLang.length ; i++){
			if(lang.compareToIgnoreCase(availableLang[i]) == 0){
				strFilename = availableFiles[i];
			}
		}		

		try {
			File fileDefLang = new File("linguagens/defaultlang.cfg");
			BufferedWriter bw = new BufferedWriter(new PrintWriter(fileDefLang));			
			bw.write(strFilename);			
			bw.close();
		} catch(Exception e){

		}
	}


	/**
	 * clase privada que vai verificar quantos ficheiros de linguagens est�o definidos
	 * � static para poder ser acedida mesmo antes de se ter o objecto de Linguagem
	 * @author F. S�rgio Barbosa
	 */
	private static class LanguageBrowser {

		String[] asLinguagens;  // lista das linguagens
		String[] fileNames;     // lista dos ficheiros com linguagens

		public LanguageBrowser(){

			// vai procurar em todos os ficheiros presentes no direct�rio "linguagens"
			// que tenham a extens�o ".lang"
			File files[] = (new File( "linguagens" )).listFiles( 
					new FilenameFilter(){
						public boolean accept(File dir, String name){
							String extensao = name.substring( name.length()- 5);
							return extensao.compareToIgnoreCase(".lang") == 0;    					
						}
					});

			// vai ver para cada ficheiro qual a linguagem que suporta
			// essa informa��o est� logo na primeira linha
			try {
				asLinguagens = new String[ files.length ];
				fileNames = new String[ files.length ];
				for (int i=0; i < files.length; i++ ) {
					// ler o ficheiro da linguagem para ver a linguagem
					BufferedReader br = new BufferedReader(new FileReader(files[i]));
					asLinguagens[i] = br.readLine();
					fileNames[i] = files[i].getName();
					br.close();
					System.out.println( asLinguagens[i] );
				}
			} catch (Exception e) {
				asLinguagens = new String[1];
				asLinguagens[0] = "Sem linguagens"; 
			}    	
		}

		public String[] getLinguagens(){
			return asLinguagens;
		}

		public String[] getFilenames(){
			return fileNames;
		}
	}		

	private class JanelaVisualizacao extends JDialog {
		public JanelaVisualizacao() {

		}
	}
}
