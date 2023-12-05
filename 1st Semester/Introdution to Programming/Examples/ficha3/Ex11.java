package ficha3;

import java.util.Scanner;

public class Ex11 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Para converter a data do formato dd/mm/yyyy para a forma extensa, preciso dos seguintes dados:");
		
		System.out.print("	Introduza o dia: ");
		int day = input.nextInt();
		
		System.out.print("	Introduza o mês: ");
		int month = input.nextInt();
		
		System.out.print("	Introduza o ano: ");
		int year = input.nextInt();
		
		String monthInLength = "";
		
		// Meses
		switch (month) {
			case 1: 
				monthInLength = "janeiro";
				break;
			case 2: 
				monthInLength = "fevereiro";
				break;
			case 3: 
				monthInLength = "março";
				break;
			case 4: 
				monthInLength = "abril";
				break;
			case 5: 
				monthInLength = "maio";
				break;
			case 6: 
				monthInLength = "junho";
				break;
			case 7: 
				monthInLength = "julho";
				break;
			case 8: 
				monthInLength = "agosto";
				break;
			case 9: 
				monthInLength = "setembro";
				break;
			case 10: 
				monthInLength = "outubro";
				break;
			case 11: 
				monthInLength = "novembro";
				break;
			case 12: 
				monthInLength = "dezembro";
				break;
			default: 
				monthInLength = "";
		}
		
		if (month > 1 && month < 12) {
			System.out.printf("\nA data introduzida %d/%d/%d, convertida para o formato extenso é: %d de %s de %d", day, month, year, day, monthInLength, year);		
		} else {
			System.out.println("\nO valor para o mês introduzido está errado! Por favor introduza um número de 1 a 12.");
		}
		input.close();
		
	}
}
