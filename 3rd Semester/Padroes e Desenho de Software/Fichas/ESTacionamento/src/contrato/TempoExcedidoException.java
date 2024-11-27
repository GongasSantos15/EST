package contrato;

public class TempoExcedidoException extends Exception {

	public TempoExcedidoException() {
	}

	public TempoExcedidoException(String message) {
		super(message);
	}

	public TempoExcedidoException(Throwable cause) {
		super(cause);
	}

	public TempoExcedidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public TempoExcedidoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
