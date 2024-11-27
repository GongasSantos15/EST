package menu;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import estairways.Aeroporto;
import estairways.ESTAirways;
import estairways.Voo;

public class Main {

	/**Arranca com a aplicação
	 */
	public static void main(String[] args) {
		ESTAirways albi = new ESTAirways();
		readAeroportos( albi, "./data/aeroportos.dat" );
		readVoos( albi, "./data/voos.dat" );
		
		// ver o tamanho do écran onde criar as janelas
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		// criar as janelas
		JanelaEscolha je = new JanelaEscolha( albi );
		JanelaReservas jr = new JanelaReservas( albi );
		JanelaVoos jv = new JanelaVoos( albi );
		
		// posicioná-las
		Rectangle r1 = je.getBounds();
		Rectangle r2 = jr.getBounds();
		Rectangle r3 = jv.getBounds();
		int posx1 = (screenSize.width - r1.width - Math.max(r2.width, r3.width) - 10)/2;
		int posx2 = posx1 + r1.width + 10;
		int posy = (screenSize.height - Math.max(r1.height,r2.height+10+r3.height))/2;
		int posy2 = posy + r2.height + 10;
				
		je.setLocation( posx1, posy );
		je.setVisible( true );
		
		jr.setLocation( posx2, posy );
		jr.setVisible( true );
		
		jv.setLocation( posx2, posy2 );
		jv.setVisible( true );
	}

	/** método para ler o ficheiro com a informação dos aeroportos
	 * @param estWays a companhia
	 * @param aeroportosFile o nome do ficheiro com a informação
	 */
	private static void readAeroportos(ESTAirways estWays, String aeroportosFile ) {
		try( BufferedReader fin = new BufferedReader( new FileReader( aeroportosFile ) ) ){
			
			// Obter a linha de cada aeroporto
			String linha;
			
			// Enquanto a linha for diferente de null
			while((linha = fin.readLine()) != null) {
				
				// Dividir a linha pelos espaços, tabulações e novas linhas
                String[] partes = linha.split("\\s+");

                // Atribuir o código do aeroporto
                String codigoAeroporto = partes[0];
                
                // Atribuir o nome (junto até o penúltimo valor)
                // Inicializar a variável nome como um string vazia 
                String nomeAeroporto = "";
                
                // Este ciclo começa a 1 (devido ao código ser a partes[0], vai até à antepenúltima partes[] (pq as 2 últimas são números), independentemente do número de palavras)
                for (int i = 1; i < partes.length - 2; i++) {
                	// É concatendo à variável nome o " ", para as palavras terem espaço entre elas
                	nomeAeroporto += partes[i] + " ";
                }
                // Isto remove o espaço extra no final, pq não é necessário, e assim apresenta os voos, sem isto não apresenta
                nomeAeroporto = nomeAeroporto.trim();

                // Atribuir as taxas aeroportuárias e as taxas de alterações
                int taxaAeroportuaria = Integer.parseInt(partes[partes.length - 2]);
                int taxaAlteracoes = Integer.parseInt(partes[partes.length - 1]);
                
                // Criar o aeroporto
                Aeroporto aeroporto = new Aeroporto(codigoAeroporto, nomeAeroporto, taxaAeroportuaria, taxaAlteracoes);
                
                // Adicionar o aeroporto ao sistema
                estWays.addAeroporto(aeroporto);
                
                // Adicionar abreviaturas ao aeroporto
                aeroporto.addAbreviatura();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Não tenho o ficheiro " + aeroportosFile );
			System.exit( 0 );
		}
		catch (Exception e) {
			System.out.println("Erro na leitura do ficheiro " + aeroportosFile );
			e.printStackTrace();
			System.exit( 0 );
		}
	}

	/**método para ler o ficheiro com a informação dos voos
	 * @param estWays a companhia
	 * @param file o nome do ficheiro com a informação
	 */
	private static void readVoos( ESTAirways estWays, String file ){
		
		// formatter para processar a hora
		final DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("H:m");
		try ( BufferedReader fin = new BufferedReader( new FileReader( file ) )){
			
			// Obter a linha de cada voo
			String linha;
			
			// Enquanto a linha do ficheiro for diferente de null
            while ((linha = fin.readLine()) != null) {
                if (linha.startsWith("<-- VOO -->")) {
                    
                	// A linha do código do voo e dos aeroportos pelos espaços, tabulações e novas linhas
                	String linha1 = fin.readLine();
                	String[] infoLinha1 = linha1.split("\\s+");         
                	String codigoVoo = infoLinha1[0];
                    String aeroportoOrigem = infoLinha1[1];
                    String aeroportoDestino = infoLinha1[2];
                    
                    // Linha data após início e hora pelos espaços, tabulações e novas linhas
                    String linha2 = fin.readLine();
                    String[] infoLinha2 = linha2.split("\\s+");  
                    int data = Integer.parseInt(infoLinha2[0]);
                    LocalTime hora = LocalTime.parse(infoLinha2[1], formatterHora);
                    
                    // Linha custo bagagem
                    int custoBagagem = Integer.parseInt(fin.readLine());
                    
                    // Linha custo lugar
                    int custoLugar = Integer.parseInt(fin.readLine());   
                    
                    // Linha classe Deluxe
                    String[] valoresClasseDeluxeArr = fin.readLine().split(",");
                    ArrayList<Long> valoresClasseDeluxe = new ArrayList<>();
                    
                    	// Percorrer o Array e adicionar os valores ao ArrayList
	                    for (String s : valoresClasseDeluxeArr) {
	                    	valoresClasseDeluxe.add(Long.parseLong(s));
	                    }
                    
                    // Linha classe Confort
                    String[] valoresClasseConfortArr = fin.readLine().split(",");  
                	ArrayList<Long> valoresClasseConfort = new ArrayList<>();
                    
                		// Percorrer o Array e adicionar os valores ao ArrayList
	                    for (String s : valoresClasseConfortArr) {
	                    	valoresClasseConfort.add(Long.parseLong(s));
	                    }
                    
                    // Linha classe Standard
                    String[] valoresClasseStandardArr = fin.readLine().split(",");
                    ArrayList<Long> valoresClasseStandard = new ArrayList<>();
                    
                    	// Percorrer o Array e adicionar os valores ao ArrayList
	                    for (String s : valoresClasseStandardArr) {
	                    	valoresClasseStandard.add(Long.parseLong(s));
	                    }                    
                    
                    // Criar o Voo
                    Voo voo = new Voo(codigoVoo, aeroportoOrigem, aeroportoDestino, data, hora, custoLugar, custoBagagem, valoresClasseStandard, valoresClasseConfort, valoresClasseDeluxe);
                    
                    // Adicionar o Voo ao sistema
                    estWays.addVoo(voo);
                }
            }
			
		} catch (FileNotFoundException e) {
			System.out.println("Não tenho o ficheiro " + file );
			System.exit( 0 );
		}
		catch (Exception e) {
			System.out.println("Erro na leitura do ficheiro " + file );
			e.printStackTrace();
			System.exit( 0 );
		}
	}
}
