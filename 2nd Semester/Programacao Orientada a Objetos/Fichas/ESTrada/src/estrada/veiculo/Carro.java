package estrada.veiculo;


import estrada.estrada.Faixa;
import estrada.estrada.MauPiso;
import prof.jogos2D.image.ComponenteVisual;

/**
 * Esta classe representa um carro
 * @author F. Sergio e <outros autores>
 */
public class Carro extends VeiculoDefault  {

	/**
	 * Construtor de um carro
	 * @param img desenho do carro
	 * @param f faixa em que o carro vai ser colocado
	 * @param veloc velocidade natural do carro
	 */
	public Carro( ComponenteVisual img, Faixa f, int veloc, int resistencia ){
		super(img, f, veloc, resistencia);
	}
	
	/**
	 * processa quando está em mau piso
	 * @param mp o mau piso em que está
	 */
	protected void estaMauPiso( MauPiso mp ){
		setVelocidade( mp.getVelMaxima() * 3/2 );
		reduzResistencia(1);
	}
		
}