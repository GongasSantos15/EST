package menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import estacionamento.ESTacionamento;

public class JanelaSaidaSimples extends JDialog {
	/** serial id */
	private static final long serialVersionUID = 2021500725063390201L;
	
	private ESTacionamento parque;
	private final Icon portaoEspera = new ImageIcon( ClassLoader.getSystemResource("icons/portaoEspera.png") );
	private final Icon portaoAberto = new ImageIcon( ClassLoader.getSystemResource("icons/portaoSai.png") );
	private final Icon portaoRecusa = new ImageIcon( ClassLoader.getSystemResource("icons/portaoRecusa.png") );
	
	private JLabel portaoLbl; 
	private JButton simularBt;
	private Timer temporizador;
	
	public JanelaSaidaSimples( JFrame owner, ESTacionamento parque, int x, int y ) {
		super( owner );
		this.parque = parque;
		setupAspeto();
		setLocation( x, y );
		temporizador = new Timer( 4000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizarPortao();				
			}
		} );
		temporizador.start();
	}

	private void processarSair() {
		String matricula = JOptionPane.showInputDialog("Matrícula?");

		// impedir novas simulações e desligar a entrada
		simularBt.setEnabled( false );

		// TODO processar a saída da viatura
		// usar caso corra bem
		portaoLbl.setIcon( portaoAberto );					
		// usar caso corra mal
		portaoLbl.setIcon( portaoRecusa );
		
		
		temporizador.restart();
	}
	
	private void atualizarPortao(){
		// fazer reset aos icons
		portaoLbl.setIcon( portaoEspera );
		
		// permitir novamente a simulação
		simularBt.setEnabled( true );
	}

	private void setupAspeto() {
		// TODO nem todos os parques tem este nome
		setTitle( "ESTParque" );
		JPanel portao = new JPanel( new GridLayout( 0, 1 ) );
		
		portaoLbl = new JLabel( portaoEspera );
		portao.add( portaoLbl );
		getContentPane().add( portao, BorderLayout.CENTER );
		
		simularBt = new JButton("Simular viatura" );
		getContentPane().add( simularBt, BorderLayout.SOUTH );
		simularBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				processarSair();
			}
		});
		
		pack();
	}
}
