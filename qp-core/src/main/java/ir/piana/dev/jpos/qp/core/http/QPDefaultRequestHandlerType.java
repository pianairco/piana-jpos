package ir.piana.dev.jpos.qp.core.http;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;

import java.io.IOException;

public enum QPDefaultRequestHandlerType {
    OK_REQUEST(200, null),
    BAD_REQUEST(400, new BadRequestHandler()),
    UNAUTHORIZED(401, new UnauthorizedHandler()),
    FORBIDDEN(403, new ForbiddenHandler()),
    NOT_FOUND(404, new NotFoundHandler()),
    INTERNAL_ERROR(500, new InternalErrorHandler());

    private int code;
    private QPHttpOperator httpHandler;

    QPDefaultRequestHandlerType(int code, QPHttpOperator httpHandler) {
        this.code = code;
        this.httpHandler = httpHandler;
    }

    public void handle(
            Request request,
            Response response)
            throws Exception {
        httpHandler.service(request, response);
    }

    public static QPDefaultRequestHandlerType fromCode(int code) {
        for (QPDefaultRequestHandlerType defaultRequestHandlerType :
                QPDefaultRequestHandlerType.values()) {
            if(defaultRequestHandlerType.code == code)
                return defaultRequestHandlerType;
        }
        return QPDefaultRequestHandlerType.BAD_REQUEST;
    }

    private static final class BadRequestHandler
            extends HttpHandler implements QPHttpOperator {
        @Override
        public void service(Request request, Response response)
                throws Exception {
            try {
                response.setStatus(400);
                response.getWriter().write(
                        "bad request!");
                response.getWriter().flush();
                response.resume();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static final class ForbiddenHandler
            extends HttpHandler implements QPHttpOperator {
        @Override
        public void service(Request request, Response response) throws Exception {
            try {
                response.setStatus(403);
                response.getWriter().write(
                        "forbidden!");
                response.getWriter().flush();
                response.resume();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static final class InternalErrorHandler
            extends HttpHandler implements QPHttpOperator {
        @Override
        public void service(Request request, Response response) throws Exception {
            try {
                response.setStatus(500);
                response.getWriter().write(
                        "internal server error!");
                response.getWriter().flush();
                response.resume();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static final class NotFoundHandler
            extends HttpHandler implements QPHttpOperator {
        @Override
        public void service(Request request, Response response) throws Exception {
            try {
                response.setStatus(404);
                response.getWriter().write(
                        "not founded!");
                response.getWriter().flush();
                response.resume();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static final class UnauthorizedHandler
            extends HttpHandler implements QPHttpOperator {
        @Override
        public void service(Request request, Response response) throws Exception {
            try {
                response.setStatus(401);
                response.getWriter().write(
                        "unauthorized!");
                response.getWriter().flush();
                response.resume();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
