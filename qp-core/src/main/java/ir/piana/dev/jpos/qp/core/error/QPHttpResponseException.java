package ir.piana.dev.jpos.qp.core.error;

import org.glassfish.grizzly.http.util.HttpStatus;

public class QPHttpResponseException extends Exception {
    private HttpStatus httpStatus;

    public QPHttpResponseException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
