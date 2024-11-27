package pds.xadrez;

import java.awt.Point;

import javax.swing.Icon;

import pds.peca.Peca;
import pds.peca.PecaDefault;

public class Bispo extends PecaDefault {

	public Bispo(int cor, Icon figura) {
		super(cor, figura);

	}

	@Override
	public boolean podeMover(Point dest) {

		if (!eDiagonal(dest)) {
			return false;
		}
		if(!caminhoLivre(dest)) {
			return false;
		}
		
		Peca p = getTabuleiro().getPeca(dest);
		return p == null || p.getCor() != getCor();

	}

	


}
