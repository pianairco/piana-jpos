package ir.piana.dev.jpos.qp.core.module;

import ir.piana.dev.jpos.qp.core.error.QPException;
import ir.piana.dev.jpos.qp.core.security.role.QPRoleManager;
import ir.piana.dev.jpos.qp.core.security.role.QPRoleManagementType;
import ir.piana.dev.jpos.qp.core.security.role.QPRoleManagerFactory;
import org.jdom2.Element;

import java.util.List;

public class QPRoleManagementModule
        extends QPBaseModule implements QPRoleManager {
    protected QPRoleManagementType roleManagementType;
    protected QPRoleManager roleManager;
    private Element roleManagementConfig;

    @Override
    protected void configBeforeRegisterQPModule() throws Exception {
        roleManagementConfig = getPersist()
                .getChild("qp-role-management");
    }

    @Override
    protected void initBeforeRegisterQPModule() throws Exception {
        roleManager = QPRoleManagerFactory
                .createRoleManager(roleManagementConfig);
    }

    @Override
    protected void initAfterRegisterQPModule() throws Exception {

    }

    @Override
    protected void configAfterRegisterQPModule() throws Exception {

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

    @Override
    public boolean hasAnyRole(
            String identity, List<String> roles)
            throws QPException {
        return roleManager.hasAnyRole(identity, roles);
    }
}
