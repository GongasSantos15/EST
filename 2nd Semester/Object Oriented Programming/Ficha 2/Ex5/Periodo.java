package tempo;

public class Periodo {
	
	// Variáveis
		private Hora inicio, fim;

	// Construtor que incializa a hora inicial e final
		public Periodo(Hora inicio, Hora fim) {
			
			// Validação geral
				if (inicio.compareTo(fim) <= 0) {		// inicio <= fim não funciona para comparar 2 objetos, usar o compareTo()
					this.inicio = inicio;
					this.fim = fim;
				} else {
					this.inicio = fim;
					this.fim = inicio;
				}
		}
		
	// Construtor que inicializa a hora inicial e uma duração (em segundos)
		public Periodo(Hora inicio, int duracao) {
			
			// Validação geral
				if (duracao >= 0) {
					this.inicio = inicio;
					fim = inicio.clone();	// Usar o clone para declarar um objeto igual ao inicio, mas não exatamente o mesmo objeto
					fim.somaSegundos(duracao);
				}
		}
		
	/* Métodos */
		
		// Método que retorna a duração do período em segundos
			public int duracao() {
				return fim.diferencaSegs(inicio);
			}
		
		// Método que indica se uma dada hora está dentro do período
			public boolean estaDentro(Hora h) {
				return inicio.compareTo(h) <= 0 && fim.compareTo(h) >= 0 ? true : false;
			}
			
		// Método que indica se este período interseta outro período
			public boolean interseta(Periodo p) {
				return p.inicio.compareTo(fim) > 0 || p.fim.compareTo(inicio) < 0 ? false : true;
			}
			
		// Método que indica se este período contém outro período
			public boolean contem(Periodo p) {
				return inicio.compareTo(p.inicio) >= 0 && fim.compareTo(p.fim) <= 0 ? true : false;
			}
		
		// Método que junta este a outro, desde que se intersetem
			public Periodo junta(Periodo p) {
				
			}
			
		// Método que retorna um período que é a união deste período com outro
			
			
		// Método que retorna um período que é a interseção deste com outro
			

	// Getters e Setters
		public Hora getInicio() {
			return inicio;
		}
	
		public void setInicio(Hora inicio) {
			// Verificar se o inicio é <= fim
				if (inicio.compareTo(fim) <= 0) {
					this.inicio = inicio;
				}
		}
	
		public Hora getFim() {
			return fim;
		}
	
		public void setFim(Hora fim) {
			
			// Verificar se o fim >= inicio
				if(fim.compareTo(inicio) >= 0) {
					this.fim = fim;
				}
		}

		
	// ToString
		@Override
		public String toString() {
			return "Período: " + inicio + " até " + fim;
		}

		
}
