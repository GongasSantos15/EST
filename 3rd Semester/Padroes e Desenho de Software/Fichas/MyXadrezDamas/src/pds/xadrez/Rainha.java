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
		
		//Verificação se a posição de destino é na diagonal, na vertical e na horizontal da peça
		if ( !eDiagonal(dest) && !eVertical(dest) && !eHorizontal(dest) )
			return false;
		
		// Verificação se o caminho está livre
		if( !caminhoLivre(dest) )
			return false;
		
		// Retornar null se a peça obtida é de cor diferente ou se não exisitr peça
		Peca p = getTabuleiro().getPeca(dest);
		return p == null || p.getCor() != getCor();
		
	}

}
