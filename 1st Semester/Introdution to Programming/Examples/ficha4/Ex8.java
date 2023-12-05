package ficha4;

import java.util.Scanner;

public class Ex8 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Introduza o valor de x: ");
		int x = input.nextInt();
		System.out.print("Introduza o valor de y: ");
		int y = input.nextInt();
		
		int power = 1;
		
		for (int i = 0; i<y; i++) {
			power *= x;
		}
		
		System.out.printf("O valor de %d^%d é: %d", x, y, power);
		
		input.close();
	}

}
