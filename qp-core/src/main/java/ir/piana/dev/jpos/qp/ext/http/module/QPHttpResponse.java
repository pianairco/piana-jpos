package ir.piana.dev.jpos.qp.ext.http.module;

import ir.piana.dev.jpos.qp.core.http.HttpMediaType;
import org.glassfish.grizzly.http.util.HttpStatus;

import java.nio.charset.Charset;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
public class QPHttpResponse<T> {
    HttpStatus httpStatus;
    T entity;
    HttpMediaType mediaType;
    String charset;
}
