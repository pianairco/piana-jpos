package ir.piana.dev.jpos.qp.core.module;

import ir.piana.dev.jpos.qp.core.error.QPException;
import ir.piana.dev.jpos.qp.core.security.authorize.QPHttpAuthorizationType;
import ir.piana.dev.jpos.qp.core.security.identity.QPIdentityManagementType;
import ir.piana.dev.jpos.qp.core.security.identity.QPIdentityProviderFactory;
import ir.piana.dev.jpos.qp.core.security.identity.QPIdentityProvider;

import java.util.Map;

public class QPIdentityManagementModule
        extends QPBaseModule
        implements QPIdentityProvider {
    protected QPIdentityManagementType identityManagementType;
    protected QPHttpAuthorizationType authorizationType;
    protected QPIdentityProvider identityProvider;

    @Override
    protected void configBeforeRegisterQPModule() throws Exception {
        String identityManagementTypeCode = getPersist()
                .getChildText("qp-identity-management-type");
        String authorizationTypeCode = getPersist()
                .getChildText("qp-authorization-provider-type");
        identityManagementType = QPIdentityManagementType
                .fromCode(identityManagementTypeCode);
        authorizationType = QPHttpAuthorizationType
                .fromCode(authorizationTypeCode);
    }

    @Override
    protected void initBeforeRegisterQPModule() throws Exception {
    }

    @Override
    protected void initAfterRegisterQPModule() throws Exception {

    }

    @Override
    protected void configAfterRegisterQPModule() throws Exception {
        identityProvider = QPIdentityProviderFactory
                .createIdentityProvider(
                        getPersist());
    }

    @Override
    protected void startQPModule() throws Exception {
    }

    @Override
    protected void stopService() throws Exception {
    }

    @Override
    protected void destroyService() throws Exception {
    }

    @Override
    public String provide(
            Map<String, Object> subjectMap)
            throws QPException {
        return identityProvider.provide(subjectMap);
    }
}
