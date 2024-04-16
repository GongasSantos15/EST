/*
    Esta classe representa uma Janela de consola que permite ao
    utilizador escrever nela. Permite ao programador escrever e
    limpar o seu conteúdo.
    Utiliza a classe ConsolePanel desenvolvida por David Eck 
    
    Autor:  F. Sérgio Barbosa 
            Departamento de Informática
            Escola Superior de Tecnologia de Castelo Branco
            email:  fsergio@ipcb.pt

    Criada em 27 de Março de 2007.
    Última atualização a 1 de Março de 2023
*/

package consola;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class SConsola {

	private ConsolePanel console = new ConsolePanel();
	private JFrame janela;

	/** cria uma janela de consola com um determinado título, posição no écran e dimensão
	 * @param titulo título a aparecer na janela da consola
	 * @param x coordenada x da posição da janela
	 * @param y coordenada y da posição da janela
	 * @param comp comprimento da janela
	 * @param alt altura da janela
	 */
	public SConsola(String titulo, int x, int y, int comp, int alt) {
		janela = new ConsoleWindow( titulo, x, y, comp, alt );
	}

	/** Cria uma janela de consola com um determinado título e dimensão.
	 * A janela será centrada no écran
	 * @param titulo título a aparecer na janela da consola
	 * @param comp comprimento da janela
	 * @param alt altura da janela
	 */
	public SConsola(String titulo, int comp, int alt) {
		janela = new ConsoleWindow( titulo, 20, 20, comp, alt );
		janela.setLocationRelativeTo( null );
	}
	
	/** Cria uma janela de consola com um dado título
	 * A janela será centrada no écran e tera uma dimensão predefinida de 500x500
	 * @param titulo título a aparecer na janela
	 */
	public SConsola( String titulo ) {
		this( titulo, 500, 500);
	}
	
	/** Cria uma janela de consola com uma dada dimensão
	 * A janela será centrada no écran.
	 * @param comp comprimento da janela
	 * @param alt altura da janela
	 */
	public SConsola(int comp, int alt) {
		this( "", comp, alt );
	}
	
	/** Cria uma janela de consola.
	 * A janela será centrada no écran e terá uma dimensão de 500x500
	 */
	public SConsola( ){
		this( "Consola", 500, 500);
	}

	// redireccionar o input da Janela para a consola 
	public byte  readByte()   { return console.getlnByte(); }
	public short readShort()  { return console.getlnShort(); }
	public boolean readBoolean() { return console.getlnBoolean(); }
	public int   readInt()    { return console.getlnInt(); }
    public long  readLong()   { return console.getlnLong(); }
    public char  readChar()   { return console.getlnChar(); }
    public float readFloat()  { return console.getlnFloat();}
    public Double readDouble(){ return console.getlnDouble();}
    public String readWord()  { return console.getlnWord();}
    public String readLine()  { return console.getln(); }
    
    // redireccionar o output da Janela para a consola
    public void print( int x )       { console.put( x ); }
    public void println( int x )     { console.putln( x ); }
    public void print( long x )      { console.put( x ); }
    public void println( long x )    { console.putln( x ); }
    public void print( double x )    { console.put( x ); }
    public void println( double x )  { console.putln( x ); }
    public void print( char x )      { console.put( x ); }
    public void println( char x )    { console.putln( x ); }
    public void print( boolean x )   { console.put( x ); }
    public void println( boolean x ) { console.putln( x ); }
    public void print( String x )    { console.put( x ); }
    public void println( String x )  { console.putln( x ); }
    public void println( )           { console.putln(); }
    
    // limpar a consola
    public void clear() {
    	console.clear();
    }
    
	class ConsoleWindow extends JFrame {
		/**/
		private static final long serialVersionUID = 20230301;

		ConsoleWindow( String titulo, int x, int y, int comp, int alt) {
			setLocation(x, y);
			setSize( comp, alt );
			setTitle( titulo );
			setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			getContentPane().add( console, BorderLayout.CENTER );			
			setVisible( true );		
		}
	}

	public void close(){
		janela.setVisible( false );
		janela.dispose();
	}
}
