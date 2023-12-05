package ficha3;
import java.util.Scanner;

public class Ex3 {

	public static void main(String[] args) {
		
		Scanner input1 = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);
		
		System.out.print("Introduza o primeiro número inteiro: ");
		int num1 = input1.nextInt();
		
		System.out.print("Introduza o segundo número inteiro: ");
		int num2 = input2.nextInt();
		System.out.println();
		
		int num3 = num2;
		int num4 = num1;
		
		if (num1 < num2) {
			System.out.println("O primeiro número é " + num3 + " e o segundo número é " + num4);
		} else {
			System.out.println("O primeiro número é " + num4 + " e o segundo número é " + num3);
		}
		input1.close();
		input2.close();
	}

}
