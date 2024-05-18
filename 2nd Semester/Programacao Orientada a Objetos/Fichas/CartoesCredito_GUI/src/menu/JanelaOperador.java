package menu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import banco.Banco;
import banco.Conta;
import cartao.Cartao;

/** Representa a janela do operador do banco, onde se podem
 * realizar as várias operações que o banco disponibiliza aos seus clientes.
 * Neste caso é muito simplificada para se adequar à ficha em questão.
 * 
 * @author F. Sergio Barbosa
 *
 */
@SuppressWarnings("serial")
public class JanelaOperador extends JFrame {

	//constante para apresentar quantia por defeito
	private static final String SEM_QUANTIA = "----€";
	private Banco banco; // o banco sobre o qual se trabalha
	private Conta contaSel = null; // qual a conta selecionada
	
	// ícones para as imagens dos vários cartões  
	private ImageIcon cartaoComprasIcon = new ImageIcon( "recursos/cartao_compras.png" );
	private ImageIcon cartaoDebitoPlusIcon = new ImageIcon( "recursos/cartao_debitoplus.png" );
	private ImageIcon cartaoFimMesIcon = new ImageIcon( "recursos/cartao_fimmes.png" );
	private ImageIcon semCartoesIcon = new ImageIcon( "recursos/sem_cartoes.png" );
	
