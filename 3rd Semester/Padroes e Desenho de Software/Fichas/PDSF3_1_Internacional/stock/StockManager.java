package stock;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe que mantém o stock 
 */
public class StockManager {

	// mapa com os produtos indexado pelo id
	private Map<Long,Produto> produtos = new HashMap<Long,Produto>();  	
	
	/**
	 * Adiciona um novo produto ao stock
	 * @param p produto a adicionar
	 * @param ini quantidade de produto inicial
	 */
	public void addProduto( Produto p, int quant ) {
		p.setQuantidade( quant );
		produtos.put( p.getId(), p);		
	}

	
	/**
	 * Devolve o produto correspondente ao ID
	 * @param id id do produto a procurar
	 * @return o produto correspondente ao ID
	 */
	public Produto getProduto(long id) {		
		return produtos.get( id );
	}

	/**
	 * retorna todos os produtos referenciados
	 * @return os produtos referenciados
	 */
	public Produto[] getProdutos() {	
		Produto ps[] = new Produto[ produtos.size() ];
		return produtos.values().toArray( ps );
	}

	/**
	 * devolve o stock do produto com o referido id
	 * @param id id do produto de que se pretende saber o stock 
	 * @return o stock do produto 
	 */
	public int getStockProduto(long id) {
		Produto p = produtos.get( id ); 
		// podia-se fazer directamente p.getStock( ), mas assim fica
		// mais estável, pois mais tarde podemos querer adicionar
		// mais coisas a getStockProduto e assim já não é necessário alterar aqui
		// A mesma t�cnica é usada no método alterarExistencias
		return getStockProduto( p );
	}

	/**
	 * devolve o stock do produto com o referido id
	 * @param id id do produto de que se pretende saber o stock 
	 * @return o stock do produto 
	 */
	public int getStockProduto(Produto p ) {
		return p.getStock();
	}
	
	/**
	 * altera as existências (quantidade) de um produto
	 * @param id id do do produto a alterar
	 * @param quant quantidade a reduzir ou a aumentar
	 * @throws OutOfStockException atira esta excepção se se quiser tirar mais do que existe
	 */
	public void alteraExistencias(int id, int quant) throws OutOfStockException {
		Produto p = getProduto( id );
		alteraExistencias( p, quant );
	}
	
	/**
	 * altera as existências (quantidade) de um produto
	 * @param p produto a alterar
	 * @param quant quantidade a reduzir ou a aumentar
	 * @throws OutOfStockException atira esta excepção se se quiser tirar mais do que existe
	 */
	public void alteraExistencias(Produto p, int quant) throws OutOfStockException {
		p.alterarExistencias( quant );
	}

}
