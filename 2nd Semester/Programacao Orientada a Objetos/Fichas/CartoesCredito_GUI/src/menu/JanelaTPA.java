package menu;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.Timer;

import banco.Banco;
import cartao.Cartao;

/* Esta janela simula um Terminal de Pagamento Automático (TPA)
 * 
 */
public class JanelaTPA extends JDialog {
	/** imagens para os vários icones */
	private final static ImageIcon IMAGEM_TPA = new ImageIcon( "recursos/tpa.png" );
	private final static ImageIcon TPA_OK_ICON = new ImageIcon( "recursos/tpa_ok.png" );
	private final static ImageIcon TPA_OKHOVER_ICON = new ImageIcon( "recursos/tpa_ok_hover.png" );
	
	private Banco banco;			// o banco com o qual este TPA comunica
	private Cartao cartaoInserido;  // o cartão que foi usado
	
	/** Cria a janela que simula o TPA
	 * @param owner janela do sistema 
	 * @param b o banco com o qual este TPA tem de comunicar
	 */
	public JanelaTPA(Frame owner, Banco b ) {
		super( owner );
		banco = b;
		inicializarInterface();
	}

	/** Método chamado quando é para pagar com o cartão
	 * @param cardNum número do cartão
	 * @param quantia quantia a pagar
	 */
	protected void pagarComCartao(int cardNum, long quantia) {
		// TODO ver qual o cartão escolhido
		cartaoInserido = banco.getCartao( cardNum );
		// mensagemn a apresentar se cartão não existe
		displayMensagem( "Cartão mal inserido" );

		// TODO realizar a compra
		
		// mensagem a apresentar, caso corra bem
		displayMensagem( "Volte sempre!" );
		// mensagem a apresentar, caso não tenha cobertura
		displayMensagem( "Não tem cobertura!" );
		// mensagem a apresentar, caso cartão esteja bloqueado
		displayMensagem( "Cartão bloqueado!" );
	}
	
	// variáveis para a interface (não é preciso altertar nada a partir daqui)
	/** serial id */
	private static final long serialVersionUID = 1L;

	private Timer timerMsg;
	private JLabel display;
	private IntegerTextField pedirQuantia;
	private JButton okBt;
	
	
	/** inicializa a interface
	 */
	private void inicializarInterface() {
		setSize( 200, 180 );
		setResizable( false );
		JPanel pedirCartao = inicializarDisplay();
		pedirCartao.setPreferredSize( new Dimension(IMAGEM_TPA.getIconWidth(), IMAGEM_TPA.getIconHeight()) );
		setContentPane( pedirCartao );
		pedirCartao.getRootPane().setDefaultButton(okBt);
		setDisplayComprar();
		setUndecorated( true );
		setBackground( new Color(1,1,1,0 ) );
		pack();
		
		timerMsg = new Timer( 3000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setDisplayComprar();
				timerMsg.stop();
				okBt.setEnabled( true );
			}
		} );
		
		MouseAdapter moveJanela = new MouseAdapter() {
			int offx, offy;
			@Override
			public void mousePressed(MouseEvent e) {
				Point topo = JanelaTPA.this.getLocationOnScreen();
				Point aqui = e.getLocationOnScreen();
				offx = topo.x - aqui.x;
				offy = topo.y - aqui.y;
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				Point aqui = e.getLocationOnScreen();
				JanelaTPA.this.setLocation(aqui.x + offx, aqui.y + offy);
			}
		}; 
		addMouseListener(moveJanela);
		addMouseMotionListener(moveJanela);
	}
	
	/** inicializa o painel com a imagem do TPA
	 * @return o painel já configurado
	 */
	private JPanel inicializarDisplay() {
		SpringLayout layout = new SpringLayout();
		@SuppressWarnings("serial")
		JPanel cartaoPanel = new JPanel( layout ) {

			@Override
			protected void paintComponent(Graphics g) {
				g.drawImage( IMAGEM_TPA.getImage(), 0, 0, null);				
			}
		};
		cartaoPanel.setOpaque( false );
		display = new JLabel();
		display.setForeground( Color.WHITE );
		pedirQuantia = new IntegerTextField( true );
		pedirQuantia.setForeground( Color.WHITE );
		pedirQuantia.setOpaque( false );
		okBt = new JButton( TPA_OK_ICON );
		okBt.setRolloverIcon( TPA_OKHOVER_ICON );
		okBt.setDisabledIcon( TPA_OK_ICON );
		okBt.setOpaque(false);
		okBt.setContentAreaFilled(false);
		okBt.setBorderPainted(false);
		okBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				long quantia = pedirQuantia.getValor();
				if( pedirQuantia.getValor() == -1 )
					return;
				pedirQuantia.setVisible( false );
				display.setText( "A pagar " + quantia + "€");
				IntegerTextField pedeNum = new IntegerTextField( true );
				JOptionPane.showMessageDialog(JanelaTPA.this, pedeNum, "Introduza o número do cartão", JOptionPane.QUESTION_MESSAGE );		
				int cardNum = (int)pedeNum.getValor();
				if( cardNum != -1 ) {
					pagarComCartao( cardNum, quantia );
				}
			}
		});
		layout.putConstraint( SpringLayout.NORTH, display, 65, SpringLayout.NORTH, cartaoPanel );
		layout.putConstraint( SpringLayout.WEST, display, 20, SpringLayout.WEST, cartaoPanel );
		layout.putConstraint( SpringLayout.NORTH, pedirQuantia, 10, SpringLayout.SOUTH, display );
		layout.putConstraint( SpringLayout.WEST, pedirQuantia, 0, SpringLayout.WEST, display );
		layout.putConstraint( SpringLayout.EAST, pedirQuantia, 0, SpringLayout.EAST, display );
		layout.putConstraint( SpringLayout.WEST, okBt, 80, SpringLayout.WEST, cartaoPanel );
		layout.putConstraint( SpringLayout.NORTH, okBt, 278, SpringLayout.NORTH, cartaoPanel );
		cartaoPanel.add( display );
		cartaoPanel.add( pedirQuantia );
		cartaoPanel.add( okBt );
		return cartaoPanel;
	}

	/** apresenta o display de pedir a quantia
	 */
	private void setDisplayComprar( ) {
		display.setText("Introduza a quantia");
		pedirQuantia.setText( "" );
		pedirQuantia.setVisible( true );
	}
	
	/** apresenta o painel com uma mensagem temporária
	 * @param msg mensagem a apresentar
	 */
	private void displayMensagem( String msg) {
		display.setText(msg);
		timerMsg.restart();
		okBt.setEnabled( false );
	}
}
