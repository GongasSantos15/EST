package ficha1;

public class Ex123 {

	public static void main(String[] args) {
		int[] vetorOrdenado = criarVetorOrdenado(25);
		System.out.println("Exercício 2:");
		imprimirVetor(vetorOrdenado);
		
		int[] vetorEmbaralhado = embaralharVetor(3, vetorOrdenado);
		System.out.println("\nExercício 3:");
		imprimirVetor(vetorEmbaralhado);

//		System.out.printf("\nExercício 4a:\n A soma de todos os elementos do array é: %d\n", somaTodosElementos(vetorOrdenado));
//		
//		System.out.printf("\nExercício 4b:\n O maior índice do array é: %d\n", indiceMaiorElemento(vetorEmbaralhado));
//		
//		System.out.printf("\nExercício 4c:\n O maior índice do array é: %d\n", indiceMenorElemento(vetorEmbaralhado));
		
		// Procura Linear
			int[] vetorOrdenado = criarVetorOrdenado(20);
			int[] vetorEmbaralhado = embaralharVetor(50, vetorOrdenado);
			imprimirVetor(vetorEmbaralhado);
			
			System.out.printf("\nIndique o elemento a procurar: ");
			Scanner input = new Scanner(System.in);
			int valorUser = input.nextInt();
			
			System.out.printf("O valor introduzido está na posição %d", procuraLinear(vetorEmbaralhado, valorUser));
			
			
		// Procura Binária
			vetorOrdenado = criarVetorOrdenado(20);
			imprimirVetor(vetorOrdenado);
			
			System.out.println("\nIndique o elemento a procurar: ");
			input = new Scanner(System.in);
			valorUser = input.nextInt();
			
			System.out.printf("O valor introduzido está na posição %d", procuraBinaria(vetorOrdenado, valorUser));
			input.close();
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
			
			static int procuraBinaria(int[] vetor, int valor) {
				
				boolean encontrar = false;
				int esquerda = 0;
				int direita = vetor.length-1;
				int meio = 0;
				
				while (esquerda <= direita && !encontrar) {
						meio = esquerda + direita / 2;
						
					if (vetor[meio] == valor) {
						encontrar = true;
					} else if (vetor[meio] < valor) {
						direita = valor - 1;
						encontrar = false;
					} else {
						esquerda = valor + 1;
						encontrar = false;
					}
					return -1;
				}
				return meio;
					
					
			}

}
