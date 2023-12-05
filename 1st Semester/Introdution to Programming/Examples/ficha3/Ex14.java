package ficha3;
import java.util.Scanner;

public class Ex14 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Introduza o primeiro número: ");
		double numberA = input.nextDouble();
		
		System.out.print("Introduza o segundo número: ");
		double numberB = input.nextDouble();
		
		System.out.print("Introduza o terceiro número: ");
		double numberC = input.nextDouble();

		double delta = numberB*numberB - 4*numberA*numberC;
		double realPart = (-numberB)/(2*numberA);
		
		double inDeltaSqrt = Math.sqrt(delta)/(2*numberA);
		double imaginaryPart = Math.sqrt(-delta)/(2*numberA);
		
		if (delta >= 0) {
			System.out.printf("\nO valor da equação é %.2f+%.2f e %.2f-%.2f \n", realPart, inDeltaSqrt, realPart, inDeltaSqrt);
		} else {
			System.out.printf("\nO valor da equação é %.2f+%.2fi e %.2f-%.2fi \n", realPart, imaginaryPart, realPart, imaginaryPart);
		}
		
		input.close();
	}

}
