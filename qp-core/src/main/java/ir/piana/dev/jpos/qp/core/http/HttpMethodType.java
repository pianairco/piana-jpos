package ir.piana.dev.jpos.qp.core.http;

import ir.piana.dev.jpos.qp.core.error.QPException;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
public enum HttpMethodType {
    DELETE("delete", 0x8),
    GET("get", 0x1),
    HEAD("head", 0x10),
    OPTIONS("options", 0x20),
    POST("post", 0x2),
    PUT("put", 0x4),
    TRACE("trace", 0x40);

    private String name;
    private int code;

    HttpMethodType(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public int getCode() {
        return code;
    }

    public boolean canHasBody() {
        int i = code | POST.code | PUT.code;
        return i > 0 ? true : false;
    }

    public static HttpMethodType fromCode(String methodType)
            throws QPException {
        for(HttpMethodType type : HttpMethodType.values()) {
            if(type.name.equalsIgnoreCase(methodType))
                return type;
        }
        throw new QPException("not supported method!");
    }
}
