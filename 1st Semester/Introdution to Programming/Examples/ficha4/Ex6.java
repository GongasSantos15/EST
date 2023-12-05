package ficha4;

import java.util.Scanner;

public class Ex6 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		char letter = ' ';
		
		do {
			System.out.print("Introduza um caractere: ");
			letter = input.next().charAt(0);
			
			if (letter >= 'a' && letter <= 'z') {
				System.out.println(letter);
			} else if (letter >= 'A' && letter <= 'Z') {
				System.out.println(letter);
			} else {
				System.out.println("");
			}
			
		}
		while (letter != '.');
		
		System.out.println("\nTerminou o ciclo!");
		input.close();
	}
}
