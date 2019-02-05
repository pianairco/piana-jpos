package ir.piana.dev.jpos.qp.ext.http.module;

import ir.piana.dev.jpos.qp.core.error.QPHttpResponseException;
import ir.piana.dev.jpos.qp.core.http.enums.HttpMediaType;
import ir.piana.dev.jpos.qp.core.http.enums.QPDefaultRequestHandlerType;
import ir.piana.dev.jpos.qp.ext.http.asset.PianaAsset;
import ir.piana.dev.jpos.qp.ext.http.asset.PianaAssetResolver;
import org.glassfish.grizzly.http.util.HttpStatus;

import java.util.Map;

public class QPHttpStaticHandler implements QPHttpHandlerExt {
    String rootPath;
    PianaAssetResolver assetResolver;

    public void config(Map<String, String> configMap) {
        rootPath = configMap.get("root-path");
        assetResolver = PianaAssetResolver.getInstance(rootPath);
    }

    @Override
    public QPHttpResponse get(QPHttpRequest request) throws QPHttpResponseException {
        System.out.println(request.getAsteriskPath());
        System.out.println(rootPath);
        try {
            PianaAsset resolve = assetResolver.resolve(request.getAsteriskPath());
            return QPHttpResponseBuilder.status(HttpStatus.OK_200)
                    .entity(resolve.getBytes())
                    .mediaType(HttpMediaType.fromCode(resolve.getMediaType()))
                    .charset("UTF-8")
                    .make();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return QPHttpResponseBuilder.status(HttpStatus.NOT_FOUND_404)
                .entity(request.asteriskPath + " : " + rootPath)
                .mediaType(HttpMediaType.TEXT_PLAIN)
                .charset(null)
                .make();
    }
}
