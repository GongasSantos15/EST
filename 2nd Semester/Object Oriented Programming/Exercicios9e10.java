package defaultPackage;

public class Exercicios9e10 {

//	// Exercício 9
//	public static void main(String[] args) {
//		
//		// Exercício 9.1 - Declarar as variáveis nome, morada, codigo postal, telefone, 
//		// data de nascimento e estado civil
//			String nome = "Vitor Virtual";
//			String morada = "Rua Ficticia, nº i";
//			String codigoPostal = "6000-000 CB";
//			String telefone = "999 555 555";
//			int diaNascimento = 8, mesNascimento = 9, anoNascimento = 2000;
//			char estadoCivil = 's';
//			
//			printPessoa(nome, morada, codigoPostal, telefone, diaNascimento, mesNascimento, anoNascimento,
//						estadoCivil);
//		
		
//	}
//			
//		static void printPessoa(String nome, String morada, String codigoPostal, String telefone,
//				int diaNascimento, int mesNascimento, int anoNascimento, 
//				char estadoCivil) {
//			
//				// Testes Exercício 9.2
//					System.out.printf("Nome:\t %s\n", nome);
//					System.out.printf("Morada:\t %s\n", morada);
//					System.out.printf("\t %s\n", codigoPostal);
//					System.out.printf("Tel:\t %s\n", telefone);
//					System.out.printf("Data de nascimento: %d/%d/%d\n", diaNascimento, mesNascimento, anoNascimento);
//					System.out.printf("Estado civil: %s\n", estadoCivilExtenso(estadoCivil));
//				
//				// Teste Exercicio 9.3
//					System.out.printf("Idade: %d", getIdade(anoNascimento, 2024));
//		}
//			
//		// Exercício 9.2 - Elaborar o método printPessoa que imprime a informação de uma pessoa
//			
//			static String estadoCivilExtenso(char estadoCivil) {
//				switch (estadoCivil) {
//					case 's': case 'S': return "solteir@";
//					case 'c': case 'C': return "casad@";
//					case 'd': case 'D': return "divorciad@";
//					case 'v': case 'V': return "viúv@";
//					case 'u': case 'U': return "união de facto";
//					default: return "???";
//					// // @ serve para os 2 géneros - masculino e feminino
//				}
//				
//			}
//			
//		// Exercício 9.3 - Elaborar o método getIdade(int anoNasc, int anoReferencia) que indica a idade
//		// de uma pessoa no final de um dado ano de referência
//			static int getIdade(int anoNascimento, int anoReferencia) {
//				return anoReferencia > anoNascimento ? anoReferencia - anoNascimento : -1;
//			}
	
	// Exercício 10
		public static void main(String[] args) {
			// Exercício 10.1
			
				// Declaração do número de pessoas
				int nPessoas = 3;
				
				// Declaração Arrays
					String nomes[] = new String [nPessoas];
					String moradas[] = new String [nPessoas];
					String codigosPostais[] = new String [nPessoas];
					String telefones[] = new String [nPessoas];
					int diasNascimento[] = new int [nPessoas];
					int mesesNascimento[] = new int [nPessoas];
					int anosNascimento[] = new int [nPessoas];
					char estadosCivis[] = new char [nPessoas];
			
				// Dados pessoas
					nomes[0] = "Pedro Primeiro";
					moradas[0] = "Rua 1º de Maio";
					codigosPostais[0] = "6000-001 CB";
					telefones[0] = "272000001";
					diasNascimento[0] = 10;
					mesesNascimento[0] = 10;
					anosNascimento[0] = 2001;
					estadosCivis[0] = 's';
					
					nomes[1] = "Dulce Dois";
					moradas[1] = "Rua das Duas Torres";
					codigosPostais[1] = "6000-002 CB";
					telefones[1] = "272000002";
					diasNascimento[1] = 12;
					mesesNascimento[1] = 12;
					anosNascimento[1] = 1972;
					estadosCivis[1] = 'c';
					
					nomes[2] = "Teresa Ternos";
					moradas[2] = "Rua dos 3 Globos";
					codigosPostais[2] = "6000-003 CB";
					telefones[2] = "272000003";
					diasNascimento[2] = 3;
					mesesNascimento[2] = 3;
					anosNascimento[2] = 1963;
					estadosCivis[2] = 'v';
			
			// Exercício 10.2
				// Não fazer
			
		}
		
}
