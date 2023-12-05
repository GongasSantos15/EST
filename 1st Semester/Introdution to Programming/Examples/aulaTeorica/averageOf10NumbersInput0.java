package aulaTeorica;

import java.util.Scanner;

public class averageOf10NumbersInput0 {

	public static void main(String[] args) {
		
		// 2. Calcular a média dos números introduzidos pelo utilizador até que ele introduza 0
		// Utilizamos o do-while porque temos de ler pelo menos um número, neste caso o 0
		
		Scanner input = new Scanner(System.in);
		int num = 0, sum = 0, count = 0;
		
		do {
			System.out.print("Introduza um número: ");
			num = input.nextInt();
			sum = sum + num;
			count = count +1;
			
		} while (num != 0);
		
		double average = (double) (sum / (count-1));
		System.out.printf("\nA média dos %d valores introduzidos é %.2f", count, average);
		
		input.close();
	}
}
