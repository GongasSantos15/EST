package menu;

import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import javax.swing.JOptionPane;

import banco.*;
import cartao.*;

public class Main {

	public static void main(String[] args) {
		Banco b = new Banco();
		lerContas( b, "contas.bnc" );
		lerCartoes( b, "cartoes.bnc" );
		
		// cria a janela do operador e colocá-la no centro do écram
		JanelaOperador jo = new JanelaOperador( "Banco EST", b );
		// saber quando a janela fecha para mandar gravar o ficheiro das contas
		jo.addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				gravarContas(b, "contas_out.bnc" );
			}
		});
		jo.setLocationRelativeTo( null );
		Point pos = jo.getLocation();
		jo.setVisible( true );
		
		// criar a janela que simula o multibanco e colocá-la à esquerda da janela principal
		JanelaATM atm = new JanelaATM( jo, "ATM", b );
		atm.setLocation( new Point(pos.x-atm.getWidth() - 10, pos.y + (jo.getHeight()-atm.getHeight())/2) );
		atm.setVisible( true );
		
		// criar a janela que simula o TPA e colocá-la à direita da janela principal
		JanelaTPA tpa = new JanelaTPA( jo, b );
		tpa.setLocation( new Point(pos.x+jo.getWidth() + 10, pos.y + (jo.getHeight()-tpa.getHeight())/2) );
		tpa.setVisible( true );
	}

	/** Lê o ficheiro das contas existentes no banco
	 * @param b banco onde colocar as contas lidas
	 * @param nomeFich nome do ficheiro a ler
	 */
	private static void lerContas(Banco b, String nomeFich ) {
		try( BufferedReader fin = new BufferedReader( new FileReader( nomeFich ) )) {
			
			// TODO fazer a leitura da informação
			String linha;
			
			// Enquanto a linha != null, se a linha começa com "$conta" (regra para ser uma conta), ler essa linha e passar à info seguinte. Separar a infomração por "," (regra também)
			while((linha = fin.readLine()) != null ) {
				if(!linha.startsWith("$conta")) {
					//linha = fin.readLine();
					continue;
				}
				
				String info[] = linha.split(",");
				
				// Converter o número para INT, pois a seguir ao split fica como string
				int numero = Integer.parseInt(info[1]);
				long saldo = Long.parseLong(info[2]);
				
				// Teste
				System.out.println("numero: " + numero + " saldo: " + saldo);
				
				// Criação da conta com as novas informações e adição ao banco
				Conta c = new Conta(numero, saldo);
				b.addConta(c);
				
				// Ler a próxima linha
				//linha = fin.readLine();

				
			}
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Ficheiro das contas " + nomeFich + " não encontrado!" );
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro na leitura do ficheiro das contas: " + nomeFich + "!" );
		} catch( Exception e ) {
			JOptionPane.showMessageDialog(null, "Má formatação no ficheiro das contas: " + nomeFich + "!" );
		}
	}
	
	/** gravar o ficheiro das contas do banco
	 * @param b o banco de onde gravas as contas
	 * @param nomeFich nome do ficheiro a gravar
	 */
	private static void gravarContas(Banco b, String nomeFich ) {
		try( PrintWriter fout = new PrintWriter( new BufferedWriter( new FileWriter( nomeFich ) ) ) ){
			// TODO fazer a gravação da informação
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro na escrita do ficheiro das contas: " + nomeFich + "!" );
		}
	}
	
	/** Lê o ficheiro com a informação dos cartões
	 * @param b o banco onde adicionar os cartões
	 * @param nomeFich nome do ficheiro a ler
	 */
	private static void lerCartoes(Banco b, String nomeFich ) {
		try(BufferedReader fin = new BufferedReader( new FileReader( nomeFich ) ) ){		
			// TODO fazer a leitura da informação 
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Ficheiro dos cartões " + nomeFich + " não encontrado!" );
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro na leitura do ficheiro dos cartões: " + nomeFich + "!" );
		} catch( Exception e ) {
			JOptionPane.showMessageDialog(null, "Má formatação no ficheiro dos cartões: " + nomeFich + "!" );
		}
	}
}