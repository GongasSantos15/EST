package cartao;

import java.awt.Image;
import java.util.Objects;

import banco.Conta;
import banco.SaldoInsuficienteException;

public abstract class CartaoDefault implements Cartao {
	
	/* -------------------------------------------------------------------------------------- VARIÁVEIS --------------------------------------------------------------------------------------------------*/

	private int numero;
	private Conta conta;
	private long plafond;
	private Image imagem;
	private boolean ativo;
	private long divida = 0;
	
	/* -------------------------------------------------------------------------------------- CONSTRUTOR --------------------------------------------------------------------------------------------------*/

	// Construtor para o cartão desativo
	public CartaoDefault(int numero, Conta conta, long plafond, Image imagem, boolean ativo) {
		this.numero = numero;
		this.conta = conta;
		this.plafond = plafond;
		this.imagem = imagem;
		this.ativo = ativo;
	}
	
	
	// Construtor para o cartão ativo
	public CartaoDefault(int numero, Conta conta, long plafond, Image imagem) {
		this(numero, conta, plafond, imagem, true);
	}

	/* -------------------------------------------------------------------------------------- MÉTODOS --------------------------------------------------------------------------------------------------*/
	// Apenas os métodos iguais para as 3 classes: CartaoDebito, CartaoDebitoPlus, CartaoFimMes
	
	@Override
	public long getPlafond() {
		return plafond;
	}
	
	@Override
	public void setPlafond(long plafond) {
		
		if (plafond < 0) {
			throw new IllegalArgumentException("Plafond tem de ser >= 0: " + plafond);
		}
		this.plafond = plafond;
		
	}
	
	@Override
	public long getPlafondDisponivel() {
		return getPlafond() - getDivida();
	}
	
	@Override
	public long getDivida() {
		return divida;
	}

	@Override
	public boolean estaAtivo() {
		return ativo;
	}
	
	@Override
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public void resetPlafond() throws SaldoInsuficienteException, CartaoInativoException {
		divida = 0;
	}

	@Override
	public int getNumero() {
		return numero;
	}

	@Override
	public Conta getConta() {
		return conta;
	}

	@Override
	public Image getImagem() {
		return imagem;
	}
	
	@Override
	public void setImagem(Image imagem) {
		
		// Usar isto para verificar que um objeto não é nulo, esta classe tem a atribuição e a exceção implementada
		this.imagem = Objects.requireNonNull(imagem, "Imagem não pode ser null!");
	}
	
	
	// Como este método é um aspeto interno dos cartões, apenas é usado pelas subclasses, logo é protected
	// Ao gastar dinheiro do plafond, aumenta-se (altera-se) a divida do mesmo
	protected void gastaPlafond (long quantia) throws SaldoInsuficienteException {
		
		if (quantia < 0) {
			throw new IllegalArgumentException("Quantia tem de ser >= 0: " + quantia);
		}
		if (getPlafondDisponivel() < quantia) {
			// Como o saldo é um dado privado de um cliente, não se coloca mensagem nenhuma
			throw new SaldoInsuficienteException();
		}	
			
		divida += quantia;
		
	}
	
	protected void checkAtivo() throws CartaoInativoException {
		if (!estaAtivo()) {
			throw new CartaoInativoException();
		}
	}

}
