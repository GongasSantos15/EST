package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import estairways.Aeroporto;
import estairways.ClasseConforto;
import estairways.ESTAirways;
import estairways.Passageiro;
import estairways.Voo;
import reserva.Reserva;
import reserva.ReservaBasic;
import reserva.ReservaBusiness;
import reserva.ReservaDefault;
import reserva.ReservaEconomica;
import reserva.ReservaExecutive;

@SuppressWarnings("serial")
/**
 * Classe responsável pela janela de escolha e reserva de voos 
 */
public class JanelaEscolha extends JFrame {
	// A companhia
	private ESTAirways estAir;
	
	private Voo voo;

	// padrões para usar quando se quiserem processar as datas e horas
	private static final DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private static final DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
	
	
	/** Cria uma janela de pesquiusa de voos
	 * @param c a companhia
	 */
	public JanelaEscolha( ESTAirways c ) {
		estAir = c;
		setTitle( "EST Global Airways - Escolha o seu próximo voo" );
		// TODO FEITO criar uma lista com os nomes dos aeroportos, exceto Castelo Branco (que devem ser lidos do ficheiro obviamente)
		// Inicializar o array sem nada 
		List<String> nomesAeros = new ArrayList<>(); 	
		
		// Percorrer todos os aeroportos e se o nome for diferente de "Castelo Branco" adicionar o nome do mesmo ao ArrayList e ordená-los de forma crescente
		for(Aeroporto a : c.getAeroportos()) {
			if (!a.getNomeAeroporto().equals("Castelo Branco")) {
				nomesAeros.add(a.getNomeAeroporto());
				nomesAeros.sort(null);
			}
		}
		
		setupJanela( nomesAeros );
	}
	
	/** método chamado quando se pressiona o botão de pesquisar voo
	 * @param nomeOrigem nome do aeroporto de origem escolhido ou null se nenhum foi escolhido
	 * @param nomeDestino nome do aeroporto de destino escolhido ou null se nenhum foi escolhido
	 * @param dataPartida a data de partida escolhida, ou null se nenhuma foi escolhida
	 * @param numPassageiros o número de passageiros que vai viajar
	 * @param classe a classe de conforto que é pretendida
	 * @param tipoOrdenacao o tipo de ordenação a usar
	 */
	private void pesquisarVoos( String nomeOrigem, String nomeDestino, LocalDate dataPartida,
			                    int numPassageiros, ClasseConforto classe, TipoOrdenacao tipoOrdenacao ) {
		
		// Criar um ArrayList e colocar lá todos os voos que atendam aos filtros colocados pelo utilizador
		ArrayList<Voo> voosSelecionados = new ArrayList<>(estAir.getVoos().stream()
			 	.filter(v -> nomeOrigem == null || Aeroporto.getNomeCompleto(v.getAeroportoOrigem()).equals(nomeOrigem))
		        .filter(v -> nomeDestino == null || Aeroporto.getNomeCompleto(v.getAeroportoDestino()).equals(nomeDestino))
		        .filter(v -> dataPartida == null || dataPartida.equals(v.adicionarDiaData()))
		        .sorted((v1, v2) -> { return ordenar(classe, tipoOrdenacao, v1, v2); })
		        .collect(Collectors.toList()));
	 
		// Para cada voo selecionado listar o voo
		 for (Voo v : voosSelecionados) {
			 
			 String tipoReserva = classe.getReservaAssociadas()[0];
			 long preco = ESTAirways.custoTotalReserva(v.getCodigoVoo(), v.getAeroportoOrigem(), v.getAeroportoDestino(), classe, tipoReserva);
			 long precoTotal = preco * numPassageiros;
			 
	        String nomeDestinoAtual = Aeroporto.getNomeCompleto(v.getAeroportoDestino());
	        String nomeOrigemAtual = Aeroporto.getNomeCompleto(v.getAeroportoOrigem());
			 
		    listarVoo(v.getCodigoVoo(), nomeOrigemAtual, nomeDestinoAtual, ESTAirways.getDataHoraPartida(v), precoTotal, classe, numPassageiros);
		 }
	}
	
