package ir.piana.dev.jpos.qp.ext.http.module;

import ir.piana.dev.jpos.qp.core.http.HttpMediaType;
import ir.piana.dev.jpos.qp.core.http.HttpMethodType;
import org.glassfish.grizzly.http.util.HttpStatus;

import java.util.List;
import java.util.Map;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
public class QPHttpRequest<T> {
    HttpMethodType httpMethodType;
    String body;
    HttpMediaType contentType;
    Map<String, List<String>> queryParams;
    Map<String, List<String>> headers;
}
