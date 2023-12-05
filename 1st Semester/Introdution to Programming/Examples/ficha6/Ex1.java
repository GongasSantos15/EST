package ficha6;

import java.util.Scanner;

public class Ex1 {
	
	static int returnAbs(int num) {
		
		 return (num < 0) ? -num : num;
//		if (num < 0) {
//			return -num;
//		} else {
//			return num;
//		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.printf("Introduza um número inteiro: ");
		int num = input.nextInt();
		int abs = returnAbs(num);
		
		System.out.printf("|%d| = %d", num, abs);
		
		input.close();
	}
}

