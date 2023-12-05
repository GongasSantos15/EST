package ficha6;

import java.util.Scanner;

public class Ex4 {
	
	static boolean isADigit(char character) {
		return (character >= '0' && character <= '9');
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Escreva um caractere: ");
		char character = input.next().charAt(0);
		
		System.out.printf("O caractere %c %s um algarismo", character, (isADigit(character)) ? "é" : "não é");
		
		input.close();
	}

}
