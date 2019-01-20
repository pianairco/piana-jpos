package ir.piana.dev.jpos.qp.core.security.authorize;

import ir.piana.dev.jpos.qp.core.error.QPException;

/**
 * @author Mohammad Rahmati, 1/20/2019
 */
public enum HttpAuthorizationType {
    BASIC("basic");

    private String code;

    HttpAuthorizationType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static HttpAuthorizationType fromCode(String code)
            throws QPException {
        for(HttpAuthorizationType type :
                HttpAuthorizationType.values()) {
            if(type.code.equalsIgnoreCase(code))
                return type;
        }
        throw new QPException(code + " not defined");
    }
}
