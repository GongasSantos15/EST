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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.Timer;

import contrato.PagamentoTicketException;
import estacionamento.ESTacionamento;
import estacionamento.MatriculaDesconhecidaException;

/* Esta janela simula um Terminal de Pagamento Automático (TPA)
 * 
 */
public class JanelaPagamentos extends JDialog {

	/** imagens para os vários icones */
	private final static ImageIcon IMAGEM_TPA = new ImageIcon( ClassLoader.getSystemResource("icons/maq_paga.png") );
	private final static ImageIcon TECLADO_ICON = new ImageIcon( ClassLoader.getSystemResource("icons/teclado.png") );
	private final static ImageIcon MOEDEIRO_ICON = new ImageIcon( ClassLoader.getSystemResource("icons/moedeiro.png") );
	
	private ESTacionamento parque;			// o parque com o qual esta máquina trabalha
	
	/** Cria a janela que simula o TPA
	 * @param owner janela do sistema 
	 * @param b o banco com o qual este TPA tem de comunicar
	 */
	public JanelaPagamentos(Frame owner, ESTacionamento b ) {
		super( owner );
		parque = b;
		inicializarInterface();
	}

	/** Método chamado quando é para pagar com o cartão
	 * @param cardNum número do cartão
	 * @param quantia quantia a pagar
	 * @throws MatriculaDesconhecidaException 
	 */
	protected void calcularPagamento( String matricula ) {
		// TODO FEITO processar o pagamento
		
		try {
			long custo = parque.calcularCusto(matricula);;
			displayMensagemCusto( "Pagar " + custo + "€" );
		} catch (MatriculaDesconhecidaException e) {
			displayMensagem( "Matrícula errada" );
		} catch (PagamentoTicketException e) {
			displayMensagem( "Já está pago" );	
		}
	}
	
	protected void estacionamentoPago(String matricula) {
		// TODO FEITO Estacionamento foi pago, indicar isso mesmo
		try {
			parque.pagar(matricula);
			// Usar a mensagem adequada 
			displayMensagem( "Remover em " + ESTacionamento.TEMPO_RETIRADA + " mins" );
		} catch (MatriculaDesconhecidaException e) {
			// TODO nem todos os parques tem este tempo de retirada
			displayMensagem( "Matrícula errada" );	
		}
		
		
	}

	
	// variáveis para a interface (não é preciso altertar nada a partir daqui)
	/** serial id */
	private static final long serialVersionUID = 1L;

	private Timer timerMsg;
	private JLabel display;
	private JTextField matriculaTF;
	private JButton tecladoBt;
	
	
	/** inicializa a interface
	 */
	private void inicializarInterface() {
		//setSize( 200, 180 );
		setResizable( false );
		JPanel pedirCartao = inicializarDisplay();
		pedirCartao.setPreferredSize( new Dimension(IMAGEM_TPA.getIconWidth(), IMAGEM_TPA.getIconHeight()) );
		setContentPane( pedirCartao );
		pedirCartao.getRootPane().setDefaultButton(tecladoBt);
		setDisplayComprar();
		setUndecorated( true );
		setBackground( new Color(1,1,1,0 ) );
		pack();
		
		timerMsg = new Timer( 3000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setDisplayComprar();
				timerMsg.stop();
				tecladoBt.setEnabled( true );
			}
		} );
		
		MouseAdapter moveJanela = new MouseAdapter() {
			int offx, offy;
			@Override
			public void mousePressed(MouseEvent e) {
				Point topo = JanelaPagamentos.this.getLocationOnScreen();
				Point aqui = e.getLocationOnScreen();
				offx = topo.x - aqui.x;
				offy = topo.y - aqui.y;
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				Point aqui = e.getLocationOnScreen();
				JanelaPagamentos.this.setLocation(aqui.x + offx, aqui.y + offy);
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
		display.setHorizontalAlignment( JLabel.CENTER );
		matriculaTF = new JTextField(  );
		matriculaTF.setForeground( Color.WHITE );
		matriculaTF.setOpaque( false );
		tecladoBt = new JButton( TECLADO_ICON );
		//okBt.setRolloverIcon( TPA_OKHOVER_ICON );
		tecladoBt.setDisabledIcon( TECLADO_ICON );
		tecladoBt.setOpaque(false);
		tecladoBt.setContentAreaFilled(false);
		tecladoBt.setBorderPainted(false);
		tecladoBt.addActionListener( new ActionListener() {
			private String matricula;

			@Override
			public void actionPerformed(ActionEvent e) {
				if( tecladoBt.getIcon() == TECLADO_ICON ) {
					matricula = matriculaTF.getText();
					System.out.println( matricula );
					if( matricula == null || matricula.isBlank() )
						return;
					matriculaTF.setVisible( false );
					calcularPagamento(matricula);
				}
				else {
					estacionamentoPago( matricula );
				}
			}
		});
		
		layout.putConstraint( SpringLayout.NORTH, display, 35, SpringLayout.NORTH, cartaoPanel );
		layout.putConstraint( SpringLayout.WEST, display, 13, SpringLayout.WEST, cartaoPanel );
		layout.putConstraint( SpringLayout.EAST, display, -15, SpringLayout.EAST, cartaoPanel );
		layout.putConstraint( SpringLayout.NORTH, matriculaTF, 10, SpringLayout.SOUTH, display );
		layout.putConstraint( SpringLayout.WEST, matriculaTF, 0, SpringLayout.WEST, display );
		layout.putConstraint( SpringLayout.EAST, matriculaTF, 0, SpringLayout.EAST, display );
		layout.putConstraint( SpringLayout.WEST, tecladoBt, -7, SpringLayout.WEST, cartaoPanel );
		layout.putConstraint( SpringLayout.NORTH, tecladoBt, 115, SpringLayout.NORTH, cartaoPanel );
	
		cartaoPanel.add( display );
		cartaoPanel.add( matriculaTF );
		cartaoPanel.add( tecladoBt );
		
		return cartaoPanel;
	}


	/** apresenta o display de pedir a quantia
	 */
	private void setDisplayComprar( ) {
		display.setText("Matrícula?");
		matriculaTF.setText( "" );
		matriculaTF.setVisible( true );
		tecladoBt.setIcon(TECLADO_ICON);
		matriculaTF.requestFocus();
	}

	/** apresenta o painel com uma mensagem do custo
	 * @param msg mensagem a apresentar
	 */
	private void displayMensagemCusto( String msg) {
		display.setText(msg);
		tecladoBt.setIcon( MOEDEIRO_ICON );
	}

	
	/** apresenta o painel com uma mensagem temporária
	 * @param msg mensagem a apresentar
	 */
	private void displayMensagem( String msg) {
		display.setText(msg);
		timerMsg.restart();
		tecladoBt.setEnabled( false );
	}
}
