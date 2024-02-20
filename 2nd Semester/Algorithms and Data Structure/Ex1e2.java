package ficha1;

public class Ex1e2 {

	public static void main(String[] args) {
		int[] vetorOrdenado = criarVetorOrdenado(25);
		imprimirVetor(vetorOrdenado);
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

}
