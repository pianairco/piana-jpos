package ir.piana.dev.jpos.qp.spring.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
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
@ComponentScan
//@ComponentScan(value="ir.piana.dev.jpos.qp.spring.data")
@EnableJpaRepositories
//@EnableJpaRepositories(basePackages="ir.piana.dev.jpos.qp.spring.data")
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@PropertySource(value = "classpath:application.properties")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringConfig {
    @Value("${persistence.unit.name}")
    private String persistenceUnitName;

    @Value("${jpa.vendor.adaptor.name}")
    private String jpaVendorAdaptorName;

    @Value("${database.platform.name}")
    private String databasePlatformName;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    BeanPostProcessor getBeanPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

    @Bean
    String getDatabasePlatformName() {
        if(databasePlatformName.equalsIgnoreCase("mysql"))
            return "org.eclipse.persistence.platform.database.MySQLPlatform";
        else if(databasePlatformName.equalsIgnoreCase("oracle"))
            return "org.eclipse.persistence.platform.database.Oracle11Platform";
        return null;
    }

    EclipseLinkJpaVendorAdapter eclipseLinkJpaVendorAdapter(
            String getDatabasePlatformName) {
        EclipseLinkJpaVendorAdapter vendorAdapter =
                new EclipseLinkJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform(databasePlatformName);
//        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setDatabase(Database.ORACLE);
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }

    JpaVendorAdapter hibernateJpaVendorAdapter(
            String databasePlatformName) {
        HibernateJpaVendorAdapter vendorAdapter =
                new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
//        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }

    @Bean
    JpaVendorAdapter jpaVendorAdapter() {
        if(jpaVendorAdaptorName.equalsIgnoreCase("eclipselink")) {
            EclipseLinkJpaVendorAdapter adapter = eclipseLinkJpaVendorAdapter(
                    getDatabasePlatformName());
            return adapter;
        } else if(jpaVendorAdaptorName.equalsIgnoreCase("hibernate"))
            return hibernateJpaVendorAdapter(getDatabasePlatformName());
        return null;
    }

//    @Bean
//    public EntityManagerFactory entityManagerFactory(
//            JpaVendorAdapter vendorAdapter) {
//        LocalEntityManagerFactoryBean factory =
//                new LocalEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPersistenceUnitName(persistenceUnitName);
//
//        // This will trigger the creation of the product manager factory
//        factory.afterPropertiesSet();
//        return factory.getObject();
//    }

    @Bean
    public DataSource getDataSource() {
        Properties props = new Properties();
        props.setProperty("jdbcUrl", "jdbc:oracle:thin:@192.168.50.56:1521:dmloradb");
        props.setProperty("driverClassName", "oracle.jdbc.driver.OracleDriver");
        props.setProperty("dataSource.user", "judicature");
        props.setProperty("dataSource.password", "123456");
        props.setProperty("dataSource.databaseName", "dmloradb");
        props.put("dataSource.logWriter", new PrintWriter(System.out));
        HikariConfig hikariConfig = new HikariConfig(props);
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        hikariDataSource.setMaximumPoolSize(100);
        return hikariDataSource;
    }

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory(
            JpaVendorAdapter vendorAdapter, DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();
//        factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        factory.setDataSource(dataSource);
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.getJpaPropertyMap()
                .put("eclipselink.weaving", "false");
        factory.setPersistenceUnitName(persistenceUnitName);

        // This will trigger the creation of the product manager factory
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    public JpaTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
