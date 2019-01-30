package ir.piana.dev.jpos.qp.spring.module;

import ir.piana.dev.jpos.qp.core.module.QPBaseModule;
import org.jdom2.Element;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public final class QPSpringContextProviderModule extends QPBaseModule {
    private List<Class> configBeanList;
    private List<String> configPackageList;
    private AnnotationConfigApplicationContext springContext;

    @Override
    protected void configBeforeRegisterQPModule()
            throws Exception {
        List<Element> configBeanElements = getPersist()
                .getChildren("config-bean");
        configBeanList = new ArrayList<>();
        configBeanElements.parallelStream().forEach(element -> {
            try {
                configBeanList.add(Class.forName(element.getText()));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        List<Element> configPackageElements = getPersist()
                .getChildren("config-package");
        configPackageList = new ArrayList<>();
        configPackageElements.parallelStream().forEach(element -> {
            configPackageList.add(element.getText());
        });
    }

    @Override
    protected void initBeforeRegisterQPModule() throws Exception {
        springContext = new AnnotationConfigApplicationContext();
    }

    @Override
    protected void initAfterRegisterQPModule() throws Exception {
    }

    @Override
    protected void configAfterRegisterQPModule() throws Exception {

    }

    @Override
    protected void startQPModule() throws Exception {
        configBeanList.stream().forEach(bean -> {
            springContext.register(bean);
        });
        configPackageList.stream().forEach(packageName -> {
            springContext.scan(packageName);
        });
        springContext.refresh();
    }

    @Override
    protected void stopQPModule() throws Exception {

    }

    @Override
    protected void destroyQPModule() throws Exception {

    }

    public Object getBean(String beanName) {
        return springContext.getBean(beanName);
    }

    public <T> T getBean(String beanName, Class<T> type) {
        return springContext.getBean(beanName, type);
    }

    public <T> T getBean(Class<T> type) {
        return springContext.getBean(type);
    }
}
