package ir.piana.dev.jpos.qp.core.security.identity;

import ir.piana.dev.jpos.qp.core.security.authorize.HttpAuthorizationType;

import java.util.Map;

/**
 * @author Mohammad Rahmati, 1/20/2019
 */
public interface QPIdentityProvider {
    String provide(
            HttpAuthorizationType authorizationType,
            Map<String, Object> subjectMap);
}
