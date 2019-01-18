package ir.piana.dev.jpos.qp.modules.sample;

import ir.piana.dev.jpos.qp.core.http.QPHttpHandler;
import ir.piana.dev.jpos.qp.core.http.QPHttpHandlerBase;
import ir.piana.dev.jpos.qp.core.http.QPHttpOperator;
import ir.piana.dev.jpos.qp.core.http.QPHttpOperatorBase;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;

import javax.management.*;
import java.io.IOException;

public class SampleHandler extends QPHttpOperatorBase {
    @Override
    public void get(Request request, Response response) {
        try {
            response.getWriter().write(
                    "GET: HelloToYou!");
            response.getWriter().flush();
            response.resume();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
