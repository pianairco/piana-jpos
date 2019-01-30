package ir.piana.jpos.test.dao;

import ir.piana.dev.jpos.qp.spring.data.dao.QPBaseDao;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author Mohammad Rahmati, 1/30/2019
 */

@Component(value = "UserDao")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserDao extends QPBaseDao {
    @PersistenceContext(
            unitName = "first-pu",
            type = PersistenceContextType.TRANSACTION)
    EntityManager entityManager;

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
