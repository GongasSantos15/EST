package ficha4;

import java.util.Scanner;

public class Ex12 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		char ch1 = 0;
		char ch2 = 0;
		
		do {
			System.out.print("Introduza 2 caracteres: ");
			ch1 = input.next().charAt(0);
			ch2 = input.next().charAt(0);
			
			if ((ch1 >= 'a' && ch2 <= 'z') && (ch1 >= 'a' && ch2 <= 'z')) {
				
				if (ch1<ch2) {
					for (char letter = ch1; letter <= ch2; letter++) {
						System.out.print(letter);
					}
				}
				else {
					for (char letter = ch1; letter >= ch2; letter--) {
						System.out.print(letter);
					} 
				}
		}
		else {
			System.out.println("Pelo menos uma das letras não é minúscula");
		}
			System.out.println();
			input.close();
		} while (ch1 != '.' || ch2 != '.');	
	}
}
