package aulaTeorica;

import java.util.Scanner;

public class averageOf10Numbers {

	public static void main(String[] args) {
		
		//1. Calcular a média de 10 números introduzidos pelo utilizador
		// Utilizamos o for porque o enunciado pede para fazer o ciclo 10 vezes
		
		Scanner input = new Scanner(System.in);
		
		int sum = 0;
		
		for (int i=0; i<10; i++) {
			System.out.print("Introduza um número: ");
			int num = input.nextInt();
			sum += num;
		}
		
		double average = sum / 10.0;
		System.out.printf("\nA média dos 10 valores introduzidos é %.2f", average);
		
		input.close();
	}
}
