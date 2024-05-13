package Trab02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Hash {

	class TabelaHash {

		// usar um array internamente
		private ListaLigada[] aTabela;

		public TabelaHash(int nPos) {
			// criar a tabela propriamente dita
			aTabela = new ListaLigada[nPos];
			// inicializar todas as posições a null (vazias)
			// em java não é preciso
			for (int i = 0; i < aTabela.length; i++)
				aTabela[i] = new ListaLigada();
		}

		boolean inserir(String palavra) {
			int key = hashing(palavra);
			
//			if(aTabela[key].estaPresente(palavra))
//				return false;
			
			aTabela[key].inserirCabeca(palavra);
			return true;
		}

		int procura(String palavra) {
			int index = hashing(palavra);
			
			return aTabela[index].procurar(palavra);
		}
		
//		String procura(String palavra) {
//			int index = hash(palavra);
//			ArrayList<String> lista = aTabela.get(index);
//			for (String str : lista) {
//				if (str.equals(palavra))
//					return palavra;
//			}
//			return null;
//		}


		boolean elimina(int chave) {
			if (aTabela[chave] == null) // ver se elemento não existe
				return false;
			aTabela[chave] = null;
			return true;
		}

		void limpar() {
			for (int i = 0; i < aTabela.length; i++)
				aTabela[i] = null;
		}

		public int hashing(String palavra){
			return palavra.length()%10;
			}

	}
}
