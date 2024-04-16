package arranque;
import java.awt.Point;

import edificios.Central;
import edificios.Posto;
import menu.JanelaControlo;
import transporte.Camiao;
import viagem.Itinerario;

/** Responsável por cria o ambiente de execução e criar a janela */
public class Main {

	public static void main(String[] args) {
		// TODO ZFEITO criar a central requerida
		Point localizacaoCentral = new Point(505, 750);
		Central c = new Central(localizacaoCentral);
		
		// TODO ZFEITO criar os postos
		// TODO ZFEITO Criar a localização do posto dentro da variável posto
		/* ----------------------------------------------- CRIAÇÃO DOS POSTOS -------------------------------- */
		Posto MinasTirith = new Posto(1, "Minas Tirith", new Point(1400,990), 2200, 10000, 40000); 
		Posto Isengard = new Posto(2, "Isengard", new Point(925,710), 3200, 7000, 30000);
		Posto DolGuldur = new Posto(3, "Dol Guldur", new Point(1235,510), 2300, 17000, 30000);
		Posto Rivendell = new Posto(4, "Rivendell", new Point(890,310), 800, 15000, 20000);
		Posto Hobbiton = new Posto(5, "Hobbiton", new Point(455,335), 1300, 25000, 30000);
		Posto Edoras = new Posto(6, "Edoras", new Point(1035,800), 1300, 20000, 25000);
		Posto BaradDur = new Posto(7,"Barad-dur", new Point(1690,915), 2300, 5000, 35000);
		Posto AmonSul = new Posto(8, "Amon Sul", new Point(735,310), 1800, 6000, 25000);
		Posto Erebor = new Posto(9, "Erebor", new Point(1500,180), 1750, 4000, 30000);
		Posto Moria = new Posto(10, "Moria", new Point(860,500), 2100, 7000, 35000);
		Posto CirithUngol = new Posto(11, "Cirith Ungol", new Point(1540,980), 1200, 2000, 25000);
		Posto EmynMuil = new Posto(12, "Emyn Muil", new Point(1380,700), 1600, 18000, 25000);
		Posto Linhir = new Posto(13, "Linhir", new Point(1110,1180), 1900, 5000, 30000);
		Posto DomBeornd = new Posto(14, "Dom Beornd", new Point(1090,240), 1600, 5000, 30000);
		Posto Harlond = new Posto(15, "Harlond", new Point(175,400), 2000, 8000, 30000);
		Posto NossoPosto1 = new Posto(16, "Nosso Posto 1", new Point(550,550), 2800, 12000, 20000);
		Posto NossoPosto2 = new Posto(17, "Nosso Posto 2", new Point(1300,1320), 1000, 15000, 30000);
		Posto NossoPosto3 = new Posto(18, "Nosso Posto 3", new Point(1800, 1100), 3600, 20000, 35000);
		Posto NossoPosto4 = new Posto(19, "Nosso Posto 4", new Point(1750,680), 2400, 6000, 40000);
		Posto NossoPosto5 = new Posto(20, "Nosso Posto 5", new Point(200,120), 2700, 5700, 35000);
		
		// Adicionar os postos ao Mapa
		c.addPosto(MinasTirith);
		c.addPosto(Isengard);
		c.addPosto(DolGuldur);
		c.addPosto(Rivendell);
		c.addPosto(Hobbiton);
		c.addPosto(Hobbiton);
		c.addPosto(Edoras);
		c.addPosto(BaradDur);
		c.addPosto(AmonSul);
		c.addPosto(Erebor);
		c.addPosto(Moria);
		c.addPosto(CirithUngol);
		c.addPosto(EmynMuil);
		c.addPosto(Linhir);
		c.addPosto(DomBeornd);
		c.addPosto(Harlond);
		c.addPosto(NossoPosto1);
		c.addPosto(NossoPosto2);
		c.addPosto(NossoPosto3);
		c.addPosto(NossoPosto4);
		c.addPosto(NossoPosto5);
		
		// TODO ZFEITO criar os camiões
		/* -------------------------------------------- CRIAÇÃO DOS CAMIÕES ------------------------------------- */
		Camiao c1 = new Camiao("11-FG-33", 20000, 0, 65, 20, new Itinerario(c.getLocalizacao()));  
		Camiao c2 = new Camiao("22-DV-22", 30000, 0, 50, 30, new Itinerario(c.getLocalizacao()));  
		Camiao c3 = new Camiao("AA-34-BB", 35000, 0, 70, 30, new Itinerario(c.getLocalizacao()));  
		Camiao c4 = new Camiao("CF-65-FC", 40000, 0, 45, 40, new Itinerario(c.getLocalizacao()));  
		Camiao nossoCamiao = new Camiao("MM-23-GS", 50000, 0, 75, 50, new Itinerario(c.getLocalizacao()));
		
		// Adicionar os camiões ao Mapa
		c.addCamiao(c1);
		c.addCamiao(c2);
		c.addCamiao(c3);
		c.addCamiao(c4);
		c.addCamiao(nossoCamiao);
		
		/* -------------------------------------------- CRIAÇÃO DA JANELA PRINCIPAL------------------------------------ */ 
		// criar a apresentar a janela principal
		JanelaControlo postosFrame = new JanelaControlo( c );
		postosFrame.setVisible( true );
	}

}
