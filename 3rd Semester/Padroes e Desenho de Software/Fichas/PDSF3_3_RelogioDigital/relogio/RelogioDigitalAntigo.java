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

public class RelogioDigitalAntigo extends JComponent {
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

	/** array com as cidades para as horas do mundo, considerando que estamos em GMT 0
	 *  array começa em GMT-12 e termina em GMT+12  
	 */
	private String cidades[] = {"-12",   "-11",    "Honolulu","Ancorage","Los Angeles","Phoenix",  "Mexico", "Washinghton","La Paz", "Brasilia","-2",      "Acores", "Lisboa",
			                    "Paris", "Atenas", "Amman",   "Moscovo", "Islamabad",  "Katemandu","Jacarta","Pequim",     "Toquio","Adelaide", "Camberra"};
	private int cidadeActual = 12;

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
	private static final int HORAS = 0;      // representa o modo de Horas
	private static final int WORLD = 1;      // representa o modo de Tempo no Mundo
	private static final int ALARME = 2;     // representa o modo de alarme
	private static final int CRONO = 3;      // representa o modo de cronómetro
	private static final int CRONO_ON = 4;   // Quando o cronómetro está a correr a resposta dos botões
	                                         // é diferente, logo é necessário um novo modo de operação

	/** Indica o modo de operação atual
	 */
	private int modoOperacao = HORAS;

	/** indica se a luz está ligada ou desligada
	 */
	private boolean luzLigada;
	
	/**
	 * Constroi um RelogioDigital com a hora atual 
	 */		
	public RelogioDigitalAntigo( ) {
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
		switch(modoOperacao){
		case CRONO_ON:
			long read = System.currentTimeMillis(); 
			crono += read - lastRead;
			lastRead = read;
			break;
		}
		display.repaint();
	}

	/**
	 * método chamado quando o botão A é premido
	 */
	public void botaoApremido() {
		switch( modoOperacao ){
		case WORLD:
			cidadeActual--;
			if( cidadeActual < 0 ) cidadeActual = cidades.length-1;
			break;
		case ALARME:
			alarme++;
			if( alarme >= alarmes.length) alarme = 0;
			break;
		case CRONO:
			modoOperacao = CRONO_ON;
			acelerarTemporizador();			
			lastRead = System.currentTimeMillis();
			break;			
		case CRONO_ON:
			modoOperacao = CRONO;
			pararTemporizador();
			break;
		}
		display.repaint();
	}
	
	/**
	 * método chamado quando o botão B é premido
	 */
	public void botaoBpremido() {
		switch( modoOperacao ){
		case HORAS: modoOperacao = WORLD; break;
		case WORLD: modoOperacao = ALARME; pararTemporizador(); break;
		case ALARME: modoOperacao = CRONO; break;
		case CRONO: modoOperacao = HORAS; retomarTemporizadorNormal(); break;
		}
		display.repaint();
	}
	
	/**
	 * método chamado quando o botão C é premido
	 */
	public void botaoCpremido() {	
		switch( modoOperacao ){
		case WORLD:
			cidadeActual++;
			if( cidadeActual >= cidades.length ) cidadeActual = 0;
			break;
		case ALARME:
			alarmesON[alarme] = !alarmesON[alarme];
			break;		
		case CRONO:
			crono = 0;
		}
		display.repaint();	
	}
	
	/**
	 * método chamado quando o botão D é premido
	 */
	public void botaoDpremido() {
		luzLigada = !luzLigada;
		display.setBackground( luzLigada? Color.GREEN: Color.LIGHT_GRAY );
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
			switch( modoOperacao ){
			case HORAS:
				// desenhar as horas
				Date agora = new Date( );
				g.setFont( horasFont );
				g.drawString( formatHoras.format(agora), 10, 60);
				break;
			case WORLD:
				// desenhar as horas do mundo
				Date world = new Date( System.currentTimeMillis()+(3600000*(cidadeActual-12)) );
				g.setFont( horasFont );
				g.drawString( formatHoras.format(world), 10, 60);
				g.setFont( alarmesFont );
				g.drawString( cidades[cidadeActual], 10, 10);
				break;
			case CRONO:
			case CRONO_ON:
				Date crononow = new Date( crono );
				g.setFont( horasFont );
				g.drawString( formatHorasCrono.format(crononow), 10, 60);
				g.setFont( alarmesFont );				
				g.drawString( formatMilis.format(crononow), 115, 60);
				break;
			case ALARME:
				Date timer = alarmes[ alarme ];
				g.setFont( horasFont );
				g.drawString( formatHoras.format(timer), 10, 60);
				g.setFont( alarmesFont );
				g.drawString("alarme " + (alarme+1), 10, 10);
			}
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
}
