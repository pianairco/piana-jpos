package ir.piana.dev.jpos.qp.ext.http.module;

import ir.piana.dev.jpos.qp.core.error.QPHttpResponseException;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.util.HttpStatus;

public interface QPHttpHandlerExt {
    default QPHttpResponse get(Request request)
            throws QPHttpResponseException {
        QPHttpResponseBuilder.status(HttpStatus.NOT_FOUND_404);
        return null;
    }

    default QPHttpResponse post(Request request)
            throws QPHttpResponseException {
        QPHttpResponseBuilder.status(HttpStatus.NOT_FOUND_404);
        return null;
    }

    default QPHttpResponse put(Request request)
            throws QPHttpResponseException {
        QPHttpResponseBuilder.status(HttpStatus.NOT_FOUND_404);
        return null;
    }

    default QPHttpResponse delete(Request request)
            throws QPHttpResponseException {
        QPHttpResponseBuilder.status(HttpStatus.NOT_FOUND_404);
        return null;
    }

    default QPHttpResponse head(Request request)
            throws QPHttpResponseException {
        QPHttpResponseBuilder.status(HttpStatus.NOT_FOUND_404);
        return null;
    }

    default QPHttpResponse options(Request request)
            throws QPHttpResponseException {
        QPHttpResponseBuilder.status(HttpStatus.NOT_FOUND_404);
        return null;
    }

    default QPHttpResponse trace(Request request)
            throws QPHttpResponseException {
        QPHttpResponseBuilder.status(HttpStatus.NOT_FOUND_404);
        return null;
    }
}
