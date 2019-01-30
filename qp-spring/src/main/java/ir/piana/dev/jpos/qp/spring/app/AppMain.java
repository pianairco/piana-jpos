package ir.piana.dev.jpos.qp.spring.app;

import ir.piana.dev.jpos.qp.spring.data.dao.QPBaseDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Mohammad Rahmati, 1/22/2019
 */
public class AppMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        SpringConfig.class);
//        UserRepository userRepository =
//                context.getBean(UserRepository.class);
//        UserTblEntity byId = userRepository.findById(10000l);

        QPBaseDao baseDao = (QPBaseDao)
                context.getBean("QPBaseDao");
//        ((QPBaseDao) baseDao).findById(UserTblEntity.class, 10000l);
        EntityManagerFactory entityManagerFactory =
                (EntityManagerFactory)context
                        .getBean("entityManagerFactory");
        if (entityManagerFactory != null) {
            EntityManager entityManager = entityManagerFactory
                    .createEntityManager();
//            UserTblEntity userTblEntity = entityManager.find(UserTblEntity.class, 10000l);
            Query query = entityManager.createQuery(
                    "select t from UserTblEntity t");
            List resultList = query.getResultList();
            if(resultList != null && !resultList.isEmpty()) {
//                UserTblEntity u = (UserTblEntity) resultList.get(0);
//                System.out.println(u.getUsername());
            }
        }
    }
}
