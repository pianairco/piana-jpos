package ir.piana.dev.jpos.qp.core.http.enums;

import ir.piana.dev.jpos.qp.core.error.QPException;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
public enum HttpMediaType {
    APPLICATION_JSON("application/json"),
    APPLICATION_XML("application/xml"),
    APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded"),
    APPLICATION_OCTET_STREAM("application/octet-stream"),
    TEXT_PLAIN("text/plain"),
    TEXT_HTML("text/html");

    private String code;

    HttpMediaType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static HttpMediaType fromCode(String mediaType)
            throws QPException {
        for(HttpMediaType type : HttpMediaType.values()) {
            if(type.code.equalsIgnoreCase(mediaType))
                return type;
        }
        throw new QPException("not supported media type!");
    }
}
