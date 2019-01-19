package ir.piana.dev.jpos.qp.core.security.role;

import java.util.List;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
public interface QPRoleManager {
    boolean hasAnyRole(String identity, List<String> roles);
}