	// Ordenar os voos pelo preco (crescente ou decrescente) e pela data (crescente ou decrescente) 
	private int ordenar(ClasseConforto classe, TipoOrdenacao tipoOrdenacao, Voo v1, Voo v2) {
		switch (tipoOrdenacao) {
		    case ORDENA_PRECO_CRESCENTE:
		        return Long.compare(ESTAirways.custoTotalReserva(v1.getCodigoVoo(), v1.getAeroportoOrigem(), v1.getAeroportoDestino(), classe, classe.getReservaAssociadas()[0]), 
		        		ESTAirways.custoTotalReserva(v2.getCodigoVoo(), v2.getAeroportoOrigem(), v2.getAeroportoDestino(), classe,classe.getReservaAssociadas()[0]));
		    case ORDENA_PRECO_DECRESCENTE:
		        return Long.compare(ESTAirways.custoTotalReserva(v2.getCodigoVoo(), v2.getAeroportoOrigem(), v2.getAeroportoDestino(), classe, classe.getReservaAssociadas()[0]), 
		        		ESTAirways.custoTotalReserva(v1.getCodigoVoo(), v1.getAeroportoOrigem(), v1.getAeroportoDestino(), classe, classe.getReservaAssociadas()[0]));
		    case ORDENA_DATA_CRESCENTE:
		        LocalDateTime dataHora1 = LocalDateTime.of(v1.adicionarDiaData(), v1.getHora());
		        LocalDateTime dataHora2 = LocalDateTime.of(v2.adicionarDiaData(), v2.getHora());
		        return dataHora1.compareTo(dataHora2);
		    case ORDENA_DATA_DECRESCENTE:
		        dataHora1 = LocalDateTime.of(v1.adicionarDiaData(), v1.getHora());
		        dataHora2 = LocalDateTime.of(v2.adicionarDiaData(), v2.getHora());
		        return dataHora2.compareTo(dataHora1);
		    default:
		        return 0;
		}
	}

	/** método que é chamado quando que se pressiona no botão de reservar um voo
	 * @param codigo código do voo selecionado
	 * @param classe a classe de conforto selecionada
	 * @param numPassageiros o número de passageiros a viajar
	 */
	private void reservarVoo( String codigo, ClasseConforto classe, int numPassageiros ) {
		VooReservarDialog view = new VooReservarDialog(this, classe, numPassageiros );
		
		// Inicializar a variável voo
		voo = ESTAirways.getVooPorCodigo(codigo);
		
		Aeroporto aeroOrigem = estAir.getAeroportoPorCodigo(voo.getAeroportoOrigem());
		Aeroporto aeroDestino = estAir.getAeroportoPorCodigo(voo.getAeroportoDestino());
		
		// Este ciclo percorrer todas as classes 
        for (ClasseConforto c : ClasseConforto.values()) {
        	
        	// O c.ordinal() enumera cada classe de conforto (0 -> STANDARD, 1 -> CONFORT, 2 -> DELUXE)
        	// Ex (Classe CONFORT):
        		// 1 >= 0 FALSE, ou seja, na classe CONFORT não vai aparecer as reservas ECONOMICA e BASIC (Reservas da classe STANDARD)
        		// 1 >= 1 e 2: TRUE, ou seja, na classe CONFORT aparecem as reservas BUSINESS (Reserva classe CONFORT) e EXECUTIVE (Reserva classe DELUXE )
            if (c.ordinal() >= classe.ordinal()) {
                
            	// Para cada reserva associada à classe, imprime os dados da reserva
            	for (String tipoReserva : c.getReservaAssociadas()) 
                    view.addTipoReserva(tipoReserva, ESTAirways.custoTotalReserva(codigo, voo.getAeroportoOrigem(), voo.getAeroportoDestino(), c, tipoReserva));
            }
                        
        }
		
        // Obter o tipo de reserva
		String tipoReserva = view.reservarVoo();
		if( tipoReserva == null ) 
			return;
		
		long custoReserva = ESTAirways.custoTotalReserva(codigo, voo.getAeroportoOrigem(),  voo.getAeroportoDestino(), classe, tipoReserva);
		
		String idr = ESTAirways.gerarReservaId();
		JOptionPane.showMessageDialog( this, "A sua reserva tem o ID " + idr );
		
		// Para cada classe de conforto, verificar se a classe contém a variável tipoReserva, se sim inicializar a classe
		for(ClasseConforto c : ClasseConforto.values()) {
			if(Arrays.asList(c.getReservaAssociadas()).contains(tipoReserva)) {
				classe = c;
			}
		}
		
		// Criar a reserva, adicionar os passageiros, adicionar a reserva e remover o voo
		Reserva reserva = criarReserva(idr, tipoReserva, aeroOrigem, aeroDestino, custoReserva, classe);
		adicionarPassageiros(numPassageiros, view, reserva);
			
		estAir.addReserva(reserva);
	}

