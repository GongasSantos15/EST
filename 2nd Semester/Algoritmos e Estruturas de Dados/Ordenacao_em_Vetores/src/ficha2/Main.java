package ficha2;

public class Main {
	
	// Método para criar um vetor
		public static int[] criarVetorOrdenado(int nElementos) {
	        int vetor[] = new int[nElementos];
	
	        for (int i = 0; i < nElementos; i++) {
	            vetor[i] = i;
	        }
	        return vetor;
	    }

	// Método para imprimir um vetor criado
    public static int[] imprimirVetor(int[] vetor) {

    	//imprime os 20 primeiros elementos do vetor
    	for (int i = 0; i < vetor.length; i++) {
			if (i > 20) {
				System.out.println("...");
				break;
			} else
				System.out.println("vetor[" + i + "]=" + vetor[i]);
		}
		return vetor;
    	
    }

    // Método que embaralha o vetor criado
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

	
	    static void mergeSortRec(int vetor[], int vetorAux[], int esq, int dir) {
	        if (esq >= dir)
	            return;
	
	        int centro = (esq + dir) / 2;
	        mergeSortRec(vetor, vetorAux, esq, centro);
	        mergeSortRec(vetor, vetorAux, centro + 1, dir);
	        
	        reagrupar(vetor, vetorAux, esq, centro + 1, dir);
	    }
	
	    static int[] ordenaMergeSort(int vetor[]) {
	        int vetorAux[] = new int[vetor.length];
	        
	        mergeSortRec(vetor, vetorAux, 0, vetor.length - 1);
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

        int[] vetorOrdenado = criarVetorOrdenado(200);
        int[] vetorEmbaralhado = embaralharVetor(vetorOrdenado, 500);
        imprimirVetor(vetorEmbaralhado);
        
        System.out.println("----- Ordenado com MergeSort -----");
        int[] ordenadoMergeSort = ordenaMergeSort(vetorEmbaralhado);
        imprimirVetor(ordenadoMergeSort);
        
        System.out.println("----- Ordenado com QuickSort -----");
        int[] ordenadoQuickSort = ordenaQuickSort(vetorEmbaralhado);
        imprimirVetor(ordenadoQuickSort);

    }

	
}