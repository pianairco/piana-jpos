package ir.piana.dev.jpos.qp.core.http;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
public enum HttpMethodType {
    GET("get"),
    POST("post"),
    PUT("put"),
    DELETE("delete"),
    HEAD("head"),
    OPTIONS("options"),
    TRACE("trace");

    private String code;

    HttpMethodType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
