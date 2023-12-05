package ficha5;

import java.util.Scanner;

public class Ex2 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Introduza números inteiros para converter em array");
		int intNums[] = new int[10];
		
		int sum=0;
		int n=0;
		
			for(n=0; n < intNums.length; n++) {
				System.out.printf("Introduza um número (0 para terminar): ", n);
				intNums[n] = input.nextInt();
				
				if (intNums[n]== 0) {
					break;
				}
			}
				
			for(int j=0;j<n;j++) {
				System.out.print(intNums[j] + " ");
				sum += intNums[j];
			}
			
			double average=((double) sum)/n;
			System.out.printf("\nA soma de todos os elementos do array é: %d e a média dos mesmos é igual a %.2f", sum, average);
			
			input.close();
	}
	
}
