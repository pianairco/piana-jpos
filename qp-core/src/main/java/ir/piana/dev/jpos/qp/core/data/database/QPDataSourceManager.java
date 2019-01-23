package ir.piana.dev.jpos.qp.core.data.database;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Mohammad Rahmati, 12/12/2018
 */
public final class QPDataSourceManager {
    private Map<String, DataSource> dataSourceMap = new LinkedHashMap<>();

    QPDataSourceManager() {
    }

    void addDataSource(
            String instance, DataSource dataSource) {
        dataSourceMap.put(instance, dataSource);
    }

    public DataSource getDataSource(String instance) {
        return dataSourceMap.get(instance);
    }
}
