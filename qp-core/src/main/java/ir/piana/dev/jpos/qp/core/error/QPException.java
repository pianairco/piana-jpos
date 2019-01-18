package ir.piana.dev.jpos.qp.core.error;

public class QPException extends Exception {
    public QPException() {
    }

    public QPException(String message) {
        super(message);
    }

    public QPException(Throwable cause) {
        super(cause);
    }
}
