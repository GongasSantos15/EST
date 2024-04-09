package securest.app;


import consola.SConsola;
import p2.tempo.*;
import securest.recurso.*;

/**
 * Classe que trata dos menus da central de comandos
 */
public class MenuCentral {
	
	private SConsola consola = new SConsola();
	private CentralControlo central;
	
	public MenuCentral( CentralControlo central ){
		this.central = central;
	}

	/**
	 * menu principal da aplicação
	 */
	public void menuPrincipal(){
		String menu = "Central de controlo - controlo de acessos\n" + 
				"1- Info sobre instalação\n"+
				"2- Info sobre funcionário\n" + 
				"3- Entrada de funcionário\n"+
				"4- Saída de funcionário\n"+
				"5- Listar presenças em todas as instalações\n"+
				"\n0- Sair";

		char opcao = 0;
		do {
			consola.clear();
			consola.println( menu );
			opcao = consola.readChar();
			switch( opcao ){
			case '1':
				printInstalacao();
				break;
			case '2':
				printFuncionario();
				break;
			case '3':
				entradaFuncionario();
				break;
			case '4':
				saidaFuncionario();
				break;
			case '5':
				listarPresencas();
				consola.readLine();
				break;
			case '0': break;
			default:
				consola.println( "opção inválida");
				consola.readLine();
			}
		} while( opcao != '0' );

		consola.close();					// desligar o menu da central		
	}

	/**
	 * imprime a informação de uma instalação 
	 */
	private void printInstalacao( ) {
		// TODO ZFEITO substituir o Object pelo tipo de dados correto
		Instalacao inst = getInstalacao( "Código da instalação? ");
		consola.clear();
		
		// TODO ZFEITO Colocar os valores corretos nos vários locais
		consola.println(inst.getDescricao());		
		consola.println("ID: " + inst.getId());
		consola.println("NÍVEL: " + inst.getNivelAcesso());
		
		consola.print("Autorizados: ");
		
		// para todos os autorizados apresentar
		for (Funcionario f : inst.getAutorizados()) {
			consola.print(f.getNome() + ", ");
		}
		consola.println();
		consola.print("Presentes: " );
		
		// para todos os presentes apresentar
		for (Funcionario f : inst.getPresentes()) {
			consola.print(f.getNome() + ", ");
		}
		consola.println();
		
		consola.println("Horário funcionamento");
		// TODO ZFEITO chamar o método printHorario para imprimir o horario
		//      pode ser necessário mudar os parâmetros de entrada
		printHorario( inst.getHorarioFuncionamento() );
		consola.readLine();
	}
	
	/** imprime a informação de um horário
	 */
	private void printHorario( Horario h ) {
		// TODO ZFEITO para todos os períodos do horário apresentá-los
		for(Periodo p : h.getPeriodos()) {
			// TODO ZFEITO iniciar as horas com os valores corretos
			Hora hi = p.getInicio();
			Hora hf = p.getFim();
			consola.println( String.format("%02d:%02d - %02d:%02d",
					         hi.getHoras(), hi.getMinutos(), hf.getHoras(), hf.getMinutos()) );
		}
	}

	/**
	 * imprime a informação de um funcionário 
	 */
	private void printFuncionario( ) {
		// TODO ZFEITO substituir o Object pelo tipo de dados correto		
		Funcionario f = getFuncionario( "Código do funcionário? ");
		consola.clear();
		// TODO ZFEITO Colocar os valores corretos nos vários locais
		consola.println("NOME: " + f.getNome());		
		consola.println("ID: " + f.getId());
		consola.println("NÍVEL: " + f.getNivelAcesso() );
		if( f.estaPresente() )
			consola.print("Presente em " + f.getInstalacao() );
		else
			consola.print("Não está presente em nenhuma instalação" );
		consola.readLine();
	}
	
	/**
	 * processa a entrada de um funcionário
	 */
	private void entradaFuncionario() {
		// TODO ZFEITO substituir o Object pelo tipo de dados correto
		Funcionario f = getFuncionario( "Código do funcionário a entrar? ") ;
		Instalacao i = getInstalacao( "Código da instalação onde vai entrar? ");
		
		// TODO AFEITO verificar se pode entrar
		consola.println( i.podeEntrar(f, new Hora()) ? "Pode entrar" : "Impedido de entrar!");
		consola.readLine();
	}

	/**
	 * processa a saída de um funcionário
	 */
	private void saidaFuncionario() {
		// TODO substituir o Object pelo tipo de dados correto
		Object f = getFuncionario( "Código do funcionário a sair? ") ;
		
		// TODO verificar se pode entrar na instalação
		if( true /* está presente numa instalação */ ){
			// processar saída da instalação onde está
			consola.println("O funcionário <NOME FUNC> saiu de <NOME INST>" );
		}
		else {
			consola.println("O funcionário <NOME FUNC> não está em nenhuma instalação!");
		}
		consola.readLine();
	}


	/**
	 * pergunta ao utilizador qual o id do funcionário que vai ser processado
	 * e retorna o funcionário correspondente
	 * @param msg a mensagem a pedir o funcionário
	 * @return o funcionário pedido
	 */
	private Funcionario getFuncionario( String msg ){
		// TODO ZFEITO substituir o Object pelo tipo de dados correto
		Funcionario f = null;
		do {
			consola.print( msg );
			int id = consola.readInt();
			f = central.getFuncionario( id );
			if( f == null ){
				consola.println("Esse funcionário não existe!");
				consola.readLine();
			}
		}while( f == null );
		return f;
	}

	/**
	 * pergunta ao utilizador qual o id da instalação que vai ser processada
	 * e retorna a instalação correspondente
	 * @param msg a mensagem a pedir a instalação
	 * @return a instalação pedida
	 */
	private Instalacao getInstalacao( String msg ){
		// TODO substituir o Object pelo tipo de dados correto
		Instalacao i = null;
		do {
			consola.print( msg );
			int id = consola.readInt();
			i = central.getInstalacao( id );
			if( i == null ){
				consola.println("Essa instalação não existe!");
				consola.readLine();
			}
		}while( i == null );
		return i;
	}
	
	/**
	 * lista todas as presenças em todas as instalações 
	 */
	private void listarPresencas() {
		consola.clear();
		// TODO completar com a informação correta
		/* para todas as instalações */ {
			consola.println( "presentes em <NOME INST>  (NIVEL INST>" );
			/* para todos os funcionários */
				consola.println( "<NOME FUNC>   (NIVEL FUNC)" );
			consola.println( "-----------------" );
		}		
	}
	
}
