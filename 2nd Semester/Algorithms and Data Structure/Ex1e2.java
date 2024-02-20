package ficha1;

public class Ex123 {

	public static void main(String[] args) {
		int[] vetorOrdenado = criarVetorOrdenado(25);
		System.out.println("Exercício 2:");
		imprimirVetor(vetorOrdenado);
		
		int[] vetorEmbaralhado = embaralharVetor(3, vetorOrdenado);
		System.out.println("\nExercício 3:");
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

}
