package ficha4;

import java.util.Scanner;

public class Ex7 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Introduza um número limite: ");
		int limit = input.nextInt();
		
		int sum = 0;
		int i = 0;
		
		while (sum <= limit) {
			i++;
			sum = sum + i;
		}
		
		System.out.printf("\nA soma resultante é de %d e a quantidade de números somados é de %d", sum, i);
		
		input.close();
	}

}
