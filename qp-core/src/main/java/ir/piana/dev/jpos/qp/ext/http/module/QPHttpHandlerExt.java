package ir.piana.dev.jpos.qp.ext.http.module;

import ir.piana.dev.jpos.qp.core.error.QPHttpResponseException;
import org.glassfish.grizzly.http.util.HttpStatus;

public interface QPHttpHandlerExt {
    default QPHttpResponse get(QPHttpRequest request)
            throws QPHttpResponseException {
        QPHttpResponseBuilder.status(HttpStatus.NOT_FOUND_404);
        return null;
    }

    default QPHttpResponse post(QPHttpRequest request)
            throws QPHttpResponseException {
        QPHttpResponseBuilder.status(HttpStatus.NOT_FOUND_404);
        return null;
    }

    default QPHttpResponse put(QPHttpRequest request)
            throws QPHttpResponseException {
        QPHttpResponseBuilder.status(HttpStatus.NOT_FOUND_404);
        return null;
    }

    default QPHttpResponse delete(QPHttpRequest request)
            throws QPHttpResponseException {
        QPHttpResponseBuilder.status(HttpStatus.NOT_FOUND_404);
        return null;
    }

    default QPHttpResponse head(QPHttpRequest request)
            throws QPHttpResponseException {
        QPHttpResponseBuilder.status(HttpStatus.NOT_FOUND_404);
        return null;
    }

    default QPHttpResponse options(QPHttpRequest request)
            throws QPHttpResponseException {
        QPHttpResponseBuilder.status(HttpStatus.NOT_FOUND_404);
        return null;
    }

    default QPHttpResponse trace(QPHttpRequest request)
            throws QPHttpResponseException {
        QPHttpResponseBuilder.status(HttpStatus.NOT_FOUND_404);
        return null;
    }
}
