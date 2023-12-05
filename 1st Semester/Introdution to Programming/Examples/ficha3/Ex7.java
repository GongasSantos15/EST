package ficha3;

import java.util.Scanner;

public class Ex7 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.print("Introduza um ano: ");
		int year = input.nextInt();
		
		System.out.printf("O ano %d introduzido é um ano %s", year, (year % 4 == 0 & year % 100 != 0 || year % 400 == 0) ? "bissexto" : "normal");

		input.close();
	}

}
