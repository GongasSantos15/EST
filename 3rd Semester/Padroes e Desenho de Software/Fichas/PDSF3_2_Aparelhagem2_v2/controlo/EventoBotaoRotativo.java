package controlo;

/* ------------------------------ VARIÁVEIS --------------------------------*/
public class EventoBotaoRotativo {
	private final  BotaoRotativo source;
	private final int incremento;
	private final float angulo;
	private final int sentido;
	private final boolean rodando;
	
	/* ------------------------- CONSTRUTOR --------------------------------*/
	public EventoBotaoRotativo(BotaoRotativo source, int incremento, float angulo, int sentido, boolean rodando) {
		super();
		this.source = source;
		this.incremento = incremento;
		this.angulo = angulo;
		this.sentido = sentido;
		this.rodando = rodando;
	}

	/* ---------------------------- GETTERS --------------------------------*/
	public BotaoRotativo getSource() {
		return source;
	}

	public int getIncremento() {
		return incremento;
	}

	public float getAngulo() {
		return angulo;
	}

	public int getSentido() {
		return sentido;
	}

	public boolean isRodando() {
		return rodando;
	}
	
	
}
