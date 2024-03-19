package tempo;

import java.time.LocalTime;

public class Hora {
	
	// Declaração das constantes a utilizar :
	// As constantes devem ser sempre a MAIUSCULAS e separadas por "_"
		// Hora
			public static final int HORAS_DIA = 24;
			public static final int HORA_MAXIMA = HORAS_DIA - 1; // HORAS_DIA - 1 -> 24 - 1 = 23
		
		// Minutos
			public static final int MINUTOS_HORA = 60;
			public static final int MINUTOS_MAX = MINUTOS_HORA - 1;
			public static final int MINUTOS_DIA = MINUTOS_HORA * HORAS_DIA;
			
		// Segundos
			public static final int SEGUNDOS_MINUTO = 60;
			public static final int SEGUNDOS_MAX = SEGUNDOS_MINUTO - 1;
			public static final int SEGUNDOS_HORA = SEGUNDOS_MINUTO * MINUTOS_HORA;
			public static final int SEGUNDOS_DIA = SEGUNDOS_HORA * HORAS_DIA;
	
	// 1º - Variáveis
		private int horas, minutos, segundos;
	
	// 2º - Construtor
		public Hora(int horas, int minutos, int segundos) {

		
			// Nesta classe pode-se usar os setters no construtor, pois as variáveis são independentes entre si, ao contrário da classe Data e Elevador
			setHoras(horas);
			setMinutos(minutos);
			setSegundos(segundos);
		}
	
		public Hora() {
			LocalTime horaAtual = LocalTime.now();
			horas = horaAtual.getHour();
			minutos = horaAtual.getMinute();
			segundos = horaAtual.getSecond();
		}
	// 3º - Getters (obter o valor da variável) e Setters (alterar valor da variável no futuro)
		// Horas
			public int getHoras() {
				return horas;
			}
	
			public void setHoras(int horas) {
				// Para o setter ser chamado no construtor, este tem de retornar algum valor, não pode ser ignorado, tem de inicializar a variável SEMPRE
				
				// Se a hora for menor que 0, retorna 0, se for maior que 23, retorna 23, senão retorna a variável horas
					if (horas < 0) {
						this.horas = 0;
					} else if (horas >= HORAS_DIA) {
						this.horas = HORA_MAXIMA; 
					} else {
						this.horas = horas;
					}
			}
		
		// Minutos
			public int getMinutos() {
				return minutos;
			}
	
			public void setMinutos(int minutos) {
				if (minutos < 0) {
					this.minutos = 0;
				} else if (minutos >= MINUTOS_HORA) {
					this.minutos = MINUTOS_MAX;
				} else {
					this.minutos = minutos;
				}
			}

		// Segundos
			public int getSegundos() {
				return segundos;
			}
		
			public void setSegundos(int segundos) {
				if (segundos < 0) {
					this.segundos = 0;
				} else if (segundos >= SEGUNDOS_MINUTO) {
					this.segundos = SEGUNDOS_MAX;
				} else {
					this.segundos = segundos;
				}
			}
	
	// 4º - toString
		@Override
		public String toString() {
			// Para colocar a hora a 0H:0M:0S se não ocupar 2 digítos
			return String.format("%02d:%02d:%02d", horas, minutos, segundos);
		}
	
	// 5º - Métodos
		public void somaHoras(int numHoras) {
			this.horas += numHoras;
			if (this.horas >= HORAS_DIA) {
				horas %= HORAS_DIA;
			}
		}
		
		public void somaMinutos(int numMinutos) {
			int totalMinutos = minutos + numMinutos;
			this.minutos = totalMinutos % MINUTOS_HORA;
			somaHoras(totalMinutos / MINUTOS_HORA);	
		}
		
		public void somaSegundos(int numSegundos) {
			int totalSegundos = segundos + numSegundos;
			this.segundos = totalSegundos % SEGUNDOS_MINUTO;
			somaMinutos(totalSegundos / SEGUNDOS_MINUTO);
		}
		
		public int toSegundos() {
			return this.horas * SEGUNDOS_HORA + this.minutos * SEGUNDOS_MINUTO + this.segundos;
		}
		
		public int diferencaSegs(Hora outraHora) {
			return toSegundos() - outraHora.toSegundos();
		}
		
		public Hora clone() {
			return new Hora(getHoras(), getMinutos(), getSegundos());
		}
		
		public int compareTo(Hora outraHora) {
			if (this.horas != outraHora.horas) {
				return this.horas - outraHora.horas;
			} 
			if (this.minutos != outraHora.minutos) {
				return this.minutos - outraHora.minutos;
			}
			return this.segundos - outraHora.segundos;
		}
}
