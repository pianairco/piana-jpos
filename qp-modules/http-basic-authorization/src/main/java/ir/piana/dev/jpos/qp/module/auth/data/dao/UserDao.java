package ir.piana.dev.jpos.qp.module.auth.data.dao;

import ir.piana.dev.jpos.qp.core.module.QPBaseModule;
import ir.piana.dev.jpos.qp.spring.data.dao.QPBaseDao;
import ir.piana.dev.jpos.qp.spring.module.QPSpringContextProviderModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author Mohammad Rahmati, 1/30/2019
 */

@Component(value = "http-basic-authorization-user-dao")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserDao extends QPBaseDao {
    @Value("${qp.http.basic.autorization.entity-manager-factory}")
    private String entityManagerFactoryName;

    @Value("${qp.http.basic.autorization.spring-provider-module}")
    private String springProviderModuleName;

//    @PersistenceContext(
//            unitName = persistenceUnit,
//            type = PersistenceContextType.TRANSACTION)
    protected EntityManager entityManager;

    @PostConstruct
    protected void init() {
        QPSpringContextProviderModule springProvider = QPBaseModule
                .getModule(springProviderModuleName);
        EntityManagerFactory factory = (EntityManagerFactory)springProvider
                .getBean(entityManagerFactoryName);
        entityManager = factory.createEntityManager();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public synchronized CriteriaBuilder getCriteriaBuilder() {
        if(criteriaBuilder == null)
            criteriaBuilder = entityManager.getCriteriaBuilder();
        return criteriaBuilder;
    }
}
