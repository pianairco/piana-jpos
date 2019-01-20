package ir.piana.dev.jpos.qp.core.module;

import ir.piana.dev.jpos.qp.core.security.authorize.HttpAuthorizationType;
import ir.piana.dev.jpos.qp.core.security.identity.IdentityManagementType;
import ir.piana.dev.jpos.qp.core.security.identity.IdentityProviderFactory;
import ir.piana.dev.jpos.qp.core.security.identity.QPIdentityProvider;
import ir.piana.dev.jpos.qp.core.security.role.QPRoleManager;
import ir.piana.dev.jpos.qp.core.security.role.RoleManagerFactory;
import org.jpos.util.NameRegistrar;

import java.util.List;
import java.util.Map;

public class QPIdentityManagementModule
        extends QPBaseModule
        implements QPIdentityProvider {
    protected IdentityManagementType identityManagementType;
    protected HttpAuthorizationType authorizationType;
    protected QPIdentityProvider identityProvider;

    @Override
    protected void configQPModule() throws Exception {
        String identityManagementTypeCode = getPersist()
                .getChildText("qp-identity-management-type");
        String authorizationTypeCode = getPersist()
                .getChildText("qp-authorization-provider-type");
        identityManagementType = IdentityManagementType
                .fromCode(identityManagementTypeCode);
        authorizationType = HttpAuthorizationType
                .fromCode(authorizationTypeCode);
    }

    @Override
    protected void initQPModule() throws Exception {
        identityProvider = IdentityProviderFactory
                .createIdentityProvider(
                        identityManagementType, getPersist());
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
            HttpAuthorizationType authorizationType,
            Map<String, Object> subjectMap) {
        return identityProvider.provide(authorizationType, subjectMap);
    }
}
