package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import locale.LanguageLocale;
import stock.Produto;
import stock.StockManager;

/** Janela principal da aplicação de vendas, que se pretende seja capaz de 
 * suportar  várias línguas.  
 */
public class JanelaPrincipal extends JFrame implements ActionListener {

	/** identificador da versão */
	private static final long serialVersionUID = 1L;

	/** Objeto que trata das conversões da linguagem */
	private LanguageLocale loc = LanguageLocale.getLocale();
	
	/** Objeto que mantém o stock de produtos */
	StockManager stock = new StockManager();
	
	/** cria a janela pricipal */
	public JanelaPrincipal(){
		super(  );
		setTitle( loc.get("TITLE") );
		setSize( 800,200);
		setLocation( 100, 200 );  
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setupMenu();	
		setupStock();
	}
	
	/** prepara o menu principal */
	private void setupMenu(){
		// criar o menu file
		JMenu file = new JMenu( loc.get( "FILE" ) );
		file.add( createMenuItem( "NEW", "new", null, this ) );
		file.add( createMenuItem( "OPEN", "open", null, this ) );
		file.add( createMenuItem( "SAVE", "save", KeyStroke.getKeyStroke( KeyEvent.VK_S, KeyEvent.CTRL_MASK ), this ) );
		file.add( createMenuItem( "SAVEAS", "saveas", null, this ) );
		JMenuItem exit = createMenuItem( "EXIT", "exit", null, this );
		file.add( exit );
		
		exit.addActionListener( new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JanelaPrincipal.this.dispose();
			}
		});
		
		// criar o menu edit
		JMenu edit = new JMenu( loc.get( "EDIT" ) );
		edit.add( createMenuItem( "UNDO", "undo", KeyStroke.getKeyStroke( KeyEvent.VK_Z, KeyEvent.CTRL_MASK ), this ) );
		edit.add( createMenuItem( "REDO", "redo", KeyStroke.getKeyStroke( KeyEvent.VK_Y, KeyEvent.CTRL_MASK ), this ) );
		edit.addSeparator();
		edit.add( createMenuItem( "CUT", "cut", KeyStroke.getKeyStroke( KeyEvent.VK_X, KeyEvent.CTRL_MASK ), this ) );
		edit.add( createMenuItem( "COPY", "copy", KeyStroke.getKeyStroke( KeyEvent.VK_C, KeyEvent.CTRL_MASK ), this ) );
		edit.add( createMenuItem( "PASTE", "paste", KeyStroke.getKeyStroke( KeyEvent.VK_V, KeyEvent.CTRL_MASK ), this ) );
		
		// criar o menu Gest�o
		JMenu manag = new JMenu( loc.get( "MANAGING" ) );
		manag.add( createMenuItem( "OUTPROD", "outprod", KeyStroke.getKeyStroke( KeyEvent.VK_I, KeyEvent.CTRL_MASK ), this ) );
		manag.add( createMenuItem( "INPROD", "inprod", KeyStroke.getKeyStroke( KeyEvent.VK_O, KeyEvent.CTRL_MASK ), this ) );
		manag.addSeparator();
		manag.add( createMenuItem( "NEWPROD", "newprod", null, this ) );
		
		// criar o menu configurar
		JMenu config = new JMenu( loc.get( "CONFIGURE" ) );
		config.add( createMenuItem( "LANGUAGE", "language", null, this ) );
				
		// criar o menu help
		JMenu help = new JMenu( loc.get( "HELP" ) );
		help.add( createMenuItem( "ABOUT", "about", null, this ) );
						
		// criar o menu principal
		JMenuBar mainMenu = new JMenuBar();
		mainMenu.add( file );
		mainMenu.add( edit );
		mainMenu.add( manag );
		mainMenu.add( config );
		mainMenu.add( help );
		
		setJMenuBar( mainMenu );
	}

	/** método que ajuda a criar menu items */
	private JMenuItem createMenuItem( String text, String action, KeyStroke key, ActionListener list ){
		JMenuItem menu = new JMenuItem( loc.get( text) );		
		menu.setActionCommand( action );
		menu.addActionListener( list );
		if( key != null )
			menu.setAccelerator( key );
		return menu;
	}
		
	/** método que cria os produtos e o stock */
	private void setupStock(){
		Produto pe = new Produto( 1, "Produto exemplo", 30, 10 );
		stock.addProduto( pe, 50 );
		Produto mo = new Produto( 2, "Mão de Obra", 30, 10 );
		stock.addProduto( mo, -1 );
		
		Produto p1 = new Produto( 3, "Peça simples 1", 20, 17 );
		stock.addProduto( p1, 20 );
		Produto p2 = new Produto( 4, "Peça simples 2", 20, 18 );
		stock.addProduto( p2, 15 );		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		// processar os eventos aqui...
		String cmd = e.getActionCommand();
		if( cmd.equals( "language")){
			LanguageDialog langD = new LanguageDialog( this );
			langD.setVisible( true );
		}		
		else if( cmd.equals("inprod") ){
			MenuES menuES = new MenuES(this, loc.get("INMENU_TITLE"), stock, 1);
			menuES.setVisible( true );
		}
		else if( cmd.equals("outprod") ){
			MenuES menuES = new MenuES(this, loc.get("OUTMENU_TITLE"), stock, -1);
			menuES.setVisible( true );			
		}		
		else if( cmd.equals("newprod") ){
			MenuNovoProduto menuNew = new MenuNovoProduto( this, stock);
			menuNew.setVisible( true );
		}
	}

	public static void main(String[] args) {
		JanelaPrincipal jan = new JanelaPrincipal();
		jan.setVisible( true );
	}

}
