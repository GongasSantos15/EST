package securest.app;

import securest.recurso.CentralControlo;

public class AppMain {

	/**
	 * despoleta a aplicação
	 */
	public static void main( String []args ){
		
		// criar a central de controlo
		CentralControlo central = new CentralControlo( );
		
		/*
		 * TODO Criar aqui as instalações e funcionários indicados no enunciado 
		 */
		
		
		// criar a aplicação propriamente dita
		MenuCentral app = new MenuCentral( central );
		app.menuPrincipal();
	}

}
