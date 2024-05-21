package cartao;

import java.awt.Image;

import banco.Conta;
import banco.SaldoInsuficienteException;

public class CartaoDebito extends CartaoDefault {

	public CartaoDebito(int numero, Conta conta, long plafond, Image imagem, boolean ativo) {
		super(numero, conta, plafond, imagem, ativo);
	}

	public CartaoDebito(int numero, Conta conta, long plafond, Image imagem) {
		super(numero, conta, plafond, imagem);
	}

	@Override
	public void levanta(long quantia) throws SaldoInsuficienteException, CartaoInativoException {
		
		// Verificar se o cartão está ativo, senão levantar a quantia indicada pelo user
		checkAtivo();
		getConta().levantar(quantia);
	}

	@Override
	public void compra(long quantia) throws SaldoInsuficienteException, CartaoInativoException {
		
		// Verificar se o cartão está ativo, se sim, verificar se tem saldo suficiente, se sim, levantar o dinheiro da conta, senão gasta o Plafond com a quantia indicada pelo user, aumentando a dívida
		checkAtivo();
		
		try {
			getConta().levantar(quantia);
		} catch (SaldoInsuficienteException e) {
			
			// Altera-se a dívida, aumentando a mesma
			gastaPlafond(quantia);
			
		}

	}
	
	@Override
	public void resetPlafond() throws SaldoInsuficienteException, CartaoInativoException {
		
		// Para resetar o plafond, neste caso aumenta-se a dívida em 10%, se o restante ainda for suficiente, paga-se a dívida (funciona como compra), senão desativar o cartão e informar o banco
		long total = (long) (getDivida() * 1.10);
		super.resetPlafond();
		
		try {
			compra(total);
		} catch (SaldoInsuficienteException e) {
			setAtivo(false);
			throw e;
		}
	}

}
