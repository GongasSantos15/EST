package ficha1;

import java.util.Scanner;

public class Exercicios {

	public static void main(String[] args) {
		
		// TESTES
			
			// Criação do vetor ordenado
		//		int[] vetorOrdenado = criarVetorOrdenado(20);
		//		System.out.println("Exercício 2:");
		//		imprimirVetor(vetorOrdenado);
	//		
	//		// Criação do vetor embaralhado	
		//		int[] vetorEmbaralhado = embaralharVetor(3, vetorOrdenado);
		//		System.out.println("\nExercício 3:");
		//		imprimirVetor(vetorEmbaralhado);
	//		
			// Soma de todos os elementos
	//			System.out.printf("\nExercício 4a:\n A soma de todos os elementos do array é: %d\n", somaTodosElementos(vetorOrdenado));
	//		
			// Índice do maior elemento
	//			System.out.printf("\nExercício 4b:\n O maior índice do array é: %d\n", indiceMaiorElemento(vetorEmbaralhado));
	//		
			// Índice do menor elemento
	//			System.out.printf("\nExercício 4c:\n O maior índice do array é: %d\n", indiceMenorElemento(vetorEmbaralhado));
			
			// Procura Linear
	//			int[] vetorOrdenado = criarVetorOrdenado(20);
	//			int[] vetorEmbaralhado = embaralharVetor(50, vetorOrdenado);
	//			imprimirVetor(vetorEmbaralhado);
	//			
	//			System.out.printf("\nIndique o elemento a procurar: ");
	//			Scanner input = new Scanner(System.in);
	//			int valorUser = input.nextInt();
	//			
	//			System.out.printf("O valor introduzido está na posição %d", procuraLinear(vetorEmbaralhado, valorUser));
				
				
	//		 // Procura Binária
	//			int[] vetorOrdenado = criarVetorOrdenado(20);
	//			imprimirVetor(vetorOrdenado);
	//			
	//			System.out.print("\nIndique o elemento a procurar: ");
	//			Scanner input = new Scanner(System.in);
	//			int valorUser = input.nextInt();
	//			
	//			System.out.printf("O valor introduzido está na posição %d", procuraBinaria(vetorOrdenado, valorUser));
	//			input.close();
				
			// Seleção
//				int[] vetorOrdenado = criarVetorOrdenado(20);
//				int[] vetorEmbaralhado = embaralharVetor(50, vetorOrdenado);
//				
//				System.out.println("Vetor embaralhado:");
//				imprimirVetor(vetorEmbaralhado);
//				
//				System.out.println("\nVetor ordenado:");
//				selecao(vetorEmbaralhado);
//				imprimirVetor(vetorEmbaralhado);
		
//			// Inserção
//				int[] vetorOrdenado = criarVetorOrdenado(20);
//				int[] vetorEmbaralhado = embaralharVetor(50, vetorOrdenado);
//				
//				System.out.println("Vetor embaralhado:");
//				imprimirVetor(vetorEmbaralhado);
//			
//				System.out.println("\nVetor ordenado:");
//				insercao(vetorEmbaralhado);
//				imprimirVetor(vetorEmbaralhado);
		
			// Permutação
				int[] vetorOrdenado = criarVetorOrdenado(20);
				int[] vetorEmbaralhado = embaralharVetor(50, vetorOrdenado);
				
				System.out.println("Vetor embaralhado:");
				imprimirVetor(vetorEmbaralhado);
			
				System.out.println("\nVetor ordenado:");
				permutacao(vetorEmbaralhado);
				imprimirVetor(vetorEmbaralhado);
			
	}
	
	// Exercício 1
		static int[] criarVetorOrdenado(int nElementos) {
			
			int[] nPositivos = new int[nElementos];
			
			for (int i = 0; i < nElementos; i++) {
				nPositivos[i] = i;
			}
			return nPositivos;
		}
	
	// Exercício 2
		static void imprimirVetor(int[] vetor) {
			for (int i = 0; i < vetor.length; i++) {
				if (i <= 19) {
					System.out.printf("v[%d] = %d\n", i, vetor[i]);
				} else {
					System.out.println("...");
				}
			}
		}
		
	// Exercício 3
		static int[] embaralharVetor(int nTrocas, int[] vetor) {
			for (int i = 0; i < nTrocas; i++) {
				
				// Criação de 2 variáveis para os 2 índices a trocar do array
					int index1 = (int) (Math.random() * vetor.length);
					int index2 = (int) (Math.random() * vetor.length);
				
				// Guardar a posição das 2 variáveis criadas anteriormente
					int auxVetor1 = vetor[index1];
					int auxVetor2 = vetor[index2];
				
				// Trocar as 2 posições
					vetor[index1] = auxVetor2;
					vetor[index2] = auxVetor1;
			}
			return vetor;
		}
		
	// Exercício 4
		// Soma de todos os elementos de um array
			static int somaTodosElementos(int[] vetor) {
					
				int soma = 0;
					
				for (int i = 0; i < vetor.length; i++ ) {
					soma += vetor[i];
				}
				return soma;
			}
		
		 // Índice do maior elemento
			static int indiceMaiorElemento(int[] vetor) {
				
				int maiorIndice = 0;
				
				if (vetor == null || vetor.length == 0) {
					return -1;
				}
				
				for (int i = 1; i < vetor.length; i++) {
					maiorIndice = vetor[i] > vetor[maiorIndice] ? i : maiorIndice;
				}
				return maiorIndice;
			}
			
		// Índice do menor elemento
			static int indiceMenorElemento(int[] vetor) {
				
				int menorIndice = 0;
				
				for (int i = 1; i < vetor.length; i++) {
					menorIndice = vetor[i] < vetor[menorIndice] ? i : menorIndice;
				}
				return menorIndice;
			}
			
	// Exercício 5
		static int procuraLinear(int[] vetor, int valor) {
				for (int i = 0; i < vetor.length; i++) {
					if (vetor[i] == valor) {
						return i;
					}
				}
				return -1;
			}
		
	// Exercício 6
		static int procuraBinaria(int[] vetor, int valor) {
				
				int esquerda = 0;
				int direita = vetor.length-1;
				int meio = 0;
				
				while (esquerda <= direita) {
						meio = (esquerda + direita) / 2;
					if (vetor[meio] == valor) {
						return meio;
					} else if (vetor[meio] < valor) {
						esquerda = meio + 1;
					} else {
						direita = meio - 1;
					}
				}
				return -1;
					
		}
		
	// Exercício 7
		static int[] selecao(int[] vetor) {
				for (int i = 0; i < vetor.length; i++) {
					int min = i;
					for (int j = i + 1; j < vetor.length; j++) {
						if (vetor[min] > vetor[j]) {
							min = j;
						}
						int aux = vetor[min];
						vetor[min] = vetor[i];
						vetor[i] = aux;
					}
				}
				return vetor;
			}
	
	// Exercício 8
		static int[] insercao(int[] vetor) {
			for(int i = 1; i < vetor.length; i++) {
				int valor = vetor[i];
				int j = i;
				while(j > 0 && vetor[j-1] > valor) {
					vetor[j] = vetor[j-1];
					j--;
				}
				vetor[j] = valor;
			}
			return vetor;
		}

	// Exercício 9
		static int[] permutacao(int[] vetor) {
			int i = 0;
			
			while(i < vetor.length - 1) {
				for (int j = vetor.length - 1; j > i; j--) {
					if (vetor[j-1] > vetor[j]) {
						int aux = vetor[j-1];
						vetor[j-1] = vetor[j];
						vetor[j] = aux;
					}
				}
				i++;
			}
			return vetor;
		}
		
}
