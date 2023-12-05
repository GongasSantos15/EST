package ficha3;

import java.util.Scanner;

public class Ex4 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.print("Introduza um número inteiro: ");
		int num1 = input.nextInt();	
		
		int rest = num1 % 2;
		
		System.out.println((rest == 0) ? "O número é par!" : "O número é ímpar!");
		
		input.close();
	}

}
