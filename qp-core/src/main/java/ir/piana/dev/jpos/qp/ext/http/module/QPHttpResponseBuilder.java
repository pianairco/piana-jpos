package ir.piana.dev.jpos.qp.ext.http.module;

import ir.piana.dev.jpos.qp.core.error.QPHttpResponseException;
import ir.piana.dev.jpos.qp.core.http.enums.HttpMediaType;
import ir.piana.dev.jpos.qp.core.http.enums.QPDefaultRequestHandlerType;
import org.glassfish.grizzly.http.util.HttpStatus;

import java.nio.charset.Charset;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
public class QPHttpResponseBuilder {
    public static EntityInjectable status(
            HttpStatus httpStatus)
            throws QPHttpResponseException {
        QPHttpResponse response = new QPHttpResponse();
        response.httpStatus = httpStatus;
        if(httpStatus != HttpStatus.OK_200)
            throw new QPHttpResponseException(
                    QPDefaultRequestHandlerType
                            .fromCode(httpStatus.getStatusCode()));
        return new EntityInjectable(response);
    }

    public static class EntityInjectable<T> {
        private QPHttpResponse qpHttpResponse;

        EntityInjectable(QPHttpResponse qpHttpResponse) {
            this.qpHttpResponse = qpHttpResponse;

        }

        public MediaTypeInjectable entity(
                T entity) {
            this.qpHttpResponse.entity = entity;
            return new MediaTypeInjectable(qpHttpResponse);
        }
    }

    public static class MediaTypeInjectable<T> {
        private QPHttpResponse qpHttpResponse;

        MediaTypeInjectable(QPHttpResponse qpHttpResponse) {
            this.qpHttpResponse = qpHttpResponse;
        }

        public CharsetInjectable mediaType(
                HttpMediaType mediaType) {
            this.qpHttpResponse.mediaType = mediaType;
            return new CharsetInjectable(qpHttpResponse);
        }
    }

    public static class CharsetInjectable<T> {
        private QPHttpResponse qpHttpResponse;

        CharsetInjectable(QPHttpResponse qpHttpResponse) {
            this.qpHttpResponse = qpHttpResponse;
        }

        public HttpResponseMaker charset(
                String charset) {
            if(charset == null || charset.isEmpty() ||
                    !Charset.isSupported(charset))
                this.qpHttpResponse.charset = Charset
                    .defaultCharset().displayName();
            else
                this.qpHttpResponse.charset = charset;
            return new HttpResponseMaker(qpHttpResponse);
        }
    }

    public static class HttpResponseMaker<T> {
        private QPHttpResponse qpHttpResponse;

        HttpResponseMaker(QPHttpResponse qpHttpResponse) {
            this.qpHttpResponse = qpHttpResponse;
        }

        public QPHttpResponse make() {
            return qpHttpResponse;
        }
    }
}
