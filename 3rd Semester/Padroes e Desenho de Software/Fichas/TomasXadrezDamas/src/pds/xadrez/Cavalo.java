package pds.xadrez;

import java.awt.Point;

import javax.swing.Icon;

import pds.peca.Peca;
import pds.peca.PecaDefault;

public class Cavalo extends PecaDefault {

	public Cavalo(int cor, Icon figura) {
		super(cor, figura);

	}

	@Override
	public boolean podeMover(Point dest) {
		
		int dx = Math.abs(dest.x - getPosicao().x);
		int dy = Math.abs(dest.y - getPosicao().y);
		
		if (!(dx == 1  && dy == 2) && !(dx ==2  && dy == 1) ){
			return false;
		}
		
		Peca p = getTabuleiro().getPeca(dest);
		return p == null || p.getCor() != getCor();

	}

	


}
