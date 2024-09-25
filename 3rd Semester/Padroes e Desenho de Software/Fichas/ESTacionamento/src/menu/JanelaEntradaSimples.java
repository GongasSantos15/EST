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

public class JanelaEntradaSimples extends JDialog {
	/** serial id */
	private static final long serialVersionUID = 4111258015444061827L;
	
	private ESTacionamento parque;
	private final Icon verdeOn = new ImageIcon( ClassLoader.getSystemResource("icons/verdeOn.png") );
	private final Icon verdeOff = new ImageIcon( ClassLoader.getSystemResource("icons/verdeOff.png") );
	private final Icon amareloOn = new ImageIcon( ClassLoader.getSystemResource("icons/amareloOn.png") );
	private final Icon amareloOff = new ImageIcon( ClassLoader.getSystemResource("icons/amareloOff.png") );
	private final Icon vermelhoOn = new ImageIcon( ClassLoader.getSystemResource("icons/vermelhoOn.png") );
	private final Icon vermelhoOff = new ImageIcon( ClassLoader.getSystemResource("icons/vermelhoOff.png") );
	
	private JLabel espereLbl, entreLbl, cheioLbl, entradaNegadaLbl; 
	private JButton simularBt;
	private Timer temporizador;
	
	public JanelaEntradaSimples( JFrame owner, ESTacionamento parque, int x, int y ) {
		super( owner );
		this.parque = parque;
		setupAspeto();
		setLocation( x, y );
		temporizador = new Timer( 4000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizarSemaforos();				
			}
		} );
		temporizador.start();
	}

	private void processarEntrada() {
		String matricula = JOptionPane.showInputDialog("Matrícula?");

		// impedir novas simulações e deligar a entrada
		simularBt.setEnabled( false );
		espereLbl.setIcon( amareloOff );			
		
		// TODO processar entrada da viatura com a matrícula introduzida
		// caso corra bem
		entreLbl.setIcon( verdeOn );					
		// caso não possa entrar
		entradaNegadaLbl.setIcon( vermelhoOn );
		// caso esteja cheio
		entradaNegadaLbl.setIcon( vermelhoOn );
		cheioLbl.setIcon( vermelhoOn ); // este probavelmente não é preciso mas vamos testar

		temporizador.restart();
	}
	
	private void atualizarSemaforos(){
		// fazer reset aos icons
		espereLbl.setIcon( amareloOn );
		entreLbl.setIcon( verdeOff );
		entradaNegadaLbl.setIcon( vermelhoOff );
		// ver se o parque está cheio
		if( parque.estaCheio() )
			cheioLbl.setIcon( vermelhoOn );
		else
			cheioLbl.setIcon( vermelhoOff);
		
		// permitir novamente a simulação
		simularBt.setEnabled( true );
	}

	private void setupAspeto() {
		// TODO nem todos os parques tem este nome
		setTitle( "ESTParque" );
		JPanel semaforos = new JPanel( new GridLayout( 0, 2 ) );
		
		espereLbl = new JLabel( "Espere", amareloOn, JLabel.LEFT );
		semaforos.add( espereLbl );

		entreLbl = new JLabel( "Entre", verdeOff, JLabel.LEFT );
		semaforos.add( entreLbl );

		cheioLbl = new JLabel( "Parque cheio", vermelhoOff, JLabel.LEFT );
		semaforos.add( cheioLbl );

		entradaNegadaLbl = new JLabel( "Não pode entrar", vermelhoOff, JLabel.LEFT );
		semaforos.add( entradaNegadaLbl );
		
		getContentPane().add( semaforos, BorderLayout.CENTER );
		
		simularBt = new JButton("Simular viatura" );
		getContentPane().add( simularBt, BorderLayout.SOUTH );
		simularBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				processarEntrada();
			}
		});
		
		pack();
	}
}
