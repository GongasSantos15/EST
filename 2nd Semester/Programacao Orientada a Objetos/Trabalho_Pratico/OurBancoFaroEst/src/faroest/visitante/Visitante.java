package faroest.visitante;

import java.awt.Graphics2D;
import java.awt.Point;
import faroest.mundo.Porta;
import prof.jogos2D.image.ComponenteVisual;

public interface Visitante {
	int fecharPorta();
	void portaAberta();
	boolean podeFechar();
	int baleado();
	void atualizar();
	void desenhar( Graphics2D g );
	ComponenteVisual getImagem();
	void setImagem( String nome );
	void setPosicao( Point posicao );
	String getNome();
	void setNome( String nome );
	int getStatus();
	void setStatus( int status);
	void fezAsneira( String nomeImg);
	void setPorta( Porta p );
	Porta getPorta();
	int getPontos();
}
