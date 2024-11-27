package stock;

public class OutOfStockException extends Exception {

	private static final long serialVersionUID = 1L;

	public OutOfStockException() {
		super();
	}

	public OutOfStockException(String arg0) {
		super(arg0);
	}

	public OutOfStockException(Throwable arg0) {
		super(arg0);
	}

	public OutOfStockException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
