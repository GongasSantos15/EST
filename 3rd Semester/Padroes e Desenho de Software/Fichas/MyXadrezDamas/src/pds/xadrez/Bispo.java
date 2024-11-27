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
		
		//Verificação se a posição de destino é apenas na diagonal da peça
		if ( !eDiagonal(dest) )
			return false;
		
		// Verificação se o caminho está livre
		if( !caminhoLivre(dest) )
			return false;
		
		// Retornar null se a peça obtida é de cor diferente ou se não exisitr peça
		Peca p = getTabuleiro().getPeca(dest);
		return p == null || p.getCor() != getCor();
		
	}

}
