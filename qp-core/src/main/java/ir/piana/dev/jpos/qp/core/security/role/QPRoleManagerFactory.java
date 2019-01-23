package ir.piana.dev.jpos.qp.core.security.role;

import ir.piana.dev.jpos.qp.core.data.database.QPQueryFactory;
import ir.piana.dev.jpos.qp.core.data.database.QPQueryStruct;
import ir.piana.dev.jpos.qp.core.error.QPException;
import org.jdom2.Element;

import java.util.*;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
public class QPRoleManagerFactory {
    public static QPRoleManager createRoleManager(
            Element roleManagementConfig)
            throws QPException {
        String roleManagementTypeCode = roleManagementConfig
                .getAttributeValue("type");
        QPRoleManagementType roleManagementType = QPRoleManagementType
                .fromCode(roleManagementTypeCode);
        switch (roleManagementType) {
            case IN_MEMORY:
                return createInMemoryRoleManager(
                        roleManagementConfig.getChildren("qp-user"));
            case DATABASE:
                return createDatabaseRoleManager(
                        roleManagementConfig);
        }
        throw new QPException("role management type not support");
    }

    protected static QPRoleManager createInMemoryRoleManager(
            List<Element> userElements) {
        final Map<String, List<String>> userToRolesMap = new LinkedHashMap<>();
        for (Element userElement : userElements) {
            String identity = userElement.getChildText("identity");
            String roles = userElement.getChildText("roles");
            List<String> roleList = new ArrayList<>();
            if(identity != null && !identity.isEmpty() &&
                    !userToRolesMap.containsKey(identity) &&
                    roles != null && !roles.isEmpty()){
                userToRolesMap.put(identity, Arrays.asList(roles.split(",")));
            }
        }
        return new QPInMemoryRoleManager(userToRolesMap);
    }

    protected static QPRoleManager createDatabaseRoleManager(
            Element roleManagementConfig) throws QPException {
        String databaseManagerName = roleManagementConfig
                .getChildText("qp-database-manager");
        String databaseInstanceName = roleManagementConfig
                .getChildText("qp-database-instance");
        QPQueryStruct queryStruct = QPQueryFactory.createQueryStruct(
                roleManagementConfig.getChild("qp-role-query"));
        return new QPDatabaseRoleManager(
                databaseManagerName,
                databaseInstanceName,
                queryStruct);
    }
}
