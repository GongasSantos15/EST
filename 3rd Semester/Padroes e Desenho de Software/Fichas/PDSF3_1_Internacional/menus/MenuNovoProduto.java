package menus;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import locale.LanguageLocale;

import stock.Produto;
import stock.StockManager;

/** Janela que pede os dados para um novo produto.
 */
public class MenuNovoProduto extends JDialog {

	private static final long serialVersionUID = 1L;
	
	/** variáveis para os componentes gráficos */
	private JTextField idTF;		// textfield para pedir o id do produto
	private JTextField descTF;		// textField para pedir a descrição do produto 
	private JTextField pvpTF;		// textField para pedir o preço de venda do produto
	private JTextField pCustoTF;	// textField para pedir o preço de custo do produto
	private JTextField quantTF;		// textField para pedir a quantidade inicial do produto
	private StockManager stock;     // stock de produtos
	
	/** objeto que faz as conversões para as linguagens */
	private LanguageLocale loc = LanguageLocale.getLocale();

	/** Cria o diálogo de novo produto */
	public MenuNovoProduto(Window owner, StockManager stock ) {
		super(owner);
		setTitle( loc.get("NEWPROD_TITLE") );
		setupGUI();
		setResizable( false );
		setModalityType( ModalityType.APPLICATION_MODAL );
		this.stock = stock;
	}

	/** configura o diálogo de novo produto */
	private void setupGUI() {
		setSize(300, 200);
		GridBagLayout gbl = new GridBagLayout();
		JPanel pane = new JPanel( gbl );
		pane.setLayout( gbl );
		// gbc para os componentes
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		// criar o label para pedir o id
		JLabel idLbl = new JLabel( loc.get("PROD_ID") );
		pane.add( idLbl, gbc );
		
		// criar o textfield para pedir o id
		idTF = new JTextField();
		gbc.gridx = 1;
		gbc.ipadx = 70;
		pane.add( idTF, gbc );
		idTF.addFocusListener( new FocusAdapter(){
			public void focusLost(FocusEvent e) {
				checkIdValido();
			}
		});
		
		// criar o label para pedir a descrição
		JLabel descLbl = new JLabel( loc.get("DESCRIPTION"));
		gbc.gridx = 0; gbc.gridy = 1;
		gbc.ipadx = 0;
		pane.add( descLbl, gbc );
		// criar o textfield para pedir a descrição
		descTF = new JTextField();
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		pane.add( descTF, gbc );
		
		// criar o label para pedir o preço de venda
		JLabel pvpLbl = new JLabel( loc.get("SALE_PRICE") );
		gbc.gridx = 0; gbc.gridy = 2;
		gbc.ipadx = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		pane.add( pvpLbl, gbc );
		// criar o textfield para pedir o preço de venda
		pvpTF = new JTextField();
		gbc.gridx = 1;						
		pane.add( pvpTF, gbc );

		// criar o label para pedir o preço de custo
		JLabel pcLbl = new JLabel( loc.get("RETAIL_PRICE") );
		gbc.gridx = 0; gbc.gridy = 3;
		gbc.ipadx = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		pane.add( pcLbl, gbc );
		// criar o textfield para pedir o preço de custo
		pCustoTF = new JTextField();
		gbc.gridx = 1;						
		pane.add( pCustoTF, gbc );		
		
		// criar o label para pedir a quantidade inicial
		JLabel quantLbl = new JLabel( loc.get("QUANTITY") );
		gbc.gridx = 0; gbc.gridy = 4;
		gbc.ipadx = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		pane.add( quantLbl, gbc );
		// criar o textfield para pedir o preço de custo
		quantTF = new JTextField();
		gbc.gridx = 1;						
		pane.add( quantTF, gbc );				
		
		// criar o bot�o de ok e o de cancel
		JPanel bts = new JPanel( );
		JButton ok = new JButton( loc.get("OK") );
		bts.add( ok );
		JButton cancel = new JButton( loc.get("CANCEL") );
		bts.add( cancel );
		
		ok.addActionListener( new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {				
				// testar os valores todos
				int id = checkIdValido();
				if(  id == -1 ) return;  // id inválido, as mensagens de erros já foram dadas
				int pvp = checkNumValido( pvpTF.getText() ); 
				if( pvp == - 1 ) return;
				int pc = checkNumValido( pCustoTF.getText() ); 
				if( pc == -1 ) return;
				int quant = checkNumValido( quantTF.getText() ); 
				if( quant == -1 ) return;
				if( descTF.getText().isEmpty() ){
					JOptionPane.showMessageDialog( MenuNovoProduto.this, loc.get("EMPTY_DESC") );
					return;
				}
				// criar o produto
				Produto p = new Produto( id, descTF.getText(), pvp, pc );
				stock.addProduto(p, quant);
				setVisible( false );
			}			
		});
		
		cancel.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		getContentPane().add( pane, BorderLayout.CENTER );
		getContentPane().add( bts, BorderLayout.SOUTH );
	}

	/**
	 * retorna o produto com base no id introduzido. Já testa se ele existe
	 * @return o produto com base no id introduzido
	 */
	private int checkIdValido() {
		try {
			int id = Integer.parseInt( idTF.getText() );
			Produto p = stock.getProduto( id );
			if( p != null )
				JOptionPane.showMessageDialog( MenuNovoProduto.this, loc.get("SAME_PROD") );
			return id;
		} catch (NumberFormatException ex ) {
			JOptionPane.showMessageDialog( MenuNovoProduto.this, loc.get("ID_NUM") );
		}
		return -1;
	}
	
	/** veridica se a quantidade introduzida é válida
	 * @param text mensagem a pedir o valor
	 * @return a quantidade se esta for válida
	 */
	private int checkNumValido(String text) {
		try {
			int valor = Integer.parseInt( text );
			if( valor < 0 )
				JOptionPane.showMessageDialog( MenuNovoProduto.this, loc.get("PRICE_QUANT_POS") );
			return valor;
		} catch (NumberFormatException ex ) {
			JOptionPane.showMessageDialog( MenuNovoProduto.this, loc.get("PRICE_QUANT_NUM") );
		}
		return -1;
	}	
}
