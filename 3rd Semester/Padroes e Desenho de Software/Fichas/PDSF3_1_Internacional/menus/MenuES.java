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

import stock.OutOfStockException;
import stock.Produto;
import stock.StockManager;

/** Janela de diálogo para entrada e saída de produtos
 */
public class MenuES extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JTextField idTF;		// para pedir o id do produto a entrar/sair
	private JTextField quantTF;		// para pedir a quantidade de produto a entrar/sair
	private JLabel descLbl;			// label pra mostrar a descrição do produto
	private StockManager stock;		// stock para validar os produtos
	private int inout;				// indica se é para sair ou entrar
	
	private LanguageLocale loc = LanguageLocale.getLocale();

	/** cria a janela de entra e saida de produtos
	 * @param owner janela que criou este diálogo
	 * @param title título a aparecer na janela
	 * @param stock o gestor de stocks
	 * @param inout se é para dar entrada (1) ou saída (-1)
	 */
	public MenuES(Window owner, String title, StockManager stock, int inout) {
		super(owner, title);
		setupGUI();
		setResizable( false );
		setModalityType( ModalityType.APPLICATION_MODAL );
		this.stock = stock;
		this.inout = inout;
	}

	/**
	 * desenhar interface gr�fica
	 */
	private void setupGUI() {
		setSize(300, 150);
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
				getProduto();
			}
		});
		
		// criar o label para pedir a quantidade
		JLabel quantLbl = new JLabel( loc.get("QUANTITY") );
		gbc.gridx = 0; gbc.gridy = 1;
		gbc.ipadx = 0;
		pane.add( quantLbl, gbc );
		// criar o textfield para pedir a quantidade
		quantTF = new JTextField();
		gbc.gridx = 1;
		gbc.ipadx = 70;
		pane.add( quantTF, gbc );
		
		// criar o label para mostrar a descrição
		JLabel dLbl = new JLabel( loc.get("DESCRIPTION") );
		gbc.gridx = 0; gbc.gridy = 2;
		gbc.ipadx = 0;
		pane.add( dLbl, gbc );
		// criar o label para mostrar a descrição
		descLbl = new JLabel( "..." );
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		pane.add( descLbl, gbc );
		
		// criar o bot�o de ok e o de cancel
		JPanel bts = new JPanel( );
		JButton ok = new JButton( loc.get("OK") );
		bts.add( ok );
		JButton cancel = new JButton( loc.get("CANCEL") );
		bts.add( cancel );
		
		ok.addActionListener( new ActionListener(){ 
			public void actionPerformed(ActionEvent e) {
				try {
					Produto p = getProduto();
					if( p == null ) return;  // as mensagens de erros já foram dadas
					
					int quant = Integer.parseInt( quantTF.getText() );
					if( quant < 0 ){
						JOptionPane.showMessageDialog( MenuES.this, loc.get("QUANT_POS") );
						return;
					}
					stock.alteraExistencias( p, quant * inout);
					setVisible( false );
				} catch (OutOfStockException ex) {
					JOptionPane.showMessageDialog( MenuES.this, loc.get("NO_STOCK") );					
				} catch( NumberFormatException ex){
					JOptionPane.showMessageDialog( MenuES.this, loc.get("QUANT_NUM") );
				}
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
	private Produto getProduto() {
		try {
			int id = Integer.parseInt( idTF.getText() );
			Produto p = stock.getProduto( id );
			if( inout > 0 && !p.eRenovavel() ){
				JOptionPane.showMessageDialog( MenuES.this, loc.get("NO_STOCKREFILL") );
				idTF.setText("");
				return null;
			}
			if( inout < 0 && p.eIlimitado() ){
				JOptionPane.showMessageDialog( MenuES.this, loc.get("NO_STOCKREMOVE") );
				idTF.setText("");
				return null;
			}
			descLbl.setText( p.getDescricao() );
			return p;
		} catch (NumberFormatException ex ) {
			JOptionPane.showMessageDialog( MenuES.this, loc.get("ID_NUM") );
			idTF.setText("");
		} catch( NullPointerException ex ) {
			JOptionPane.showMessageDialog( MenuES.this, loc.get("NO_PRODUCT") );
			idTF.setText("");
		}
		return null;
	}	
}