	// Método para adicionar os passageiros
	private void adicionarPassageiros(int numPassageiros, VooReservarDialog view, Reserva reserva) {
	    // Obtém os nomes dos passageiros da view
	    List<String> nomes = view.getNomes();

	    // Percorre os nomes e adiciona os passageiros
	    for (String nome : nomes) {
	        Passageiro passageiro = new Passageiro(numPassageiros++, nome, null, 0);
	        reserva.addPassageiro(passageiro);
	    }
	}


	// Método para criar a reserva para cada tipo de reserva existente
	private Reserva criarReserva(String idr, String tipoReserva, Aeroporto aeroOrigem, Aeroporto aeroDestino, long custoReserva, ClasseConforto classe) {
		switch(tipoReserva) {
			case ESTAirways.ECONOMICA:
				return new ReservaEconomica(idr, tipoReserva, voo, aeroOrigem, aeroDestino, custoReserva, classe);
			case ESTAirways.BASIC:
				return new ReservaBasic(idr, tipoReserva, voo, aeroOrigem, aeroDestino, custoReserva, classe);
			case ESTAirways.BUSINESS:
				return new ReservaBusiness(idr, tipoReserva, voo, aeroOrigem, aeroDestino, custoReserva, classe);
			case ESTAirways.EXECUTIVE:
				return new ReservaExecutive(idr, tipoReserva, voo, aeroOrigem, aeroDestino, custoReserva, classe);
		}
		return null;
	}
	
	/** chamar este método para adicionar a informação de um voo à lista dos voos a mostrar
	 * @param codigo o código do voo
	 * @param origem o nome do aeroporto de origem
	 * @param destino o nome do aeroporto de destino
	 * @param partida a data de partida
	 * @param custo o custo total do voo
	 * @param classe a classe de conforto escolhida
	 * @param numPassageiros o número de passageiros a viajar
	 */
	private void listarVoo( String codigo, String origem, String destino, LocalDateTime partida, long custo, ClasseConforto classe, int numPassageiros) {		
		String data = partida.format(formatterData) + " " + partida.format( formatterHora );		
		VooInfo vi = new VooInfo(codigo, origem, destino, data, custo, numPassageiros, classe );
		painelLista.add( vi.renderer.getComponent() );
	}

	/** enumeração que armazena as várias hipóteses de ordenação,
	 * incluindo informação de como devem aparecer no écran
	 */
	private enum TipoOrdenacao {
		ORDENA_PRECO_CRESCENTE ("Preço \u02C4"),
		ORDENA_PRECO_DECRESCENTE("Preço \u02C5"),
		ORDENA_DATA_CRESCENTE("Partida \u02C4"),
		ORDENA_DATA_DECRESCENTE("Partida \u02C5");
		
		String nomeSimples;
		
		TipoOrdenacao( String nomeSimples ){
			this.nomeSimples = nomeSimples;
		}
		
		@Override
		public String toString() {
			return nomeSimples;
		}
	} 
	
