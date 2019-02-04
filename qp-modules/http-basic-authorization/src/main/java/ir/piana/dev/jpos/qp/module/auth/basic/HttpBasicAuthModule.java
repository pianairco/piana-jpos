package ir.piana.dev.jpos.qp.module.auth.basic;

import ir.piana.dev.jpos.qp.core.data.database.QPQueryFactory;
import ir.piana.dev.jpos.qp.core.data.database.QPQueryStruct;
import ir.piana.dev.jpos.qp.core.error.QPException;
import ir.piana.dev.jpos.qp.core.error.QPHttpResponseException;
import ir.piana.dev.jpos.qp.core.http.QPHttpAuthorizable;
import ir.piana.dev.jpos.qp.core.http.QPHttpRoleManageable;
import ir.piana.dev.jpos.qp.core.module.QPBaseModule;
import ir.piana.dev.jpos.qp.core.module.QPDatabaseManagerModule;
import ir.piana.dev.jpos.qp.ext.http.module.QPHttpRequest;
import ir.piana.dev.jpos.qp.module.auth.data.dao.UserDao;
import ir.piana.dev.jpos.qp.module.auth.data.entity.UserEntity;
import ir.piana.dev.jpos.qp.spring.data.dao.QueryConditionStruct;
import ir.piana.dev.jpos.qp.spring.module.QPSpringContextProviderModule;
import ir.piana.dev.secure.util.Base64Converter;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.jdom2.Element;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mohammad Rahmati, 1/30/2019
 */
public class HttpBasicAuthModule
        extends QPBaseModule
        implements QPHttpAuthorizable {
    private String springProviderModuleName;
    private QPQueryStruct queryStruct;

    @Override
    protected void configBeforeRegisterQPModule() throws Exception {
        springProviderModuleName = getPersist()
                .getChildText("spring-provider-module");
//        queryStruct = QPQueryFactory.createQueryStruct(
//                getPersist().getChild("identity-query"));
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
    public QPHttpRoleManageable authorize(QPHttpRequest request)
            throws QPHttpResponseException {
        String authorizationB64 = request.getHeader("Authorization");
        if(authorizationB64 == null || authorizationB64.isEmpty())
            throw new QPHttpResponseException(HttpStatus.UNAUTHORIZED_401);
        String authorization = new String(
                Base64Converter.fromBase64String(authorizationB64
                        .substring(authorizationB64.indexOf(" "))));
        String[] split = authorization.split(":");
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("username", split[0]);
        map.put("password", split[1]);
        QPSpringContextProviderModule springProviderModule = QPBaseModule
                .getModule(springProviderModuleName);

        UserDao userDao = springProviderModule.getBean(UserDao.class);
        QueryConditionStruct queryConditionStruct = new QueryConditionStruct();
        queryConditionStruct.setField("username");
        queryConditionStruct.setFilters(Arrays.asList(map.get("username")));
        queryConditionStruct.setFilterType(QueryConditionStruct.FilterType.IN_LIST);
        List<UserEntity> users = userDao.selectAll(UserEntity.class, Arrays.asList(
                new QueryConditionStruct("username",
                        Arrays.asList(split[0]),
                        QueryConditionStruct.FilterType.IN_LIST),
                new QueryConditionStruct("password",
                        Arrays.asList(split[1]),
                        QueryConditionStruct.FilterType.IN_LIST)));
        if(users.isEmpty())
            return null;
        String roles = users.get(0).getRoles();

        return new BasicHttpRoleManageable(
                Arrays.asList(roles.split(",")));
    }

    protected static class BasicHttpRoleManageable
            implements QPHttpRoleManageable {

        private List<String> roles;

        BasicHttpRoleManageable(List<String> roles) {
            this.roles = roles;
        }

        @Override
        public boolean hasAnyRoles(List<String> requiredRoles) {
            for(String requiredRole : requiredRoles) {
                if (roles.contains(requiredRole))
                    return true;
            }
            return false;
        }
    }
}
