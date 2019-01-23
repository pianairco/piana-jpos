package ir.piana.dev.jpos.qp.core.security.role;

import ir.piana.dev.jpos.qp.core.data.database.QPQueryStruct;
import ir.piana.dev.jpos.qp.core.error.QPException;
import ir.piana.dev.jpos.qp.core.module.QPBaseModule;
import ir.piana.dev.jpos.qp.core.module.QPDatabaseManagerModule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mohammad Rahmati, 1/20/2019
 */
public class QPDatabaseRoleManager implements QPRoleManager {
    Map<String, List<String>> userToRolesMap = new LinkedHashMap<>();
    protected String databaseManagerName;
    protected String databaseInstanceName;
    protected QPQueryStruct queryStruct;

    public QPDatabaseRoleManager(
            String databaseManagerName,
            String databaseInstanceName,
            QPQueryStruct queryStruct) {
        this.databaseManagerName = databaseManagerName;
        this.databaseInstanceName = databaseInstanceName;
        this.queryStruct = queryStruct;
    }

    @Override
    public boolean hasAnyRole(
            String identity, List<String> mandatoryRoles)
            throws QPException {
        if(mandatoryRoles == null || mandatoryRoles.isEmpty())
            return true;
        List<String> roles = userToRolesMap.get(identity);
        if(roles == null) {
            QPDatabaseManagerModule databaseManagerModule =
                    QPBaseModule.getModule(databaseManagerName);
            Map<String, Object> map = new LinkedHashMap<>();
            map.put(queryStruct.getParamName(0), identity);
            ResultSet resultSet = databaseManagerModule.executeQuery(
                    databaseInstanceName, queryStruct, map);
            try {
                if(resultSet.next())
                    roles = Arrays.asList(resultSet.getString(1).split(","));
                userToRolesMap.put(identity, roles);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new QPException(e);
            }
        }
        for (String identityRole : roles) {
            if(mandatoryRoles.contains(identityRole))
                return true;
        }
        return false;
    }
}
