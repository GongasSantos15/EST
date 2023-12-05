package ficha3;
import java.util.Scanner;

public class Ex13 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Introduza o primeiro número: ");
		double numberA = input.nextDouble();
		
		System.out.print("Introduza o segundo número: ");
		double numberB = input.nextDouble();
		
		System.out.print("Introduza o terceiro número: ");
		double numberC = input.nextDouble();

		double delta = numberB*numberB - 4*numberA*numberC;
		
		if (delta > 0) {
			System.out.printf("\nO valor de delta é %.2f. Nesta equação existem 2 raízes reais.\n", delta);
		} else if (delta == 0) {
			System.out.println("\nO valor de delta é 0. Nesta equação não existem raízes reais\n");
		} else {
			System.out.println("\nO valor de delta não é real! Por favor apresente outros valores para os coeficientes a, b e c");
		}
		
		input.close();
	}

}
