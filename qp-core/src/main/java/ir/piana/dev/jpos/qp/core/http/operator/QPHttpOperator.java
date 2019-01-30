package ir.piana.dev.jpos.qp.core.http.operator;


import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;

import static ir.piana.dev.jpos.qp.core.http.enums.QPDefaultRequestHandlerType.NOT_FOUND;

public interface QPHttpOperator {
    default void service(Request request, Response response)
            throws Exception {
        NOT_FOUND.handle(request, response);
    }
}
