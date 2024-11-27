package pds.xadrez;

import java.awt.Point;

import javax.swing.Icon;

import pds.peca.Peca;
import pds.peca.PecaDefault;

public class Peao extends PecaDefault {

	public Peao(int cor, Icon figura) {
		super(cor, figura);

	}

	@Override
	public boolean podeMover(Point dest) {

		int dx = Math.abs(dest.x - getPosicao().x);
		int dy = (dest.y - getPosicao().y);

		if(( dy >= 0 && getCor()!= Peca.BRANCAS) ||
				(dy <= 0 && getCor()!= Peca.PRETAS))
			return false;

		dy = Math.abs(dy);
		if (dy> 2 || dx >1){
			return false;
		}

		if(dx == 1) {
			Peca p = getTabuleiro().getPeca(dest);
			return dy == 1 && p != null && p.getCor() != getCor();
		}

		boolean primeiraVez = getCor() == Peca.BRANCAS ? getPosicao().y == 2 : getPosicao().y == 7;

		if(!primeiraVez && dy == 2) {
			return false;
		}

		Peca p = getTabuleiro().getPeca(dest);
		return p == null && caminhoLivre(dest);

	}

	@Override
	public boolean ePromovivel() {

		return ((getPosicao().y == 8 && getCor()==Peca.BRANCAS) || (getPosicao().y == 1 && getCor()==Peca.PRETAS));

	}
}
