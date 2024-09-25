/* ENTRADA 
 * 
 * - Identificar matricula
 * 	- TICKET
 * 		- Matricula
 * 		- Hora Entrada
 * 
 * - Contrato Ocasional (se não conhece matricula)
 * - Contrato do cliente
 * */

/* PAGAMENTO 
 * 
 * - Indicar Matricula
 * - Calcular Tempo + Custo (depende contrato)
 * - Apresenta Custo
 * 
 * - TICKET
 * 		- Hora Pagamento
 * 		- Custo
 * */

/* SAIDA 
 * 
 * - Identificar Matricula
 * - Abrir Portas (depende do pagamento e contrato)
 * 
 * - TICKET
 * 		- Hora Saida
 * */

package menu;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import estacionamento.ESTacionamento;

/** Configuração e arranque do sistema
 */
public class MainApp {

	public static void main(String[] args) {

		// criação do estacionamento
		ESTacionamento est = new ESTacionamento();
		loadContratos( est, "data/contratos.txt" );
		
		// criação das várias janela do sistema
		JanelaParque parque = new JanelaParque(est);
		parque.setLocationRelativeTo( null );
		Point pos = parque.getLocation();
		parque.setVisible( true );
		
		JanelaEntradaSimples entrada = new JanelaEntradaSimples( parque, est, 350, 10 );
		entrada.setLocation( new Point(pos.x, pos.y - entrada.getHeight() - 10) );
		entrada.setVisible( true );

		JanelaPagamentos paga = new JanelaPagamentos( parque, est );
		paga.setLocation( new Point(pos.x+(parque.getWidth() - paga.getWidth())/2, pos.y - paga.getHeight() - 10) );
		paga.setVisible( true );
		
		JanelaSaidaSimples saida = new JanelaSaidaSimples( parque, est, 650, 10 );
		saida.setLocation( new Point(pos.x+parque.getWidth() - saida.getWidth(), pos.y - saida.getHeight() - 10) );
		saida.setVisible( true );
	}

	private static void loadContratos(ESTacionamento est, String nomeFich) {
		// TODO ler contratos
		try( BufferedReader fin = new BufferedReader( new FileReader( nomeFich ))){
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "<html>Ficheiro de contratos não encontrado!<br>"+nomeFich+"<br><br>O sistema irá arrancar sem contratos!</html>", "Erro de configuração", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "<html>Ficheiro de contratos danificado!<br>"+nomeFich+"<br><br>O sistema irá arrancar sem contratos!</html>", "Erro de configuração", JOptionPane.ERROR_MESSAGE);
		}
	}
}
