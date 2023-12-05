package ficha3;

import java.util.Scanner;

public class Ex6 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.print("Introduza um caractere: ");
		char char1 = input.next().charAt(0);
		
		char LowerCase = (char) (char1 + 32);
		char UpperCase = (char) (char1 - 32);
		
		if (char1 >= 'A' && char1 <= 'Z') {
			System.out.println("A letra " + char1 + " introduzida é maiúscula e o seu inverso é " + LowerCase);
		} else if (char1 >= 'a' && char1 <= 'z'){
			System.out.println("A letra " + char1 + " introduzida é minúscula e o seu inverso é " + UpperCase);
		}
		
		input.close();
		
	}

}
