package ir.piana.dev.jpos.qp.core.data.jpa;

import java.util.List;

/**
 * @author Mohammad Rahmati, 1/30/2019
 */
public class JpaInfo {
    private String instanceName;
    private String datasourceManagerName;
    private String datasourceInstanceName;
    private String databasePlatform;
    private String jpaProvider;
    private String persistenceUnitName;
    private List<String> basePackages;

    public JpaInfo(
            String instanceName,
            String datasourceManagerName,
            String datasourceInstanceName,
            String databasePlatform,
            String jpaProvider,
            String persistenceUnitName,
            List<String> basePackages) {
        this.instanceName = instanceName;
        this.datasourceManagerName = datasourceManagerName;
        this.datasourceInstanceName = datasourceInstanceName;
        this.databasePlatform = databasePlatform;
        this.jpaProvider = jpaProvider;
        this.persistenceUnitName = persistenceUnitName;
        this.basePackages = basePackages;
    }

    public String getDatasourceManagerName() {
        return datasourceManagerName;
    }

    public void setDatasourceManagerName(String datasourceManagerName) {
        this.datasourceManagerName = datasourceManagerName;
    }

    public String getDatasourceInstanceName() {
        return datasourceInstanceName;
    }

    public void setDatasourceInstanceName(String datasourceInstanceName) {
        this.datasourceInstanceName = datasourceInstanceName;
    }

    public String getDatabasePlatform() {
        return databasePlatform;
    }

    public void setDatabasePlatform(String databasePlatform) {
        this.databasePlatform = databasePlatform;
    }

    public String getJpaProvider() {
        return jpaProvider;
    }

    public void setJpaProvider(String jpaProvider) {
        this.jpaProvider = jpaProvider;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getPersistenceUnitName() {
        return persistenceUnitName;
    }

    public void setPersistenceUnitName(String persistenceUnitName) {
        this.persistenceUnitName = persistenceUnitName;
    }

    public List<String> getBasePackages() {
        return basePackages;
    }

    public void setBasePackages(List<String> basePackages) {
        this.basePackages = basePackages;
    }
}
