package ir.piana.dev.jpos.qp.ext.http.module;

import ir.piana.dev.jpos.qp.core.http.enums.HttpMethodType;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
class QPHttpHandlerInfo {
    private String url;
    private QPHttpHandlerExt httpHandlerExt;
    private QPHttpRole roles;

    public QPHttpHandlerInfo() {
    }

    public QPHttpHandlerInfo(
            String url,
            QPHttpHandlerExt httpHandlerExt,
            QPHttpRole roles) {
        this.url = url;
        this.httpHandlerExt = httpHandlerExt;
        this.roles = roles;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public QPHttpHandlerExt getHttpHandlerExt() {
        return httpHandlerExt;
    }

    public void setHttpHandlerExt(QPHttpHandlerExt httpHandlerExt) {
        this.httpHandlerExt = httpHandlerExt;
    }

    public QPHttpRole getRoles() {
        return roles;
    }

    public boolean mustAuthorized(HttpMethodType methodType) {
        if(roles.getRole(methodType) == null ||
                roles.getRole(methodType).isEmpty()) {
            return false;
        }
        return true;
    }

    public void setRoles(QPHttpRole roles) {
        this.roles = roles;
    }
}
