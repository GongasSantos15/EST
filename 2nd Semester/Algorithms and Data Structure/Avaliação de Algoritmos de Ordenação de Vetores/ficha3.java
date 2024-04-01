package ficha3;

public class exercicio {

	public static int[] criarVetorOrdenado(int dimensao) {
		int vetor[] = new int[dimensao];

		for (int i = 0; i < dimensao; i++) {
			vetor[i] = i;
		}
		return vetor;
	}

	public static int[] embaralharVetor(int[] vetor, int nTrocas) {

		for (int i = 0; i < nTrocas; i++) {
			int index1 = (int) (Math.random() * vetor.length);
			int index2 = (int) (Math.random() * vetor.length);

			int auxVetor1 = vetor[index1];
			int auxVetor2 = vetor[index2];

			vetor[index1] = auxVetor2;
			vetor[index2] = auxVetor1;

		}
		return vetor;
	}

	/* Seleçao */
	static int[] ordenaSelecao(int[] vetor) {
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

	/* Insercao */
	static int[] ordenaInsercao(int[] vetor) {
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

	/* Permutaçao */
	static int[] ordenaPermutaçao(int vetor[]) {
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

	/* Merge Sort */
		static void reagrupar(int vetor[], int vAux[], int i1, int i2, int fim2) {
			int fim1 = i2 - 1;
			int inicio = i1;
			int iAux = i1;
			while (i1 <= fim1 && i2 <= fim2) {
				if (vetor[i1] < vetor[i2]) {
					vAux[iAux] = vetor[i1];
					i1++;
				} else {
					vAux[iAux] = vetor[i2];
					i2++;
				}
				iAux++;
			}
			for (; i1 <= fim1; i1++, iAux++)
				vAux[iAux] = vetor[i1];
			for (; i2 <= fim2; i2++, iAux++)
				vAux[iAux] = vetor[i2];
	
			for (int i = inicio; i <= fim2; i++)
				vetor[i] = vAux[i];
		}
	
		static void mergeSortRec(int vetor[], int vAux[], int esquerda, int direita) {
			if (esquerda >= direita)
				return;
	
			int centro = (esquerda + direita) / 2;
			mergeSortRec(vetor, vAux, esquerda, centro);
			mergeSortRec(vetor, vAux, centro + 1, direita);
			reagrupar(vetor, vAux, esquerda, centro + 1, direita);
		}
	
		static int[] ordenaMergeSort(int vetor[]) {
			int vAux[] = new int[vetor.length];
			mergeSortRec(vetor, vAux, 0, vetor.length - 1);
			return vetor;
		}

	/* QuickSort */
		static int pivotMediana(int vetor[], int inicio, int fim) {
	    	int centro = (inicio + fim) / 2;
	    	
	    	if (vetor[inicio] > vetor[centro]) {
	    		troca(vetor, inicio, centro);
	    	}
	    	if (vetor[inicio] > vetor[fim]) {
	    		troca(vetor, inicio, fim);
	    	}
	    	if (vetor[centro] > vetor[fim]) {
	    		troca(vetor, centro, fim);
	    	}
	    	
	    	troca(vetor, centro, fim - 1);
	    	 
	    	return vetor[fim - 1];
	    }
	    
	    // Método troca
	    static void troca(int vetor[], int i, int j) {
	    	int aux = vetor[i];
	    	vetor[i] = vetor[j];
	    	vetor[j] = aux;
	    }
	    
	    // Método que divide os maiores e menores
		    static int divideMaioresMenores(int vetor[], int inicio, int fim, int pivot) {
		    	int i = inicio;
		    	int j = fim - 1;
		    	
		    	while (i < j) {
		    		while (vetor[++i] < pivot);
		    		while(j > 0 && vetor[--j] > pivot);
		    		if(i < j) {
		    			troca(vetor, i, j);
		    		} else {
		    			break;
		    		}
		    	}
		    	troca(vetor, i, fim -1);
		    	return i;
		    }
		
		    static void quickSortRec(int vetor[], int inicio, int fim) {
		        if (inicio >= fim) {
	        		return;
		        }
		        
	            int pivot = pivotMediana(vetor, inicio, fim); 
	            int i = divideMaioresMenores(vetor, inicio, fim, pivot);
		            
	            quickSortRec(vetor, inicio, i - 1);	// Ordenar os menores
	            quickSortRec(vetor, i + 1, fim);	// Ordenar os maiores
	        }
		
		    static int[] ordenaQuickSort(int vetor[]) {
		        quickSortRec(vetor, 0, vetor.length - 1);
		        return vetor;
		    }

	public static void main(String[] args) {
		int dimensao = 100000;

		int[] vetorOrdenado = criarVetorOrdenado(dimensao);
		int[] vetorEmbaralhado = embaralharVetor(vetorOrdenado, 2 * dimensao);
		long inicio, fim, tempo;
		inicio = System.nanoTime();
		ordenaSelecao(vetorEmbaralhado);
		ordenaPermutaçao(vetorEmbaralhado);
		ordenaInsercao(vetorEmbaralhado);
		ordenaQuickSort(vetorEmbaralhado);
		ordenaMergeSort(vetorEmbaralhado);

		fim = System.nanoTime();
		tempo = fim - inicio;
		System.out.println("a tarefa demorou " + tempo + " nanosegundos");

	}
}

