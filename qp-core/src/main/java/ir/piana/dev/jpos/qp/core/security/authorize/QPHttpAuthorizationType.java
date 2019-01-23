package ir.piana.dev.jpos.qp.core.security.authorize;

import ir.piana.dev.jpos.qp.core.error.QPException;

/**
 * @author Mohammad Rahmati, 1/20/2019
 */
public enum QPHttpAuthorizationType {
    UNKNOWN("unknown"),
    BASIC("basic"),
    BEARER("bearer");

    private String code;

    QPHttpAuthorizationType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static QPHttpAuthorizationType fromCode(String code)
            throws QPException {
        for(QPHttpAuthorizationType type :
                QPHttpAuthorizationType.values()) {
            if(type.code.equalsIgnoreCase(code))
                return type;
        }
        throw new QPException(code + " not defined");
    }
}
