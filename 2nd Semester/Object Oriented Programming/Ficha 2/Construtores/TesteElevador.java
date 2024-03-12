package default_package;

public class TesteElevador {

	public static void main(String[] args) {
		// Criação de um elevador com andar inferior = -3, andar atual = 5 e andar superior = 10
			Elevador e1 = new Elevador(-3, 5, 10);
			System.out.println(e1);
			
		// Criação de um elevador com andar inferior = -1 e andar superior = 12
			Elevador e2 = new Elevador(-1, 12);
			System.out.println(e2);
		
		// Criação de um elevador com andar superior = 4
			Elevador e3 = new Elevador(4);
			System.out.println(e3);
			
			System.out.println();
			
		// Mudar o andar inferior:
			e1.setAndarInferior(3);
			System.out.println("Mudando o andar inferior fica: " + e1);
				
		// Mudar o andar atual:
			e1.setAndarAtual(3);
			System.out.println("Mudando o andar atual fica: " + e1);
			
		// Mudar o andar superior:
			e1.setAndarSuperior(2);
			System.out.println("Mudando o andar superior fica: " + e1);
			
		System.out.println();
			
		// Verificar se o elevador está no topo:
			System.out.println("Está no topo: "+ e1.estaTopo());
			
		// Verificar se o elevador está no fundo:
			System.out.println("Está no fundo: "+ e1.estaFundo());
			
		// Verificar se o elevador acede a um andar:
			System.out.println("Acede ao andar: " + e1.acede(7));
			
		// Verificar se o elevador sobe:
			e1.sobe();
			System.out.println("O elevador subiu para o andar: " + e1.getAndarAtual());
			
		// Verificar se o elevador desce:
			e1.desce();
			System.out.println("O elevador desceu para o andar: " + e1.getAndarAtual());
			
		// Para o elevador descer ou subir n andares usar o for
//			for(int i = 0; i < n; i++) {
//				e1.sobe(); ou e1.desce();
//			}
			
		// Verificar se o elevador vai para um dado andar:
			e1.irPara(7);
			System.out.println("O elevador foi para o andar: " + e1.getAndarAtual());
	}

}
