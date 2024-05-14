package menu;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import banco.Banco;
import cartao.Cartao;

/** Janela que simula uma máquina de pagamento automático (ATM em inglês),
 *  vulgo Multibanco. Tem uma simplificação enorme, mas serve para esta ficha
 */
public class JanelaATM extends JDialog {
	/** imagens para os vários icones */
	private final static ImageIcon ECRAN_INSERIR_IMG = new ImageIcon( "recursos/atm_inserir.png" );
	private final static ImageIcon ECRAN_RETIRAR = new ImageIcon( "recursos/atm_retirar.png" );
	private final static ImageIcon ECRAN_ERRO_CARTAO = new ImageIcon( "recursos/atm_erro_cartao.png" );
	private final static ImageIcon ECRAN_ERRO_OPERACAO = new ImageIcon( "recursos/atm_erro_operacao.png" );
	private final static ImageIcon ECRAN_ERRO_RETER = new ImageIcon( "recursos/atm_erro_reter.png" );
	private final static ImageIcon BOTAO_ESQ_ICON = new ImageIcon( "recursos/botao_esq.png" );
	private final static ImageIcon BOTAO_ESQOVER_ICON = new ImageIcon( "recursos/botao_esq_over.png" );
	private final static ImageIcon BOTAO_DIR_ICON = new ImageIcon( "recursos/botao_dir.png" );
	private final static ImageIcon BOTAO_DIROVER_ICON = new ImageIcon( "recursos/botao_dir_over.png" );
	
	private Banco banco;			// o banco com o qual este multibanco comunica
	private Cartao cartaoInserido;  // o cartão que está inserido
	
	/** Cria a janela que simula o multibanco
	 * @param owner janela do sistema 
	 * @param title Título a colocar nesta janela
	 * @param b o banco com o qual este multibanco tem de comunicar
	 */
	public JanelaATM(Frame owner, String title, Banco b ) {
		super(owner, title);
		banco = b;
		inicializarInterface();
	}
	
	/** método chamado quando um cartão é inserido na máquina
	 * @param cardNum número do cartão
	 */
	protected void cartaoInserido(int cardNum) {
		// TODO saber qual o cartão escolhido
		cartaoInserido = null;
		if( cartaoInserido == null ) {
			showPainelTemporario( ECRAN_ERRO_CARTAO );
		} else  {
			showPainelQuantia();
		}
	}

	/** método chamado quanto se pressiona um dos
	 * botões de selecionar a quantia a levantar
	 * @param quantia a quantia a levantar
	 */
	protected void levantarQuantia(long quantia) {
		// TODO levantar a quantia usando o cartao inserido
		
		// mensagem a apresentar em caso de sucesso
		showPainelTemporario( ECRAN_RETIRAR );
		// mensagem a apresentar em caso de não ter saldo
		showPainelTemporario( ECRAN_ERRO_OPERACAO );
		// mensagem a apresentar em caso de cartão não ativo
		showPainelTemporario( ECRAN_ERRO_RETER );		
	}
	
	// variáveis para a interface (não é preciso alterar depois daqui)
	/** serial id */
	private static final long serialVersionUID = 1L;

	private Timer timerMsg;  // o temporizador para fechar a mensagem de erro
	private JLabel msgLbl;   // o labekl onde se apresenta o ícone da mensagem de erro
	private JPanel ecrans;    // gestor dos vários paineis do multibanco
	
	// constantes para a gestão dos paineis
	private static final String ECRAN_INTRODUZIR = "Introduzir";
	private static final String ECRAN_QUANTIA = "Quantia";
	private static final String ECRAN_ERRO = "Erro";
	private static final Dimension dimIcon = new Dimension( new Dimension(BOTAO_ESQ_ICON.getIconWidth(), BOTAO_ESQ_ICON.getIconHeight()) );
	
	/** inicializa a interface
	 */
	private void inicializarInterface() {
		setSize( 200, 180 );
		setResizable( false );
		JPanel pedirCartao = inicializarPedirCartao();
		JPanel pedirQuantia = inicializarPedirQuantia();
		JPanel erroPanel = inicializarEcranTemporario();
		ecrans = new JPanel( new CardLayout() );
		ecrans.add( pedirCartao, ECRAN_INTRODUZIR );
		ecrans.add( pedirQuantia, ECRAN_QUANTIA );
		ecrans.add( erroPanel, ECRAN_ERRO );
		setContentPane( ecrans );
		pack();
	}
	
