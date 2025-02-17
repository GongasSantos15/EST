package freecell;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import carta.Baralho;
import carta.Carta;
import carta.ContentorCartas;


public class FreeCell extends JFrame {

	private ZonaJogo mesaJogo = new ZonaJogo();
	
	private static JFrame frame;
	
	// estamos no primeiro ou 2º click?
	private int click = 1;
	
	private JMenuItem repor;
	
	//escolher qual dos baralhos utilizar
	//private Baralho baralho = new Baralho( 73, 97, "cartas.gif", 1 );	
	private Baralho baralho = new Baralho( 73, 97, "cartaswin.gif", 1 );	
	
	private static final int NCOLUNAS = 8;
	private static final int NCELULAS = 4;
	private static final int NCASAS = 4;
	private static final int INICIOCELULAS = NCOLUNAS;
	private static final int INICIOCASAS = NCOLUNAS + NCELULAS;

	ContentorCartas contentores[] = new ContentorCartas[ NCOLUNAS + NCELULAS  + NCASAS];
	private ContentorCartas origem;
	
	public FreeCell( ){
		setTitle( "Freecell" );
		setSize( 680, 600 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		mesaJogo.setBackground( new Color( 0, 127, 0 ) );
		getContentPane().add( mesaJogo );		
		
		colocarMenus();
		
		// criar colunas 
		for( int i = 0; i< NCOLUNAS; i++ ){ 
			contentores[ i ] = new Coluna( new Point( 8 + (baralho.getComprimentoCarta()+8)*i, 130 ),
										baralho.getComprimentoCarta(), baralho.getAlturaCarta());
		}

		// criar celulas
		for( int i = 0; i< NCELULAS; i++ ){
			contentores[ INICIOCELULAS + i ] = new Celula( new Point( 2 + (baralho.getComprimentoCarta()+2+1)*i, 0 ) ,
					                     baralho.getComprimentoCarta()+2, baralho.getAlturaCarta()+2 );
		}
		
		// criar casas 
		for( int i = 0; i< NCASAS; i++ ) {
			contentores[ INICIOCASAS + i ] = new Casa( new Point( 360 + (baralho.getComprimentoCarta()+2+1)*i, 0 ) ,
					                                baralho.getComprimentoCarta()+2, baralho.getAlturaCarta()+2 );			
		}

		// dispor os componentes na janela
		colocarComponentes();
		// distribuir cartas
		distribuirCartas();
		
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
	
	private void colocarComponentes() {
		int minComp = (contentores[INICIOCELULAS].getComprimento()) * NCOLUNAS;
		Rectangle mesa = mesaJogo.getBounds();
		
		int comp = mesa.width < minComp? minComp: mesa.width;
		int cx = (comp - contentores[INICIOCELULAS].getComprimento() * NCOLUNAS) / (NCOLUNAS + 1); 
		int distCol = cx + contentores[INICIOCELULAS].getComprimento();
				
		// colocar colunas no sitio
		for( int i = 0; i< NCOLUNAS; i++ ){ 
			contentores[ i ].setPosicao( new Point( cx + distCol*i, contentores[NCOLUNAS].getAltura() + 10 ));
		}

		// colocar celulas no sitio
		for( int i = 0; i < NCELULAS; i++ ){
			contentores[ INICIOCELULAS + i ].setPosicao( new Point( contentores[i].getComprimento()*i, 0 ) );
		}
		
		// colocar casas no sitio
		int px = comp - contentores[INICIOCASAS].getComprimento()*NCASAS;
		for( int i = 0; i < NCASAS; i++ ) {
			contentores[ INICIOCASAS + i ].setPosicao( new Point( px+contentores[INICIOCASAS + i].getComprimento()*i,0) );			
		}
	}
	
	private void distribuirCartas() {
		baralho.baralhar();
		for( int i=0; i < 52; i++ ) {
			Carta c = baralho.dar( i );
			c.virar();
			contentores[ i % 8 ].colocar( c );
		}
	}
	
		
	private void escolherOrigem( Point pt ) {
		for( ContentorCartas cc : contentores){
			if( cc.estaDentro( pt ) ){
				if( cc.estaVazio() )
					return;
				origem = cc;
				origem.setSeleccionado( true );
				click = 2;
				repaint();
			}
		}
	}

	
	private void escolherDestino( Point pt ) {
		// volta tudo ao estado inicial
		origem.setSeleccionado( false );
		click = 1;
		
		// ver se se pode mexer a carta
		Carta c = origem.getCarta(); 
		for( ContentorCartas cc : contentores){
			if( cc.estaDentro( pt ) ){
				if( cc.podeReceber( c ) ){
					cc.receber( origem.retirar() );
					break;
				}							
			}
		}
		repaint();
				
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
		for( int i = INICIOCASAS; i < INICIOCASAS + NCASAS; i++ )
			if( contentores[i].estaVazio() || contentores[i].getCarta().getFace() != Carta.REI )
				return false;
		
		return true;			
	}
	
	private boolean perdeu() {
		// ver todas as combinacões possíveis a ver se já não pode jogar
		// isto está aqui para verem mais uma vez as vantagens da herança
		for( ContentorCartas co: contentores ){
			if( co.estaVazio() ) // se a origem está vazia não perdeu de certeza
				return false;
			Carta c = co.getCarta();
			for( ContentorCartas cd : contentores  )
				if( cd.podeReceber( c ))
					return false;
		}
			
		// se após todas as combinações não conseguir mover é porque perdeu 
		return true;
	}
	
	private void memorizarSituacaoJogo() {
		restauro = new PontoRestauro();
		repor.setEnabled( true );
	}

	private void reporSituacaoJogo() {
		restauro.repor();
		mesaJogo.repaint();
	}
	
	private PontoRestauro restauro;
	
	private class PontoRestauro {

		ContentorCartas contentoresCopia[];
		ContentorCartas origemCopia;
		
		PontoRestauro() {
			// Criar um novo contentores de cartas com o tamanho do anterior
			contentoresCopia = new ContentorCartas[contentores.length];
			
			// Para cada conteúdo, clonar o mesmo
			for (int i = 0; i < contentores.length; i++) {
				contentoresCopia[i] = contentores[i].clone();
				
				// Se é a origem, então clonar a mesma
				if (origem == contentores[i])
					origemCopia = contentoresCopia[i];
			}
		}
		
		void repor() {
			// Criar um novo contentores de cartas com o tamanho do anterior
				contentores = new ContentorCartas[contentoresCopia.length];
						
			// Para cada conteúdo, clonar o mesmo
			for (int i = 0; i < contentoresCopia.length; i++) {
				contentores[i] = contentoresCopia[i].clone();
					
				// Se é a origem, então clonar a mesma
				if (origem == contentoresCopia[i])
					origemCopia = contentores[i];
			}
		}
		
	}

	// método que vai colocar os menus que temos de implementar
	private void colocarMenus(){
		JMenuBar bar = new JMenuBar();
		
		JMenu menu = new JMenu( "Jogo" );
		JMenuItem lembrar = new JMenuItem("Memorizar posição jogo");
		lembrar.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			  memorizarSituacaoJogo();
			}
		});
		menu.add( lembrar );
		repor = new JMenuItem("Repor posição jogo");
		repor.setEnabled( false );
		repor.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			  reporSituacaoJogo();
			}
		});
		menu.add( repor );
		bar.add( menu );
		
		setJMenuBar( bar );
	}

	
	private class ZonaJogo extends JPanel {
		
		public void paint( Graphics g ){
			super.paint( g );
			for( ContentorCartas cc : contentores )
				cc.desenhar( g );
		}
	}
	
	public static void main(String[] args) {
		frame = new FreeCell( );
		frame.setVisible( true );
	}

}
