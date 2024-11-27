package aplicacao;

import java.awt.*;

import javax.swing.*;

import controlo.*;

public class Aparelhagem extends JFrame implements BotaoRotativoListener {
		
	/** bot�o que roda para volume
	 */
	private BotaoRotativo btVolume;
	
	/** bot�o que roda para frequ�ncias
	 */
	private BotaoRotativo btFreq;
	
	/** display
	 */
	private Display display;
	
	/** fonte para as frequ�ncias */
	private Font freqFont = new Font("Roman", Font.BOLD, 24 );	
	/** fonte para os mhz */
	private Font mhzFont = new Font("Roman", Font.BOLD, 14 );
	 

	// vari�veis que guardam os diversos valores da aparelhagem
	/** valor do nivel de som */
	private int som = 3;         // valor do som
	private static final int SOM_MAX = 10;

	/** valor da frequ�ncia */
	private int freq = 905;      // as frequencias s�o entre as 87.5 e 108.0 MHz
	private static final int FREQUENCIA_MIN = 875;
	private static final int FREQUENCIA_MAX = 1080;
	
	/** constantes para as cores a usar nos display
	 */
	private static final Color COR_ON = Color.RED;
	private static final Color COR_OFF = new Color( 120, 0, 0 );

	/** vari�vel que indica a cor do display */
	private Color corDisplay = COR_ON;

	
	/** construtor da aparelhagem
	 */
	public Aparelhagem() {
		super( "Aparelhagem de som" );
		setResizable( false );
		
		// criar a interface da aplica��o
		setupAspecto( getContentPane() );
		
		// come�ar a testar se o bot�o roda 
		// testaRodar();		
		
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		pack();
	}
		
	/** m�todo auxiliar que cria o aspeto da aplica��o
	 * @param janela
	 */
	private void setupAspecto( Container janela ) {
		janela.setLayout( new FlowLayout() );
		
		// cria o bot�o rotativo do volume
		ImageIcon f = new ImageIcon( "botao.gif" ); 
		ImageIcon m = new ImageIcon( "marca.gif" );
		btVolume =  new BotaoRotativo(f, m, 90, 10, 40 );
		btVolume.setSize( f.getIconWidth(), f.getIconHeight() );
		// Adicionar esta linha para o btVolume ser observado pela aparelhagem
		btVolume.addBotaoRotativoListener(this);
		janela.add( btVolume );

		// criar o display onde ir�o ser desenhados o som e a frequ�ncia
		display = new Display();
		janela.add( display );

		// cria o bot�o rotativo da frequ�ncia
		btFreq =  new BotaoRotativo(f, m, 90, 10, 40 );
		btFreq.setSize( f.getIconWidth(), f.getIconHeight() );
		// Adicionar esta linha para o btFreq ser observado pela aparelhagem
		btFreq.addBotaoRotativoListener(this);
		janela.add( btFreq );
	}

//	/** M�todo que vai ver se o bot�o est� a rodar 
//	 * ESTE M�TODO DEVE DESAPARECER
//	 */
//	private void testaRodar(){
//		// o que este m�todo faz � verificar se o bot�o est� a rodar de 1000 em 1000 milisegundos		
//		
//		Thread t = new Thread(){
//			public void run(){
//				while (true) {
//					if( btVolume.rodou() ){
//						int incremento = btVolume.getRodado();
//						btVolume.resetRodou();
//						alterarVolume( incremento );
//						display.repaint();
//					}
//					if( btFreq.rodou() ){
//						int incremento = btFreq.getRodado();
//						btFreq.resetRodou();
//						mudarFrequencia( incremento );
//						display.repaint();
//					}
//					// daqui a 1000 milisegundos volta a verificar se mudou
//					try {
//						sleep(1);
//					} catch (InterruptedException e) {
//					}
//				}
//			}
//		};
//		t.start();
//	}
	
	// M�todo que verifica se os bot�es foram rodados pelo utilizador, se sim incrementar o valor da frequ�ncia e/ou volume
	@Override
	public void botaoRodou() {
		if( btVolume.rodou() ){
			int incremento = btVolume.getRodado();
			btVolume.resetRodou();
			alterarVolume( incremento );
		}
		if( btFreq.rodou() ){
			int incremento = btFreq.getRodado();
			btFreq.resetRodou();
			mudarFrequencia( incremento );
		}
		// Atualizar o bot�o depois de rodado
		display.repaint();
	}
	
	private void mudarFrequencia(int incremento) {
		freq += incremento;
		if( freq < FREQUENCIA_MIN ) freq = FREQUENCIA_MIN;
		else if( freq > FREQUENCIA_MAX ) freq = FREQUENCIA_MAX;
	}

	private void alterarVolume(int incremento) {
		som += incremento;
		if( som <= 0 ) som  = 0;
		else if( som >= SOM_MAX ) som = SOM_MAX;
	}
		
	
	/** classe privada que ilustra o display da aparelhagem
	 */
	private class Display extends JPanel {
		
		public Display(){
			setBackground( Color.BLACK );
			setPreferredSize( new Dimension( 10 + 12*SOM_MAX + 10,100 ) );
		}
		
		public void paintComponent( Graphics g ){
			super.paintComponent( g );
			// desenhar o som
			g.setColor( corDisplay );
			for( int i =0; i < SOM_MAX; i++ ){
				if( i < som ) 
					g.fillRect( 10 + 12*i, 25 - i, 10, 10+i );
				g.drawRect(10 + 12*i, 25 - i, 10, 10+i );
			}

			// desenhar as frequ�ncias
			g.setFont( freqFont );
			g.drawString(""+freq/10.0, 10, 90);
			g.setFont( mhzFont );
			g.drawString("MHz", 70, 90);
		}
	}	
	
	public static void main( String [] args ){
		Aparelhagem app = new Aparelhagem();
		app.setLocationRelativeTo( null );
		app.setVisible( true );
	}
}
