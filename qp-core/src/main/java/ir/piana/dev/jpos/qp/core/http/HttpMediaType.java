package ir.piana.dev.jpos.qp.core.http;

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
}
