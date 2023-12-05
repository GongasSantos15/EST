package ficha4;
import java.util.Scanner;

public class Ex1While {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Introduza um número inteiro positivo: ");
		int number = input.nextInt();
		
		int i = 0;
		
		System.out.printf("Os números inteiros até %d são: ", number);
		
		while (i < number) {
			System.out.printf("%d ", i);
			i++;
		}
		input.close();
	}
}
