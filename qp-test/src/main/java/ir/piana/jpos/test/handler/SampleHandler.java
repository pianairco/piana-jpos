package ir.piana.jpos.test.handler;

import ir.piana.dev.jpos.qp.core.error.QPException;
import ir.piana.dev.jpos.qp.core.error.QPHttpResponseException;
import ir.piana.dev.jpos.qp.core.http.enums.HttpMediaType;
import ir.piana.dev.jpos.qp.core.module.QPBaseModule;
import ir.piana.dev.jpos.qp.ext.http.module.QPHttpHandlerExt;
import ir.piana.dev.jpos.qp.ext.http.module.QPHttpRequest;
import ir.piana.dev.jpos.qp.ext.http.module.QPHttpResponse;
import ir.piana.dev.jpos.qp.ext.http.module.QPHttpResponseBuilder;
import ir.piana.dev.jpos.qp.spring.module.QPSpringContextProviderModule;
import ir.piana.jpos.test.bazar.dao.CountryDao;
import ir.piana.jpos.test.bazar.entity.CountryEntity;
import ir.piana.jpos.test.judicature.entity.UserTblEntity;
import ir.piana.jpos.test.party.dao.UserDao;
import ir.piana.jpos.test.party.entity.UserEntity;
import org.glassfish.grizzly.http.util.HttpStatus;

import java.util.Map;

/**
 * @author Mohammad Rahmati, 1/30/2019
 */
public class SampleHandler implements QPHttpHandlerExt {
    @Override
    public QPHttpResponse get(QPHttpRequest request)
            throws QPHttpResponseException {

        QPSpringContextProviderModule module = QPBaseModule
                .getModule("qp-spring-context-provider");
//        UserRepository bean = module.getBean(UserRepository.class);
//        UserTblEntity byId = bean.findById(10000);

//        CountryDao countryDao = module.getBean(CountryDao.class);
//        CountryEntity byId = (CountryEntity) countryDao.findById(
//                CountryEntity.class, 1);
//
//        UserDao userDao = module.getBean(UserDao.class);
//        UserEntity userEntity = (UserEntity) userDao.findById(
//                UserEntity.class, 9);

        ir.piana.jpos.test.judicature.dao.UserDao userDao = module.getBean(ir.piana.jpos.test.judicature.dao.UserDao.class);
        UserTblEntity byId = (UserTblEntity) userDao.findById(
                UserTblEntity.class, 10000);


        return QPHttpResponseBuilder.status(HttpStatus.OK_200)
                .entity(new SampleModel("ali", 13))
                .mediaType(HttpMediaType.APPLICATION_JSON)
                .charset(null)
                .make();
    }

    @Override
    public QPHttpResponse post(QPHttpRequest request)
            throws QPHttpResponseException {
        QPSpringContextProviderModule module = QPBaseModule
                .getModule("qp-spring-context-provider");
//        UserDao userDao = module.getBean(UserDao.class);
//        UserTblEntity byId = (UserTblEntity) userDao.findById(
//                UserTblEntity.class, 10000);


        Map<String, String> map = null;
        try {
            map = request.getBodyAs(Map.class);
        } catch (QPException e) {
            throw new QPHttpResponseException(HttpStatus.INTERNAL_SERVER_ERROR_500);
        }

        return QPHttpResponseBuilder.status(HttpStatus.OK_200)
                .entity(new SampleModel(map.get("name"), 13))
                .mediaType(HttpMediaType.APPLICATION_JSON)
                .charset(null)
                .make();
    }
}
