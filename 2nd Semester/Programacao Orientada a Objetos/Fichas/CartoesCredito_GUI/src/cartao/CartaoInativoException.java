package cartao;

public class CartaoInativoException extends Exception {

	public CartaoInativoException() {
	}

	public CartaoInativoException(String message) {
		super(message);
	}

	public CartaoInativoException(Throwable cause) {
		super(cause);
	}

	public CartaoInativoException(String message, Throwable cause) {
		super(message, cause);
	}

	public CartaoInativoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
