package ir.piana.dev.jpos.qp.core.http;

import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;

import static ir.piana.dev.jpos.qp.core.http.DefaultRequestHandlerType.NOT_FOUND;

public interface QPHttpOperator extends QPHttpHandler {
    default void get(Request request, Response response)
            throws Exception {
        NOT_FOUND.handle(request, response);
    }

    default void post(Request request, Response response)
            throws Exception {
        NOT_FOUND.handle(request, response);
    }

    default void put(Request request, Response response)
            throws Exception {
        NOT_FOUND.handle(request, response);
    }

    default void delete(Request request, Response response)
            throws Exception {
        NOT_FOUND.handle(request, response);
    }

    default void head(Request request, Response response)
            throws Exception {
        NOT_FOUND.handle(request, response);
    }

    default void options(Request request, Response response)
            throws Exception {
        NOT_FOUND.handle(request, response);
    }

    default void trace(Request request, Response response)
            throws Exception {
        NOT_FOUND.handle(request, response);
    }
}
