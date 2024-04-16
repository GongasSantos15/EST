package poo.freecell;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import poo.carta.Baralho;
import poo.carta.Carta;


@SuppressWarnings("serial")
public class FreeCell extends JFrame {
	private ZonaJogo mesaJogo = new ZonaJogo();
	
	// constantes a usar para identificar a origem
	private static final int ORIGEM_CASA = 0;
	private static final int ORIGEM_COLUNA = 1;
	private static final int ORIGEM_CELULA = 2;
	
	// estamos no primeiro ou segundo click?
	private int click = 1;
	
	//escolher qual dos baralhos utilizar
	//private Baralho baralho = new Baralho( 73, 97, "cartas.gif", 1 );	
	private Baralho baralho = new Baralho( 73, 97, "cartaswin.gif", 1 );
	private Coluna asColunas[] = new Coluna[ 8 ];
	private Celula asCelulas[] = new Celula[ 4 ];
	private Casa   asCasas[]   = new Casa[ 4 ];

	private int origemIdx;

	/** Cria o freecell
	 */
	public FreeCell( ){
		setTitle( "Freecell" );
		setSize( 680, 600 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		mesaJogo.setBackground( new Color( 0, 127, 0 ) );
		getContentPane().add( mesaJogo );		
			
		// Criar as colunas 
		for( int i = 0; i< asColunas.length; i++ ){ 
			asColunas[ i ] = new Coluna( new Point( 8 + (baralho.getComprimentoCarta()+8)*i, 150 ),
										baralho.getComprimentoCarta(), baralho.getAlturaCarta());
		}

		// Criar as células
		for( int i = 0; i< asCelulas.length; i++ ){
			asCelulas[ i ] = new Celula( new Point( 2 + (baralho.getComprimentoCarta()+2+1)*i, 0 ) ,
					                     baralho.getComprimentoCarta()+2, baralho.getAlturaCarta()+2 );
		}
		
		// Criar as casas
		for( int i = 0; i< asCasas.length; i++ ) {
			asCasas[ i ] = new Casa( new Point( 360 + (baralho.getComprimentoCarta()+2+1)*i, 0 ) ,
					                     baralho.getComprimentoCarta()+2, baralho.getAlturaCarta()+2 );			
		}

		// dispor os componentes na janela
		colocarComponentes();
		
		// distribuir cartas
		distribuirCartas();

		// colocar os menus
		colocarMenus();
		
		mesaJogo.addMouseListener( new MouseAdapter() {
			public void mousePressed( MouseEvent e ){
				if( e.getButton() != MouseEvent.BUTTON1 )
					return;
				if( click == 1 )
					escolherOrigem( e.getPoint() );
				else {
					escolherDestino( e.getPoint() );
					testarFim();
				}
			}			
		});
		
		mesaJogo.addComponentListener( new ComponentAdapter() {			
			public void componentResized(ComponentEvent e) {
				colocarComponentes();
				repaint();
			}
		});
	}
	
	/** Reposiciona os componentes na janela quando esta é redimensionada
	 */
	private void colocarComponentes() {
		int minComp = (asCasas[0].getComprimento()) * asColunas.length;
		Rectangle mesa = mesaJogo.getBounds();
		
		int comp = mesa.width < minComp? minComp: mesa.width;
		int cx = (comp - asColunas[0].getComprimento() * asColunas.length) / (asColunas.length + 1); 
		int distCol = cx + asColunas[0].getComprimento();
				
		// colocar colunas no sitio
		for( int i = 0; i< asColunas.length; i++ ){ 
			asColunas[ i ].setPosicao( new Point( cx + distCol*i, asCelulas[0].getAltura() + 10 ));
		}

		// colocar células no sitio
		for( int i = 0; i< asCelulas.length; i++ ){
			asCelulas[ i ].setPosicao( new Point( asCelulas[i].getComprimento()*i, 0 ) );
		}
		
		// colocar casas no sitio
		int px = comp - asCasas[0].getComprimento()*asCasas.length;
		for( int i = 0; i< asCasas.length; i++ ) {
			asCasas[ i ].setPosicao( new Point( px+asCasas[i].getComprimento()*i,0) );			
		}
	}

	/** Reinicia o jogo, distribuindo novamente as cartas
	 */
	protected void novoJogo() {
		// limpar as cartas todas (também se podiam criar novamente os componentes)
		for( int i = 0; i< asColunas.length; i++ ){ 
			asColunas[ i ].limpar();
		}

		for( int i = 0; i< asCelulas.length; i++ ){
			asCelulas[ i ].limpar();
		}
		
		for( int i = 0; i< asCasas.length; i++ ) {
			asCasas[ i ].limpar();			
		}
		
		distribuirCartas();
		mesaJogo.repaint();
	}

	/** Método chamado quando o utilizador altera a opção de 
	 * escolher as casas como origem ou não
	 * @param eOrigem true se as casas forem origem, false caso contrário
	 */
	protected void mudarCasaOrigem(boolean eOrigem ) {
		for( int i = 0; i< asCasas.length; i++ ) {
			asCasas[ i ].setComoOrigem( eOrigem );			
		}		
	}

	/** distribui as cartas pelas colunas
	 */
	private void distribuirCartas() {
		baralho.baralhar();
		for( int i=0; i < 52; i++ ) {
			Carta c = baralho.dar( i );
			c.setFaceUp( true );
			asColunas[ i % 8 ].colocar( c );
		}
	}
	
	/** método chamado quando o utilizador clica com o rato na
	 * zona de jogo para escolher o local de origem
	 * @param pt coordenada onde o rato foi clicado
	 */
	private void escolherOrigem( Point pt ) {
		// TODO ZFEITO implementar este método
		for (int i = 0; i < asColunas.length; i++) {
			if (asColunas[i].estaDentro(pt)) {
				asColunas[i].setSelecionado(true);
				click = 2;
				origemIdx = i;
			}
		}
		mesaJogo.repaint();
	}

	
	/** método chamado quando o utilizador clica com o rato na
	 * zona de jogo poara escolher o local de destino
	 * @param pt coordenada onde o rato foi clicado
	 */	private void escolherDestino( Point pt ) {
		// TODO ZFEITO implementar este método
		 
		 Carta c = asColunas[origemIdx].getCarta();
		 click = 1;
		 asColunas[origemIdx].setSelecionado(false);
		 
		for (int i = 0; i < asColunas.length; i++) {
			if (asColunas[i].estaDentro(pt)) {
				if (asColunas[i].podeReceber(c)) {
					asColunas[i].receber(c);
					asColunas[origemIdx].retirar();
				}
			}
		}
		mesaJogo.repaint();
	}
	
	private void testarFim() {
		if( ganhou() )
			JOptionPane.showMessageDialog( this, "Parabéns! Ganhou!",
                                           "Freecell", JOptionPane.INFORMATION_MESSAGE);
		else if( perdeu() )
			JOptionPane.showMessageDialog( this, "Já não tem mais jogadas válidas!!! Perdeu!",
	                                       "Freecell", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private boolean ganhou(){
		// TODO implementar este método
		return false;			
	}
	
	private boolean perdeu() {
		// TODO implementar este método
		// ver todas as combinações possíveis a ver se já não pode jogar
		return false;
	}

	
	/** método que vai colocar os menus que temos de implementar
	 */
	private void colocarMenus(){
		JMenuBar bar = new JMenuBar();
		
		JMenu menu = new JMenu( "Jogo" );
		JMenuItem novoBt = new JMenuItem("Novo Jogo");
		novoBt.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			  novoJogo();
			}
		});
		menu.add( novoBt );
		JCheckBoxMenuItem casasBt = new JCheckBoxMenuItem("Casas são origem");
		casasBt.setSelected( true );
		casasBt.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			  mudarCasaOrigem( casasBt.isSelected() );
			}
		});
		menu.add( casasBt );
		bar.add( menu );
		
		setJMenuBar( bar );
	}

	
	/** classe que representa a zona de jogo, que é o local
	 * onde se vão desenhar os contentores e as cartas 
	 */
	class ZonaJogo extends JPanel {		
		public void paint( Graphics g ){
			super.paint( g );
			
			// desenhar os vários elementos do jogo
			for( Coluna c : asColunas )
			      c.desenhar( g );
			for( Casa c : asCasas )
			      c.desenhar( g );			
			for( Celula c : asCelulas )
			      c.desenhar( g );
		}
		
	}

	public static void main(String[] args) {
		FreeCell jogo = new FreeCell( );
		jogo.setVisible( true );
	}
}
