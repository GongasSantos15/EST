package Ficha2;

public class Ex8 {

	public static void main(String[] args) {
		double degrees = 90;
		double radians = 0;
		double grades = 0;
		double pi = 3.1416;
		
		radians = degrees * pi/180;
		grades = degrees * 10/9;
		
		System.out.printf("%.1f degrees = %.4f radians = %.1f\n", degrees, radians, grades);

	}

}
