package Ficha2;

public class Ex9 {

	public static void main(String[] args) {
		int angleInDegrees = 45;
		double cos = 0;
		double sin = 0;
		double tan = 0;
		double arctan = 0;
		double angleInRadians = 0;

		// Converter para radianos os valores porque na documentação oficial o método cos() assume um ângulo em radianos
			angleInRadians = Math.toRadians(angleInDegrees);

		// Cosseno
			cos = Math.cos(angleInRadians);
			System.out.printf("Cos: %.2f \n", cos);
		
		// Seno
			sin = Math.sin(angleInRadians);
			System.out.printf("Sin: %.2f \n", sin);
			
		// Tangente
			tan = Math.tan(angleInRadians);
			System.out.printf("Tan: %.2f \n", tan);
			
		// Arcotangente
			arctan = Math.atan(angleInRadians);
			System.out.printf("Arctan: %.2f \n", arctan);
	}

}
