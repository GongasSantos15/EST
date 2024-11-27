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
		int dx = Math.abs(dest.x - getPosicao().x) ;
		int dy = dest.y - getPosicao().y ;
		
		// Restringe o movimento de andar para trás e para os lados
		if (( dy >= 0 && getCor() != Peca.BRANCAS )  ||  (dy <= 0 && getCor() != Peca.PRETAS ))
			return false;
		
		// Permite ao peao andar 2 casas para a frente apenas
		dy = Math.abs( dy );
		if ( dy > 2 || dx > 1 )
			return false;
		
		// Permite eliminar a peça de cor diferente se a distancia em x for igual a 1
		if ( dx == 1 ) {
			Peca p = getTabuleiro().getPeca(dest);
			return dy == 1 && p != null && p.getCor() != getCor();
		}
		
		// Restringe a primeira jogada de cada peão a 2 casas e nas restantes a apenas 1 casa
		boolean primeiraJogada = getCor() == Peca.BRANCAS ? getPosicao().y == 2 : getPosicao().y == 7;
		if ( !primeiraJogada && dy == 2 )
			return false;
		
		Peca p = getTabuleiro().getPeca(dest);
		return p == null && caminhoLivre(dest);
		
	}
	
	@Override
	public boolean ePromovivel() {
		return getCor() == Peca.BRANCAS ? getPosicao().y == 8 : getPosicao().y == 1;
	}

}
