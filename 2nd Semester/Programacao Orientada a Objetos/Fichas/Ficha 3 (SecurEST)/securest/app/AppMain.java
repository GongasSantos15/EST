package securest.app;

import p2.tempo.*;
import securest.recurso.CentralControlo;
import securest.recurso.*;

public class AppMain {

	/**
	 * despoleta a aplicação
	 */
	public static void main( String []args ){
		
		/* ---------------------------------------------- CRIAÇÃO DA CENTRAL DE CONTROLO ----------------------------------------------- */
		CentralControlo central = new CentralControlo( );
		
		/* -------------------------------------------------- CRIAÇÃO DOS FUNCIONÁRIOS ------------------------------------------------- */
		central.addFuncionario(new Funcionario(1, "Asdrúbal", 1));
		central.addFuncionario(new Funcionario(2, "Josefina", 2));
		central.addFuncionario(new Funcionario(3, "Ambrósio", 3));		
		central.addFuncionario(new Funcionario(4, "Albertina", 4));
		central.addFuncionario(new Funcionario(5, "Zé Bigboss", 5));
		central.addFuncionario(new Funcionario(6, "Josefa", 3));

		/* -------------------------------------------------- CRIAÇÃO DAS INSTALAÇÕES ------------------------------------------------- */
		Horario h1 = new Horario();
		h1.addPeriodo(new Periodo(new Hora(8, 0, 0), new Hora(20, 0, 0)));
		Instalacao i1 = new Instalacao(1, 1, "Sala 1", h1);
		central.addInstalacao(i1);
		
		Horario h2 = new Horario();
		h2.addPeriodo(new Periodo(new Hora(8, 0, 0), new Hora(12, 0, 0)));
		h2.addPeriodo(new Periodo(new Hora(14, 0, 0), new Hora(20, 0, 0)));
		Instalacao i2 = new Instalacao(2, 2, "Sala 2", h2);
		central.addInstalacao(i2);
		
		Horario h3 = new Horario();
		h3.addPeriodo(new Periodo(new Hora(10, 0, 0), new Hora(14, 0, 0)));
		h3.addPeriodo(new Periodo(new Hora(16, 0, 0), new Hora(19, 0, 0)));
		Instalacao i3 = new Instalacao(3, 3, "Sala 3", h3);
		i3.addAutorizado(central.getFuncionario(1));
		central.addInstalacao(i3);
		
		Horario h4 = new Horario();
		h4.addPeriodo(new Periodo(new Hora(0, 0, 0), new Hora(7, 30, 0)));
		h4.addPeriodo(new Periodo(new Hora(21, 0, 0), new Hora(23, 59, 0)));
		Instalacao i4 = new Instalacao(4, 4, "Laboratório Secreto", h4);
		i4.addAutorizado(central.getFuncionario(2));
		i4.addAutorizado(central.getFuncionario(3));
		central.addInstalacao(i4);
		
		
		Horario h5 = new Horario();
		h5.addPeriodo(new Periodo(new Hora(0, 0, 0), new Hora(23, 59, 0)));
		Instalacao i5 = new Instalacao(5, 5, "Jacuzzi", h5);
		i5.addAutorizado(central.getFuncionario(2));
		i5.addAutorizado(central.getFuncionario(4));
		i5.addAutorizado(central.getFuncionario(6));
		central.addInstalacao(i5);
		
		Horario h6 = new Horario();
		h6.addPeriodo(new Periodo(new Hora(9, 30, 0), new Hora(16, 30, 0)));
		h6.addPeriodo(new Periodo(new Hora(17, 30, 0), new Hora(20, 30, 0)));
		Instalacao i6 = new Instalacao(6, 3, "Laboratório 3", h6);
		i6.addAutorizado(central.getFuncionario(1));
		central.addInstalacao(i6);
		
		
		
		
		// criar a aplicação propriamente dita
		MenuCentral app = new MenuCentral( central );
		app.menuPrincipal();
	}

}
