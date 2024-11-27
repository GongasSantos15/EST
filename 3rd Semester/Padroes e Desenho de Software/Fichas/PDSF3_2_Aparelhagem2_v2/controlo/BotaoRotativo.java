package controlo;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.JComponent;

public class BotaoRotativo extends JComponent implements MouseListener, MouseMotionListener {

	/** constantes para definir o sentido de rotação do botão
	 */
	public static int SENTIDO_RELOGIO = 1;
	public static int SENTIDO_CONTRARIO_RELOGIO = -1;

		
	/** ângulo em que a marca está colocada
	 */
	private float angulo;
	
	/** ângulo antes de haver manipulação do botão
	 */
	private float antigoAng;
	
	/** ângulo minimo para se despoletar uma acção
	 */
	private float minAng;
	
	/** sentido da rotação
	 */
	private int sentido = SENTIDO_RELOGIO;
	
	/** imagem do fundo
	 */
	private Icon fundo;
	
	/** imagem da marca do botão que vai sendo colocada em va´rios sitios de modo a parecer que roda
	 */
	private Icon marca;
	
	/** centro do botão (é o centro do icon de fundo)
	 */
	private int centrox, centroy;
	
	/** raio a que a marca faz a sua rotação
	 */
	private int raio;
	
	/** deslocamento desde o ponto superior da marca até ao seu centro
	 */
	private int dx, dy;
	
	/** variável que indica se o botão está a ser rodado
	 */
	private boolean rodando = false;
	
	/**
	 * variáveis que indicam que rodou e de quanto rodou desde a última vez que se mexeu
	 * estas devem desaparecer
	 */
	private boolean rodou = false;
	private int rodado = 0;
	
	/** constantes para converter de radianos para graus e vice-versa
	 */
	private static final float GRAUS_RAD = (float)Math.PI / 180.0f; 
	private static final float RAD_GRAUS = 180.0f / (float)Math.PI;
	
	/**
	 * Constroi um BotaoRotativo com uma dada imagem de fundo e imagem de marca, 
	 * com posição inicial de ang
	 * @param fundo imagem do fundo do botão
	 * @param marca imagem da marca de rotação do botão
	 * @param ang ângulo inicial (em graus) do botão
	 * @param minang ângulo minimo de deslocação (em graus) para despoletar uma acção 
	 * @param r   raio da rotação da marca 
	 */		
	public BotaoRotativo( Icon fundo, Icon marca, float ang, float minang, int r ) {
		angulo = ang * GRAUS_RAD;
		antigoAng = angulo;
		minAng = minang * GRAUS_RAD;
		this.fundo = fundo;
		this.marca = marca;
		raio = r;
		
		// calcular o ponto central do icon da marca
		dx = marca.getIconWidth() / 2;
		dy = marca.getIconHeight() / 2;
		
		// calcular o ponto central do botão
		centrox = fundo.getIconHeight() / 2;
		centroy = fundo.getIconHeight() / 2;
		
		// adicionar os listeners
		addMouseMotionListener( this );
		addMouseListener( this );
		
		// definir o tamanho do painel
		Dimension dim = new Dimension( fundo.getIconWidth(), fundo.getIconHeight() );
		setSize( dim );
		setPreferredSize( dim );
		setMinimumSize( dim );
	}

	/**
	 * indica se se está a rodar o botão ou não
	 * @return true se o botão está a ser rodado
	 */
	public boolean estaRodar() {
		return rodando;
	}
	
	/** devolve o angulo de rotação do botão, em graus
	 * @return o angulo de rotação do botão, em graus
	 */
	public int getAngulo( ){
		return (int)(angulo*RAD_GRAUS);
	}
	
	/** devolve o sentido da rotação do botão
	 * @return  o sentido da rotação do botão
	 */
	public int getSentido(){
		return sentido;
	}
	
	/** método que vai desenhar o botão
	 */
	public void paintComponent( Graphics g ){
		fundo.paintIcon( this, g, 0, 0);
		
		// calcular a posição da marca
		int x = centrox + (int)(raio * Math.cos( angulo )) - dx;
		int y = centroy + (int)(raio * Math.sin( angulo )) - dy;
				
		marca.paintIcon(this, g, x, y );
	}
		
	public void mouseClicked(MouseEvent arg0) {	}

	public void mouseEntered(MouseEvent arg0) {	}

	public void mouseExited(MouseEvent arg0) { }

	public void mousePressed(MouseEvent arg0) {
		rodando = true;
		
		// Notificar quando o botão é clicado pelo utilizador
		EventoBotaoRotativo e = new EventoBotaoRotativo(this, 0, angulo, sentido, rodando);
		notificarBotaoComecaRodar(e);
	}

	public void mouseReleased(MouseEvent arg0) { 
		rodando = false;	
		
		// Notificar quando o botão acabou de rodar
		EventoBotaoRotativo e = new EventoBotaoRotativo(this, rodado, angulo, sentido, rodando);
		notificarBotaoRodou(e);
	}

	public void mouseDragged(MouseEvent arg0) {
		Point agora = arg0.getPoint();
		
		// ver o ângulo de rotação dado pelo movimento do rato
		int x = agora.x - centrox;
		int y = agora.y - centroy;
		
		double comp = Math.sqrt( (double)x*x + (double)y*y );

		// garantir que comp nunca é zero, pois vai ser usado numa divisão
		if( comp== 0.0 ) 
			comp = 0.000001;
		
		angulo = (float)Math.acos( x / comp );
		// como acos dá um ângulo entre 0 e PI temos de ver em que quadrante está
		if( y < 0 )
			angulo = (float)(2.0*Math.PI - angulo);
			
		float dif = angulo - antigoAng;
		
		// vamos ver em ângulos de minAng, quer dizer que se variar menos de minAng não se faz nada
		if( Math.abs( dif ) < minAng ) return;
	
		rodou = true;
		
		antigoAng = angulo;
		sentido = dif > 0 ? SENTIDO_RELOGIO: SENTIDO_CONTRARIO_RELOGIO;
		if( dif > 0 ) rodado++;
		else rodado--;
		
		// redesenhar o botão de acordo
		repaint();
		
		// Notificar quando o botão está a rodar
		EventoBotaoRotativo e = new EventoBotaoRotativo(this, rodado, angulo, sentido, rodando);
		notificarBotaoEstaRodar(e);
		
		resetRodou();
	}

	public void mouseMoved(MouseEvent arg0) { }
	
	public boolean rodou(){
		return rodou;
	}
	
	public void resetRodou(){
		rodou = false;
		rodado = 0;
	}
	
	public int getRodado(){
		return rodado;
	}
	
	private ArrayList<BotaoRotativoListener> listeners = new ArrayList<BotaoRotativoListener>();
	
	public void addBotaoRotativoListener(BotaoRotativoListener l) {
		listeners.add(l);
	}
	
	public void removeBotaoRotativoListener(BotaoRotativoListener l) {
		listeners.remove(l);
	}
	
	private void notificarBotaoComecaRodar(EventoBotaoRotativo e) {
		for(int i = listeners.size() - 1; i >= 0; i--)
			listeners.get(i).botaoComecaRodar(e);
	}
	
	private void notificarBotaoEstaRodar(EventoBotaoRotativo e) {
		for(int i = listeners.size() - 1; i >= 0; i--)
			listeners.get(i).botaoEstaRodar(e);
	}
	
	private void notificarBotaoRodou(EventoBotaoRotativo e) {
		for(int i = listeners.size() - 1; i >= 0; i--)
			listeners.get(i).botaoRodou(e);
	}
}
