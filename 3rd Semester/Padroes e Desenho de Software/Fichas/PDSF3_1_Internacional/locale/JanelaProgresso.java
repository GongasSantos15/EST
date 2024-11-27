package locale;

import javax.swing.JPanel;
import java.awt.Frame;
import javax.swing.JDialog;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class JanelaProgresso extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JProgressBar jProgressBar = null;

	/**
	 * @param owner
	 */
	public JanelaProgresso(Frame owner) {
		super(owner);
		setTitle("Lendo ficheiros");
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(288, 112);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setContentPane(getJContentPane());
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJProgressBar(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jProgressBar	
	 * 	
	 * @return javax.swing.JProgressBar	
	 */
	private JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(15, 16, 244, 41));
			jProgressBar.setIndeterminate( true );
		}
		return jProgressBar;
	}

	@Override
	public void setVisible(boolean b) {		
		if( !b ) {
			super.setVisible(b);
			return;
		}
		Timer t = new Timer(1500, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JanelaProgresso.this.setVisible( false );
			}
		});
		t.start();
		super.setVisible(b);
	}
	
	
	

}  //  @jve:decl-index=0:visual-constraint="10,10"
