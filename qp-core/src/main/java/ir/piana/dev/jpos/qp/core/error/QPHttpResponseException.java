package ir.piana.dev.jpos.qp.core.error;

import ir.piana.dev.jpos.qp.core.http.enums.QPDefaultRequestHandlerType;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import org.glassfish.grizzly.http.util.HttpStatus;

public class QPHttpResponseException extends Exception {
    private HttpStatus httpStatus;
    private QPDefaultRequestHandlerType requestHandlerType;

    public QPHttpResponseException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public QPHttpResponseException(
            QPDefaultRequestHandlerType requestHandlerType) {
        this.requestHandlerType = requestHandlerType;
    }

    public void sendResponse(Request request, Response response)
            throws Exception {
        requestHandlerType.handle(request, response);
    }
}
