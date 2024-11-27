package menu;

import controlo.CentralControlo;
import controlo.Funcionario;
import instalacao.*;

public class Arranque {

	/**
	 * despoleta a aplicação
	 */
	public static void main( String []args ){
		CentralControlo central = new CentralControlo();
		setupCentral( central );
		
		MenuCentral menu = new MenuCentral( central );
		menu.menuPrincipal();
	}

	/** cria as instalações que se vão usar no sistema
	 * @param central central onde serão colocadas as instalações
	 */
	private static void setupCentral( CentralControlo central) {
		// criar os funcionarios
		central.addfuncionario( new Funcionario(1, 1));
		central.addfuncionario( new Funcionario(2, 2));
		central.addfuncionario( new Funcionario(3, 3));
		central.addfuncionario( new Funcionario(4, 4));
		central.addfuncionario( new Funcionario(5, 5));

		// criar as instalações
		// criar as salas
		Sala s1 = new Sala( 11, "Sala 1", 1);
		Sala s2 = new Sala( 12, "Sala 2", 2);
		Sala s3 = new Sala( 13, "Sala 3", 3);
		Sala s4 = new Sala( 14, "Sala 4", 4);		
		central.addInstalacao( s1 );
		central.addInstalacao( s2 );
		central.addInstalacao( s3 );
		central.addInstalacao( s4 );
		
		// criar os laboratórios
		Laboratorio lab1 = new Laboratorio( 21, "Lab. 1" );
		lab1.addAutorizado( central.getFuncionario( 1 ) );
		lab1.addAutorizado( central.getFuncionario( 2 ) );
		central.addInstalacao( lab1 );
		
		Laboratorio lab3 = new Laboratorio( 22, "Lab. 3" );
		lab3.addAutorizado( central.getFuncionario( 3 ) );
		lab3.addAutorizado( central.getFuncionario( 4 ) );
		central.addInstalacao( lab3 );
		
		// Criar os complexos
		Complexo blocoA = new Complexo(31, "Bloco A");
		blocoA.addInstalacao(s1);
		blocoA.addInstalacao(s2);
		blocoA.addInstalacao(lab1);
		central.addInstalacao(blocoA);
		
		Complexo blocoB = new Complexo(32, "Bloco B");
		blocoB.addInstalacao(s3);
		blocoB.addInstalacao(s4);
		blocoB.addInstalacao(lab3);
		central.addInstalacao(blocoB);
		
		Complexo complexoEmpresarial = new Complexo(41, "Complexo Empresarial");
		complexoEmpresarial.addInstalacao(blocoA);
		complexoEmpresarial.addInstalacao(blocoB);
		central.addInstalacao(complexoEmpresarial);
	}		
}
