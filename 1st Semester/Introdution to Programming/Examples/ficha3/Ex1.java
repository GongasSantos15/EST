package ficha3;

import java.util.Scanner; // Forma de input no java

public class Ex1 {

	public static void main(String[] args) {
//		int num1 = -5;
//		
//		if (num1 < 0) {
//			System.out.print(Math.abs(num1));
//		} else {
//			System.out.print(Math.abs(num1));
//		}
	
	Scanner input=new Scanner(System.in);
	System.out.println("Introduza um número:");
	int num1 = input.nextInt();
	
	int abs = Math.abs(num1);
	
	System.out.printf("%d", (num1 < 0) ? abs : abs);
	
	input.close();
	
	}

}
