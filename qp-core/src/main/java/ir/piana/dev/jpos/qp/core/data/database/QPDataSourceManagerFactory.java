package ir.piana.dev.jpos.qp.core.data.database;

import ir.piana.dev.jpos.qp.core.error.QPException;

import javax.sql.DataSource;

/**
 * @author Mohammad Rahmati, 1/23/2019
 */
public final class QPDataSourceManagerFactory {
    private QPDataSourceManagerFactory() {

    }

    public static DataSourceInjectable initializeDataSourceManager() {
        QPDataSourceManager dataSourceManager =
                new QPDataSourceManager();
        return new DataSourceInjectable(dataSourceManager);
    }

    public static QPDataSourceManager build(
            DataSourceInjectable dataSourceInjectable) {
        return dataSourceInjectable.dataSourceManager;
    }

    public static class DataSourceInjectable {
        private QPDataSourceManager dataSourceManager;

        private DataSourceInjectable(
                QPDataSourceManager dataSourceManager) {
            this.dataSourceManager = dataSourceManager;
        }

        public DataSourceInjectable inject (
                ConnectionInfo connectionInfo)
                throws QPException {
            DataSource datasource = QPDataSourceFactory
                    .createDatasource(connectionInfo);
            dataSourceManager.addDataSource(
                    connectionInfo.getInstanceName(),
                    datasource);
            return this;
        }
    }
}
