package faroest.visitante;

public class Troca extends VisitanteDefault {
	
	/** constantes para os estados possíveis deste visitante */
	private static final int MORTO = 10;
		
	private Assaltante assaltante;

	public Troca(String nome ,int pontos) {
		super(nome, pontos);
	}

	@Override
	public int fecharPorta() {
		return 0;
	}

	@Override
	public void portaAberta() {

	}

	@Override
	public boolean podeFechar() {
		return false;
	}

	@Override
	public int baleado() {
//		if 
		if( getStatus() == MORTO )
			return pontos;
		
		setImagem( nome + "_morte" );
		fezAsneira("oops");
		setStatus(MORTO);
		return 0;
	}

	@Override
	public void atualizar() {

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
