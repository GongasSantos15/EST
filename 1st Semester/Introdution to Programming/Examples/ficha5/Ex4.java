package ficha5;

public class Ex4 {

	public static void main(String[] args) {
		
		// Array
		double temperature[] = {21.8, 23.5, 24, 28.5, 27.4, 29.2, 27.5, 29.4, 22, 19.2, 20.9, 21.6, 33.4, 24.6, 32.8, 34, 36, 37.3, 35, 30, 33, 34, 28.5, 40, 43, 27.7, 21, 20, 36, 37.7, 28.4};
		
		// Variáveis máximo e mínimo
		double max=temperature[0];
		int day1=0;
		
		double min=temperature[0];
		int day2=0;
		
		// Variáveis média
		double average=0;
		double sum=0;
		
		// Variável String
		String text = "";

		// Ciclo para ver qual o máximo e o mínimo
		for(int i=0; i < temperature.length; i++) {
				if (temperature[i] > max)  {
					max = temperature[i];
					day1 = i+1;
					
				}
				
				if (temperature[i] < min) {
					min = temperature[i];
					day2 = i+1;
				}
				
				sum += temperature[i];
		}
		
		average = sum/temperature.length;
		
		// Ciclo para imprimir as temperaturas dia-a-dia
		for(int i=0; i < temperature.length; i++) {
			
			if (temperature[i] == max) {
				text = "máxima";
			} else  if (temperature[i] == min){
				text = "mínima";
			} else if (temperature[i] > average) {
				text = "acima da média";
			} else if (temperature[i] < average) {
				text = "abaixo da média";
			} else {
				text = "temperatura média";
			}
			
			System.out.printf("Dia %d - %.1fºC - %s\n", (i+1), temperature[i], text);
		}

		System.out.println("\nA temperatura mais alta do mês de agosto foi " + max + "ºC no dia " + day1 + " de agosto");
		System.out.println("A temperatura mais baixa do mês de agosto foi " + min + "ºC no dia " + day2 + " de agosto");
		System.out.printf("A temperatura média do mês de agosto foi %.1fºC\n", average);

	}

}
