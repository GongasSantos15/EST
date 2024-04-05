package arranque;
import java.awt.Point; 

import menu.Mapa;
import menu.JanelaControlo;
import petroleum.Camiao;
import petroleum.Central;
import petroleum.Posto;

/** Responsável por cria o ambiente de execução e criar a janela */
public class Main {

	public static void main(String[] args) {
		// TODO criar a central requerida
		Central c = null;
		
		// TODO criar os postos

		// TODO criar os camiões
		
		// criar a apresentar a janela principal
		JanelaControlo postosFrame = new JanelaControlo( c );
		postosFrame.setVisible( true );
	}

}