	/** prepara a janela para ser apresentada
	 * @param nomesAeroportos lista com os nomes dos aeroprotos a aparecerem nas janelas de escolha
	 */
	private void setupJanela(List<String> nomesAeroportos) {
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		JPanel escolhas = setupPainelEscolha( nomesAeroportos );
		JPanel tabelas = setupPainelVoos( );
		getContentPane().add( escolhas, BorderLayout.NORTH );
		getContentPane().add( tabelas, BorderLayout.CENTER );
		setSize( 600, 700 ); // Mexi nisto
	}

	/** Prepara a janela onde aparecem os critérios de escolha
	 * @param nomesPortos nome dos portos a aparecer na lista de escolhas
	 * @return o painel com os controlos criados
	 */
	private JPanel setupPainelEscolha(List<String> nomesPortos) {
		SpringLayout layout = new SpringLayout();
		JPanel painel = new JPanel( layout );
		painel.setBorder( BorderFactory.createTitledBorder("Critérios de seleção"));
		
		Vector<String> nomes = new Vector<String>();
		nomes.add( "Qualquer aeroporto");
		nomes.addAll( nomesPortos );
		nomes.remove( "Castelo Branco" );
		DefaultComboBoxModel<String> modeloTodos = new DefaultComboBoxModel<String>( nomes );
		Vector<String> ctbNome = new Vector<String>();

		ctbNome.add( "Castelo Branco" );
		DefaultComboBoxModel<String> modeloCB = new DefaultComboBoxModel<String>( ctbNome );
		
		JLabel deLbl = new JLabel( "De");
		painel.add( deLbl );
		JComboBox<String> origensBox = new JComboBox<String>( modeloCB );
		painel.add( origensBox );
		
		JLabel paraLbl = new JLabel( "Para");
		painel.add( paraLbl );
		JComboBox<String> destinosBox = new JComboBox<String>( modeloTodos );
		painel.add( destinosBox );
		
		JButton idaVoltaBt = new JButton("«»"); 
		idaVoltaBt.addActionListener( a -> {	
			ComboBoxModel<String> mo = origensBox.getModel();
			ComboBoxModel<String> md = destinosBox.getModel();
			origensBox.setModel( md );
			destinosBox.setModel( mo );
		});
		painel.add( idaVoltaBt );
		JLabel partidaLbl = new JLabel("Partida:");
		painel.add( partidaLbl );
		
		String dias[] = new String[ 8 ];
		dias[0] = "Qualquer dia";
		for( int i=1; i < dias.length; i++ )
			dias[i] = LocalDate.now().plusDays(i-1).format( formatterData );
		
		JComboBox<String> diasBox = new JComboBox<String>( dias ); 
		painel.add( diasBox );
		
		JButton pesquisaBt = new JButton("Pesquisar");
		painel.add( pesquisaBt );
		
		JLabel numLbl =new JLabel( "Viajantes" );
		painel.add( numLbl );
		Integer opcoesNum[] = new Integer[ESTAirways.MAX_PASSAGEIROS_RESERVA];
		for( int i = 0; i < opcoesNum.length; )	opcoesNum[i] = ++i;
		JComboBox<Integer> numBox = new JComboBox<Integer>( opcoesNum ); 
		painel.add( numBox );

		JLabel classeLbl =new JLabel( "Conforto" );
		painel.add( classeLbl );
		String opcoesClasse[] = {"Standard", "Comfort", "Deluxe"};
		JComboBox<String> classeBox = new JComboBox<String>( opcoesClasse ); 
		painel.add( classeBox );
		
		JLabel ordenarLbl = new JLabel("Ordenar por");
		painel.add( ordenarLbl );
		JComboBox<TipoOrdenacao> ordenarBox = new JComboBox<TipoOrdenacao>( TipoOrdenacao.values() ); 
		painel.add( ordenarBox );
		
		
		pesquisaBt.addActionListener( e -> {
			painelLista.removeAll();
			LocalDate dataPartida = null;
			String dstr = (String)diasBox.getSelectedItem();
			if( dstr != "Qualquer dia" ) {
				dataPartida = LocalDate.parse(dstr, formatterData);
			}
			String aeroOrigem = (String)origensBox.getSelectedItem();
			String aeroDestino = (String)destinosBox.getSelectedItem();
			pesquisarVoos( aeroOrigem.equals("Qualquer aeroporto")? null: aeroOrigem,
					       aeroDestino.equals("Qualquer aeroporto")? null: aeroDestino,
					       dataPartida, (Integer)numBox.getSelectedItem(),
					       ClasseConforto.values( )[classeBox.getSelectedIndex()],
					       (TipoOrdenacao)ordenarBox.getSelectedItem() );
		});

		layout.putConstraint( SpringLayout.NORTH, deLbl, 10, SpringLayout.NORTH, painel);
		layout.putConstraint( SpringLayout.WEST, deLbl, 10, SpringLayout.WEST, painel);

		layout.putConstraint( SpringLayout.BASELINE, origensBox, 0, SpringLayout.BASELINE, deLbl );
		layout.putConstraint( SpringLayout.WEST, origensBox, 5, SpringLayout.EAST, deLbl );
		layout.putConstraint( SpringLayout.EAST, origensBox, -5, SpringLayout.WEST, idaVoltaBt );

		layout.putConstraint( SpringLayout.BASELINE, idaVoltaBt, 0, SpringLayout.BASELINE, deLbl );
		layout.putConstraint( SpringLayout.HORIZONTAL_CENTER, idaVoltaBt, 0, SpringLayout.HORIZONTAL_CENTER, painel );

		layout.putConstraint( SpringLayout.BASELINE, paraLbl, 0, SpringLayout.BASELINE, deLbl);
		layout.putConstraint( SpringLayout.EAST, paraLbl, -10, SpringLayout.EAST, painel );

		layout.putConstraint( SpringLayout.BASELINE, destinosBox, 0, SpringLayout.BASELINE, deLbl );
		layout.putConstraint( SpringLayout.WEST, destinosBox, 5, SpringLayout.EAST, idaVoltaBt );
		layout.putConstraint( SpringLayout.EAST, destinosBox, -5, SpringLayout.WEST, paraLbl );
		
		layout.putConstraint( SpringLayout.NORTH, partidaLbl, 15, SpringLayout.SOUTH, deLbl );
		layout.putConstraint( SpringLayout.WEST, partidaLbl, 0, SpringLayout.WEST, deLbl );

		layout.putConstraint( SpringLayout.BASELINE, diasBox, 0, SpringLayout.BASELINE, partidaLbl );
		layout.putConstraint( SpringLayout.WEST, diasBox, 5, SpringLayout.EAST, partidaLbl );

		layout.putConstraint( SpringLayout.BASELINE, numLbl, 0, SpringLayout.BASELINE, partidaLbl );
		layout.putConstraint( SpringLayout.WEST, numLbl, 10, SpringLayout.EAST, diasBox );

		layout.putConstraint( SpringLayout.BASELINE, numBox, 0, SpringLayout.BASELINE, numLbl );
		layout.putConstraint( SpringLayout.WEST, numBox, 5, SpringLayout.EAST, numLbl );

		layout.putConstraint( SpringLayout.BASELINE, classeLbl, 0, SpringLayout.BASELINE, numBox );
		layout.putConstraint( SpringLayout.WEST, classeLbl, 10, SpringLayout.EAST, numBox );
		
		layout.putConstraint( SpringLayout.BASELINE, classeBox, 0, SpringLayout.BASELINE, classeLbl );
		layout.putConstraint( SpringLayout.WEST, classeBox, 5, SpringLayout.EAST, classeLbl );

		layout.putConstraint( SpringLayout.WEST, ordenarLbl, 0, SpringLayout.WEST, deLbl );
		layout.putConstraint( SpringLayout.NORTH, ordenarLbl, 15, SpringLayout.SOUTH, partidaLbl );

		layout.putConstraint( SpringLayout.WEST, ordenarBox, 5, SpringLayout.EAST, ordenarLbl );
		layout.putConstraint( SpringLayout.BASELINE, ordenarBox, 0, SpringLayout.BASELINE, ordenarLbl );
		
		layout.putConstraint( SpringLayout.EAST, pesquisaBt, -5, SpringLayout.EAST, painel );
		layout.putConstraint( SpringLayout.BASELINE, pesquisaBt, 0, SpringLayout.BASELINE, diasBox );

		layout.putConstraint( SpringLayout.SOUTH, painel, 10, SpringLayout.SOUTH, ordenarLbl );
		return painel;
	}
	
