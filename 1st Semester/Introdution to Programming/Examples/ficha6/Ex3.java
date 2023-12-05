package ficha6;

import java.util.Scanner;

public class Ex3 {
	
	static boolean returnEvenNum(int num) {
		return num % 2 == 0;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Escreva um número inteiro: ");
		int num = input.nextInt();
		
		boolean even = returnEvenNum(num);
		
		System.out.printf("\nO número %d é %s", num, even ? "par" : "ímpar");
		
//		System.out.printf("\nO número %d é %s", num, returnEvenNum(num) ? "par" : "ímpar");
		
		input.close();
	}

}
