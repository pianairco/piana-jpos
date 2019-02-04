package ir.piana.dev.jpos.qp.module.auth.data;

import ir.piana.dev.jpos.qp.spring.data.QPSpringDataConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManagerFactory;

/**
 * @author Mohammad Rahmati, 1/30/2019
 */
@Configuration
//@PropertySource(value = "file:./application.properties")
//@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class QpConfig extends QPSpringDataConfig {
    @Value("${qp.spring.jpa.module.qp}")
    private String qpJpaModuleName;

    @Bean(name = "qp-entity-manager-factory")
    public EntityManagerFactory getEntityManagerFactory() {
        return super.entityManagerFactory();
    }

    @Bean(name = "qpTransactionManager")
    protected JpaTransactionManager getTransactionManager(
            @Qualifier("qp-entity-manager-factory") EntityManagerFactory entityManagerFactory) {
        return super.transactionManager(entityManagerFactory);
    }

    @Override
    protected String getJpaModuleName() {
        return qpJpaModuleName;
    }

//    @Bean(name = "entityManagerFactory")
//    public EntityManagerFactory entityManagerFactory() {
//        QPJpaManagerModule module = QPBaseModule.getModule(qpJpaModuleName);
//        return module.getEntityManagerFactory();
//    }
//
//    @Bean
//    public JpaTransactionManager transactionManager(
//            EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory);
//        return txManager;
//    }
}
