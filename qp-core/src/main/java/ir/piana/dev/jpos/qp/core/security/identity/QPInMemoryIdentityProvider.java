package ir.piana.dev.jpos.qp.core.security.identity;

import ir.piana.dev.jpos.qp.core.security.authorize.QPHttpAuthorizationType;

import java.util.Map;

/**
 * @author Mohammad Rahmati, 1/20/2019
 */
public class QPInMemoryIdentityProvider implements QPIdentityProvider {
    Map<String, String> userToCredentialMap;
    QPHttpAuthorizationType authorizationType;

    QPInMemoryIdentityProvider(
            QPHttpAuthorizationType authorizationType,
            Map<String, String> userToCredentialMap) {
        this.authorizationType = authorizationType;
        this.userToCredentialMap = userToCredentialMap;
    }

    @Override
    public String provide(
            Map<String, Object> subjectMap) {
        if(authorizationType == QPHttpAuthorizationType.BASIC) {
            return userToCredentialMap.get(subjectMap.get("basic"));
        }
        return null;
    }
}
