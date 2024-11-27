package relogio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class RelogioDigital extends JComponent {
	private static final long serialVersionUID = 1L;

	/** display que serve de mostrador do relógio
	 */
	private ClockDisplay display = new ClockDisplay();
	
	/** temporizador que vai atualizando o relógio
	 */
	private Timer temporizador;

	/** fontes para as horas e alarmes
	 */
	private Font horasFont;
	private Font alarmesFont;

	/** formato para mostrar a info de várias maneiras
	 */
	private SimpleDateFormat formatHoras = new SimpleDateFormat ("HH:mm:ss"); 
	private SimpleDateFormat formatHorasCrono = new SimpleDateFormat ("HH:mm:ss");
	private SimpleDateFormat formatMilis = new SimpleDateFormat ("S");

	/** array com o estado dos 6 alarmes */
	private boolean alarmesON[] = {true, true, true, true, true, true};

	/** array com as horas definidas para os 6 alarmes */
	private Date alarmes[] = { new Date(System.currentTimeMillis() + 3600000 ),  // daqui a 1 hora
			new Date(System.currentTimeMillis() + 7200000 ),  // daqui a 2 horas
			new Date(System.currentTimeMillis() - 3600000 ),  // menos 1 hora
			new Date(System.currentTimeMillis() - 7200000 ),  // menos 2 horas
			new Date(System.currentTimeMillis() + 36000000 ), // mais 10 horas
			new Date(System.currentTimeMillis() - 36000000 )};// menos 10horas
	
	/** indicação de qual o alarme actual
	 */
	private int alarme = 0;

	/** valor do cronómetro actual;
	 */
	private long crono = 0;
	private long lastRead = System.currentTimeMillis();

	/** constantes para os vários modos de operação
	 */
//	private static final int HORAS = 0;      // representa o modo de Horas
//	private static final int WORLD = 1;      // representa o modo de Tempo no Mundo
//	private static final int ALARME = 2;     // representa o modo de alarme
//	private static final int CRONO = 3;      // representa o modo de cronómetro
//	private static final int CRONO_ON = 4;   // Quando o cronómetro está a correr a resposta dos botões
	                                         // é diferente, logo é necessário um novo modo de operação
	
	// Estado para cada Modo
	private ModoHoras modoHoras = new ModoHoras();
	private ModoMundo modoMundo = new ModoMundo();
	private ModoAlarme modoAlarme = new ModoAlarme();
	private ModoCrono modoCrono = new ModoCrono();
	private ModoCronoON modoCronoON = new ModoCronoON();


	
	/** Indica o modo de operação atual
	 */
//	private int modoOperacaoAntigo;
	private ModoOperacao modoOperacao = modoHoras;

	/** indica se a luz está ligada ou desligada
	 */
	private boolean luzLigada;
	
	/**
	 * Constroi um RelogioDigital com a hora atual 
	 */		
	public RelogioDigital( ) {
		// para garantir que o cronómetro tem um GMT correcto
		formatHorasCrono.setTimeZone( TimeZone.getTimeZone("GMT"));
		
		// vamos tentar uma fonte digital para as horas
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			ge.registerFont( Font.createFont(Font.TRUETYPE_FONT, new File("relogio/DS-DIGIB.TTF") ) ); 
			horasFont = new Font("DS-Digital",Font.BOLD, 30);
			alarmesFont = new Font("DS-Digital",Font.PLAIN, 12);
		} catch (FontFormatException | IOException e1) {
			// a leitura das fontes "digitais" falhou, vamos usar umas normais
			horasFont = new Font("Roman", Font.BOLD, 24 );
			alarmesFont = new Font("Roman", Font.BOLD, 10 );

		}
		
		// criar o timer que atualiza o tempo de segundo a segundo
		temporizador = new Timer( 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizar();
			}
		});
		temporizador.start();
	}
	
	/** método que é chamado sempre que é necessário atualizar o relógio
	 */
	protected void atualizar() {
		modoOperacao.atualizar();
		display.repaint();
	}

	/**
	 * método chamado quando o botão A é premido
	 */
	public void botaoApremido() {
		modoOperacao.botaoApremido();
		display.repaint();
	}
	
	/**
	 * método chamado quando o botão B é premido
	 */
	public void botaoBpremido() {
		modoOperacao.botaoBpremido();
		display.repaint();
	}
	
	/**
	 * método chamado quando o botão C é premido
	 */
	public void botaoCpremido() {	
		modoOperacao.botaoCpremido();
		display.repaint();	
	}
	
	/**
	 * método chamado quando o botão D é premido
	 */
	public void botaoDpremido() {
		modoOperacao.botaoDpremido();
	}	

	/**
	 * retorna o display onde este relógio se vai desenhar
	 * @return o display onde este relógio se vai desenhar
	 */
	public JPanel getDisplay( ) {
		return display; 
	}

	/**
	 * classe que representa o display do relógio
	 */
	private class ClockDisplay extends JPanel {
		
		ClockDisplay(){
			Dimension dim = new Dimension( 140, 100 );
			setSize( dim );
			setPreferredSize( dim );
			setMinimumSize( dim );
			setBackground( Color.LIGHT_GRAY );
		}

		/**
		 * este é o método que desenha o relógio
		 */
		public void paintComponent( Graphics g ){
			super.paintComponent( g );
			modoOperacao.desenhar(g);
			// desenhar o estado dos alarmes
			g.setFont( alarmesFont );				
			for( int i=0; i < alarmesON.length; i++ ){
				if( alarmesON[i] )
					g.drawString( "A" + (i+1), 10+20*i, 90 );
			}
		}		
	}

	/**
	 * Pára o temporizador
	 */
	private void pararTemporizador(){
		temporizador.stop();
	}

	/** retoma o temporizador
	 */
	private void retomarTemporizadorNormal(){
		temporizador.setInitialDelay( 1000 );
		temporizador.setDelay( 1000 );
		temporizador.restart();
	}
	
	private void acelerarTemporizador() {
		temporizador.setInitialDelay( 1 );
		temporizador.setDelay( 1 );
		temporizador.restart();
	}
	
	private class ModoOperacao {
		void botaoApremido() { }
		void botaoBpremido() { }
		void botaoCpremido() { }
		
		void botaoDpremido() { 
			luzLigada = !luzLigada;
			display.setBackground( luzLigada? Color.GREEN: Color.LIGHT_GRAY );
		}
		
		void atualizar() { }
		void desenhar( Graphics g ) { }
	}
	
	private class ModoHoras extends ModoOperacao {
		void botaoBpremido() { 
			modoOperacao = modoMundo;
		}
		
		@Override
		void desenhar(Graphics g) {
			Date agora = new Date( );
			g.setFont( horasFont );
			g.drawString( formatHoras.format(agora), 10, 60);
		}
	}
	
	private class ModoMundo extends ModoOperacao {
		/** array com as cidades para as horas do mundo, considerando que estamos em GMT 0
		 *  array começa em GMT-12 e termina em GMT+12  
		 */
		private String cidades[] = {"-12",   "-11",    "Honolulu","Ancorage","Los Angeles","Phoenix",  "Mexico", "Washinghton","La Paz", "Brasilia","-2",      "Acores", "Lisboa",
				                    "Paris", "Atenas", "Amman",   "Moscovo", "Islamabad",  "Katemandu","Jacarta","Pequim",     "Toquio","Adelaide", "Camberra"};
		private int cidadeActual = 12;
		
		void botaoApremido() { 
			cidadeActual--;
			if( cidadeActual < 0 ) cidadeActual = cidades.length-1;
		}
		
		void botaoBpremido() { 
			modoOperacao = modoAlarme;
			pararTemporizador();
		}
		
		@Override
		void botaoCpremido() {
			cidadeActual++;
			if( cidadeActual >= cidades.length ) cidadeActual = 0;
		}
		
		@Override
		void desenhar(Graphics g) {
			Date world = new Date( System.currentTimeMillis()+(3600000*(cidadeActual-12)) );
			g.setFont( horasFont );
			g.drawString( formatHoras.format(world), 10, 60);
			g.setFont( alarmesFont );
			g.drawString( cidades[cidadeActual], 10, 10);
		}
	}
	
	private class ModoAlarme extends ModoOperacao {
		void botaoApremido() { 
			alarme++;
			if( alarme >= alarmes.length) alarme = 0;
		}
		
		@Override
		void botaoBpremido() {
			 modoOperacao = modoCrono;
		}
		
		@Override
		void botaoCpremido() {
			alarmesON[alarme] = !alarmesON[alarme];
		}
		
		@Override
		void desenhar(Graphics g) {
			Date timer = alarmes[ alarme ];
			g.setFont( horasFont );
			g.drawString( formatHoras.format(timer), 10, 60);
			g.setFont( alarmesFont );
			g.drawString("alarme " + (alarme+1), 10, 10);
		}
	}
	
	private class ModoCrono extends ModoOperacao {
		void botaoApremido() { 
			modoOperacao = modoCronoON;
			acelerarTemporizador();			
			lastRead = System.currentTimeMillis();
		}
		
		@Override
		void botaoBpremido() {
			modoOperacao = modoHoras; 
			retomarTemporizadorNormal();
		}
		
		@Override
		void botaoCpremido() {
			crono = 0;
		}
		
		@Override
		void desenhar(Graphics g) {
			Date crononow = new Date( crono );
			g.setFont( horasFont );
			g.drawString( formatHorasCrono.format(crononow), 10, 60);
			g.setFont( alarmesFont );				
			g.drawString( formatMilis.format(crononow), 115, 60);
		}
	
	}
	
	private class ModoCronoON extends ModoOperacao {
		@Override
		void atualizar() {
			long read = System.currentTimeMillis(); 
			crono += read - lastRead;
			lastRead = read;
		}
		
		void botaoApremido() { 
			modoOperacao = modoCrono;
			pararTemporizador();
		}
		
		@Override
		void desenhar(Graphics g) {
			Date crononow = new Date( crono );
			g.setFont( horasFont );
			g.drawString( formatHorasCrono.format(crononow), 10, 60);
			g.setFont( alarmesFont );				
			g.drawString( formatMilis.format(crononow), 115, 60);
		}
	}
}
