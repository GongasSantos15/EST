package ficha6;

import java.util.Scanner;

public class Ex5 {
	
	static boolean isALeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0);
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Introduza um ano: ");
		int year = input.nextInt();
		
		System.out.printf("\nO ano %d %s", year, (isALeapYear(year)) ? "é bissexto" : "não é bissexto");
		
		input.close();
	}

}
