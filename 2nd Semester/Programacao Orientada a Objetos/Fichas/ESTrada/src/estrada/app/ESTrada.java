package estrada.app;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;

import estrada.estrada.Bloqueio;
import estrada.estrada.Estrada;
import estrada.estrada.Faixa;
import estrada.estrada.MauPiso;
import estrada.veiculo.Veiculo;
import prof.jogos2D.image.*;

/**
 * Esta classe representa o jogo em si. 
 */
public class ESTrada extends JFrame {

	/** versão da app*/
	private static final long serialVersionUID = 1L;
	
	// Os elementos visuais do jogo
	private JPanel jContentPane = null;
	private JPanel zonaJogo = null;
	
	// imagem usada para melhorar as animações
	private Image ecran;
	
	// imagem de fundo do jogo
	private ComponenteSimples fundo;
	
	// elementos do jogo
	Estrada estrada = new Estrada();   // a estrada a usar		
	Veiculo sel = null;                  // o carro seleccionado 
	Faixa dest = null;                 // a faixa de destino do carro quando este muda de faixa 
	Point origem = null, fim = null;   // o ponto de partida e o ponto e chegada quando muda de faixa 

	// o criador de veículos
	private CriadorVeiculos criador;

	// estilos de linha e efeito alfa para desenhar a mudança de faixa
	Stroke estiloLinhaExterior = new BasicStroke(8.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND );
	Stroke estiloLinhaInterior = new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND );
	Composite alphaMeio = AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 0.5f );
	Composite alphaFull = AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 1f );

	// fontes e cores para a pontuação
	private int pontuacao;
	private Color pontColor = new Color( 0, 0, 100 );
	private Font pontFont = new Font( "Roman", Font.BOLD, 32 );
	
	// fontes e cores para o tempo
	private Color tempoColor = new Color( 0, 0, 100 );
	private Font tempoFont = new Font( "Roman", Font.BOLD, 32 );
	
	/**
	 * construtor da aplicação
	 */
	public ESTrada() {
		super();
		initialize();
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	
	/**
	 *  vai inicializar a aplicação
	 */
	private void initialize() {
		// características da janela
		this.setResizable(false);		
		this.setContentPane( getJContentPane() );
		this.setTitle("ESTrada");
	    this.pack();
		this.setLocationRelativeTo( null );
		
		// carregar o desenho do fundo da estrada
		try {
			fundo = new ComponenteSimples( "art/estrada.gif" );						
		} catch (IOException e) {
			System.out.println("Não consegui ler o ficheiro " + e.getLocalizedMessage() );
		}
		// criar e configurar a imagem para melhorar as animações 
		ecran = new BufferedImage( 800,600, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D ge = (Graphics2D )ecran.getGraphics();		
		ge.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);
	    ge.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	            RenderingHints.VALUE_INTERPOLATION_BILINEAR);	
	    
	    // criar as faixas da estrada
	    for( int i= 0; i < 4; i++)
	    	estrada.addFaixa( new Faixa( new Point(-60, 97 + i*50), 860, 30, Faixa.EsqDir ) );	    
	    for( int i= 0; i < 4; i++)
	    	estrada.addFaixa( new Faixa( new Point(-60, 310 + i*50), 860, 30, Faixa.DirEsq ) );
	    
	    // criar mau piso e bloqueio
	    try {
			MauPiso mp = new MauPiso( new ComponenteSimples(new Point(500,90), "art/maupiso.gif"), 2 );
			estrada.addMauPiso( mp );
			Bloqueio b = new Bloqueio( new ComponenteAnimado(new Point(100,302), "art/bloqueio.gif", 2, 8) );
			estrada.addBloqueio( b );			
		} catch (IOException e) {
			System.out.println("Sem ficheiros de mau piso ou bloqueio");
		}	    
	}

	/**
	 * Desenha os vários componentes do jogo (estrada, pontos e tempo)
	 * @param g elemento onde se vai desenhar
	 */
	private void desenharJogo( Graphics2D g ){
		// passar para graphics2D pois este é mais avançado
		Graphics2D ge = (Graphics2D )ecran.getGraphics();

		// desenhar a imagem da estrada 
		fundo.desenhar( ge );
		// desenhar a mudança de faixa de houver
		if( fim != null ){
			desenharMudancaFaixa(ge);
		}			
		// desenhar a estrada (inclui desenhar os carros)
		estrada.desenhar( ge );

		// desenhar os pontos
		ge.setColor( pontColor );
		ge.setFont( pontFont );
		// TODO ZFEITOs apresentar a pontuação
		ge.drawString( "" + pontuacao , 700, 50 );
		
		// desenhar o tempo
		ge.setColor( tempoColor );
		ge.setFont( tempoFont );
		// TODO calcular o tempo de jogo
		ge.drawString( "<tempo>", 30, 50);
		
		// agora que a imagem auxiliar está pronta, desenhá-la no écran
		g.drawImage( ecran, 0, 0, null );		
	}

	/**
	 * Desenhar a linha a indicar a mudança de faixa
	 * @param g onde desenhar
	 */
	private void desenharMudancaFaixa(Graphics2D g) {
		// como se vai mudar os estilos de linha é preciso usar um ambiente alternativo
		Graphics2D ge = (Graphics2D)g.create();
		
		// vai desenhar desde o centro do carro selecionado até ao fim
		Point ini = sel.getPosicaoCentro();
		
		// com a cor amarelo se pode, ou vermelho se não pode, mudar de faixa
		if( sel.podeMudarFaixa(dest, fim.x))
			ge.setPaint(Color.YELLOW);
		else
			ge.setPaint(Color.RED);
		
		// cria a linha e desenha em duas fases, uma mais grossa mas meio transparente
		// e outra mais fina mas opaca, para dar um efeito "bonito"
		Line2D.Double line = new Line2D.Double( ini, fim );
		ge.setComposite( alphaMeio );
		ge.setStroke( estiloLinhaExterior );											
		ge.draw( line );		
		ge.setComposite( alphaFull );
		ge.setStroke( estiloLinhaInterior );										
		ge.draw( line );
		ge.dispose();
	}
	
	/**
	 * método para arrancar com o jogo
	 */
	public void start(){
		// criar o criador de veículos
	    criador = new CriadorVeiculos( estrada.getFaixas(), 30, 50);

	    //criar o atualizador
		Atualizador actualiza = new Atualizador();
		actualiza.start();
	}

	/** 
	 * Atualiza todos os elementos da aplicação
	 * Atenção! Este método NÃO desenha nada - usar o método desenharJogo para isso.
	 * @return -1 para terminar a aplicação, ou outro qualquer para continuar 
	 */
	private int actualizarJogo() {		
		// mexer os carros atuais, se retornar -1 quer dizer que terminou
		int res = estrada.actualizar();
		if( res == -1 ) return res;
		pontuacao += res;
		
		// criar os novos carros
		Veiculo []criados = criador.criarVeiculos( );
		for( Veiculo v : criados )
			estrada.addVeiculo( v );
		
		return res;
	}	

	/**
	 * método chamado quando se pressiona o rato em cima do terreno de jogo 
	 * @param pt ponto onde o rato foi premido
	 */
	private void ratoPremido( Point pt ){
		// ver qual o carro em que se clicou (se houver algum)
		sel = estrada.getVeiculoAt( pt );
		if( sel != null ){
			// se clicou em cima o carro tem de parar
			sel.setParado( !sel.estaParado() );
			// marcar o início da viragem como sendo o
			// ponto mais próximo do centro da faixa em relação ao rato
			origem = sel.getFaixa().getPontoProximo(pt);			
		}
	}
	
	/**
	 * Método chamado quando o rato é libertado em cima do terreno de jogo
	 * @param pt ponto onde o rato foi libertado
	 */
	private void ratoLibertado( Point pt ){
		// se não tiver carro selecionado ou não se tiver afastado
		// pelo menos 4 pixeis (4*4 = 16) da origem não faz nada
		if( sel == null ) return;
		if( origem.distanceSq( pt ) < 16 ) return;
		
		// ver qual a faixa seleccionada, para isso tem o rato de estar dentro da faixa
		// ou a uma distância inferior a 30 pixeis do centro da antiga faixa seleccionada
		Faixa f = estrada.getFaixaAt( pt );
		if( f != null || dest.getPontoProximo(pt).distance( pt ) < 30 ){
			if( f != null ) dest = f;
			// ver qual o ponto do centro da faixa mais perto do rato
			fim = dest.getPontoProximo(pt);
			sel.mudarFaixa(dest, fim.x );				
		}	
		// desmarcar as selecções
		sel = null;
		fim = null;
	}

	/**
	 * Método chamado quando o rato é arrastado no terreno de jogo
	 * @param pt ponto onde o rato se encontra
	 */
	private void ratoArrastado( Point pt ){
		// se não tem carro selecionado ou o rato está a menos de 7 (7*7 = 49) pixeis da
		// origem é porque não quer mudar de faixa mas parar o carro
		if( sel == null ) return;
		if( origem.distanceSq( pt ) < 49 ) return;

		// se se afastou da origem é porque quer mudar de faixa e não parar
		// nesse caso, não pode estar parado e deve andar
		if( sel.estaParado() )
			sel.setParado( false );
		
		// ver qual a faixa seleccionada, é aquela em que o rato estiver por cima
		Faixa f = estrada.getFaixaAt( pt );
		if( f != null ){
			// ver qual o ponto do centro da faixa mais perto do rato
			fim = f.getPontoProximo(pt);
			dest = f;
		}
	}
	
	/**
	 * Inicializa a zonaJogo, AQUI NÃO DEVEM ALTERAR NADA 	
	 */
	private JPanel getZonaJogo() {
		if (zonaJogo == null) {
			zonaJogo = new JPanel(){
				/** versão deste painel */
				private static final long serialVersionUID = 1L;

				public void paintComponent(Graphics g) {
					desenharJogo( (Graphics2D)g );
				}
			};
			Dimension d = new Dimension(800, 600);
			zonaJogo.setPreferredSize( d );
			zonaJogo.setSize( d );
			zonaJogo.setMinimumSize( d );
			zonaJogo.setBackground(Color.pink);
			zonaJogo.addMouseListener( new MouseAdapter(){
				public void mousePressed(MouseEvent e) {
					ratoPremido( e.getPoint() );
				}
				public void mouseReleased(MouseEvent e) {
					ratoLibertado( e.getPoint() );
				}				
			});
			zonaJogo.addMouseMotionListener( new MouseMotionAdapter(){
				public void mouseDragged(MouseEvent e) {
					ratoArrastado( e.getPoint() );
				}
			});						
		}
		return zonaJogo;
	}
	
	/**
	 * Classe responsável pela criação da thread que vai actualizar o mundo de x em x tempo
	 * AQUI NÃO DEVEM ALTERAR NADA 
	 * @author F. Sergio Barbosa
	 */
	class Atualizador extends Thread {
		public void run(){
			long mili = System.currentTimeMillis();
			long target = mili + 33;
			int res = 0;
			do {
				res = actualizarJogo();		
				zonaJogo.repaint();
				// esperar 33 milisegundos o que dá umas 30 frames por segundo
				while( mili < target ) mili = System.currentTimeMillis();
				target = mili + 33;
			} while( res != -1 );
						
			// fazer o restart
			int resp = JOptionPane.showConfirmDialog(null, "Deseja Recomeçar?", "Fim de jogo", JOptionPane.YES_NO_OPTION);
			if( resp == JOptionPane.YES_OPTION ){
				estrada.limpar();		// limpar a estrada dos carros que contém
				ESTrada.this.start();
			}
			else if( resp == JOptionPane.NO_OPTION )
				ESTrada.this.dispose();
		}
	};	

	/**
	 * método auxiliar para configurar a janela
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getZonaJogo(),BorderLayout.CENTER);
		}
		return jContentPane;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ESTrada jogo = new ESTrada();
		jogo.setVisible( true );
		jogo.start();
	}
}
