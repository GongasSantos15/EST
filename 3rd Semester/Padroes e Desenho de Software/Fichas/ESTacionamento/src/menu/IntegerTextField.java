package menu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/** Classe simplificada para ter um Text Field
 *  que só aceita números inteiros positivos, útil
 *  para pedir quantias e números de cartões
 * @author F. Sergio Barbosa
 */
@SuppressWarnings("serial")
class IntegerTextField extends JTextField {

	/** Cria um IntegerTextField que pode ter o focus
	 * (estar automaticamente selecionado) ou não
	 * @param autoFocus se deve estar automaticamente selecionado
	 */
	public IntegerTextField( boolean autoFocus ) {
		super();
		setupFilter();
		if( autoFocus )
			addHierarchyListener( new RequestFocusListener() ); 
	}

	/** Cria um IntegerTextFields com um determinado número de colunas
	 * @param columns números de colunas do text field
	 */
	public IntegerTextField(int columns) {
		super(columns);
		setupFilter();
	}
	
	/** retorna o valor introduzido pelo utilizador no TextField, ou -1 se 
	 * o utilizador tiver cancelado a operação ou não tiver introduzido nada
	 * @return o valor introduzido no TextField, -1 se não houver valor
	 */
	public long getValor() {
		try {
			return Long.parseLong( getText() );
		} catch (NumberFormatException e) {
			return -1;
		}
	}
	
	// criar o filtro responsável por aceitar apenas inteiros
	private void setupFilter() {
		// garantir que só se colocam números no textfield
		((AbstractDocument)this.getDocument()).setDocumentFilter(new DocumentFilter(){
	        Pattern regEx = Pattern.compile("\\d*");
  	        @Override
	        public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws BadLocationException {          
	            Matcher matcher = regEx.matcher(text);
	            if(!matcher.matches()){
	                return;
	            }
	            super.replace(fb, offset, length, text, attrs);
	        }
	    });
	}
}