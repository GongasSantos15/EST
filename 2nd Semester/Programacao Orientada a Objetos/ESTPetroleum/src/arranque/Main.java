package arranque;
import java.awt.Point; 

import menu.Mapa;
import menu.JanelaControlo;
import petroleum.Camiao;
import petroleum.Central;
import petroleum.Itinerario;
import petroleum.Posto;

/** Responsável por cria o ambiente de execução e criar a janela */
public class Main {

	public static void main(String[] args) {
		// TODO ZFEITO criar a central requerida
		Point localizacaoCentral = new Point(505, 750);
		Central c = new Central(localizacaoCentral);
		
		// TODO ZFEITO criar os postos
		Point minastirith = new Point(1400,990);
		Posto MinasTirith = new Posto(1,minastirith, 2200, 10000, 40000); // id, point, gastoMedio, quantidadeAtual, capacidade

		Point isengard = new Point(925,710);
		Posto Isengard = new Posto(2, isengard, 3200, 7000, 30000);

		Point dolguldur = new Point(1235,510);
		Posto DolGuldur = new Posto(3,dolguldur, 2300, 17000, 30000);

		Point rivendell = new Point(890,310);
		Posto Rivendell = new Posto(4,rivendell, 800, 15000, 20000);

		Point hobbiton = new Point(455,335);
		Posto Hobbiton = new Posto(5,hobbiton, 1300, 25000, 30000);

		Point edoras = new Point(1035,800);
		Posto Edoras = new Posto(6,edoras, 1300, 20000, 25000);

		Point baraddur = new Point(1690,915);
		Posto BaradDur = new Posto(7,edoras, 2300, 5000, 35000);

		Point amonsul = new Point(735,310);
		Posto AmonSul = new Posto(8,amonsul, 1800, 6000, 25000);

		Point erebor = new Point(1500,180);
		Posto Erebor = new Posto(9,erebor, 1750, 4000, 30000);

		Point moria = new Point(860,500);
		Posto Moria = new Posto(10,moria, 2100, 7000, 35000);

		Point cirithungol = new Point(1540,980);
		Posto CirithUngol = new Posto(11,cirithungol, 1200, 2000, 25000);

		Point emynmuil = new Point(1380,700);
		Posto EmynMuil = new Posto(12,emynmuil, 1600, 18000, 25000);

		Point linhir = new Point(1110,1180);
		Posto Linhir = new Posto(13,linhir, 1900, 5000, 30000);

		Point dombeornd = new Point(1090,240);
		Posto DomBeornd = new Posto(14,dombeornd, 1600, 5000, 30000);

		Point harlond = new Point(175,400);
		Posto Harlond = new Posto(15,harlond, 2000, 8000, 30000);

		Point nosso1 = new Point(225,700);
		Posto Nosso1 = new Posto(16,nosso1, 2800, 12000, 20000);

		Point nosso2 = new Point(400,835);
		Posto Nosso2 = new Posto(17,nosso2, 1000, 15000, 30000);

		Point nosso3 = new Point(500, 742);
		Posto Nosso3 = new Posto(18,nosso3, 3600, 20000, 35000);

		Point nosso4 = new Point(900,320);
		Posto Nosso4 = new Posto(19,nosso4, 2400, 6000, 40000);

		Point nosso5 = new Point(100,456);
		Posto Nosso5 = new Posto(20,nosso5, 2700, 5700, 35000);
		
		
		// TODO ZFEITO criar os camiões
		Camiao c1 = new Camiao("11-FG-33", 20000, 0, 65, 20, new Itinerario(20000, new Point()));  // matricula, capacidadeMax, quantidadeAtual, velocidadeMedia, debito, itinerario
		Camiao c2 = new Camiao("22-DV-22", 30000, 0, 50, 30, new Itinerario(30000, new Point()));  // matricula, capacidadeMax, quantidadeAtual, velocidadeMedia, debito, itinerario
		Camiao c3 = new Camiao("AA-34-BB", 35000, 0, 70, 30, new Itinerario(35000, new Point()));  // matricula, capacidadeMax, quantidadeAtual, velocidadeMedia, debito, itinerario
		Camiao c4 = new Camiao("CF-65-FC", 40000, 0, 45, 40, new Itinerario(40000, new Point()));  // matricula, capacidadeMax, quantidadeAtual, velocidadeMedia, debito, itinerario
		Camiao nosso = new Camiao("MM-23-GS", 50000, 0, 75, 50, new Itinerario(20000, new Point()));  // matricula, capacidadeMax, quantidadeAtual, velocidadeMedia, debito, itinerario
		
		// criar a apresentar a janela principal
		JanelaControlo postosFrame = new JanelaControlo( c );
		postosFrame.setVisible( true );
	}

}
