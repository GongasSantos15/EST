package ficha4;

import java.util.Scanner;

public class Ex3DoWhile {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int num = 0;
		
		do {
			//	System.out.println(Math.abs(number));
			System.out.print("Introduza um número inteiro: ");
			num = input.nextInt();
			
			if (num != 0) {
				int abs = (num > 0) ? num : -num;
				System.out.println("|"+num+"| = "+abs);
			}
		}
		while (num != 0);
		
		System.out.println("\nTerminou o ciclo!");
		input.close();

	}

}
