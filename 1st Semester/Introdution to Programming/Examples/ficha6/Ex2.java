package ficha6;

import java.util.Scanner;

public class Ex2 {
	
	static int returnBiggerInt(int num1, int num2) {
		
		return (num1 > num2) ? num1 : num2;
//		if(num1 > num2) {
//			return num1;
//		} else {
//			return num2;
//		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Introduza o primeiro número inteiro: ");
		int num1 = input.nextInt();
		System.out.print("Introduza o segundo número inteiro: ");
		int num2 = input.nextInt();
		
		int bigger = returnBiggerInt(num1, num2);
		
		System.out.printf("\nO maior número entre %d e %d é %d", num1, num2, bigger);
		
		input.close();
	}

}
