package pds.peca;

/* --------------------------------------------------------------------------------- IMPORTS------------------------------------------------------------------------------------ */
import java.awt.*;
import java.util.Objects;

import javax.swing.Icon;

public class PecaDefault implements Peca {
	
	/* ----------------------------------------------------------------------------- VARIÁVEIS --------------------------------------------------------------------------------- */
	private Tabuleiro tabuleiro;
	private Icon figura;
	private int cor;
	private Point posicao;
	
	/* ----------------------------------------------------------------------------- CONSTRUTOR -------------------------------------------------------------------------------- */
	public PecaDefault(int cor, Icon figura) {
		this.cor = cor;
		this.figura = Objects.requireNonNull(figura);
	}
	
	/* ------------------------------------------------------------------------------- MÉTODOS --------------------------------------------------------------------------------- */
	@Override
	public boolean podeMover(Point dest) {
		if( dest.equals(posicao) )
			return false;
		return true;
	}

	@Override
	public boolean mover(Point dest) {
		if (!podeMover( dest )) 
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
		return posicao;
	}

	@Override
	public void setTabuleiro(Tabuleiro tab) {
		this.tabuleiro = tab;		
	}

	@Override
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	@Override
	public int getCor() {
		return cor;
	}

	@Override
	public void setCor(int cor) {
		this.cor = cor;
		
	}

	@Override
	public Icon getFigura() {
		return figura;
	}

	@Override
	public void setFigura(Icon icon) {
		this.figura = icon;
	}
	
	// Verifica se é possível andar na Horizonal (x da peca de origem == x da peca de destino )
	protected boolean eHorizontal(Point dest) {
		return getPosicao().y == dest.y;
	}

	// Verifica se é possível andar na Vertical (y da peça de origem == y da peça de destino)
	protected boolean eVertical(Point dest) {
		return getPosicao().x == dest.x;
	}
	
	// Verifica se é possível andar na Diagonal (módulo do x da peca de origem - x peca destino == módulo do y da peca de origem - y peca destino)
	protected boolean eDiagonal( Point dest ) {
		return Math.abs( getPosicao().x - dest.x ) == Math.abs( getPosicao().y - dest.y );
	}
	

	/** verifica até chegar ao destino se a casa está livre
	 * @param dest casa de destino da peça
	 * @return true se tem caminho livre, false caso contrário
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
}
	