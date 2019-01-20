package ir.piana.dev.jpos.qp.core.security.identity;

import ir.piana.dev.jpos.qp.core.security.authorize.HttpAuthorizationType;

import java.util.Map;

/**
 * @author Mohammad Rahmati, 1/20/2019
 */
public class InMemoryIdentityProvider implements QPIdentityProvider {
    Map<String, String> userToCredentialMap;

    InMemoryIdentityProvider(
            Map<String, String> userToCredentialMap) {
        this.userToCredentialMap = userToCredentialMap;
    }

    @Override
    public String provide(
            HttpAuthorizationType authorizationType,
            Map<String, Object> subjectMap) {
        if(authorizationType == HttpAuthorizationType.BASIC) {
            return userToCredentialMap.get(subjectMap.get("basic"));
        }
        return null;
    }
}
