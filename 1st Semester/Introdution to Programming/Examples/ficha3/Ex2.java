package ficha3;
import java.util.Scanner;

public class Ex2 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Introduza um número de 0 a 100:");
		
		double num1 = input.nextDouble();
		
		if (num1 < 100) {
			System.out.printf("%.2f", num1);
		} else {
			System.out.printf("%s", "100. O valor foi truncado!");
		}
	input.close();	
	}

}

//Escreva um programa que garanta que um número tem valor inferior a 100. Se for
//maior que 100 deverá colocá-lo a 100, e apresentar a mensagem de que o valor foi
//truncado.