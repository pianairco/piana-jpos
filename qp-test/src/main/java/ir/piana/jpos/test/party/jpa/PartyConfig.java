package ir.piana.jpos.test.party.jpa;

import ir.piana.dev.jpos.qp.spring.data.QPSpringDataConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManagerFactory;

/**
 * @author Mohammad Rahmati, 1/30/2019
 */
@Configuration
@PropertySource(value = "file:./application.properties")
//@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class PartyConfig extends QPSpringDataConfig {
    @Value("${qp.spring.jpa.module.party}")
    private String qpJpaModuleName;

    @Override
    protected String getJpaModuleName() {
        return qpJpaModuleName;
    }

    @Bean(name = "PartyEntityManagerFactory")
    public EntityManagerFactory getEntityManagerFactory() {
        return super.entityManagerFactory();
    }

    @Bean(name = "PartyTransactionManager")
    protected JpaTransactionManager getTransactionManager(
            @Qualifier("PartyEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return super.transactionManager(entityManagerFactory);
    }
}
