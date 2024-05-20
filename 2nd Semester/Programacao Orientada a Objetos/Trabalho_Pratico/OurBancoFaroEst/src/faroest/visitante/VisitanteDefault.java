package faroest.visitante;

import java.awt.Graphics2D;
import java.awt.Point;

import faroest.mundo.Porta;
import prof.jogos2D.image.ComponenteVisual;
import prof.jogos2D.util.ComponenteVisualLoader;

public abstract class VisitanteDefault implements Visitante {
	
	/* ---------------------------------------------- Variáveis ------------------------------------ */
	String nome;
	int pontos;
	int status;
	Porta porta;
	ComponenteVisual img;

	/* --------------------------------------------- Construtor ------------------------------------ */
	
	public VisitanteDefault( String nome, int pontos) {
		
		setNome( nome );
		this.pontos = pontos;
		
	}
	
	/* --------------------------------------------- Métodos ------------------------------------ */

	@Override
	public void desenhar(Graphics2D g) {
		img.desenhar(g);
	}
	
	@Override
	public ComponenteVisual getImagem() {
		return img;
	}
	
	@Override
	public void setImagem(String nome) {
		Point p = img != null? img.getPosicao() : null;
		img = ComponenteVisualLoader.getCompVisual( nome );
		img.setPosicao( p );
	}
	
	@Override
	public void setPosicao(Point posicao) {
		img.setPosicao( (Point)posicao.clone() );
	}
	
	@Override
	public String getNome() {
		return nome;
	}
	
	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public int getStatus() {
		return status;
	}
	
	@Override
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public void fezAsneira(String nomeImg) {
		porta.getMundo().perdeNivel( nomeImg );	
	}
	
	@Override
	public Porta getPorta() {
		return porta;
	}
	
	@Override
	public void setPorta(Porta p) {
		porta = p;
	}
	
	@Override
	public int getPontos() {
		return pontos;
	}
	
	

}
