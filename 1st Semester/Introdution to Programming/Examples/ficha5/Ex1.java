package ficha5;

import java.util.Scanner;

public class Ex1 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Introduza 10 números inteiros para converter em array: ");
		
		int intNums[] = new int[10];
		int sum=0;
		double average=0;

		for(int i=0; i < intNums.length; i++) {
			System.out.printf("número [%d] = ", i);
			intNums[i] = input.nextInt();
		}

		System.out.print("\nO array é: [ ");
		for(int i=0; i < intNums.length; i++ ) {
			System.out.printf(intNums[i]+" ");

			sum += intNums[i];
			average = sum/intNums.length;
		}

		System.out.printf("]\nA soma de todos os elementos do array é: %d e a média dos mesmos é igual a %f", sum, average);
		input.close();
	}

}
