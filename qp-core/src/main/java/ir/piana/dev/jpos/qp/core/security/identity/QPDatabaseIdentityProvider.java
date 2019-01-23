package ir.piana.dev.jpos.qp.core.security.identity;

import ir.piana.dev.jpos.qp.core.data.database.QPDatabaseManager;
import ir.piana.dev.jpos.qp.core.data.database.QPQueryStruct;
import ir.piana.dev.jpos.qp.core.error.QPException;
import ir.piana.dev.jpos.qp.core.module.QPBaseModule;
import ir.piana.dev.jpos.qp.core.module.QPDatabaseManagerModule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Mohammad Rahmati, 1/20/2019
 */
public class QPDatabaseIdentityProvider implements QPIdentityProvider {
    protected Map<String, String> uniqueToIdentityMap = new LinkedHashMap<>();
    protected String databaseManagerName;
    protected String databaseInstanceName;
    protected QPQueryStruct queryStruct;

    public QPDatabaseIdentityProvider(
            String databaseManagerName,
            String databaseInstanceName,
            QPQueryStruct queryStruct) {
        this.databaseManagerName = databaseManagerName;
        this.databaseInstanceName = databaseInstanceName;
        this.queryStruct = queryStruct;
    }

    @Override
    public String provide(
            Map<String, Object> subjectMap)
            throws QPException {
        String identity = uniqueToIdentityMap.get(subjectMap.get("unique"));
        if(identity != null)
            return identity;
        QPDatabaseManagerModule databaseManagerModule = QPBaseModule
                .getModule(databaseManagerName);

        try {
            ResultSet resultSet = databaseManagerModule.executeQuery(
                    databaseInstanceName,
                    queryStruct,
                    subjectMap);

            if(resultSet.next()) {
                identity = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new QPException(e);
        } catch (QPException e) {
            e.printStackTrace();
            throw e;
        }

        return identity;
    }
}
