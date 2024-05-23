package faroest.visitante;

public class Aleatorio extends VisitanteDefault {

	public Aleatorio(String nome, int pontos) {
		super(nome, pontos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int fecharPorta() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void portaAberta() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean podeFechar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int baleado() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void atualizar() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public VisitanteDefault clone() {
		return super.clone();
	}

}
