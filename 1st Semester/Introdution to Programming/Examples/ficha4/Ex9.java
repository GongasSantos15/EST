package ficha4;

import java.util.Scanner;

public class Ex9 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Introduza um número inteiro para fatorizar: ");
		int num = input.nextInt();
		
		long factorial = 1;
		
		for (int i=1; i<=num; i++) {
			factorial *= i;
		}
		
		System.out.printf("\nO número %d! = %d", num, factorial);	
		input.close();
	}

}
