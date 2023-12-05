package ficha6;

import java.util.Scanner;

public class Ex8 {
	
	static long returnPower(int x, int y) {
		long power = 1;
		
		for (int i = 0; i<y; i++) {
			power *= x;
		}
		return power;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Introduza uma base: ");
		int x = input.nextInt();
		
		System.out.print("Introduza um expoente: ");
		int y = input.nextInt();
		
		System.out.printf("\nO número %d^%d = %d", x, y, returnPower(x, y));
		
		input.close();
	}

}
