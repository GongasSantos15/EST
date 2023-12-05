package ficha6;

import java.util.Scanner;

public class Ex7 {
	
	static boolean isAPrimeNum(int num) {
		boolean isPrime = true;
		
		for (int i=2; i<num; i++) {
			if (num % 2 == 0) {
				isPrime = false;
				break;
			}
		}
		return isPrime;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Introduza um número inteiro: ");
		int num = input.nextInt();
		
		System.out.printf("\nO número %d %s", num, (isAPrimeNum(num) ? "é primo" : "não é primo"));
		
		input.close();
	}

}