	/** constroi o painel onde se apresentam os voos
	 * @return o painel onde se apresentam os voos
	 */
	private JPanel setupPainelVoos() {
		JPanel painel = new JPanel( new BorderLayout() );

		painelLista = new PainelListador();
		JScrollPane pu = new JScrollPane( painelLista );
		
		pu.setBorder( BorderFactory.createTitledBorder("Voos"));
		painel.add( pu, BorderLayout.CENTER );
		return painel;		
	}
	
	/**
	 * Class que armazena as informações de um voo listado, para as apresentar na respetiva linha
	 */
	// Isto seria melhor declarado como um record, mas como não falamos disso nas aulas...
	private class VooInfo {
		String codigo;
		String partida;
		String origem, destino;
		long custo;
		int numPassageiros;
		ClasseConforto classe;
		
		VooComponent renderer;
		
		public VooInfo(String codigo, String origem, String destino, String partida, long custo, int numP, ClasseConforto classe ) {
			this.codigo = codigo;
			this.partida = partida;
			this.origem = origem;
			this.destino = destino;
			this.custo = custo;
			this.numPassageiros = numP;
			this.classe = classe;
			renderer = new VooComponent( this );
		}
	}

	// variáveis da interface que estão aqui para não "atrapalhar"
	static Font nomeFont = new Font("ROMAN", Font.BOLD, 16 );
	static Font mediaFontBold = new Font("ROMAN", Font.BOLD, 13 );
	private static Font mediaFont = new Font("ROMAN", Font.PLAIN, 13 );
	private PainelListador painelLista;

