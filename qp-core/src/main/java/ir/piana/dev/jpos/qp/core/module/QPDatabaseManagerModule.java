package ir.piana.dev.jpos.qp.core.module;

import ir.piana.dev.jpos.qp.core.data.database.*;
import ir.piana.dev.jpos.qp.core.data.database.QPDataSourceManagerFactory.DataSourceInjectable;
import ir.piana.dev.jpos.qp.core.error.QPException;
import org.jdom2.Element;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QPDatabaseManagerModule extends QPBaseModule {
    protected List<ConnectionInfo> connectionInfoList = new ArrayList<>();
    protected QPDatabaseManager databaseManager;

    @Override
    protected void configBeforeRegisterQPModule() throws Exception {
        for(Element connectionElement : getPersist().getChildren("datasource")) {
            String instanceName = connectionElement
                    .getChildText("instance-name");
            String databasePlatform = connectionElement
                    .getChildText("database-platform");
            String jdbcUrl = connectionElement
                    .getChildText("jdbc-url");
            String driverClassName = connectionElement
                    .getChildText("driver-class-name");
            String user = connectionElement
                    .getChildText("user");
            String password = connectionElement
                    .getChildText("password");
            String sid = connectionElement
                    .getChildText("sid");
            String poolSizeString = connectionElement
                    .getChildText("pool-size");
            Integer poolSize = poolSizeString == null ?
                    100 : new Integer(poolSizeString);
            ConnectionInfo connectionInfo = new ConnectionInfo(
                    instanceName, databasePlatform,
                    jdbcUrl, driverClassName,
                    user, password,
                    sid, poolSize);
            connectionInfoList.add(connectionInfo);
        }
    }

    @Override
    protected void initBeforeRegisterQPModule() throws Exception {
        DataSourceInjectable dataSourceInjectable =
                QPDataSourceManagerFactory.initializeDataSourceManager();

        for (ConnectionInfo connectionInfo : connectionInfoList) {
            dataSourceInjectable.inject(connectionInfo);
        }
        QPDataSourceManager dataSourceManager =
                QPDataSourceManagerFactory.build(dataSourceInjectable);
        databaseManager = new QPDatabaseManager(dataSourceManager);
    }

    @Override
    protected void initAfterRegisterQPModule() throws Exception {

    }

    @Override
    protected void configAfterRegisterQPModule() throws Exception {

    }

    @Override
    protected void startQPModule() throws Exception {
    }

    @Override
    protected void stopService() throws Exception {
    }

    @Override
    protected void destroyService() throws Exception {
    }

    protected QPDatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public ResultSet executeQuery(
            String instanceName,
            QPQueryStruct queryStruct,
            Map<String, Object> paramValueMap)
            throws QPException {
        return databaseManager.executeQuery(
                instanceName, queryStruct, paramValueMap);
    }
}
