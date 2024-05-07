package poo.freecell;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import poo.carta.Baralho;
import poo.carta.Carta;


@SuppressWarnings("serial")
public class FreeCell extends JFrame {
	private ZonaJogo mesaJogo = new ZonaJogo();

	// estamos no primeiro ou segundo click?
	private int click = 1;

	//escolher qual dos baralhos utilizar
	//private Baralho baralho = new Baralho( 73, 97, "cartas.gif", 1 );	
	private Baralho baralho = new Baralho( 73, 97, "cartaswin.gif", 1 );

	private Coluna asColunas[] = new Coluna[ 8 ];

	// Células -> 4 células da esquerda para colocar qualquer carta do jogo
	private Celula asCelulas[] = new Celula[ 4 ];

	// Casas -> 4 células da direita para colocar as cartas por ordem (ex: A -> 2 -> 3, ...)
	private Casa asCasas[]   = new Casa[ 4 ];

	// Variável para determinar o índice da origem
	private ContentorCartasDefault origem;
	
	// ArrayList que vai armazenar as colunas, as casas e as celulas
	private ArrayList <ContentorCartasDefault> osContentores = new ArrayList<ContentorCartasDefault>(asColunas.length + asCelulas.length + asCasas.length);

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
			osContentores.add(asColunas[i]);
		}

		// Criar as células
		for( int i = 0; i< asCelulas.length; i++ ){
			asCelulas[ i ] = new Celula( new Point( 2 + (baralho.getComprimentoCarta()+2+1)*i, 0 ) ,
					baralho.getComprimentoCarta()+2, baralho.getAlturaCarta()+2 );
			osContentores.add(asCelulas[i]);
		}

		// Criar as casas
		for( int i = 0; i< asCasas.length; i++ ) {
			asCasas[ i ] = new Casa( new Point( 360 + (baralho.getComprimentoCarta()+2+1)*i, 0 ) ,
					baralho.getComprimentoCarta()+2, baralho.getAlturaCarta()+2 );		
			osContentores.add(asCasas[i]);
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

		// Ciclo para percorrer a ArrayList e verifica se alguma das colunas, celulas e casas estão dentro e se não estão vazias e verificar se as casas são ou não origem
		for (ContentorCartasDefault c : osContentores) {
			if (c.estaDentro(pt) && !c.estaVazio() && c.eOrigem()  ) {
				c.setSelecionado(true);
				click = 2;
				origem = c;
			}
		}

		mesaJogo.repaint();
	}

	/** método chamado quando o utilizador clica com o rato na
	 * zona de jogo poara escolher o local de destino
	 * @param pt coordenada onde o rato foi clicado
	 */
	private void escolherDestino( Point pt ) {

		 // TODO ZFEITO implementar este método
		 Carta c = origem.getCarta();
		 click = 1;
		 origem.setSelecionado(false);

		 // Ciclo para percorrer as colunas, celulas e casas do jogo, verificar se cada podem receber x carta, retirar a carta de origem e receber a carta
		 for (ContentorCartasDefault destino : osContentores) {
			 if (destino.estaDentro(pt)) {
				 if (destino.podeReceber(c)) {
					 origem.retirar();
					 destino.receber(c);
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
		 // TODO ZFEITO implementar este método
		 
		 // Percorrer as casas todas e verificar se alguma não está cheia -> return false
		 for (Casa c : asCasas) {
			 if (c.estaVazio() || (c.getCarta().getFace() != Carta.REI)) {
				 return false;
			 }
		 }
		 return true;
	 }

	 private boolean perdeu() {
		 // TODO ZFEITO implementar este método
		 
		 for (ContentorCartasDefault orig : osContentores) {
			 if (orig.estaVazio() || !orig.eOrigem()) {
				 continue;
			 }
			 Carta c = orig.getCarta();
			 for (ContentorCartasDefault destino : osContentores) {
				 if (destino.podeReceber(c)) {
					 return false;
				 }
			 }
		 }
		 return true;
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
