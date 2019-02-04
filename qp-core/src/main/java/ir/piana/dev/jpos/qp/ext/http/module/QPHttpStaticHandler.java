package ir.piana.dev.jpos.qp.ext.http.module;

import ir.piana.dev.jpos.qp.core.error.QPHttpResponseException;
import ir.piana.dev.jpos.qp.core.http.enums.HttpMediaType;
import ir.piana.dev.jpos.qp.core.http.enums.QPDefaultRequestHandlerType;
import org.glassfish.grizzly.http.util.HttpStatus;

import java.util.Map;

public class QPHttpStaticHandler implements QPHttpHandlerExt {
    String rootPath;

    public void config(Map<String, String> configMap) {
        rootPath = configMap.get("root-path");
    }

    @Override
    public QPHttpResponse get(QPHttpRequest request) throws QPHttpResponseException {
        System.out.println(request.getAsteriskPath());
        System.out.println(rootPath);
        return QPHttpResponseBuilder.status(HttpStatus.NOT_FOUND_404)
                .entity(request.asteriskPath + " : " + rootPath)
                .mediaType(HttpMediaType.TEXT_PLAIN)
                .charset(null)
                .make();
    }
}
