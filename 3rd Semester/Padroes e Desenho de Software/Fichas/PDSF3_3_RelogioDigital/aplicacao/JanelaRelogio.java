package aplicacao;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import relogio.*;

public class JanelaRelogio extends JFrame {
	private static final long serialVersionUID = 1L;
	
	/** Relógio digital
	 */
	private RelogioDigital relogio= new RelogioDigital();
	
	 		
	/** construtor da janela que apresenta o relógio
	 */
	public JanelaRelogio() {
		super( "Relógio Digital" );
		
		// criar a interface da aplicação
		setupAspecto( getContentPane() );
		
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		pack();
		setResizable( false );
	}
		
	/** método auxiliar que cria o aspeto da apicação
	 * @param janela
	 */
	private void setupAspecto( Container janela ) {
		// criar o painel e os botões da esquerda
		JPanel pesq = new JPanel( new GridLayout(0,1) );
		JButton jbutA = new JButton( );
		jbutA.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relogio.botaoApremido();
			}
		});
		JButton jbutB = new JButton( );
		jbutB.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relogio.botaoBpremido();
			}
		});
		pesq.add( jbutA );
		pesq.add( jbutB );
		janela.add( BorderLayout.WEST, pesq );

		// criar o display onde será desenhado o relógio
		janela.add( relogio.getDisplay() );

		// criar o painel e os botões da direita
		JPanel pdir = new JPanel( new GridLayout(0,1) );
		JButton jbutC = new JButton( );
		jbutC.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relogio.botaoCpremido();
			}
		});
		JButton jbutD = new JButton( );
		jbutD.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relogio.botaoDpremido();
			}
		});
		pdir.add( jbutC );
		pdir.add( jbutD );
		janela.add( BorderLayout.EAST, pdir );
	}		
	
	public static void main( String [] args ){
		JanelaRelogio app = new JanelaRelogio();
		app.setLocationRelativeTo( null );
		app.setVisible( true );
	}
}
