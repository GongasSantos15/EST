package faroest.visitante;

import faroest.util.GeradorAleatorio;

public class Troca extends VisitanteDefault {
	
	/* ---------------------------------------------- CONSTANTES ------------------------------------ */
	
	/** Estados possíveis do Troca */
	private static final int MORTO = 10;
	private static final int ENTRAR = 11;
	private static final int ESPERA = 12;
	private static final int TROCAR = 13;
	private static final int MORTE = 14;
		
	/* ---------------------------------------------- VARIÁVEIS ------------------------------------ */
	
	private Assaltante assaltante;
	private int tempoMinTroca, tempoMaxTroca;
	
	/* ---------------------------------------------- CONSTRUTOR ------------------------------------ */

	public Troca(String nome ,int pontos, int tempoMinTroca, int tempoMaxTroca) {
		super(nome, pontos);
		this.tempoMinTroca = tempoMinTroca;
		this.tempoMaxTroca = tempoMaxTroca;
		setStatus(ENTRAR);
		setImagem(nome + "_ola");
	}

	/* ---------------------------------------------- MÉTODOS ------------------------------------ */
	
	@Override
	public int fecharPorta() {
		return 0;
	}

	@Override
	public void portaAberta() {
		GeradorAleatorio.proxTempo(tempoMinTroca, tempoMaxTroca);
	}

	@Override
	public boolean podeFechar() {
		return getStatus() == MORTO && getImagem().numCiclosFeitos() > 0;
	}

	@Override
	public int baleado() {
		if( getStatus() == MORTO )
			return pontos;
		
		setImagem( nome + "_morte" );
		fezAsneira("oops");
		setStatus(MORTO);
		return 0;
	}

	@Override
	public void atualizar() {
			setStatus(TROCAR);
			setImagem(nome + "_troca");
	}
	
	@Override
	public void setImagem(String nome) {
		super.setImagem(nome);
	}
	
	@Override
	public VisitanteDefault clone() {
		return super.clone();
	}

}
