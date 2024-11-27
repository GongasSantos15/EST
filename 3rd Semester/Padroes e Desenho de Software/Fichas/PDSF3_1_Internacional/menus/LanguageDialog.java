package menus;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import locale.LanguageLocale;

/**
 * Uma dialogo para apresentar a escolha da linguagem
 * @author F. Sergio Barbosa
 */
public class LanguageDialog extends JDialog {

	/** identificador da versão */
	private static final long serialVersionUID = 1L;
	
	/** objeto para fazer a conversão da linguagem */
	private LanguageLocale loc = LanguageLocale.getLocale();
	
	
	/** Cria a janela de diálogo de escolha de liguagem
	 * @param owner janela que criou este diálogo
	 */
	public LanguageDialog( JFrame owner ){
		super( owner );
		setTitle( loc.get("LANGDIALOG") );
		setSize( 320, 150 );
		setResizable( false );
		setLocationRelativeTo( owner ); // centrado na janela
		
		JPanel pane = new JPanel( );
		pane.setBorder( BorderFactory.createRaisedBevelBorder() );
		
		JLabel lLang = new JLabel(  loc.get("LANGCHOOSE") );
		pane.add( lLang );
		
		String lings[] = LanguageLocale.getLanguages(); 
		final JComboBox<String> lanBox = new JComboBox<String>( lings );
		lanBox.setSelectedIndex( loc.getSelectedLanguageIndex() );
		pane.add( lanBox );
		add( pane, BorderLayout.CENTER );

		JLabel lAviso = new JLabel( loc.get("LANGNEWEXECUTION") );
		pane.add( lAviso );
		
		// criar os botões ok e cancel
		JPanel bts = new JPanel( new FlowLayout(FlowLayout.RIGHT) );
		
		JButton btOK = new JButton( loc.get("OK"));
		JButton btCancel = new JButton( loc.get("CANCEL"));
		
		btOK.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				String newLangDef = (String)lanBox.getSelectedItem();
				LanguageLocale.setDefaultLanguage(newLangDef);
				dispose();
			}
			
		});
		
		btCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
			}		
		});
		
		
		bts.add( btOK);
		bts.add( btCancel);
		
		add( bts, BorderLayout.SOUTH );
	}
}
