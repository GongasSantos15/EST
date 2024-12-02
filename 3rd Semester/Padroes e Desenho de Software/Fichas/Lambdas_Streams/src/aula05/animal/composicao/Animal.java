package aula05.animal.composicao;

public interface Animal {

	public void comer();
	public void dormir();
	public void mover();
	public void falar();

	public String getNome();
	public int getIdade();
	public int getTamanho( );
	public int getPeso();
}
