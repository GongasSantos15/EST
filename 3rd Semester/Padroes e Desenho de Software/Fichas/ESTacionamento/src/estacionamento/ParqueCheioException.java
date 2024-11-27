package estacionamento;

public class ParqueCheioException extends Exception {

	public ParqueCheioException() {
	}

	public ParqueCheioException(String message) {
		super(message);
	}

	public ParqueCheioException(Throwable cause) {
		super(cause);
	}

	public ParqueCheioException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParqueCheioException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
