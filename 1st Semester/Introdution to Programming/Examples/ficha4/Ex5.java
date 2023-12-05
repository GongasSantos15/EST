package ficha4;

import java.util.Scanner;

public class Ex5 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Introduza um número inteiro: ");
		int num = input.nextInt();
		
		for (int i=0; i<num; i++) {
			System.out.print("*");
		}
		
		input.close();
	}
}