	/** inicializa o painel de pedir o cartão
	 * @return o painel já configurado
	 */
	private JPanel inicializarPedirCartao() {
		JLabel ecranLbl = new JLabel( ECRAN_INSERIR_IMG );
		
		JPanel cartaoPanel = new JPanel( new BorderLayout() );
		JButton pedeCartaoBt = new JButton("Introduzir cartão");
		pedeCartaoBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IntegerTextField pedeNum = new IntegerTextField( true );
				JOptionPane.showMessageDialog(JanelaATM.this, pedeNum, "Introduza o número do cartão", JOptionPane.QUESTION_MESSAGE );		
				int cardNum = (int)pedeNum.getValor();
				if( cardNum != -1 ) {
					cartaoInserido( cardNum );
				}
			}

		});
		cartaoPanel.add( ecranLbl, BorderLayout.CENTER );
		cartaoPanel.add( pedeCartaoBt, BorderLayout.SOUTH );
		return cartaoPanel;
	}

	/** inicializa o painel com os vários botões de pedir quantia
	 * @return o painel já configurado
	 */
	private JPanel inicializarPedirQuantia() {
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints cesq = new GridBagConstraints();
		cesq.anchor = GridBagConstraints.CENTER;
		cesq.weighty = 0.2f;		
		GridBagConstraints cdir = (GridBagConstraints) cesq.clone();
		cdir.weightx = 1;
		cdir.gridwidth = GridBagConstraints.REMAINDER;
		cdir.anchor = GridBagConstraints.EAST;
		JPanel quantiaPanel = new JPanel( layout );
		quantiaPanel.setBackground( new Color( 5, 107, 245) );
		
		quantiaPanel.add( criarBotao(20, true), cesq );
		quantiaPanel.add( criarBotao(100, false), cdir );
		quantiaPanel.add( criarBotao(40, true), cesq );
		quantiaPanel.add( criarBotao(120, false), cdir );
		quantiaPanel.add( criarBotao(60, true), cesq );
		quantiaPanel.add( criarBotao(150, false), cdir );
		quantiaPanel.add( criarBotao(80, true), cesq );
		quantiaPanel.add( criarBotao(200, false), cdir );
		return quantiaPanel;
	}

	/** cria um botão que apresenta uma quantia
	 * @param quantia quantia associada ao botão
	 * @param esquerda se está na esquerda ou na direita
	 * @return o botão já configurado
	 */
	private JButton criarBotao( final long quantia, boolean esquerda ) {
		JButton bt = new JButton("" + quantia );
		bt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				levantarQuantia( quantia );
			}
		});
		if( esquerda ) {
			bt.setIcon( BOTAO_ESQ_ICON );
			bt.setRolloverIcon(BOTAO_ESQOVER_ICON);
		} else {
			bt.setIcon( BOTAO_DIR_ICON );
			bt.setRolloverIcon(BOTAO_DIROVER_ICON);
		}
		bt.setSize( dimIcon );
		bt.setForeground( Color.WHITE );
		bt.setPreferredSize( dimIcon );
		bt.setHorizontalTextPosition( JButton.CENTER );
		bt.setOpaque(false);
		bt.setContentAreaFilled(false);
		bt.setBorderPainted(false);
		return bt;
	}
	
	/** inicializa o painel com uma mensagem temporária
	 * @return o painel já configurado
	 */
	private JPanel inicializarEcranTemporario( ) {
		JPanel erroPanel = new JPanel( new BorderLayout() );
		msgLbl = new JLabel( );
		JButton okBt = new JButton("ok");

		ActionListener terminaErro = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showPainelCartao();
				timerMsg.stop();
			}
		};

		okBt.addActionListener( terminaErro ); 
		
		timerMsg = new Timer( 3000, terminaErro );
		erroPanel.add( msgLbl, BorderLayout.CENTER );
		erroPanel.add( okBt, BorderLayout.SOUTH );
		return erroPanel;
	}

	/** apresenta o painel de inserir cartão
	 */
	private void showPainelCartao() {
		((CardLayout)ecrans.getLayout()).show( ecrans, ECRAN_INTRODUZIR );
	}

	/** apresenta o painel com os botões de quantia
	 */
	private void showPainelQuantia() {
		((CardLayout)ecrans.getLayout()).show( ecrans, ECRAN_QUANTIA );
	}

	/** apresenta o painel com uma mensagem temporária
	 * @param iconMsg ícone representativo da mensagem
	 */
	private void showPainelTemporario( Icon iconMsg) {
		msgLbl.setIcon( iconMsg );
		((CardLayout)ecrans.getLayout()).show( ecrans, ECRAN_ERRO );
		timerMsg.restart();
	}
}
