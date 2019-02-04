package ir.piana.dev.jpos.qp.ext.http.module;

import ir.piana.dev.jpos.qp.core.http.enums.HttpMediaType;
import org.glassfish.grizzly.http.util.HttpStatus;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
public class QPHttpResponse<T> {
    HttpStatus httpStatus;
    T entity;
    HttpMediaType mediaType;
    String charset;

    QPHttpResponse() {
    }

    QPHttpResponse(
            HttpStatus httpStatus,
            T entity,
            HttpMediaType mediaType,
            String charset) {
        this.httpStatus = httpStatus;
        this.entity = entity;
        this.mediaType = mediaType;
        this.charset = charset;
    }
}
