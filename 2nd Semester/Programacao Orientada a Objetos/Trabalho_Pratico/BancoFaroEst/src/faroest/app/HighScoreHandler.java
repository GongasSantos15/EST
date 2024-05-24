package faroest.app;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.swing.JOptionPane;

/** Classe responsável por tratar das pontuações máximas. É ela que mantém 
 * as pontuações, as lê e grava no disco.
 */
public class HighScoreHandler {
	/** número máximo de pontuações que são armazenadas */
	public static final int MAX_SCORES = 20;       
	/** número máximo de caracteres de um nome */
	public static final int MAX_CHARS_NOME = 20; 
	/** número máximo de digitos da pontuação (guardados no ficheiro) */
	public static final int MAX_DIGITOS_PONT = 7; 

	/** Classe interna que representa uma pontuação na lista de records.
	 * Colocou-se esta interna para estarem as duas classes juntas já que esta só
	 * é usada em conjunção com a HighScoreHandler
	 */
	public static class Score {
		private String nome;
		private int pontuacao;
		
		/** Cria um Score, com o nome do jogador e respetiva pontuação
		 * @param nome nome do jogador (tem de ter menos de 20 caracteres)
		 * @param pontuacao pontuação obtida pelo jogador (tem de ser positiva)
		 */
		public Score(String nome, int pontuacao) {
			if( pontuacao < 0 )
				throw new IllegalArgumentException("pontuacao tem de ser positiva: " + pontuacao );
			this.nome = Objects.requireNonNull(nome);
			if( nome.length() > 20 )
				throw new IllegalArgumentException("nome comprido demais " + nome );
			this.pontuacao = pontuacao;
		}

		/** retorna o nome do jogador que obteve a pontuação 
		 * @return o nome do jogador que obteve a pontuação
		 */
		public String getNome() {
			return nome;
		}

		/** retorna a pontuação obtida
		 * @return  a pontuação obtida
		 */
		public int getPontuacao() {
			return pontuacao;
		}
	}

	/** o ficheiro onde se armazenam as pontuações */
	private File file;
	
	/** a lista de pontuações */
	private ArrayList<Score> pontuacoes = new ArrayList<Score>( MAX_SCORES );

	/** Cria um HishScoreHandler indicando onde este deve ler e armazenar as pontuações.
	 * Começa por ler e carregar as pontuações do ficheiro
	 * @param nomeFich nome do ficheiro onde ler e armazenar as pontuações
	 * @throws IOException se houver algum erro a ler o ficheiro
	 */
	public HighScoreHandler( String nomeFich ) throws IOException {
		file = new File( nomeFich );
		loadScores( );
	}

	/** carrega as pontuações máximas do ficheiro
	 * @throws IOException se houver algum erro a ler o ficheiro
	 */
	public void loadScores() throws IOException {
		// TODO ZFEITO implementar este método
		
		try( BufferedReader fin = new BufferedReader( new FileReader( file ) )) {
			
			// TODO ZFEITO fazer a leitura da informação
			String linha;
			
			while((linha = fin.readLine()) != null ) {
					continue;
				}
				
				// Dividir a informação da linha num array separados por ","
				String nome = linha.substring(0, 20);
				int pontuacao = Integer.parseInt(linha.substring(21,28));
				
				System.out.println(nome);
				System.out.println(pontuacao);
				
				pontuacoes.add(new Score(nome, pontuacao));
			}
		}
	
	/** Classifica uma pontuação indicando em que posição da
	 * lista ela deve ser colocada, ou -1 se não foi suficiente para atingir
	 * um lugar na mesma
	 * @param pontuacao a pontuação a classificar
	 * @return a posição onde ficou, ou -1 se não foi boa o suficiente
	 */
	public int classificarScore( int pontuacao ) {
		for( int i=0; i < pontuacoes.size(); i++ )
			if( pontuacao > pontuacoes.get(i).getPontuacao() )
				return i;
		return pontuacoes.size() >= MAX_SCORES? -1: pontuacoes.size();
	}

	/** Adiciona a pontuação à lista. A pontuação só é adicionada se 
	 * for suficientemente boa.
	 * @param nome nome do jogador que obteve a pontuação
	 * @param pontuacao pontuação obtida
	 */
	public void addScore( String nome, int pontuacao ) {
		int pos = classificarScore( pontuacao );
		if( pos == -1 )
			return;
		pontuacoes.add( pos, new Score(nome,pontuacao) );
		while( pontuacoes.size() > MAX_SCORES )
			pontuacoes.remove( pontuacoes.size()-1 );
	}
	
	/** Guarda a pontuação para o ficheiro de pontuações
	 * @throws IOException se alguma coisa correr mal na gravação do ficheiro
	 */
	public void saveScores() throws IOException {
		// TODO FEITO implementar este método
		try(PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
			
			for (Score s: pontuacoes) {
				fout.println(s);
			}
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro na escrita do ficheiro : " + file );
		}
	}

	/** retorna a lista das pontuações
	 * @return a lista das pontuações
	 */
	public List<HighScoreHandler.Score> getScores() {
		return Collections.unmodifiableList( pontuacoes );
	}
}
