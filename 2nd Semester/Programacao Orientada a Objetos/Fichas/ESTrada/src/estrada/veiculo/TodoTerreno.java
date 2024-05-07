package estrada.veiculo;

import estrada.estrada.Estrada;
import estrada.estrada.Faixa;
import prof.jogos2D.image.ComponenteVisual;

public class TodoTerreno extends VeiculoDefault {

	public TodoTerreno(ComponenteVisual img, Faixa f, int veloc, int resistencia) {
		super(img, f, veloc, resistencia);
	}
	
	@Override
	public boolean podeMudarFaixa(Faixa f, int x) {
		
		// Limite de 2 faixas
		// Para resolver o problema podemos chamar o método distanciaEntreFaixas() <= 2, para isso criar o objeto Estrada dentro de cada Faixa
		
		Estrada e = getFaixa().getEstrada();
		return super.podeMudarFaixa(f, x) && e.distanciaEntreFaixas(getFaixa(), f) <= 2;
	}
	
	@Override
	protected void checkMauPiso() {}
	
	@Override
	protected void checkResistencia() {
		super.checkResistencia();
	}
	
}
