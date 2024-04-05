package exercicio2;

public class Pessoa {
	
	// Criação das variáveis da classe em private
		private String nome;
		private String morada;
		private String codigoPostal;
		private String telefone;
		private int diaNascimento, mesNascimento, anoNascimento;
		private char estadoCivil;

	// Getters and Setters: 
		// Nome
			public String getNome() {
				return nome;
			}
			public void setNome ( String n ) {
				if (n != null && !n.isBlank()) {
					nome = n;
				} else {
					nome = "Zé Ninguém";
				}
			}
		
		// Dia de Nascimento
			public int getDiaNascimento() {
				return diaNascimento;
			}
			public void setDiaNascimento ( int dn ) {
				diaNascimento = dn;
			}
		
		// Estado Civil
			public char getEstadoCivil() {
				return estadoCivil;
			}
			public void setEstadoCivil ( char ec ) {
				char ecm = Character.toLowerCase(ec);
				if (ecm == 's' || ecm == 'c' || ecm == 'd' || ecm == 'v' || ecm == 'u') {
					estadoCivil = ecm;
				} else {
					estadoCivil = '?';
				}
			}
		
		// Morada
			public String getMorada() {
				return morada;
			}
			public void setMorada(String m) {
				this.morada = m;
			}
			
		// Código Postal
			public String getCodigoPostal() {
				return codigoPostal;
			}
			public void setCodigoPostal(String cp) {
				this.codigoPostal = cp;
			}
			
		// Telefone
			public String getTelefone() {
				return telefone;
			}
			public void setTelefone(String t) {
				this.telefone = t;
			}
			
		// Mês de Nascimento
			public int getMesNascimento() {
				return mesNascimento;
			}
			public void setMesNascimento(int mn) {
				this.mesNascimento = mn;
			}
			
		// Ano de Nascimento
			public int getAnoNascimento() {
				return anoNascimento;
			}
			public void setAnoNascimento(int an) {
				this.anoNascimento = an;
			}
			
	// Método toSting()
		@Override
		public String toString() {
			return "Nome:\t\t\t" + nome + 
					"\nMorada:\t\t\t" + morada + 
					"\nCódigo Postal:\t\t" + codigoPostal + 
					"\nTel.:\t\t\t" + telefone + 
					"\nData de Nascimento:\t" + diaNascimento + "/" + mesNascimento + "/" + anoNascimento + 
					"\nEstado Civil:\t\t" + getEstadoCivilExtenso() + 
					"\nIdade:\t\t\t" + getIdade(2023) + 
					"\nÉ Maior de Idade:\t" + eMaiorIdade(2024);
		}
		
	// Método para obter o estado civil por extenso
		public String getEstadoCivilExtenso() {
			switch (estadoCivil) {
				case 's': return "Solteir@";
				case 'c': return "Casad@";
				case 'd': return "Divorciad@";
				case 'v': return "Viúv@";
				case 'u': return "União de facto";
				default: return "???";
			}
		}
		
	// Método para calcular a idade de uma pessoa com base no ano de referência
		public int getIdade(int anoReferencia) {
			return anoReferencia > anoNascimento ? anoReferencia - anoNascimento : -1;
		}
		
	// Método para verifiar se é maior de idade	
		public boolean eMaiorIdade(int anoReferencia) {
			return getIdade(anoReferencia) >= 18;
		}
 	
}