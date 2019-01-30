package ir.piana.jpos.test.jpa;

import ir.piana.dev.jpos.qp.spring.data.SpringDataConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mohammad Rahmati, 1/30/2019
 */
@Configuration
//@PropertySource(value = "file:./application.properties")
//@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class JudicatureConfig extends SpringDataConfig {
    @Value("${qp.spring.jpa.module}")
    private String qpJpaModuleName;

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
