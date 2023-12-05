package ficha4;

import java.util.Scanner;

public class Ex10 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Introduza um número inteiro positivo: ");
		int num = input.nextInt();
		
		boolean isPrime = true;
		
		for (int i=2; i<num; i++) {
			
			if (num % 2 == 0) {
				isPrime = false;
				break;
			}
		}
		
		
		System.out.printf("\nO número %d %s", num, (isPrime ? "é primo" : "não é primo"));
		input.close();
	}
}