	/** Cria uma janela de operador
	 * @param title título da janela
	 * @param b banco que vai ser usado
	 */
	public JanelaOperador(String title, Banco b) {
		super(title);
		banco = b;
		setSize( 600, 180 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		inicializarInterface();	
		
		preencherListaContas( b.getContas() );
	}

	/** chamado quando o utilizador escolhe uma conta
	 * @param c conta escolhida
	 */
	protected void selecionarConta(Conta c) {
		contaSel = c;
		
		// TODO Colocar a informação nas váriaveis
		int numero = 0;
		long saldo = 0;  
		List<Cartao> cartoes = List.of();	
		
		numeroContaLbl.setText( ""+numero );
		saldoContaLbl.setText( saldo + "€");				
		// adicionar os cartões à lista
		cardModel.removeAllElements();
		for( Cartao card : cartoes ) {
			cardModel.addElement( card );
		}
		if( !cartoes.isEmpty() ) {
			cartoesCombo.setEnabled( true );
			cardModel.setSelectedItem( cartoes.get(0) );
		} else {
			cartoesCombo.setEnabled( false );
			porDadosCartaoDefault();
		}
	}
	
	/** chamado quando o utilizador seleciona a opção de depositar
	 * @param quantia a quantia a depositar
	 */
	protected void depositarQuantia(int quantia) {
		// TODO depositar a quantia na conta selecionada
				
	}

	/** chamado quando o utilizador seleciona um cartão
	 * @param card o cartão selecionado
	 */
	private void selecionarCartao(Cartao card) {
		// TODO Colocar a informação nas váriaveis
		long disponivel = 0;
		long plafond = 0;
		long gasto = 0;
		boolean ativo = true;
		
		disponivelLbl.setText( disponivel + "€" );
		plafondLbl.setText( plafond + "€" );
		gastoLbl.setText( gasto + "€" );
		ativoCb.setEnabled( true );		
		ativoCb.setSelected(ativo);
	}

	/** chamado quando o utilizador pretende criar uma conta
	 * @param saldo saldo da conta a criar
	 */
	protected void criarConta( long saldo ) {
		// TODO criar a conta e adicioná-la ao banco
		Conta c = null;
		
		numerosContas.addElement( c );
		JOptionPane.showMessageDialog(this, "Conta criada com o número " + c.getNumero() );
	}
	
	/** chamado quando o utilizador pretende criar um cartão
	 * @param c conta à qual o cartão vai ficar associado
	 * @param plafond plafond do cartão
	 * @param tipo tipo de cartão a criar
	 */
	protected void criarCartao(Conta c, long plafond, int tipo) {	
		// TODO criar o cartão correto e adicioná-lo ao banco
		Cartao card = null;
		switch( tipo ) {
		case 0: 
		break;
		case 1: 
		break;
		case 2: 
		break;
		default:
			JOptionPane.showMessageDialog(this, "Erro na criação do cartão");
			return;
		}
		banco.addCartao( card );

		cardModel.addElement(card);
		cardModel.setSelectedItem( card );
	}
	
	/** muda o estado de ativo do cartão selecionado
	 * @param card o cartão selecionado
	 * @param ativo novo estado de ativo do cartão
	 */
	protected void ativarCartao(Cartao card, boolean ativo ) {
		// TODO mudar o estado de ativo do cartão selecionado
	}

	/** chamado quando é preciso processar
	 * as contas no final do mês
	 */
	protected void fimMes() {
		// TODO fazer as operações de fim do mês e obter a lista dos cartões com problemas
		List<Cartao> problemas = List.of();
		if( problemas.isEmpty() )
			JOptionPane.showMessageDialog( this, "Tudo decorreu sem problemas");
		else {
			JList<Cartao> cards = new JList<Cartao>( problemas.toArray( new Cartao[ problemas.size()] ) );
			cards.setCellRenderer( new CartaoRenderer() );
			JScrollPane sp = new JScrollPane( cards );
			JOptionPane.showMessageDialog( this, sp, "Cartões com problemas", JOptionPane.ERROR_MESSAGE );
		}
	}

	// variáveis e métodos para a interface (não alterar a partir daqui)
	private JLabel numeroContaLbl = new JLabel("");
	private JLabel saldoContaLbl = new JLabel("");
	private DefaultListModel<Conta> numerosContas;
	private DefaultComboBoxModel<Cartao> cardModel;
	private JLabel disponivelLbl;
	private JLabel plafondLbl;
	private JLabel gastoLbl;
	private JComboBox<Cartao> cartoesCombo;
	private JCheckBox ativoCb;
	private JList<Conta> contasList;

	/** adiciona as contas à lista das contas na interface
	 * @param contas as contas a apresentar na lista
	 */
	private void preencherListaContas(Collection<Conta> contas) {
		List<Conta> ordenadas = contas.stream().sorted( (c1,c2)->c1.getNumero()-c2.getNumero() ).toList();
		for( Conta c : ordenadas ) {
			numerosContas.addElement( c );
			
		}
		if( !contas.isEmpty() ) {
			contasList.setSelectedIndex(0);
		}
	}

	/** apresenta um cartão que não está selecionado
	 */
	private void porDadosCartaoDefault() {
		disponivelLbl.setText( SEM_QUANTIA );
		gastoLbl.setText( SEM_QUANTIA );
		plafondLbl.setText( SEM_QUANTIA );
		ativoCb.setEnabled( false );
	}

	private void updateInterface( ) {
		if( contaSel != null ) {
			Cartao card = (Cartao)cardModel.getSelectedItem(); 
			selecionarConta( contaSel );
			if( card != null )
				cardModel.setSelectedItem(card);
		}
	}
	
	/** inicializa a interface da janela
	 */
	private void inicializarInterface() {
		SpringLayout layout = new SpringLayout();
		JPanel panel = new JPanel( layout );
		
		JPanel controlos = setupControlosConta( );
		JPanel dadosConta = setupDadosConta();
		
		layout.putConstraint( SpringLayout.WEST, controlos, 2, SpringLayout.WEST, panel);
		layout.putConstraint( SpringLayout.NORTH, controlos, 2, SpringLayout.NORTH, panel);
		layout.putConstraint( SpringLayout.SOUTH, controlos, 2, SpringLayout.SOUTH, panel);
		layout.putConstraint( SpringLayout.EAST, controlos, 200, SpringLayout.WEST, panel);

		layout.putConstraint( SpringLayout.WEST, dadosConta, 2, SpringLayout.EAST, controlos);
		layout.putConstraint( SpringLayout.EAST, dadosConta, -2, SpringLayout.EAST, panel);
		layout.putConstraint( SpringLayout.NORTH, dadosConta, 2, SpringLayout.NORTH, panel);
		layout.putConstraint( SpringLayout.SOUTH, dadosConta, 2, SpringLayout.SOUTH, panel);
		
		panel.add( controlos );
		panel.add( dadosConta );
		setContentPane( panel );
		
		new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateInterface();				
			}
		}).start();
	}

	/** cria o painel com os controlos de gestão das contas
	 * @return o painel configurado
	 */
	private JPanel setupControlosConta() {
		SpringLayout layout = new SpringLayout();
		JPanel panel = new JPanel( layout );
		
		JButton criarBt = new JButton( "<html>Nova<br>Conta</html>" );
		JButton fimMesBt = new JButton( "<html>Fim<br>mês</html>" );
		
		numerosContas = new DefaultListModel<Conta>(); 
		contasList = new JList<Conta>( numerosContas );
		contasList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		contasList.setCellRenderer( new ContaRenderer() );
		JScrollPane contasPane = new JScrollPane(contasList);

		layout.putConstraint( SpringLayout.NORTH, criarBt, 2, SpringLayout.NORTH, panel);
		layout.putConstraint( SpringLayout.SOUTH, criarBt, -1, SpringLayout.VERTICAL_CENTER, panel);
		layout.putConstraint( SpringLayout.WEST, criarBt, 2, SpringLayout.WEST, panel);
		layout.putConstraint( SpringLayout.EAST, criarBt, 80, SpringLayout.WEST, panel);
		
		layout.putConstraint( SpringLayout.NORTH, fimMesBt, 2, SpringLayout.SOUTH, criarBt);
		layout.putConstraint( SpringLayout.WEST, fimMesBt, 0, SpringLayout.WEST, criarBt);
		layout.putConstraint( SpringLayout.SOUTH, fimMesBt, 2, SpringLayout.SOUTH, panel);
		layout.putConstraint( SpringLayout.EAST, fimMesBt, 0, SpringLayout.EAST, criarBt);

		layout.putConstraint( SpringLayout.EAST, contasPane, 2, SpringLayout.EAST, panel);
		layout.putConstraint( SpringLayout.WEST, contasPane, 2, SpringLayout.EAST, criarBt );
		layout.putConstraint( SpringLayout.NORTH, contasPane, 2, SpringLayout.NORTH, panel);
		layout.putConstraint( SpringLayout.SOUTH, contasPane, 2, SpringLayout.SOUTH, panel );
		
		panel.add( criarBt );
		panel.add( fimMesBt );
		panel.add( contasPane );
		
		criarBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IntegerTextField text = new IntegerTextField( true );
				JOptionPane.showMessageDialog( JanelaOperador.this, text, "Introduza o saldo da conta", JOptionPane.QUESTION_MESSAGE );
				try {
					criarConta( Integer.parseInt( text.getText() ) );
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(JanelaOperador.this, "Operação cancelada!");
				}
			}
		});
		
		fimMesBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fimMes();
			}
		});
		
		contasList.addListSelectionListener( new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if( e.getValueIsAdjusting() )
					return;
				selecionarConta( numerosContas.get( contasList.getSelectedIndex() ));				
			}
		});
		
		panel.setBorder( BorderFactory.createTitledBorder("Controlo Contas"));
		return panel;
	}

	/** inicializa o painel com as iformações da conta
	 * isto é, o saldo e os cartões que a conta possui
	 * @return o painel configurado
	 */
	public JPanel setupDadosConta() {
		SpringLayout layout = new SpringLayout();
		JPanel panel = new JPanel( layout );

		numeroContaLbl = new JLabel("----");
		numeroContaLbl.setFont( new Font("ROMAN", Font.BOLD, 20 ) );
		saldoContaLbl = new JLabel( SEM_QUANTIA );
		saldoContaLbl.setFont( new Font("ROMAN", Font.BOLD, 16 ) );
		JButton criarBt = new JButton("Novo Cartao");
		JButton depositarBt = new JButton("Depositar");

		layout.putConstraint( SpringLayout.WEST, numeroContaLbl, 4, SpringLayout.WEST, panel );
		layout.putConstraint( SpringLayout.NORTH, numeroContaLbl, 4, SpringLayout.NORTH, panel );
		layout.putConstraint( SpringLayout.WEST, saldoContaLbl, 5, SpringLayout.WEST, numeroContaLbl );
		layout.putConstraint( SpringLayout.NORTH, saldoContaLbl, 5, SpringLayout.SOUTH, numeroContaLbl );
		layout.putConstraint( SpringLayout.WEST, depositarBt, 0, SpringLayout.WEST, saldoContaLbl );
		layout.putConstraint( SpringLayout.NORTH, depositarBt, 2, SpringLayout.SOUTH, saldoContaLbl );
		layout.putConstraint( SpringLayout.EAST, depositarBt, 0, SpringLayout.EAST, criarBt );
		layout.putConstraint( SpringLayout.WEST, criarBt, 0, SpringLayout.WEST, saldoContaLbl );
		layout.putConstraint( SpringLayout.NORTH, criarBt, 2, SpringLayout.SOUTH, depositarBt );
		
		JPanel cartoesPanel = new JPanel( new BorderLayout() );
		cardModel = new DefaultComboBoxModel<Cartao>(); 
		cartoesCombo = new JComboBox<Cartao>( cardModel );
		
		cartoesCombo.setPreferredSize( new Dimension(120,70) );
		cartoesPanel.setBorder( BorderFactory.createTitledBorder("Cartões") );
		
		cartoesPanel.add( new JScrollPane( cartoesCombo ), BorderLayout.CENTER );

		layout.putConstraint( SpringLayout.WEST, cartoesPanel, 10, SpringLayout.EAST, criarBt );
		layout.putConstraint( SpringLayout.NORTH, cartoesPanel, 2, SpringLayout.NORTH, panel );

		disponivelLbl = new JLabel(SEM_QUANTIA);
		disponivelLbl.setFont( new Font("ROMAN", Font.BOLD, 16 ) );
		plafondLbl = new JLabel(SEM_QUANTIA);
		gastoLbl = new JLabel(SEM_QUANTIA);
		ativoCb = new JCheckBox("Ativo");
		
		layout.putConstraint( SpringLayout.WEST, disponivelLbl, 5, SpringLayout.EAST, cartoesPanel );
		layout.putConstraint( SpringLayout.NORTH, disponivelLbl, 2, SpringLayout.NORTH, cartoesPanel );
		layout.putConstraint( SpringLayout.WEST, plafondLbl, 0, SpringLayout.WEST, disponivelLbl );
		layout.putConstraint( SpringLayout.NORTH, plafondLbl, 2, SpringLayout.SOUTH, disponivelLbl );
		layout.putConstraint( SpringLayout.WEST, gastoLbl, 0, SpringLayout.WEST, disponivelLbl );
		layout.putConstraint( SpringLayout.NORTH, gastoLbl, 2, SpringLayout.SOUTH, plafondLbl );
		layout.putConstraint( SpringLayout.WEST, ativoCb, 0, SpringLayout.WEST, disponivelLbl );
		layout.putConstraint( SpringLayout.NORTH, ativoCb, 10, SpringLayout.SOUTH, gastoLbl );
		
		panel.add( numeroContaLbl );
		panel.add( saldoContaLbl );
		panel.add( depositarBt );
		panel.add( criarBt );
		panel.add( cartoesPanel );
		panel.add( disponivelLbl );
		panel.add( plafondLbl );
		panel.add( gastoLbl );
		panel.add( ativoCb );
				
		cartoesCombo.setRenderer( new CartaoRenderer() );
		cartoesCombo.addItemListener( new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if( e.getStateChange() == ItemEvent.SELECTED )
					selecionarCartao( (Cartao)e.getItem() );
			}
		});
		
		depositarBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IntegerTextField quantiaText = new IntegerTextField( true );
				JOptionPane.showMessageDialog(JanelaOperador.this, quantiaText, "Quantia a depositar?", JOptionPane.QUESTION_MESSAGE );		
				int quantia = (int)quantiaText.getValor();
				if( quantia != -1 ) {
					depositarQuantia( quantia );
				}
			}
		});
		
		criarBt.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ImageIcon []icons = { cartaoComprasIcon, cartaoDebitoPlusIcon, cartaoFimMesIcon };
				IntegerTextField plafondText = new IntegerTextField( true );
				int res = JOptionPane.showOptionDialog( JanelaOperador.this, plafondText, "Introduza plafond e tipo de cartão", JOptionPane.OK_CANCEL_OPTION,
						                                JOptionPane.QUESTION_MESSAGE, null, icons, icons[0]);
				if( plafondText.getText().isEmpty() )
					JOptionPane.showMessageDialog( JanelaOperador.this, "Plafond não definido!");
				else if( res != JOptionPane.CLOSED_OPTION )
					criarCartao( contaSel, Long.parseLong( plafondText.getText() ), res );
			}
		});
		
		ativoCb.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ativarCartao( (Cartao)cardModel.getSelectedItem(), ativoCb.isSelected() );
			}
		});
		
		panel.setBorder( BorderFactory.createTitledBorder("Dados Conta"));
		return panel;
	}


	/** responsável por desenhar as contas nas células da lista
	 */
	private class ContaRenderer extends BasicComboBoxRenderer {
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			Conta conta = (Conta)value;
			if( conta != null )
				setText( ""+conta.getNumero() );
			return this;
		}
	}

	/** responsável por desenhar os cartões nas células da lista
	 */
	private class CartaoRenderer extends BasicComboBoxRenderer {
		
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, "TESTE", index, isSelected, cellHasFocus);
			Cartao card = (Cartao)value;
			if( card != null ) {
				setIcon( new ImageIcon( card.getImagem()) );
				setText( "" );
			} else {
				setIcon( semCartoesIcon );
			}
			return this;
		}
	}
	
}
