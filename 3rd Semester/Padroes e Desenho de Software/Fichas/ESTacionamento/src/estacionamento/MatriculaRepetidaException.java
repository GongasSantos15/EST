package estacionamento;

public class MatriculaRepetidaException extends Exception {

	public MatriculaRepetidaException() {
	}

	public MatriculaRepetidaException(String message) {
		super(message);
	}

	public MatriculaRepetidaException(Throwable cause) {
		super(cause);
	}

	public MatriculaRepetidaException(String message, Throwable cause) {
		super(message, cause);
	}

	public MatriculaRepetidaException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
