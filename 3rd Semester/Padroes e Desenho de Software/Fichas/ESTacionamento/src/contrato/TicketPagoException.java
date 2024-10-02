package contrato;

public class TicketPagoException extends RuntimeException {

	public TicketPagoException() {
	}

	public TicketPagoException(String message) {
		super(message);
	}

	public TicketPagoException(Throwable cause) {
		super(cause);
	}

	public TicketPagoException(String message, Throwable cause) {
		super(message, cause);
	}

	public TicketPagoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
