package pds.xadrez;

import java.awt.Point;

import javax.swing.Icon;

import pds.peca.Peca;
import pds.peca.PecaDefault;

public class Torre extends PecaDefault {

	public Torre(int cor, Icon figura) {
		super(cor, figura);

	}

	@Override
	public boolean podeMover(Point dest) {

		if (!eVertical(dest) && !eHorizontal(dest)) {
			return false;
		}
		if(!caminhoLivre(dest)) {
			return false;
		}
		
		Peca p = getTabuleiro().getPeca(dest);
		return p == null || p.getCor() != getCor();

	}

	


}
