package teste;

import tempo.Hora;

public class TesteHora {

	public static void main(String[] args) {
		// Criação da hora
			Hora h1 = new Hora(12, 30, 0);
		//System.out.println(h1);
		
		// Criação de uma nova hora
			Hora h2 = new Hora(11, 40, 10);
		
		// Utilização do método compareTo():
			if (h2.compareTo(h1) >= 0) {
				System.out.println("Vamos almoçar");
			} else {
				System.out.println("Toca a trabalhar!");
			}
			System.out.println();
		
		// Utilização do método somaHoras()
			h2.somaHoras(20);
			System.out.println("Somar Horas: " + h2);
		
		System.out.println();
			
		// Utilização do método somaMinutos()
		h2.somaMinutos(70);
		System.out.println("Somar Minutos: " + h2);
		
		System.out.println();
		
		// Utilização do método somaSegundos()
		h2.somaSegundos(100);
		System.out.println("Somar Segundos: " + h2);
		
		System.out.println();
		
		// Utilização do método toSegundos()
		System.out.println("Hora para segundos: " + h2.toSegundos());
		
		System.out.println();
		
		// Utilização do método diferençaSegs()
		System.out.println("Diferença em segundos: " + h2.diferencaSegs(h1));
		
		System.out.println();
		
		// Utilização do método clone()
		System.out.println("Clone: " + h2.clone());
		
		System.out.println();
		
		// Utilização LocalTime()
		Hora horaAtual = new Hora();
		
		System.out.println("Hora Atual: " + horaAtual);
	}

}
