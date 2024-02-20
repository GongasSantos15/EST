package defaultPackage;

import java.util.Scanner;

public class Exercicios2a7 {
	
	// Neste caso os parâmetros de entrada são horas, minutos e segundos
	static int horasParaSegundos(int horas, int minutos, int segundos) {
		
		// Primeiro código feito:
//		int total = horas * 3600 + minutos * 60 + segundos;
//		return total;
		
		// Código ainda mais otimizado e eficiente:
		return horas * 3600 + minutos * 60 + segundos;
	}

	public static void main(String[] args, Scanner input) {
		
		// Para testar o método apenas:
			horasParaSegundos( 2, 10, 30); 	// 7800
			horasParaSegundos( 1, 0, 0);	// 3600
			horasParaSegundos( 0, 1, 0);	//   60
			horasParaSegundos( 0, 0, 1);	// 	  1	
			horasParaSegundos( 10, 10, 10);	// ERRADO (tem os parâmetros iguais, não é um bom teste)	

		
//		// Para testar com o utilizador
//			int horas = input.nextInt();
//			int minutos = input.nextInt();
//			int segundos = input.nextInt();
//			
//			int total = horasParaSegundos(horas, minutos, segundos);
//			
//			System.out.printf("%d:%d:%d = %d segundos", horas, minutos, segundos, total);
	}
	
}
