package ir.piana.dev.jpos.qp.ext.http.module;

import ir.piana.dev.jpos.qp.core.http.HttpMethodType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
class QPHttpRole {
    private List<String> defaultRoles;
    private List<String> getRoles;
    private List<String> postRoles;
    private List<String> putRoles;
    private List<String> deleteRoles;
    private List<String> headRoles;
    private List<String> optionsRoles;
    private List<String> traceRoles;

    public QPHttpRole() {
        this((List<String>)null);
    }

    public QPHttpRole(String defaultRoles) {
        this(defaultRoles != null && !defaultRoles.isEmpty() ?
                Arrays.asList(defaultRoles.split(",")) :
                null);
    }

    public QPHttpRole(
            List<String> defaultRoles) {
        if(defaultRoles == null)
            this.defaultRoles = new ArrayList<String>();
        else
            this.defaultRoles = defaultRoles;
    }

    public List<String> getDefaultRole() {
        return defaultRoles;
    }

    public void setDefaultRole(String defaultRoles) {
        setDefaultRole(defaultRoles != null && !defaultRoles.isEmpty() ?
                Arrays.asList(defaultRoles.split(",")) :
                null);
    }

    public void setDefaultRole(List<String> defaultRoles) {
        if(defaultRoles == null)
            this.defaultRoles = new ArrayList<String>();
        else
            this.defaultRoles = defaultRoles;
    }

    public List<String> getRole(HttpMethodType methodType) {
        switch (methodType) {
            case GET:
                return getRoles != null ? getRoles : defaultRoles;
            case POST:
                return postRoles != null ? postRoles : defaultRoles;
            case PUT:
                return putRoles != null ? putRoles : defaultRoles;
            case DELETE:
                return deleteRoles != null ? deleteRoles : defaultRoles;
            case HEAD:
                return headRoles != null ? headRoles : defaultRoles;
            case OPTIONS:
                return optionsRoles != null ? optionsRoles : defaultRoles;
            case TRACE:
                return traceRoles != null ? traceRoles : defaultRoles;
        }
        return defaultRoles;
    }

    public void setRole(HttpMethodType methodType,
                        String roles) {
        List<String> roleList = null;
        if(roles != null && !roles.isEmpty()) {
            roleList = Arrays.asList(roles.split(","));
        }
        setRole(methodType, roleList);
    }

    public void setRole(HttpMethodType methodType,
                        List<String> roles) {
        switch (methodType) {
            case GET:
                getRoles = roles;
            case POST:
                postRoles = roles;
            case PUT:
                putRoles = roles;
            case DELETE:
                deleteRoles = roles;
            case HEAD:
                headRoles = roles;
            case OPTIONS:
                optionsRoles = roles;
            case TRACE:
                traceRoles = roles;
        }
    }
}
