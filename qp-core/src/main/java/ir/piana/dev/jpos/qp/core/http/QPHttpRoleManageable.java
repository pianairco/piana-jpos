package ir.piana.dev.jpos.qp.core.http;

import ir.piana.dev.jpos.qp.ext.http.module.QPHttpRequest;

/**
 * @author Mohammad Rahmati, 1/30/2019
 */
public interface QPHttpRoleManageable {
    boolean hasAnyRoles(QPHttpRequest request);
}
