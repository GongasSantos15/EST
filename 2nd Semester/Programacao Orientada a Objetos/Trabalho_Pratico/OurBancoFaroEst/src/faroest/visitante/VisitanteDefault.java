package faroest.visitante;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import faroest.mundo.Porta;
import prof.jogos2D.image.ComponenteAnimado;
import prof.jogos2D.image.ComponenteVisual;
import prof.jogos2D.util.ComponenteVisualLoader;

public abstract class VisitanteDefault implements Visitante, Cloneable { // CLONEABLE???
	
	private ComponenteVisual img;
	private String nome;
	private int pontos;
	private Porta porta;
	private int status;
	
	public VisitanteDefault (String nome, int pontos) {
		setNome(nome);
		this.pontos = pontos;
	}

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
		Point p = img != null ? img.getPosicao() : null;
		img = ComponenteVisualLoader.getCompVisual( nome );
		img.setPosicao( p );
	}

	@Override
	public void setPosicao(Point posicao) {
		img.setPosicao((Point) posicao.clone() );
		
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public void setNome( String nome ) {
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
	public void setPorta( Porta porta ) {
		this.porta = porta;
	}

	@Override
	public Porta getPorta() {
		return porta;
	}

	@Override
	public int getPontos() {
		return pontos;
	}
	
	/* MODIFICADO */
	/** define qual a imagem de saída. A imagem de saída é um "efeito especial",
	 * que pode ser o dinheiro ou outra (num futuro) 
	 * @param nomeImg o nome da imagem de saida
	 */

}
