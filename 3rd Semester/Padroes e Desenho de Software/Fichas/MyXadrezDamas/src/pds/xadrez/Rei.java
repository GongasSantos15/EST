package pds.xadrez;

import java.awt.Point;
import javax.swing.Icon;
import pds.peca.Peca;
import pds.peca.PecaDefault;

public class Rei extends PecaDefault {

	public Rei(int cor, Icon figura) {
		super(cor, figura);
	}
	
	@Override
	public boolean podeMover(Point dest) {
		
		int dx = Math.abs( dest.x - getPosicao().x );
		int dy = Math.abs( dest.y - getPosicao().y );
		
		//Verificação se a distancia entre a origem e destino = 1, tanto na vertical, vertical ou diagonal
		if ( dx > 1 || dy > 1 )
			return false;
					
		
		// Retornar null se a peça obtida é de cor diferente ou se não exisitr peça
		Peca p = getTabuleiro().getPeca(dest);
		return p == null || p.getCor() != getCor();
		
	}

}
