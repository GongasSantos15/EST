package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import estacionamento.ESTacionamento;

public class JanelaParque extends JFrame {
	/** serial id */
	private static final long serialVersionUID = 650033267247382940L;
	
	// o estacionamento a controlar
	private ESTacionamento parque;
	private BarraOcupacao barraOcup;
	private JLabel capacidadeLbl;
	private DefaultTableModel ticketsModel;
	private DefaultTableModel contratosModel;

	public JanelaParque( ESTacionamento p ) {
		// TODO nem todos os parques tem este nome
		setTitle( "ESTParque" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		parque = p;
		setupInterface();
		
		updateBarraOcupacao( );
		updateContratos();
	}
	
	private void updateContratos() {
		// TODO usar esta linha para todos os contratos
		// matrícula e valor a pagar fim mês
		linhaContrato( "AA1234", 0 );
	}
	
	private void updateTickets() {
		// TODO usar esta linha para todos os tickets
		// matrícula, hora entrada, hora pagamento e custo
		linhaTicket( "AA1234", LocalDateTime.now(), LocalDateTime.now(), 10 );
	}

	private void updateBarraOcupacao( ) {
		// TODO atualizar estes valores 
		int lugaresOcupados = parque.lugaresOcupados();
		int capacidade = parque.capacidade();
		
		barraOcup.setValor( lugaresOcupados );
		barraOcup.setMax( capacidade );
		capacidadeLbl.setText( "" + capacidade);
	}

	private void darEntrada(String m) {
		// TODO processar a entrada da viatura
		// mensagens de erro
		JOptionPane.showMessageDialog( this, "Viatura já esta no Parque!", "Viatura Repetida", JOptionPane.ERROR_MESSAGE);
		JOptionPane.showMessageDialog( this, "Parque está cheio!", "Sem espaço livre", JOptionPane.ERROR_MESSAGE);
	}

	private void fazerPagamento(String m) {
		// TODO processar o pagamento
		long custo = 0;
		int opcao = JOptionPane.showConfirmDialog(this, "Pagar " + custo + "€?", "Confirmar pagamento", JOptionPane.YES_NO_OPTION );
		if( opcao == JOptionPane.YES_OPTION ) {		
			// TODO nem todos os parques tem este tempo de retirada
			JOptionPane.showMessageDialog( this, "Cliente tem " + 10 + " minutos para remover a viatura.", "Pagamento efetuado", JOptionPane.PLAIN_MESSAGE);
		}
		// mensagens de erro
		JOptionPane.showMessageDialog( this, "Viatura não está no Parque!", "Matricula Desconhecida", JOptionPane.ERROR_MESSAGE);
		JOptionPane.showMessageDialog( this, "Estacionamento já esta pago!", "Pago", JOptionPane.ERROR_MESSAGE);
	}

	private void darSaida(String m) {
		// TODO processar a saida da viatura
		// mensagens de erro
		JOptionPane.showMessageDialog( this, "Viatura não está no Parque!", "Matrícula desconhecida", JOptionPane.ERROR_MESSAGE);
		JOptionPane.showMessageDialog( this, "Saída não autorizada", "Matrícula desconhecida", JOptionPane.ERROR_MESSAGE);
	}
	

	private boolean eMatriculaValida(String matricula) {
		// TODO verificar se matrícula introduzida é válida
		//      A matrícula não pode ser null, não estar vazia
		//      não pode ser repetida    
		//      NÃO pensar apenas em matrícula portuguesas!!!
		return false;
	}

	private void criarContratoViaEst(String matricula ) {
		// TODO criar o contrato
	}
	
	private void criarContratoResidente(String matricula, long mensal) {
		// TODO criar o contrato
	}
	
	private void setupInterface() {
		JPanel manual = setupPanelManuais(); 
		JPanel contratos = setupPanelContratos();
		JPanel tickets = setupPanelTickets();
		getContentPane().add( manual, BorderLayout.EAST );
		getContentPane().add( contratos, BorderLayout.WEST );
		getContentPane().add( tickets, BorderLayout.CENTER );
		pack();

		// Atualizar a interface de segundo a segundo,
		// isto é muito ineficiente, mas só conseguimos melhorar
		// quando falarmos da Observer
		new Timer( 1000, e -> {
			ticketsModel.setRowCount(0);
			contratosModel.setRowCount(0);
			updateContratos();
			updateTickets();
			updateBarraOcupacao();
		} ).start();
	}

	private JPanel setupPanelManuais() {
		JPanel panel = new JPanel( new GridLayout(0,1) );
		panel.setBorder( BorderFactory.createTitledBorder( "Manual" ));
		JButton entradaBt = new JButton("Entrada");
		entradaBt.addActionListener( e -> {
			String m = JOptionPane.showInputDialog( JanelaParque.this, "Matricula?");
			darEntrada( m );
		});
		JButton pagaBt = new JButton("Pagamento");
		pagaBt.addActionListener( e -> {
			String m = JOptionPane.showInputDialog( JanelaParque.this, "Matricula?");
			fazerPagamento( m );
		});
		JButton saidaBt = new JButton("Saida");
		saidaBt.addActionListener( e -> {
			String m = JOptionPane.showInputDialog( JanelaParque.this, "Matricula?");
			darSaida( m );
		});
		panel.add( entradaBt );
		panel.add( pagaBt );
		panel.add( saidaBt );
		return panel;
	}
	
	private JPanel setupPanelContratos() {
		SpringLayout layout = new SpringLayout();
		JPanel panel = new JPanel( layout );
		panel.setBorder( BorderFactory.createTitledBorder( "Contratos" ));
		panel.setPreferredSize( new Dimension( 200,200 ) );
		
		String []titulos = {"Matrícula", "Mensal" };
//		Object [][]data = { {"14bj87", 100 },
//				            {"89SU01", 120 }, 
//				            {"5426hn", 130 }
//
//		};
		contratosModel = new DefaultTableModel(titulos, 0);
//		contratosModel.addRow( data[0] );
//		contratosModel.addRow( data[1] );
//		contratosModel.addRow( data[2] );
		JTable contratosTbl = new JTable( contratosModel );
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment( SwingConstants.CENTER );
		contratosTbl.setDefaultRenderer( Object.class, renderer );
		contratosTbl.setDefaultEditor( Object.class, null);

		JScrollPane scroll = new JScrollPane( contratosTbl );
		panel.add( scroll );
		
		JButton novoBt = new JButton( "Novo Contrato" );
		novoBt.addActionListener( e -> {
			pedirDadosContrato();
		});
		panel.add( novoBt );

		layout.putConstraint( SpringLayout.NORTH, scroll, 2, SpringLayout.NORTH, panel );
		layout.putConstraint( SpringLayout.WEST, scroll, 2, SpringLayout.WEST, panel );
		layout.putConstraint( SpringLayout.EAST, scroll, -2, SpringLayout.EAST, panel );
		layout.putConstraint( SpringLayout.SOUTH, scroll, -2, SpringLayout.NORTH, novoBt );

		layout.putConstraint( SpringLayout.SOUTH, novoBt, -2, SpringLayout.SOUTH, panel );
		layout.putConstraint( SpringLayout.WEST, novoBt, 0, SpringLayout.WEST, scroll );
		layout.putConstraint( SpringLayout.EAST, novoBt, 0, SpringLayout.EAST, scroll );
		return panel;
	}

	private void linhaContrato(String matricula, long pagamentoMensal) {
		Object data[] = {matricula, pagamentoMensal };
		contratosModel.addRow( data );		
	}

	private void pedirDadosContrato() {
		JPanel p = new JPanel( new GridLayout(0,1) );
		
		JPanel p1 = new JPanel( new FlowLayout( FlowLayout.LEFT, 0, 0));
		p1.add( new JLabel("Matrícula: ") );
		JTextField matri = new JTextField("", 10);
		p1.add( matri );
		p.add( p1 );
		
		ButtonGroup grp = new ButtonGroup();
		JRadioButton via= new JRadioButton("Via EST");
		grp.add( via );
		p.add( via );
		JPanel p2 = new JPanel( new FlowLayout( FlowLayout.LEFT, 0, 0));
		JRadioButton residente = new JRadioButton ("Residente, mensalidade: ");
		grp.add( residente );
		p2.add( residente );
		via.setSelected( true );			
		IntegerTextField mensal = new IntegerTextField( false );
		mensal.setColumns( 10 );
		mensal.setEnabled( false );
		residente.addChangeListener( cl -> mensal.setEnabled( residente.isSelected() ) );
		p2.add(mensal);
		p.add( p2 );
		int res = JOptionPane.showConfirmDialog( this, p, "Tipo de contrato", JOptionPane.OK_CANCEL_OPTION );
		//int res = JOptionPane.showOptionDialog( this, p, "Tipo de contrato", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if( res != JOptionPane.OK_OPTION )
			return;
		String matricula = matri.getText();
		if( !eMatriculaValida( matricula ) ){
			JOptionPane.showMessageDialog( this, "Matrícula inválida", "Erro nos dados", JOptionPane.ERROR_MESSAGE );
			return;
		}
		if( via.isSelected() )
			criarContratoViaEst( matricula );
		else if( residente.isSelected( )  ) {
			if( mensal.getValor() == -1)
				JOptionPane.showMessageDialog( this, "Tem de introduzir uma quantia", "Erro nos dados", JOptionPane.ERROR_MESSAGE );
			else
				criarContratoResidente( matricula, mensal.getValor() );
		}
	}


	private JPanel setupPanelTickets() {
		SpringLayout layout = new SpringLayout();
		JPanel panel = new JPanel( layout );
		panel.setBorder( BorderFactory.createTitledBorder( "Estado do Parque" ));
		panel.setPreferredSize( new Dimension( 500,200 ) );
		
		JLabel zeroLbl = new JLabel( "0" );
		panel.add( zeroLbl );
		
		barraOcup = new BarraOcupacao( 0, 1000 );
		panel.add( barraOcup );
		
		capacidadeLbl = new JLabel( "1000" );
		panel.add( capacidadeLbl );
		
		String []titulos = {"Matrícula", "Entrada", "Pagamento", "Custo" };
//		Object [][]data = { {"14bj87", LocalDateTime.now(), LocalDateTime.now(), 100 },
//				            {"89SU01", LocalDateTime.now(), LocalDateTime.now(), 100 }, 
//				            {"5426hn", LocalDateTime.now(), LocalDateTime.now(), 100 }
//				          };
		ticketsModel = new DefaultTableModel(titulos, 0);
//		contratosModel.addRow( data[0] );
//		contratosModel.addRow( data[1] );
//		contratosModel.addRow( data[2] );
		JTable tabelaTickets = new JTable( ticketsModel );
		tabelaTickets.setDefaultRenderer( Object.class, new TicketRendeder());
		tabelaTickets.setDefaultEditor( Object.class, null);
		TableColumnModel tm = tabelaTickets.getColumnModel();
		tm.getColumn(0).setPreferredWidth(50);
		tm.getColumn(1).setPreferredWidth(80);
		tm.getColumn(2).setPreferredWidth(80);
		tm.getColumn(3).setPreferredWidth(50);
		JScrollPane scroll = new JScrollPane( tabelaTickets );
		panel.add( scroll );
		
		layout.putConstraint( SpringLayout.NORTH, zeroLbl, 2, SpringLayout.NORTH, panel );
		layout.putConstraint( SpringLayout.WEST, zeroLbl, 2, SpringLayout.WEST, panel );
		layout.putConstraint( SpringLayout.NORTH, capacidadeLbl, 0, SpringLayout.NORTH, zeroLbl );
		layout.putConstraint( SpringLayout.EAST, capacidadeLbl, 2, SpringLayout.WEST, panel );
		layout.putConstraint( SpringLayout.NORTH, barraOcup, 0, SpringLayout.NORTH, zeroLbl );
		layout.putConstraint( SpringLayout.WEST, barraOcup, 2, SpringLayout.EAST, zeroLbl );
		layout.putConstraint( SpringLayout.EAST, barraOcup, 2, SpringLayout.WEST, capacidadeLbl );

		layout.putConstraint( SpringLayout.NORTH, scroll, 5, SpringLayout.SOUTH, zeroLbl );
		layout.putConstraint( SpringLayout.WEST, scroll, 0, SpringLayout.WEST, zeroLbl );
		layout.putConstraint( SpringLayout.SOUTH, scroll, 2, SpringLayout.SOUTH, panel );
		layout.putConstraint( SpringLayout.EAST, scroll, 2, SpringLayout.EAST, panel );
		layout.putConstraint( SpringLayout.EAST, capacidadeLbl, 0, SpringLayout.EAST, scroll );

		return panel;
	}
	
	private void linhaTicket(String matricula, LocalDateTime in, LocalDateTime pago, long custo ) {
		Object data[] = {matricula, in, pago, custo };
		ticketsModel.addRow( data );		
	}
	
	private static class TicketRendeder extends DefaultTableCellRenderer {		
		/** serial id */
		private static final long serialVersionUID = -7435374727539963646L;

		public TicketRendeder() {
			setHorizontalAlignment( JLabel.CENTER );
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			if( column == 0 )
				setText( (String)value );
			else if( column == 1 || column == 2) {
				LocalDateTime t = (LocalDateTime) value;
				if( t != null )
					setText( String.format("%2d/%2d/%4d - %02d:%02d:%02d", t.getDayOfMonth(), t.getMonthValue(), t.getYear(),
					  	                                                   t.getHour(), t.getMinute(), t.getSecond() ) );
				else
					setText( "---" );
			}
			else 
				setText( ""+(long)value );
			
			return this;
		}
		
	}
	
	private static class BarraOcupacao extends JLabel {
		/** serial id */
		private static final long serialVersionUID = -1768678354835112332L;

		public BarraOcupacao( int valor, int max) {
			setValor(valor);
			setMax( max );
			setOpaque( false );
			setHorizontalAlignment( JLabel.CENTER );
			setBorder( BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		}

		private int max, valor;
		
		public void setMax(int max) {
			this.max = max;
			repaint();
		}
		
		public void setValor(int valor) {
			this.valor = valor;
			setText( "" + valor );
			repaint();
		}
		
		private static Color darkRed = new Color( 150, 50, 50 );
		private static Color darkGreen = new Color( 50, 150, 50 );
		private static Color darkYellow = new Color( 150, 150, 50 );
		
		@Override
		protected void paintComponent(Graphics g) {
			Rectangle bounds = getBounds();
			// 70% ocupado - amarelo, 90% vermelho
			float percentagem = (float)valor / max;
			g.setColor( percentagem < 0.7? Color.GREEN:
				        percentagem < 0.9? Color.YELLOW: Color.red );
			int x = (int)(percentagem*bounds.width);
			g.fillRect(0, 0, x, bounds.height );
			g.setColor( percentagem < 0.7? darkGreen:
		                percentagem < 0.9? darkYellow: darkRed );
			g.fillRect(x, 0, bounds.width-x, bounds.height );
			super.paintComponent(g);
		}
	}
}
