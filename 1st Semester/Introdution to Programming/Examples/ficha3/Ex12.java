package ficha3;
import java.util.Scanner;

public class Ex12 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Introduza apenas a primeira letra do seu estado civil: ");
		char civilState = input.next().charAt(0);
		
		String civilStateInLength = "";
		
		switch (civilState) {
			case 'c':
			case 'C':
				civilStateInLength = "Casado";
				break;
			case 'v':
			case 'V':
				civilStateInLength = "Víuvo";
				break;
			case 'u':
			case 'U':
				civilStateInLength = "União de facto";
				break;
			case 's':
			case 'S':
				civilStateInLength = "Solteiro";
				break;
			case 'd':
			case 'D':
				civilStateInLength = "Divorciado";
				break;
				
			default: 
				civilStateInLength = "Introduza uma letra válida! Lembre-se que é apenas a primeira letra do seu estado civil.";
		}
		
		System.out.printf("O estado civil '%c', por extenso é: %s", civilState, civilStateInLength);
		
		input.close();
	}

}
