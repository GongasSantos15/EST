package faroest.visitante;

import java.awt.Graphics2D;
import java.awt.Point;
import faroest.mundo.Porta;
import prof.jogos2D.image.ComponenteVisual;

public interface Visitante {

	/* ---------------------------------------------- PORTA ------------------------------------ */
	int fecharPorta();
	void portaAberta();
	boolean podeFechar();
	
	/* -------------------------------------------- VISITANTE ------------------------------------ */
	int baleado();
	
	// Sistema
	void atualizar();
	void desenhar( Graphics2D g );
	void fezAsneira( String nomeImg );
	Visitante clone();
	
	/* --------------------------------------------- GETTERS ------------------------------------ */
	ComponenteVisual getImagem();
	String getNome();
	int getStatus();
	Porta getPorta();
	int getPontos();
	
	/* --------------------------------------------- SETTERS ------------------------------------ */
	void setImagem( String nome );
	void setPosicao( Point posicao );
	void setNome( String nome );
	void setStatus( int status );
	void setPorta( Porta p );
	
	
}
