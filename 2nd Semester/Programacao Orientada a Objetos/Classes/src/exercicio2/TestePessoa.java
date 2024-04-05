package exercicio2;

public class TestePessoa {

	public static void main(String[] args) {
		
		// Inicialização de p usando a classe Pessoa
			Pessoa p = new Pessoa();
			p.setNome("Gonçalo Santos");
			p.setMorada("Beco da Escola, nº9");
			p.setCodigoPostal("2710-107 Várzea de Sintra");
			p.setTelefone("925070256");
			p.setDiaNascimento(15);
			p.setMesNascimento(06);
			p.setAnoNascimento(2003);
			p.setEstadoCivil('s');
			
			// Impressão usando apenas a classe Pessoa
			System.out.println( p );
			
			// Impressão de cada atributo da classe Pessoa
//				System.out.printf( "Nome: %s\n", p.getNome() );
//				System.out.printf( "Morada: %s\n", p.getMorada() );
//				System.out.printf( "Estado Civil: %c\n", p.getEstadoCivil() );
	}

}