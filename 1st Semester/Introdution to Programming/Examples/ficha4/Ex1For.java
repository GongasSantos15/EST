package ficha4;

import java.util.Scanner;

public class Ex1For {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Introduza um número inteiro positivo: ");
		int number = input.nextInt();
		
		System.out.printf("Os números inteiros até %d são: ", number);
		
		for (int i=0; i<number; i++) {
			if (number < i) {
				i++;
			}
			System.out.printf("%d ", i);
		}
		input.close();
	}

}
