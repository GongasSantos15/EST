package exercicio5;

import exercicio4.Hora;

public class TestePeriodo {

	public static void main(String[] args) {
		
		// Criação do 1º período, criando 2 horas fora da variável
			Hora h1 = new Hora(8,30,0);
			Hora h2 = new Hora(10,30,0);
			Periodo p1 = new Periodo(h1, h2);
			System.out.println(p1);
		
		// Criação do 2º período, criando 2 horas dentro da variável (consome menos memória)
			Periodo p2 = new Periodo(new Hora(10,30,0), new Hora(12,30,0));
			System.out.println(p2);
			
		// Teste setInicio()
			p2.setInicio(new Hora(9,30,0));
			System.out.println("\n------ Mudando a hora inicial: ------");
			System.out.println(p2);
			
		// Teste setFim()
			p2.setFim(new Hora(15,30,0));
			System.out.println("\n ------ Mudando a hora final: ------");
			System.out.println(p2);
			
		// Teste Periodo(inicio, duracao)
			Periodo p3 = new Periodo(new Hora(12,30,0), Hora.SEGUNDOS_HORA);
			System.out.println("\n ------ Novo Período(inicio, duracao) ------");
			System.out.println(p3);
			
		// Teste método duracao
			System.out.println("\n ------ Calcular a duração ------");
			System.out.println(p3.duracao() + " segundos");
			
		// Teste método estaDentro
			System.out.println("\n ------ Verificar se uma hora está dentro do período ------");
			System.out.println(p3.estaDentro(new Hora(12,29,59)));
			
		// Teste método interseta
			System.out.println("\n ------ Verificar se um período interseta outro ------");
			System.out.println(p3.interseta(p2));
			
		// Teste método contem
			System.out.println("\n ------ Verificar se um período contém outro ------");
			System.out.println(p3.contem(p1));
	}

}