package ir.piana.dev.jpos.qp.spring.data;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ir.piana.dev.jpos.qp.core.module.QPBaseModule;
import ir.piana.dev.jpos.qp.spring.module.QPJpaManagerModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * @author Mohammad Rahmati, 1/22/2019
 */
@Configuration
@PropertySource(value = "file:./application.properties")
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public abstract class QPSpringDataConfig {
    protected abstract String getJpaModuleName();

    protected final EntityManagerFactory entityManagerFactory() {
        QPJpaManagerModule module = QPBaseModule.getModule(getJpaModuleName());
        return module.getEntityManagerFactory();
    }

    protected final JpaTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
