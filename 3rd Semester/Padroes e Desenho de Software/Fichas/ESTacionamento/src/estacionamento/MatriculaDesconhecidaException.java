package estacionamento;

public class MatriculaDesconhecidaException extends Exception {

	public MatriculaDesconhecidaException() {
	}

	public MatriculaDesconhecidaException(String message) {
		super(message);
	}

	public MatriculaDesconhecidaException(Throwable cause) {
		super(cause);
	}

	public MatriculaDesconhecidaException(String message, Throwable cause) {
		super(message, cause);
	}

	public MatriculaDesconhecidaException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
