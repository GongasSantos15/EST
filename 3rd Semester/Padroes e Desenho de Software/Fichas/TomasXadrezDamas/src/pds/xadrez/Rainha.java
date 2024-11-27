package pds.xadrez;

import java.awt.Point;

import javax.swing.Icon;

import pds.peca.Peca;
import pds.peca.PecaDefault;

public class Rainha extends PecaDefault {

	public Rainha(int cor, Icon figura) {
		super(cor, figura);

	}

	@Override
	public boolean podeMover(Point dest) {

		if (!eDiagonal(dest) && (!eVertical(dest) && !eHorizontal(dest))){
			return false;
		}
		
		if(!caminhoLivre(dest)) {
			return false;
		}
		
		
		Peca p = getTabuleiro().getPeca(dest);
		return p == null || p.getCor() != getCor();

	}

	


}
