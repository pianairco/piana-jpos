package ir.piana.dev.jpos.qp.core.module;

import ir.piana.dev.jpos.qp.core.error.QPException;
import ir.piana.dev.jpos.qp.core.security.authorize.QPAuthorizationProviderFactory;
import ir.piana.dev.jpos.qp.core.security.authorize.QPHttpAuthorizationType;
import ir.piana.dev.jpos.qp.core.security.authorize.QPAuthorizationProvider;
import ir.piana.dev.jpos.qp.core.security.identity.QPIdentityProviderFactory;
import ir.piana.dev.jpos.qp.core.security.identity.QPIdentityProvider;
import org.glassfish.grizzly.http.server.Request;
import org.jdom2.Element;

import java.util.Map;

public class QPAuthorizationProviderModule
        extends QPBaseModule {
    protected QPHttpAuthorizationType httpAuthorizationType;
    protected QPAuthorizationProvider authorizationProvider;
    protected QPIdentityProvider identityProvider;

    @Override
    protected void configBeforeRegisterQPModule() throws Exception {
        String authorizationProviderTypeCode = getPersist()
                .getChildText("qp-authorization-provider-type");
        httpAuthorizationType = QPHttpAuthorizationType
                .fromCode(authorizationProviderTypeCode);

        Element identityProviderElement = getPersist()
                .getChild("qp-identity-provider");

        identityProvider = QPIdentityProviderFactory
                .createIdentityProvider(identityProviderElement);
    }

    @Override
    protected void initBeforeRegisterQPModule() throws Exception {
    }

    @Override
    protected void initAfterRegisterQPModule() throws Exception {

    }

    @Override
    protected void configAfterRegisterQPModule() throws Exception {
        authorizationProvider = QPAuthorizationProviderFactory
                .createAuthorizationProvider(
                        httpAuthorizationType, getPersist());
    }

    @Override
    protected void startQPModule() throws Exception {
    }

    @Override
    protected void stopQPModule() throws Exception {

    }

    @Override
    protected void destroyQPModule() throws Exception {

    }

    public String provide(Request request)
            throws QPException {
        Map<String, Object> credentialMap = authorizationProvider
                .provide(request);
        return identityProvider.provide(credentialMap);
    }
}
