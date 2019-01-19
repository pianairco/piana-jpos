package ir.piana.dev.jpos.qp.modules.sample;

import ir.piana.dev.jpos.qp.core.error.QPHttpResponseException;
import ir.piana.dev.jpos.qp.core.http.HttpMediaType;
import ir.piana.dev.jpos.qp.ext.http.module.QPHttpHandlerExt;
import ir.piana.dev.jpos.qp.ext.http.module.QPHttpResponse;
import ir.piana.dev.jpos.qp.ext.http.module.QPHttpResponseBuilder;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.util.HttpStatus;

public class SampleHandler implements QPHttpHandlerExt {
    @Override
    public QPHttpResponse get(Request request)
            throws QPHttpResponseException {
        return QPHttpResponseBuilder.status(HttpStatus.OK_200)
                .entity(new SampleModel("ali", 13))
                .mediaType(HttpMediaType.APPLICATION_JSON)
                .charset(null)
                .make();
    }
}
