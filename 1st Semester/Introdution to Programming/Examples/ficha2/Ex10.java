package Ficha2;

public class Ex10 {

	public static void main(String[] args) {
		int x1 = 2;
		int x2 = 7;
		int y1 = 4;
		int y2 = 5;
		double m = 0;
		double b = 0;
		double diff1 = y2-y1;
		double diff2 = x2-x1;
		double distance = 0;
		
		distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
		m = diff1 / diff2;
		b = (y1-x1) * diff1 / diff2;
				
		System.out.printf("The distance between two points is: %.2f. \n", distance);
		System.out.printf("The equation of the straight line is: %.2fx + %.2f. \n", m, b);
	}

}
