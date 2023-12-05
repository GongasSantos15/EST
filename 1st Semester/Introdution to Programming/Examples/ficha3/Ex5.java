package ficha3;
import java.util.Scanner;

public class Ex5 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.print("Introduza um número: ");
		double num1 = input.nextDouble();
				
		System.out.printf("A raiz quadrada de %.2f é: %.2fi", num1, (num1 > 0) ? Math.sqrt(num1) : Math.sqrt(-num1));
		
		input.close();
	}

}