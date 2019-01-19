package ir.piana.dev.jpos.qp.core.module;

import ir.piana.dev.jpos.qp.core.security.role.RoleManagementType;
import ir.piana.dev.jpos.qp.ext.http.module.QPHttpHandlerExt;
import org.jdom2.Element;

import java.util.List;

public class QPRoleManagementModule
        extends QPBaseModule {
    @Override
    protected void configQPModule() throws Exception {
        String roleManagementTypeCode = getPersist()
                .getChildText("qp-role-management-type");
        RoleManagementType roleManagementType = RoleManagementType
                .fromCode(roleManagementTypeCode);

        List<Element> children = getPersist().getChildren("qp-user");
    }

    @Override
    protected void initQPModule() throws Exception {
    }

    @Override
    protected void startService() throws Exception {

    }

    @Override
    protected void stopService() throws Exception {

    }

    @Override
    protected void destroyService() throws Exception {

    }
}