	/** Classe que representa uma linha na lista de voos
	 */
	private class VooComponent {
		JPanel painelCruise;
		VooInfo currentInfo;
//		static int count = 0;
//		static Color par = new Color( 210, 210, 210 );
//		static Color impar = new Color( 170, 170, 170 );

		public VooComponent( VooInfo ci ) {
			
			currentInfo = ci;
			SpringLayout layout = new SpringLayout();
			painelCruise = new JPanel( layout ) {
				protected void paintComponent(java.awt.Graphics g) {
					super.paintComponent(g);
					g.setColor( Color.DARK_GRAY );
					Rectangle r = getBounds();
					g.drawRoundRect( 2, 2, r.width-10, r.height-10, 20, 20 );
					g.setFont( nomeFont );
					g.drawString( currentInfo.custo + "€", r.width - 160, r.height/2 + 10);
					g.drawString( currentInfo.origem + "  >> " + currentInfo.destino, 10, 25);
					g.setFont(mediaFontBold);
					g.drawString( currentInfo.partida + "   " + currentInfo.codigo, 10, 50);
					g.setFont(mediaFont);
				};
			};
			//count++;
			//Color cor = count %2 == 0? par: impar;
			//painelCruise.setBackground(cor);
			
			JButton reservarBt = new JButton("Reservar");
			reservarBt.addActionListener( e -> reservarVoo( ci.codigo, ci.classe, ci.numPassageiros ) );
			painelCruise.add( reservarBt );
			painelCruise.setPreferredSize( new Dimension( 400, 70 ) );
			
			layout.putConstraint( SpringLayout.VERTICAL_CENTER, reservarBt, 0, SpringLayout.VERTICAL_CENTER,  painelCruise);
			layout.putConstraint( SpringLayout.EAST, reservarBt, -20, SpringLayout.EAST,  painelCruise);
		}
		
		public Component getComponent() {
			return painelCruise;
		};
	}
}