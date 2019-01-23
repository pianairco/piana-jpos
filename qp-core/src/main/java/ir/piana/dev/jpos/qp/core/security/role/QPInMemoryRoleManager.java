package ir.piana.dev.jpos.qp.core.security.role;

import java.util.List;
import java.util.Map;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
public class QPInMemoryRoleManager implements QPRoleManager {
    Map<String, List<String>> userToRolesMap;

    QPInMemoryRoleManager(Map<String, List<String>> userToRolesMap) {
        this.userToRolesMap = userToRolesMap;
    }

    @Override
    public boolean hasAnyRole(String identity, List<String> mandatoryRoles) {
        if(mandatoryRoles == null || mandatoryRoles.isEmpty())
            return true;
        if(userToRolesMap.containsKey(identity)) {
            for (String identityRole : userToRolesMap.get(identity)) {
                if(mandatoryRoles.contains(identityRole))
                    return true;
            }
        }
        return false;
    }
}
