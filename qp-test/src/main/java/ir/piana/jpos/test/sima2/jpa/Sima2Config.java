package ir.piana.jpos.test.sima2.jpa;

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
public class Sima2Config extends QPSpringDataConfig {
    @Value("${qp.spring.jpa.module.sima2}")
    private String qpJpaModuleName;

    @Override
    protected String getJpaModuleName() {
        return qpJpaModuleName;
    }

    @Bean(name = "sima2EntityManagerFactory")
    public EntityManagerFactory getEntityManagerFactory() {
        return super.entityManagerFactory();
    }

    @Bean(name = "sima2TransactionManager")
    protected JpaTransactionManager getTransactionManager(
            @Qualifier("sima2EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return super.transactionManager(entityManagerFactory);
    }
}
