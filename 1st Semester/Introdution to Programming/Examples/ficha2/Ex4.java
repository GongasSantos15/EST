package Ficha2;

public class Ex4 {

	public static void main(String[] args) {
		int num1 = 10;
		int num2 = 5;
		int product = 0;
		int division = 0;
		int rest = 0;
		int square = 0;

		product = num1 * num2;
		division = num1 / num2;
		rest  = num1 % num2;
		square = num2 * num2;

		System.out.println("Product:");
		System.out.println(num1 + " x " + num2 + " = " + product);
		System.out.print("\n");
		
		System.out.println("Product and Rest:");
		System.out.println(num1 + " / " + num2 + " = " + division + ". And the rest is: " + rest);
		System.out.print("\n");
		
		System.out.println("Square:");
		System.out.print(num2 + " x " + num2 + " = " + square);

	}

}
