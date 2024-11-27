package contrato;

public class PagamentoTicketException extends RuntimeException {

	public PagamentoTicketException() {
	}

	public PagamentoTicketException(String message) {
		super(message);
	}

	public PagamentoTicketException(Throwable cause) {
		super(cause);
	}

	public PagamentoTicketException(String message, Throwable cause) {
		super(message, cause);
	}

	public PagamentoTicketException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
