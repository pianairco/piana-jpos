package ir.piana.dev.jpos.qp.core.security.authorize;

import org.glassfish.grizzly.http.server.Request;

import java.util.Map;

/**
 * @author Mohammad Rahmati, 1/20/2019
 */
public interface QPAuthorizationProvider {
    Map<String, Object> provide(Request request);
}
