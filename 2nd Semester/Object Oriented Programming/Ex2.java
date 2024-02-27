package default_package;

public class Ex2 {
	
	// Método para imprimir pessoas
		public static void imprimirPessoas(Pessoa pessoas[]) {
			for (int i = 0; i < pessoas.length; i++) {
				System.out.println(pessoas[i] + "\n");
			}
		}
		
	// Método para imprimir pessoas maior de idade
		public static void imprimirPessoasMaioresIdade(Pessoa pessoas[], int anoReferencia) {
			
			// For each
			for (Pessoa p : pessoas) {
				if (p.eMaiorIdade(anoReferencia)) {
					System.out.println(p + "\n");
				}
			}
		}
		
	// Método para imprimir pessoas com um determinado estado civil
		public static void imprimirPessoasDadoEstadoCivil(Pessoa pessoas[], char estadoCivil) {
			for (Pessoa p : pessoas) {
				if (p.getEstadoCivil() == estadoCivil) {
					System.out.println(p + "\n");
				}
			}
		}
		
	// Método para imprimir pessoas com um determinado código postal
			public static void imprimirPessoasDadoCodigoPostal(Pessoa pessoas[], String codigoPostal) {
				for (Pessoa p : pessoas) {
					if (p.getCodigoPostal().equals(codigoPostal)) {
						System.out.println(p + "\n");
					}
				}
			}
		
	// Método para imprimir pessoas com um determinado número de telefone
		public static void imprimirPessoasDadoTelefone(Pessoa pessoas[], String telefone) {
			for (Pessoa p : pessoas) {
				if (p.getTelefone().equals(telefone)) {
					System.out.println(p + "\n");
				}
			}
		}
		
	// Método para imprimir pessoas com um determinado número de telefone
			public static void imprimirNomeMoradaDadoTelefone(Pessoa pessoas[], String telefone) {
				for (Pessoa p : pessoas) {
					if (p.getTelefone().equals(telefone)) {
						System.out.println("Nome:\t" + p.getNome() + "\n" + "Morada:\t" + p.getMorada() + "\n");
					}
				}
			}

	public static void main(String[] args) {
		
		// Criação de um array pessoas para guardar os dados
		Pessoa pessoas[] = new Pessoa[5];
		
		// Primeira pessoa
			// Inicialização da primeira pessoa:
				pessoas[0] = new Pessoa();
		
			// Dados da primeira pessoa
				pessoas[0].setNome("Pedro Primeiro");
				pessoas[0].setMorada("Av. 1berto Delgado, nº 1");
				pessoas[0].setCodigoPostal("6000-001 CB");
				pessoas[0].setTelefone("272000001");
				pessoas[0].setDiaNascimento(11);
				pessoas[0].setMesNascimento(1);
				pessoas[0].setAnoNascimento(2001);
				pessoas[0].setEstadoCivil('s');
			
		// Segunda pessoa
			// Inicialização da segunda pessoa:
			pessoas[1] = new Pessoa();
	
			// Dados da segunda pessoa
				pessoas[1].setNome("Dulce Dois");
				pessoas[1].setMorada("Rua Duque Segundo");
				pessoas[1].setCodigoPostal("6000-002 CB");
				pessoas[1].setTelefone("272000002");
				pessoas[1].setDiaNascimento(22);
				pessoas[1].setMesNascimento(12);
				pessoas[1].setAnoNascimento(1962);
				pessoas[1].setEstadoCivil('c');
		
		// Terceira pessoa
			// Inicialização da terceira pessoa:
				pessoas[2] = new Pessoa();
	
			// Dados da segunda pessoa
				pessoas[2].setNome("Teresa Três");
				pessoas[2].setMorada("Avenida dos 3 globos");
				pessoas[2].setCodigoPostal("6000-003 CB");
				pessoas[2].setTelefone("272000003");
				pessoas[2].setDiaNascimento(13);
				pessoas[2].setMesNascimento(3);
				pessoas[2].setAnoNascimento(2013);
				pessoas[2].setEstadoCivil('d');
		
		// Quarta pessoa
			// Inicialização da quarta pessoa:
				pessoas[3] = new Pessoa();
	
			// Dados da quarta pessoa
				pessoas[3].setNome("Quim Quadras");
				pessoas[3].setMorada("Rua Quarta, nº 4");
				pessoas[3].setCodigoPostal("6000-004 CB");
				pessoas[3].setTelefone("272000004");
				pessoas[3].setDiaNascimento(4);
				pessoas[3].setMesNascimento(4);
				pessoas[3].setAnoNascimento(2004);
				pessoas[3].setEstadoCivil('v');
				
		// Quinta pessoa
				// Inicialização da quinta pessoa:
				pessoas[4] = new Pessoa();
		
				// Dados da quinta pessoa
					pessoas[4].setNome("Paulo Penta");
					pessoas[4].setMorada("Quinta da Granja, nº 5");
					pessoas[4].setCodigoPostal("6000-005 CB");
					pessoas[4].setTelefone("272000005");
					pessoas[4].setDiaNascimento(25);
					pessoas[4].setMesNascimento(5);
					pessoas[4].setAnoNascimento(1995);
					pessoas[4].setEstadoCivil('u');
					
		// Impressão das 5 pessoas
			//imprimirPessoas(pessoas);
					
		// Impressão das pessoas maiores de idade
			//imprimirPessoasMaioresIdade(pessoas, 2024);
			
		// Impressão das pessoas que possuem um determinado estado civil
			//imprimirPessoasDadoEstadoCivil(pessoas, 's');
		
		// Impressão das pessoas com um determinado número de telefone
			//imprimirPessoasDadoTelefone(pessoas, "272000004");
					
		// Impressão das pessoas que dado o telefone, aparece o nome e morada
			//imprimirNomeMoradaDadoTelefone(pessoas, "272000005");
			
	}	

}
