package ficha3;

import java.util.Scanner;

public class Ex10 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Introduza o seu salário bruto: ");
		double grossSalary = input.nextDouble();
		
		double rate = 0;
		
		if (grossSalary < 500) {
			rate = 0;
		} else if (grossSalary < 650){
			rate = 0.015;
		} else if (grossSalary < 800) {
			rate = 0.035;
		} else if (grossSalary < 1200) {
			rate = 0.05;
		} else if ( grossSalary < 2000) {
			rate = 0.1;
		} else if (grossSalary < 3000) {
			rate = 0.2;
		} else {
			rate = 35;
		}
		
		double quantity = grossSalary*rate;
		double liquidSalary = grossSalary - quantity;
		
		System.out.printf("O valor do salário bruto apresentado é de %.2f€, o que resulta em %.2f€ (salário líquido) e a quantidade de montante "
				+ "a reter é de: %.2f€", grossSalary, liquidSalary, quantity);
		
		input.close();
	}

}
