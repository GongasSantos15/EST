package stock;

public class InvalidIDException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidIDException() {
		
	}

	public InvalidIDException(String arg0) {
		super(arg0);
		
	}

	public InvalidIDException(Throwable arg0) {
		super(arg0);
		
	}

	public InvalidIDException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

}
