package ir.piana.dev.jpos.qp.core.security.role;

import ir.piana.dev.jpos.qp.core.error.QPException;
import org.jdom2.Element;

import java.util.*;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
public class RoleManagerFactory {
    public static QPRoleManager createRoleManager(
            RoleManagementType roleManagementType,
            Element element)
            throws QPException {
        switch (roleManagementType) {
            case IN_MEMORY:
                return createInMemoryRoleManager(
                        element.getChildren("qp-user"));
            case DATABASE:
                break;
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
        return new InMemoryRoleManager(userToRolesMap);
    }
}
