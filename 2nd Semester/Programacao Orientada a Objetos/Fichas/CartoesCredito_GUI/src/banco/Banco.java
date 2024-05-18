package banco;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

import cartao.*;

public class Banco {

	/**
	 * Mapas para guardar as contas e os cartões
	 */
	private Map<Integer,Conta> contas = new HashMap<Integer,Conta>();
	private Map<Integer,Cartao> cartoes = new HashMap<Integer,Cartao>();
	
	/** imagens para os cartões */
	private BufferedImage IMAGEM_CARTAO_COMPRAS; 
	private BufferedImage IMAGEM_CARTAO_DEBITOPLUS; 
	private BufferedImage IMAGEM_CARTAO_FIMMES; 
	
	/** Cria o banco
	 */
	public Banco(){
		try {
			IMAGEM_CARTAO_COMPRAS = ImageIO.read( new File("./recursos/card_azul.png") );
			IMAGEM_CARTAO_DEBITOPLUS = ImageIO.read( new File("./recursos/card_rosa.png") );
			IMAGEM_CARTAO_FIMMES = ImageIO.read( new File("./recursos/card_verde.png") );
		} catch (IOException e) {
			throw new RuntimeException( "Imagens de cartões não existem!" );
		}
	}

	/** Cria um cartão de compras, indicando o seu número
	 * @param num número a usar no cartão
	 * @param c conta à qual vai ser adicionado
	 * @param plafond plafond inicial do cartão
	 * @return o cartão criado
	 */
	public Cartao criarCartaoCompras( int num, Conta c, long plafond ) {
		Image img = criarImagem( num, IMAGEM_CARTAO_COMPRAS );
		// TODO criar o cartão
		return null;
	}
	
	/** Cria um cartão de compras, sendo que o número será gerado automaticamente
	 * @param c conta à qual vai ser adicionado
	 * @param plafond plafond inicial do cartão
	 * @return o cartão criado
	 */
	public Cartao criarCartaoCompras( Conta c, long plafond ) {
		return criarCartaoCompras( gerarNumeroCartao(), c, plafond );
	}

	/** Cria um cartão de débito plus, indicando o seu número
	 * @param num número a usar no cartão
	 * @param c conta à qual vai ser adicionado
	 * @param plafond plafond inicial do cartão
	 * @return o cartão criado
	 */
	public Cartao criarCartaoDebitoPlus( int num, Conta c, long plafond ) {
		Image img = criarImagem( num, IMAGEM_CARTAO_DEBITOPLUS );
		// TODO criar o cartão
		return null;
	}
	
	/** Cria um cartão de débito plus, sendo que o número será gerado automaticamente
	 * @param c conta à qual vai ser adicionado
	 * @param plafond plafond inicial do cartão
	 * @return o cartão criado
	 */
	public Cartao criarCartaoDebitoPlus( Conta c, long plafond ) {
		return criarCartaoDebitoPlus( gerarNumeroCartao(), c, plafond );
	}

	/** Cria um cartão de Fim do Mês, indicando o seu número
	 * @param num número a usar no cartão
	 * @param c conta à qual vai ser adicionado
	 * @param plafond plafond inicial do cartão
	 * @return o cartão criado
	 */
	public Cartao criarCartaoFimMes( int num, Conta c, long plafond ) {
		Image img = criarImagem( num, IMAGEM_CARTAO_FIMMES );
		// TODO criar o cartão
		return null;
	}
	
	/** Cria um cartão de fim do mês, sendo que o número será gerado automaticamente
	 * @param c conta à qual vai ser adicionado
	 * @param plafond plafond inicial do cartão
	 * @return o cartão criado
	 */
	public Cartao criarCartaoFimMes( Conta c, long plafond ) {
		return criarCartaoFimMes( gerarNumeroCartao(), c, plafond );
	}

	/** Faz as operações do fim do mês
	 * @return retorna uma lista com todos os cartões que apresentaram
	 * problemas nesta fase , ou seja, cartões cujo saldo não foi suficiente
	 * para pagar as dívidas
	 */
	public List<Cartao> fimMes() {
		// TODO fazer este método
		ArrayList<Cartao> problemas = new ArrayList<Cartao>();
		
		return problemas;
	}
	
	/** Gera um número de conta segundo o esquema escolhido pelo banco
	 * @return o número a usar numa nova conta
	 */
	public int gerarNumeroConta( ) {
		// cada banco tem um algoritmo próprio para gerar 
		// o número de conta. O deste é muito simples: Vê quantas contas existem
		// e multiplica por 10 e verifica se o número já foi atribuído,
		// se foi vai somando 10 até não haver conta com esse número
		int numero = contas.size() * 10;
		while( getConta( numero ) != null )
			numero += 10;
		return numero;		
	}
	
	/** Gera um número de cartão sugundo o esquema escolhido pelo banco
	 * @return o número a usar num novo cartão
	 */
	public int gerarNumeroCartao( ) {
		// cada banco tem um algoritmo próprio para gerar 
		// o número de cartão. O deste é muito simples: Vê quantos cartões existem
		// e multiplica por 10 e verifica se o número já foi atribuído,
		// se foi vai somando 10 até não haver conta com esse número
		int numero = cartoes.size() * 10;
		while( getCartao( numero ) != null )
			numero += 10;
		return numero;		
	}

	/** Pesquisa e retona a conta que tenha um dado número
	 * @param numero o número da conta a procurar
	 * @return a conta que tem o número ou null se não existir
	 */
	public Conta getConta( int numero ) {
		return contas.get( numero );
	}
	
	/** Adiciona uma conta ao banco
	 * @param c conta a adicionar
	 */
	public void addConta( Conta c ) {		
		contas.put( c.getNumero(), c);
	}
	
	/** Remove a conta do banco
	 * @param c a conta a remover
	 */
	public void removeConta( Conta c ) {
		contas.remove( c.getNumero() );
	}
	
	/** Retorna as contas do banco
	 * @return as contas do banco
	 */
	public Collection<Conta> getContas(){
		return Collections.unmodifiableCollection( contas.values() );
	}
	
	/** Pesquisa e retona o cartão que tenha um dado número
	 * @param numero o número do cartão a procurar
	 * @return o cartão que tem o número ou null se não existir
	 */
	public Cartao getCartao( int numero ) {
		return cartoes.get( numero );
	}
	
	/** adiciona um cartão ao banco
	 * @param c cartão a adicionar
	 */
	public void addCartao( Cartao c ) {
		cartoes.put( c.getNumero(), c);
	}
	
	/** remove um cartão do banco
	 * @param c cartão a remover
	 */
	public void removeCartao( Cartao c ) {
		contas.remove( c.getNumero() );
	}
	
	/** retorna todos os cartões do banco
	 * @return todos os cartões do banco
	 */
	public Collection<Cartao> getCartoes(){
		return Collections.unmodifiableCollection( cartoes.values() );
	}

	/** cria uma imagem de um cartão, "imprimido" o número no mesmo
	 * @param num número a "imprimir" no cartão
	 * @param baseImg imagem base do cartão
	 * @return a imagem do cartão "impresso"
	 */
	private Image criarImagem( int num, BufferedImage baseImg ) {
		// cria uma imagem igual à original
		BufferedImage cardImg = new BufferedImage(baseImg.getWidth(), baseImg.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = cardImg.createGraphics();
		g.drawImage( baseImg, 0, 0, null);
		// desenha os números no cartão		
		g.setColor( Color.black );
		g.drawString( "" + num, 31, 36 );
		g.setColor( Color.WHITE );
		g.drawString( "" + num, 30, 35 );
		g.dispose();
		return cardImg;
	}
}
