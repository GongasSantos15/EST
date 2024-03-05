package default_package;

public class Elevador {

	// Criar 3 variáveis para andar inferior, atual e superior
		private int andarInferior;
		private int andarSuperior;
		private int andarAtual;

	// Construtor que recebe 3 variáveis: andar inferior, andar atual e andar superior
		public Elevador(int andarInferior, int andarAtual, int andarSuperior) {
		
		// Validações
			if(andarInferior < andarSuperior) {
				this.andarInferior = andarInferior;
				this.andarSuperior = andarSuperior;
			} else {
				this.andarInferior = andarSuperior;
				this.andarSuperior = andarInferior;
			}
			
			if (acede(andarAtual)) {
				this.andarAtual = andarAtual;
			} else {
				// Solução colocar andar atual = andar inferior
					this.andarAtual = this.andarInferior;
				
				// Solução colocar andar atual no intermédio dos 2 andares (inferior e superior)
					//this.andarAtual = (this.andarSuperior + this.andarInferior) / 2;
					
				// Solução colocar andar atual na extremidade de um dos andares (inferior e superior)
					//this.andarAtual = andarAtual > this.andarSuperior ? this.andarSuperior : this.andarInferior;
			}	
			
	}

	// Construtor que recebe 2 variáveis: andar inferior e andar superior
		public Elevador(int andarInferior, int andarSuperior) {
			
			// Validações: chamada ao primeiro construtor criado
				this(andarInferior, andarInferior, andarSuperior);			
		
		}

	// Construtor que recebe 1 variável: andar superior
		public Elevador(int andarSuperior) {
			
			// Validações
				this(0, 0, andarSuperior);
		}

			
	// Criação dos métodos
		// Faz com que o elevador suba um andar
			public void sobe() {
				if (!estaTopo()) {
					andarAtual++;
				}
			}
			
		// Faz com que o elevador desça um andar
			public void desce() {
				if (!estaFundo()) {
					andarAtual--;
				}
				
			}
			
		// Desloca o elevador para o andar referido (o elevador pode andar um andar de cada vez)
			public void irPara(int andar) {
				if(!acede(andar) || andar == getAndarAtual()) {
					return;
				} 
				
				while(getAndarAtual() < andar) {
					sobe();
				}
				
				while(getAndarAtual() > andar) {
					desce();
				}
			}
			
		// Dado um andar indica se o elevador está no andar superior
			public boolean estaTopo() {
				return getAndarAtual() == getAndarSuperior();
			}

		// Dado um andar indica se o elevador está no andar inferior
			public boolean estaFundo() {
				return getAndarAtual() == getAndarInferior();
			}
		
		// 	Indica se um dado andar é acedido pelo elevador
			public boolean acede(int andar) {
				return andar >= getAndarInferior() && andar <= getAndarSuperior();
			}
			
	
	// Criação de getters e setters
		public int getAndarInferior() {
			return andarInferior;
		}
		public void setAndarInferior(int andarInferior) {
			if (andarInferior <= andarAtual) {
				this.andarInferior = andarInferior;
			}
		}
		
		public int getAndarAtual() {
			return andarAtual;
		}
		public void setAndarAtual(int andarAtual) {
			if (acede(andarAtual)) {
				this.andarAtual = andarAtual;
			}
		}

		public int getAndarSuperior() {
			return andarSuperior;
		}
		public void setAndarSuperior(int andarSuperior) {
			if (andarAtual <= andarSuperior) {
				this.andarSuperior = andarSuperior;
			}
		}
		
		
	// Teste dos elevadores criados (os 3 com andares inferiores, atuais e superiores)
		@Override
		public String toString() {
			return "Elevador [" + andarInferior + " | " + andarAtual + " | "+ andarSuperior + "]";
		}
		
}
