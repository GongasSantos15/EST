package pds.peca;

import java.awt.*;
import java.util.Objects;

import javax.swing.Icon;

public class PecaDefault implements Peca {
	
	private Icon figura;
	private Point posicao;
	private int cor;
	private Tabuleiro tabuleiro;
	
	public PecaDefault(int cor, Icon figura) {
		this.cor = cor;
		this.figura = Objects.requireNonNull(figura);
	}
	
	/** verifica até chegar ao destino se a casa está livre
	 * @param dest casa de destino da peça
	 * @return true se tem caminho livew, false caso contrário
	 */
	protected boolean caminhoLivre( Point dest ){
		int dx = 0;   // deslocamento em x
		int dy = 0;   // deslocamento em y
		if( dest.x - getPosicao().x < 0 )
			dx = -1;
		else if( dest.x - getPosicao().x > 0 )
			dx = 1;
		if( dest.y - getPosicao().y < 0 )
			dy = -1;
		else if( dest.y - getPosicao().y > 0 )
			dy = 1;
				
		// podia estar dentro do for, mas assim torna-se mais legível
		Point atual = new Point(getPosicao().x+dx, getPosicao().y+dy);
		for( ; !atual.equals( dest ); atual.translate(dx, dy) ){
 		    if( getTabuleiro().getPeca( atual ) != null )
			   	return false;
		}
		
		return true;
	}

	@Override
	public boolean podeMover(Point dest) {
		if(dest.equals(posicao))
			return false;
		return true;
	}

	@Override
	public boolean mover(Point dest) {
		if (!podeMover(dest))
			return false;
		posicao = dest;
		return true;
	}

	@Override
	public boolean ePromovivel() {
		return false;
	}

	@Override
	public void setPosicao(Point pos) {
		this.posicao = pos;
	}

	@Override
	public Point getPosicao() {
		return this.posicao;
	}

	@Override
	public void setTabuleiro(Tabuleiro tab) {
		this.tabuleiro = tab;
		
	}

	@Override
	public Tabuleiro getTabuleiro() {
		return this.tabuleiro;
	}

	@Override
	public int getCor() {
		return this.cor;
	}

	@Override
	public void setCor(int cor) {
		this.cor = cor;
	}

	@Override
	public Icon getFigura() {
		return this.figura;
	}

	@Override
	public void setFigura(Icon icon) {
		this.figura = icon;
	}
	
	protected boolean eHorizontal(Point dest) {
		return dest.y==getPosicao().y;
	}
	
	protected boolean eVertical(Point dest) {
		return dest.x == getPosicao().x;
	}
	
	protected boolean eDiagonal(Point dest) {
		return Math.abs(getPosicao().x-dest.x) == Math.abs(getPosicao().y-dest.y);
	}
}
