package ficha1;

public class Exercicios2457 {
//	
//// Exercício 2
//	
	//	// Neste caso os parâmetros de entrada são horas, minutos e segundos
	//	static int horasParaSegundos(int horas, int minutos, int segundos) {
	//		
	////		// Primeiro código feito:
	////		int total = horas * 3600 + minutos * 60 + segundos;
	////		return total;
	//		
	//		// Código ainda mais otimizado e eficiente:
	//		return horas * 3600 + minutos * 60 + segundos;
	//	}
	//
	//	public static void main(String[] args) {
	//		
	//		// Para testar o método apenas:
	//			horasParaSegundos( 2, 10, 30); 	// 7800
	//			horasParaSegundos( 1, 0, 0);	// 3600
	//			horasParaSegundos( 0, 1, 0);	//   60
	//			horasParaSegundos( 0, 0, 1);	// 	  1	
	//			horasParaSegundos( 10, 10, 10);	// ERRADO (tem os parâmetros iguais, não é um bom teste)		
//		}
//
	
	//Exercício 4
	
		// Primeiro código feito:
//		static boolean eBissexto(int ano) {
//			if ((ano % 4 == 0 && ano % 100 != 0) || ano % 400 == 0) {
//				return true;
//			}
//			else {
//				return false;
//			}
//		}
		
		// Código feito com operador trenário:
//		static boolean eBissexto(int ano) {
//			return ((ano % 4 == 0 && ano % 100 != 0) || ano % 400 == 0) ? true : false;
//		}
		
//		// Código mais eficiente:
//		static boolean eBissexto(int ano) {
//			return ((ano % 4 == 0 && ano % 100 != 0) || ano % 400 == 0);
//		}	
//		
//		public static void main(String[] args) {
//			System.out.println(	eBissexto ( 2024 )); // true
//			System.out.println( eBissexto ( 2023 )); // false
//			System.out.println(	eBissexto ( 2000 )); // true
//			System.out.println( eBissexto (  300 )); // false
//		}
	
	// Exercício 5
	
//		// Método do problema anterior
//		static boolean eBissexto(int ano) {
//			return ((ano % 4 == 0 && ano % 100 != 0) || ano % 400 == 0);
//		}	
//	
//		//Primeiro código feito:
//		static int diasAno(int ano) {
//			return (eBissexto(ano)) ? 366 : 365;
//		}
//		
//		public static void main(String[] args) {
//			System.out.println(	diasAno ( 2024 )); 	// 366
//			System.out.println( diasAno ( 2023 )); 	// 365
//			System.out.println(	diasAno ( 2000 )); 	// 366
//			System.out.println( diasAno (  300 )); 	// 365
//		}
	
	// Exercício 7
		
		
		// Primeiro código feito:
//			static int comparaDatas(int dia1, int mes1, int ano1, int dia2, int mes2, int ano2) {
//				if (ano1 > ano2) {
//					return 1;
//				}
//				if (ano1 < ano2) {
//					return -1;
//				}
//				
//				if (mes1 > mes2) {
//					return 2;
//				}
//				if (mes1 < mes2) {
//					return -2;
//				}
//				
//				if (dia1 > dia2) {
//					return 3;
//				}
//				if (dia1 < dia2) {
//					return -3;
//				}
//				return 0;
//			}
	
		// Código usando a diferença entre dias, mêses e anos
				static int comparaDatas(int dia1, int mes1, int ano1, int dia2, int mes2, int ano2) {
				if (ano1 != ano2) {
					return ano1 - ano2;
				}
				
				if (mes1 != mes2) {
					return mes1 - mes2;
				}
				
				return dia1 - dia2;
			}
	
		public static void main(String[] args) {
			System.out.println(	comparaDatas ( 10, 2, 2024, 23, 11, 2008 )); 	// > 0
			System.out.println(	comparaDatas ( 10, 2, 2024, 23, 11, 2025 )); 	// < 0
			
			System.out.println(	comparaDatas ( 10, 12, 2024, 23, 11, 2024 )); 	// > 0
			System.out.println(	comparaDatas ( 10, 2, 2024, 23, 11, 2024 )); 	// < 0
			
			System.out.println(	comparaDatas ( 10, 2, 2024, 3, 2, 2024 )); 		// > 0
			System.out.println(	comparaDatas ( 10, 2, 2024, 23, 2, 2024 )); 	// < 0
			
			System.out.println(	comparaDatas ( 10, 2, 2024, 10, 2, 2024 )); 	// = 0
			
		}
			
}

