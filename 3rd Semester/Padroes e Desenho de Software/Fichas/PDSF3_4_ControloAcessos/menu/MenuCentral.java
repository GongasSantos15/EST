package menu;

import instalacao.Instalacao;
import instalacao.Laboratorio;
import instalacao.Sala;

import java.util.Collection;

import consola.SConsola;
import controlo.CentralControlo;
import controlo.Funcionario;

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
		              "1- Entrar numa instalação\n" +
		              "2- Sair de uma instalação\n"+
		              "3- Listar funcionários presentes\n"+
		              "4- Listar instalações (exerc. 4.2)\n"+
		              "5- Editar características de instalação (exerc. 4.2)\n" + 
		              "\n0- Sair";
		
		char opcao = 0;
		do {
			consola.clear();
			consola.println( menu );
			opcao = consola.readChar();
			switch( opcao ){
			case '1':
				entrarInstalacao();
				break;
			case '2':
				sairInstalacao();
				break;
			case '3':
				listarFuncionariosPresentes();
				break;
			case  '4':
				listarInstalacoes();
				break;
			case  '5':
				editarInstalacao( );
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
	 * Pede os dados para entrar numa instalação
	 */
	private void entrarInstalacao() {
		consola.clear();
		Instalacao inst = pedirInstalacao();
		if( inst == null )
			return;

		Funcionario u = pedirFuncionario();
		if( u == null ) 
			return;
		
		if( inst.entrar( u ) )
			consola.println( inst.getNome() + ": pode entrar ");
		else
			consola.println( inst.getNome() + ": não pode entrar ");
		consola.readLine();
	}

	
	/**
	 * Pede os dados para sair de uma instalação
	 */
	private void sairInstalacao() {
		consola.clear();
		
		Instalacao inst = pedirInstalacao();			
		if( inst == null )
			return;

		Funcionario u = pedirFuncionario();			
		if( u == null )
			return;
		
		inst.sair( u );
		consola.println( inst.getNome() + ": pode sair");
		consola.readLine();
	}

	/** Pede ao utilizador para identificar a instalação pretendida
	 * @return a instalação pretendida, null se não a encontrar
	 */
	private Instalacao pedirInstalacao() {
		int id;
		consola.print("Código da instalação? ");
		id = consola.readInt();
		Instalacao inst = central.getInstalacao( id );			
		if( inst == null ){
			consola.print("Essa instalação não existe!");
			consola.readLine();
			return null;
		}
		return inst;
	}

	/** pede ao utilizador para identificar o funcionário a entrar/sair
	 * @return o funcionário selecionado, ou null caso não o encontre
	 */
	private Funcionario pedirFuncionario() {
		consola.print("Código do funcionário? ");
		int id = consola.readInt();
		Funcionario u = central.getFuncionario( id );			
		if( u == null ){
			consola.print("Esse funcionário não existe");
			consola.readLine();
			return null;
		}
		return u;
	}

	
	/**
	 * lista todas as presenças dos funcionários 
	 */
	private void listarFuncionariosPresentes() {
		consola.clear();
		for( Funcionario f : central.getFuncionarios() ){
			if( f.getInstalacao() != null )
				consola.println( f.getId() + " presente em " + f.getInstalacao().getNome() );
		}
		consola.readLine();
	}

	/**
	 * Lista as istalações e respetivas características
	 */
	private void listarInstalacoes() {
		consola.clear();
		consola.println("------- INSTALAÇÔES --------" );		
		// TODO listar as instalações todas, usando uma solução melhor que esta
		for( Instalacao i : central.getInstalacoes() ) {
			if( i instanceof Sala )
				listarSala( (Sala) i );
			else if( i instanceof Laboratorio )
				listarLab( (Laboratorio) i );
		}		
		consola.readLine();
	}

	/** lista as características de uma sala e quais os funcionários presentes nesta
	 * @param s a sala a ser apresentada
	 */
	private void listarSala( Sala s) {
		consola.println( "Sala (" + s.getId() + ") " + s.getNome() + " nivel " + s.getNivelAcesso());
		consola.println( "   presentes:" + listarFuncionarios( s.getPresentes() ) );
	}

	/** listas as características de um laboratório e quais os funcionarios presentes neste
	 * @param lab o laboratório a ser apresentado
	 */
	private void listarLab(Laboratorio lab) {
		consola.print( "Laboratório (" + lab.getId() + ") " + lab.getNome() + " Autorizados: " +
	                    listarFuncionarios( lab.getAutorizados() ));
		consola.println( "\n   presentes:" + listarFuncionarios( lab.getPresentes() ) );
	}
	
	/** retorna uma string com os identificadores dos funcionarios presentes numa lista
	 * @param funcionarios funcionarios a listar
	 * @return  uma string com os identificadores dos funcionarios presentes na lista 
	 */
	private String listarFuncionarios( Collection<Funcionario> funcionarios ) {
		String str = "";
		for( Funcionario f : funcionarios )
			str += f.getId() + ", ";
		return str;
	}

	/** menu de edição de uma instalação
	 */
	private void editarInstalacao() {
		consola.clear();
		// pedir ao utilizador a instalação a editar
		Instalacao inst = pedirInstalacao();
		if( inst == null )
			return;
		
		// TODO editar as instalações todas usando uma solução melhor que esta
		if( inst instanceof Sala )
			editarSala( (Sala) inst );
		else if( inst instanceof Laboratorio )
			editarLab( (Laboratorio) inst );
	}

	/** editar as características de uma sala
	 * @param s sala a ser editada
	 */
	private void editarSala( Sala s ) {
		String menu = "1- Mudar nome\n2- Mudar nível\n\n0- voltar";
		int opcao;
		do {
			consola.clear();
			consola.println("Mudar características da sala (" + s.getId() + ")\nNome: " + s.getNome() + 
			                 "\nNível: " + + s.getNivelAcesso() + "\n" );
			consola.println( menu );
			opcao = consola.readInt();
			switch( opcao ) {
			case 1:
				mudarNomeInstalacao( s );
				break;
			case 2:
				alterarNivelSala( s );
				break;
			}
		} while( opcao != 0 );
	}

	/** pede um novo nivel para a sala ao utilizador
	 * @param s sala a ter o novo nível
	 */
	private void alterarNivelSala(Sala s) {
		try {
			consola.println("Novo nível (" + Funcionario.NIVEL_ACESSO_MIN + "-" + Funcionario.NIVEL_ACESSO_MAX + "): ");
			int nivel = consola.readInt();
			s.setNivelAcesso( nivel );
			consola.println("Nível alterado para "  + nivel );
		} catch (IllegalArgumentException e) {
			consola.println("Nível de acesso inválido");
		}
		consola.readLine();
	}

	/** editar as características de um laboratório
	 * @param lab o laboratório a ser editado
	 */
	private void editarLab(Laboratorio lab) {
		String menu = "1- Mudar nome\n2- Adicionar autorizado\n3- Remover Autorizado\n\n0- voltar";
		int opcao;
		do {
			consola.clear();
			consola.print("Mudar características do laboratório (" + lab.getId() + ")\nNome: " + lab.getNome() + 
			                 "\nAutorizados: " );
			for( Funcionario f : lab.getAutorizados() )
				consola.print( f.getId() + ", " );
			consola.println();
			consola.println( menu );
			opcao = consola.readInt();
			Funcionario f;
			switch( opcao ) {
			case 1:
				mudarNomeInstalacao( lab );
				break;
			case 2:
				f = pedirFuncionario();
				if( f != null )
					lab.addAutorizado( f );
				break;
			case 3:
				f = pedirFuncionario();
				if( f != null )
					lab.removeAutorizado( f );
				break;

			}
		} while( opcao != 0 );	
	}
	
	/** pede um novo nome para a instalação e usa-o
	 * @param i instalação à qual vai ser mudado o nome
	 */
	private void mudarNomeInstalacao(Instalacao i ) {
		try {
			consola.println("Novo nome: ");
			String nome= consola.readLine();
			i.setNome(nome);
			consola.println("Nome alterado para "  + nome );
		} catch (IllegalArgumentException e) {
			consola.println("Nome inválido");
		}
		consola.readLine();
	}
}
