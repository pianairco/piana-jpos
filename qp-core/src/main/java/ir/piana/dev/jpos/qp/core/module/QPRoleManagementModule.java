package ir.piana.dev.jpos.qp.core.module;

import ir.piana.dev.jpos.qp.core.security.role.QPRoleManager;
import ir.piana.dev.jpos.qp.core.security.role.RoleManagementType;
import ir.piana.dev.jpos.qp.core.security.role.RoleManagerFactory;
import org.jpos.util.NameRegistrar;

import java.util.List;

public class QPRoleManagementModule
        extends QPBaseModule implements QPRoleManager {
    protected RoleManagementType roleManagementType;
    protected QPRoleManager roleManager;

    @Override
    protected void configQPModule() throws Exception {
        String roleManagementTypeCode = getPersist()
                .getChildText("qp-role-management-type");
        roleManagementType = RoleManagementType
                .fromCode(roleManagementTypeCode);
    }

    @Override
    protected void initQPModule() throws Exception {
        roleManager = RoleManagerFactory
                .createRoleManager(roleManagementType, getPersist());
    }

    @Override
    protected void startService() throws Exception {
        NameRegistrar.register(getName(), this);
    }

    @Override
    protected void stopService() throws Exception {
    }

    @Override
    protected void destroyService() throws Exception {
    }

    @Override
    public boolean hasAnyRole(String identity, List<String> roles) {
        return roleManager.hasAnyRole(identity, roles);
    }
}
