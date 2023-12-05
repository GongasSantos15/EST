package ficha4;

import java.util.Scanner;

public class Ex3While {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Introduza um número inteiro: ");
		int number = input.nextInt();
		
		while (number != 0) {
			//	System.out.println(Math.abs(number));
			System.out.println(number <0 ? -number : number);
			System.out.print("Introduza um número inteiro: ");
			number = input.nextInt();
		}
		
		System.out.println("\nTerminou o ciclo!");
		input.close();
	}
}
