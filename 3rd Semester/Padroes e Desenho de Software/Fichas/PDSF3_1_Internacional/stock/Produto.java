package stock;

/**
 * Esta classe representa um Produto presente em stock. 
 * Neste caso não se usaram interfaces no topo da hierarquia porque existem
 * métodos que só podem ser chamados pelo stock, os quais não poderiam aparecer
 * na interface, pois esta só pode apresentar métodos públicos.
 * 
 * @author F. Sérgio Barbosa
 *
 */
public class Produto {

	private int id;
	private String descricao;
	private int quantidade = 0; 
	private long precoVenda;
	private long precoCusto;
		
	/**
	 * construtor de um produto, só deve ser chamado pelo stock
	 * @param id identificador do produto
	 * @param descricao descrição do produto
	 * @param preco preço de referência
	 */
	public Produto(int id, String descricao, long pvp, long pcusto) {
		this.id = id;
		this.descricao = descricao;
		this.precoVenda = pvp;
		this.precoCusto = pcusto;
	}
	
	/**
	 * retorna a descrição do produto
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * retorna o id do produto
	 */
	public long getId() {
		return id;
	}

	/**
	 * retorna o preço de venda do produto
	 * @return o preço de venda do produto
	 */
	public long getPrecoVenda() {
		return precoVenda;
	}

	/**
	 * retorna o preço de custo do produto
	 * @return o preço de custo do produto
	 */
	public long getPrecoCusto() {
		return precoCusto;
	}
	
	/**
	 * retorna a quantidade em stock desde produto
	 * @return a quantidade em stock desde produto
	 */
	public int getStock() {
		return quantidade;
	}

	/**
	 * indica se o produto tem uma quantidade ilimitada
	 * @return true se o produto é ilimitado
	 */
	public boolean eIlimitado() {
		return quantidade == -1;
	}
	
	/**
	 * indica se o produto é renov�vel
	 * @return true se o produto for renovável
	 */
	public boolean eRenovavel() {
		return !eIlimitado();
	}
	
	
	/**
	 * altera a quantidade em stock deste produto. este método só pode ser chamado
	 * pela classe StockManager
	 * @param quant quantidade a tirar/repor em stock
	 * @throws OutOfStockException quando se pretende tirar mais que o existente
	 */
	protected void alterarExistencias(int quant) throws OutOfStockException {
		// este método está assim feito para ser reutilizado pelas subclasses
		// se não fosse assim a subclasse Composto teria de fazer estes
		// testes todos novamente. È a vantagem de usarmos os métodos getStock
		// em vez de usar directamente a quantidade
		int existe = getStock();
		// se quantidade for -1 é porque é sem limite, logo ignora
		if( existe == -1 )
			return;
		if( quant > 0 && quant > existe )
			throw new OutOfStockException();
		quantidade += quant;
	}
	
	/**
	 * Remove todo o stock do produto.
	 * Tamb�m só deve ser chamado pela classe StockManager
	 */
	protected void limpaStock(){
		// se quantidade for -1 é porque é sem limite, logo ignora
		if( quantidade == -1 )
			return;
		quantidade = 0;
	}

	/**
	 * Altera a quantidade do produto
	 * @param quant nova quantidade do produto
	 */
	protected void setQuantidade( int quant ){
		quantidade = quant;
	}
}
