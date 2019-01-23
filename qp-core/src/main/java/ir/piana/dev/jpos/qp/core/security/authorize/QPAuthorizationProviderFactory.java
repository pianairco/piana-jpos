package ir.piana.dev.jpos.qp.core.security.authorize;

import ir.piana.dev.jpos.qp.core.error.QPException;
import org.jdom2.Element;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
public class QPAuthorizationProviderFactory {
    public static QPAuthorizationProvider createAuthorizationProvider(
            QPHttpAuthorizationType authorizationType,
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
        return new QPBasicAuthorizationProvider();
    }
}
