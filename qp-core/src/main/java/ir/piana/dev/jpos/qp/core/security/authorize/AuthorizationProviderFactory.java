package ir.piana.dev.jpos.qp.core.security.authorize;

import ir.piana.dev.jpos.qp.core.error.QPException;
import ir.piana.dev.jpos.qp.core.security.identity.IdentityManagementType;
import ir.piana.dev.jpos.qp.core.security.identity.InMemoryIdentityProvider;
import ir.piana.dev.jpos.qp.core.security.identity.QPIdentityProvider;
import org.glassfish.grizzly.http.server.Request;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static ir.piana.dev.secure.util.Base64Converter.toBase64String;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
public class AuthorizationProviderFactory {
    public static QPAuthorizationProvider createAuthorizationProvider(
            HttpAuthorizationType authorizationType,
            Element element)
            throws QPException {
        switch (authorizationType) {
            case BASIC:
                return createBasicAuthorizationProvider(element);
        }
        throw new QPException("identity provider type not support");
    }

    protected static QPAuthorizationProvider createBasicAuthorizationProvider(
            Element element) {
        return new BasicAuthorizationProvider();
    }
}
