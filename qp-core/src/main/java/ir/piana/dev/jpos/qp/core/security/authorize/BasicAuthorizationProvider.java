package ir.piana.dev.jpos.qp.core.security.authorize;

import org.glassfish.grizzly.http.server.Request;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Mohammad Rahmati, 1/20/2019
 */
public class BasicAuthorizationProvider
        implements QPAuthorizationProvider {
    Map<String, String> userToCredentialMap;

    BasicAuthorizationProvider() {
    }

    @Override
    public Map<String, Object> provide(Request request) {
        String authorization = request.getHeader("Authorization");
        Map<String, Object> map = new LinkedHashMap<>();
        if(authorization.startsWith("Basic"))
            map.put("basic", authorization);
        return map;
    }
}
