package ir.piana.dev.jpos.qp.module.auth.basic;

import ir.piana.dev.jpos.qp.core.data.database.QPQueryFactory;
import ir.piana.dev.jpos.qp.core.data.database.QPQueryStruct;
import ir.piana.dev.jpos.qp.core.error.QPException;
import ir.piana.dev.jpos.qp.core.http.QPHttpAuthorizable;
import ir.piana.dev.jpos.qp.core.http.QPHttpRoleManageable;
import ir.piana.dev.jpos.qp.core.module.QPBaseModule;
import ir.piana.dev.jpos.qp.core.module.QPDatabaseManagerModule;
import ir.piana.dev.jpos.qp.ext.http.module.QPHttpRequest;
import ir.piana.dev.secure.util.Base64Converter;
import org.jdom2.Element;

import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Mohammad Rahmati, 1/30/2019
 */
public class HttpBasicAuthModule
        extends QPBaseModule
        implements QPHttpAuthorizable {
    private String databaseManagerName;
    private String databaseInstanceName;
    private QPQueryStruct queryStruct;

    @Override
    protected void configBeforeRegisterQPModule() throws Exception {
        databaseManagerName = getPersist()
                .getChildText("database-manager");
        databaseInstanceName = getPersist()
                .getChildText("database-instance");
        queryStruct = QPQueryFactory.createQueryStruct(
                getPersist().getChild("identity-query"));
    }

    @Override
    protected void initBeforeRegisterQPModule() throws Exception {

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
    protected void stopQPModule() throws Exception {

    }

    @Override
    protected void destroyQPModule() throws Exception {

    }

    @Override
    public QPHttpRoleManageable authorize(QPHttpRequest request) throws QPException {
        String authorizationB64 = request.getHeader("Authorization");
        String authorization = new String(
                Base64Converter.fromBase64String(authorizationB64
                        .substring(authorizationB64.indexOf(" "))));
        String[] split = authorization.split(":");
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("username", split[0]);
        map.put("password", split[1]);
        QPDatabaseManagerModule dbmModule = QPBaseModule
                .getModule(databaseManagerName);
        ResultSet resultSet = dbmModule.executeQuery(
                databaseInstanceName, queryStruct, map);
//        if(resultSet.next())
        return null;
    }

    public static String getPackage() {
        return "";
    }
}
