package ficha4;
import java.util.Scanner;

public class Ex11 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int num=0;
		
		do {
			System.out.print("\nIntroduza um número inteiro: ");
			num = input.nextInt();
			
			for (int i=0; i<num; i++) {
				System.out.print("*");
			}
		}
		while (num != 0);
		
		System.out.println("\nTerminou o ciclo!");
		input.close();
	}
}

