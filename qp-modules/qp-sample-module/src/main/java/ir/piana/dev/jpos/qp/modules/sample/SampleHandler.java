package ir.piana.dev.jpos.qp.modules.sample;

import ir.piana.dev.jpos.qp.core.error.QPException;
import ir.piana.dev.jpos.qp.core.error.QPHttpResponseException;
import ir.piana.dev.jpos.qp.core.http.HttpMediaType;
import ir.piana.dev.jpos.qp.ext.http.module.QPHttpHandlerExt;
import ir.piana.dev.jpos.qp.ext.http.module.QPHttpRequest;
import ir.piana.dev.jpos.qp.ext.http.module.QPHttpResponse;
import ir.piana.dev.jpos.qp.ext.http.module.QPHttpResponseBuilder;
import org.glassfish.grizzly.http.util.HttpStatus;

import java.util.Map;

public class SampleHandler implements QPHttpHandlerExt {
    @Override
    public QPHttpResponse get(QPHttpRequest request)
            throws QPHttpResponseException {
        return QPHttpResponseBuilder.status(HttpStatus.OK_200)
                .entity(new SampleModel("ali", 13))
                .mediaType(HttpMediaType.APPLICATION_JSON)
                .charset(null)
                .make();
    }

    @Override
    public QPHttpResponse post(QPHttpRequest request)
            throws QPHttpResponseException {
        Map<String, String> map = null;
        try {
            map = request.getBodyAs(Map.class);
        } catch (QPException e) {
            throw new QPHttpResponseException(HttpStatus.INTERNAL_SERVER_ERROR_500);
        }

        return QPHttpResponseBuilder.status(HttpStatus.OK_200)
                .entity(new SampleModel(map.get("name"), 13))
                .mediaType(HttpMediaType.APPLICATION_JSON)
                .charset(null)
                .make();
    }
}
