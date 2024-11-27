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
		
		int dx = Math.abs( dest.x - getPosicao().x );
		int dy = Math.abs( dest.y - getPosicao().y );
		
		// Verificação se a distancia x == 1 e a distancia y == 2  OU  distancia x == 2 e a distancia y == 1, de forma a fazer o formato L característico do cavalo
		if ( (dx == 1 && dy == 2) || (dx == 2 && dy == 1) ) {		
			// Retornar null se a peça obtida é de cor diferente ou se não exisitr peça
			Peca p = getTabuleiro().getPeca(dest);
			return p == null || p.getCor() != getCor();
		}
		return false;
		
	}

}
