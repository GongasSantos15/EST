package estrada.veiculo;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Area;

import estrada.estrada.Faixa;
import prof.jogos2D.image.ComponenteVisual;
import prof.jogos2D.util.Vector2D;

public interface Veiculo {
	
	/**
	 * devolve o componente visual associado ao veiculo
	 * @return o componente visual associado ao veiculo
	 */
	ComponenteVisual getImagem();

	/**
	 * define o componente visual do veiculo
	 * @param imagem o novo componente visual do veiculo
	 */
	void setImagem(ComponenteVisual imagem);

	/**
	 * devolve a velocidade atual do veiculo
	 * @return a velocidade atual do veiculo
	 */
	int getVelocidade();

	/**
	 * define a velocidade do veiculo
	 * @param veloc a velocidade a colocar no veiculo
	 */
	void setVelocidade(int veloc);

	/**
	 * devolve a velocidade natural deste veiculo
	 * @return a velocidade natural deste veiculo
	 */
	int getVelocidadeNatural();

	/**
	 * define a posição no écran para o veiculo.
	 * A posição é o ponto superior esquerdo da imagem
	 * @param pos a posição no écran para o veiculo
	 */
	void setPosicao(Point pos);

	/**
	 * definir qual a posição do centro do veiculo
	 * @param pos a posição do centro do veiculo
	 */
	void setPosicaoCentro(Point pos);

	/**
	 * devolve a posição no écran do veiculo.
	 * A posição é o ponto superior esquerdo da imagem
	 * @return a posição no écran do veiculo.
	 */
	Point getPosicao();

	/**
	 * devolve a posição no écran do centro do veiculo.
	 * @return a posição do centro do veiculo.
	 */
	Point getPosicaoCentro();

	/**
	 * método que vai actualizar o veiculo, em cada turno de movimento
	 */
	void atualizar();

	/**
	 * devolve a área ocupada pelo veiculo
	 * @return a área ocupada pelo veiculo
	 */
	Area getArea();

	/**
	 * desenhar o veiculo
	 * @param g elemento onde desenhar o veiculo 
	 */
	void desenhar(Graphics g);

	/**
	 * verifica se o ponto pt está dentro da área do veiculo
	 * @param pt ponto a verificar
	 * @return true se pt está dentro do veiculo
	 */
	boolean estaDentro(Point pt);

	/**
	 * indica se o veiculo está parado
	 * @return true quando o veiculo está parado
	 */
	boolean estaParado();

	/**
	 * indica se o veiculo está a virar
	 * @return true se o veiculo está a virar
	 */
	boolean estaVirar();

	/**
	 * define o estado de paragem do veiculo
	 * @param parado estado de paragem a colocar
	 */
	void setParado(boolean parado);

	/**
	 * define qual a faixa em que o veiculo vai ficar
	 * @param f faixa a colocar o veiculo
	 */
	void setFaixa(Faixa f);

	/**
	 * devolve a faixa onde o veiculo se desloca
	 * @return a faixa onde o veiculo se desloca
	 */
	Faixa getFaixa();

	/**
	 * indica se o veiculo esta ativo
	 * @return true se o veiculo está ativo
	 */
	boolean isActivo();

	/**
	 * define o estado do veiculo
	 * @param activo o estado a ficar
	 */
	void setAtivo(boolean activo);

	/**
	 * indica se pode mudar de faixa
	 * @param f faixa ara onde mudar
	 * @param x posição da faixa para onde vai
	 * @return true se puder mudar de faixa
	 */
	boolean podeMudarFaixa(Faixa f, int x);

	/**
	 * mudar de faixa
	 * @param f faixa para onde mudar
	 * @param x posição da faixa para onde deve ir
	 */
	void mudarFaixa(Faixa f, int x);

	/**
	 * devolve a direção de movimento do veiculo
	 * @return a direção de movimento do veiculo
	 */
	Vector2D getDireccao();

	/**
	 * devolve a direção inicial do movimento do veiculo
	 * @return a direção inicial do movimento do veiculo
	 */
	Vector2D getDireccaoInicial();
	
	public int getResistencia();
	public void setResistencia(int resistencia);
	public void reduzResistencia(int r);

}