package ir.piana.dev.jpos.qp.core.security.authorize;

import ir.piana.dev.secure.util.Base64Converter;
import org.glassfish.grizzly.http.server.Request;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Mohammad Rahmati, 1/20/2019
 */
public class QPBasicAuthorizationProvider
        implements QPAuthorizationProvider {
    Map<String, Map<String, Object>> userToCredentialMap = new LinkedHashMap<>();

    QPBasicAuthorizationProvider() {
    }

    @Override
    public Map<String, Object> provide(Request request) {
        String authorization = request.getHeader("Authorization");
        Map<String, Object> credentialMap = userToCredentialMap.get(authorization);
        if(credentialMap != null)
            return credentialMap;
        credentialMap = new LinkedHashMap<>();
        if(authorization.startsWith("Basic")) {
            String s = new String(Base64Converter
                    .fromBase64String(authorization.substring(6)));
            String[] split = s.split(":");
            credentialMap.put("username", split[0]);
            credentialMap.put("password", split[1]);
            credentialMap.put("unique", authorization);
            userToCredentialMap.put(authorization, credentialMap);
        }
        return credentialMap;
    }
}
