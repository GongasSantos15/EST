package Trab02;

public class ListaLigada {

	int nElementos;
	private No cabeca;
	private int tamanho;  // MODIFIQUEI AQUI

	public class No {
		public No(String e) {
			item = e;
			prox = null;
		}

		No prox;
		String item;

		public String getItem() {
			return item;
		}
	}

	public No getCabeca() {
		return cabeca;
	}

	public void Lista() {
		cabeca = null;
		nElementos = 0;
	}

	public void inserirCabeca(String e) {
		No novoNo = new No(e);
		novoNo.prox = cabeca;
		cabeca = novoNo;
		nElementos++;
	}

	public void inserirCauda(String e) {
		No novoNo = new No(e);
		if (cabeca == null) {
			cabeca = novoNo;
			nElementos++;
			return;
		}
		No ultimo = cabeca;
		while (ultimo.prox != null)
			ultimo = ultimo.prox;
		ultimo.prox = novoNo;
		nElementos++;
	}

	// Inserir elemento a meio com inserção
	public void inserirOrdem(String e) {
		No novoNo = new No(e);
		No atual = cabeca;
		No anterior = null;
		// procurar o local onde inserir
		while (atual != null && atual.item.compareTo(e) < 0) {
			anterior = atual;
			atual = atual.prox;
		}
		novoNo.prox = atual;
		if (anterior == null)
			cabeca = novoNo;
		else
			anterior.prox = novoNo;
		nElementos++;
	}

	// Inserir elemento a meio indicando a posição

	public void inserir(String e, int idx) {
		No novoNo = new No(e);
		int posAtual = 0;
		No atual = cabeca;
		No anterior = null;
		while (atual != null && posAtual < idx) {
			anterior = atual;
			atual = atual.prox;
			posAtual++;
		}
		novoNo.prox = atual;
		if (anterior == null)
			cabeca = novoNo;
		else
			anterior.prox = novoNo;
		nElementos++;
	}

	// Procurar um elemento numa lista não ordenada
	public int procurar(String e) {
		No atual = cabeca;
		int pos = 0;
		while (atual != null && !atual.item.equals(e)) {
			atual = atual.prox;
			pos++;
		}
		return atual == null ? -1 : pos;
	}

	// Remover um elemento numa lista
	public void retirar(String palavra) {
		No atual = cabeca;
		No anterior = null;
		int pos = 0, i = procurar(palavra);

		while (atual != null && pos < i) {
			anterior = atual;
			atual = atual.prox;
			pos++;
		}

		if (anterior != null)
			anterior.prox = atual.prox;
		else
			cabeca = atual.prox;

		this.nElementos--;
	}

	// Remover todos os elementos de uma lista
	public void limpar() {
		cabeca = null;
		nElementos = 0;
	}

	public boolean estaPresente(String palavra) {
		return procurar(palavra)!= -1;
	}

}
