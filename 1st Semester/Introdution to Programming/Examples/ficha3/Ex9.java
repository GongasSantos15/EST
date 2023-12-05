package ficha3;

import java.util.Scanner;

public class Ex9 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Introduza a cilindrada do automóvel: ");
		int cylinder = input.nextInt();
		
		double rate1 = 3.74;
		double rate2 = 8.86;
		double portion1 = 2417.56;
		double portion2 = 8813.22;
		double automobileTax1 = cylinder*rate1-portion1;
		double automobileTax2 = cylinder*rate2-portion2;
		
		// Adicionar uma linha para garantir que o imposto não é negativo (if ia < 0  ? 0 : ia)
		
		System.out.printf("A cilindrada introduzida foi %d o que resulta no IA igual a %.2f", cylinder, (cylinder < 1250) ? automobileTax1 : automobileTax2);
		
		input.close();
	}

}
