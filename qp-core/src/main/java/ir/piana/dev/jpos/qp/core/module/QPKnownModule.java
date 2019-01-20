package ir.piana.dev.jpos.qp.core.module;

/**
 * @author Mohammad Rahmati, 1/20/2019
 */
public enum QPKnownModule implements QPModule {
    QP_ROLE_MANAGEMENT(
            "qp-role-management", KIND_QBEAN,
            "com.dml.sima3.core.module.MobileCommandArranger");


    private String name;
    private String kind;
    private String classFqn;

    QPKnownModule(
            String name, String kind,
            String classFqn) {
        this.name = name;
        this.kind = kind;
        this.classFqn = classFqn;
    }

    @Override
    public String nameId() {
        return name;
    }

    @Override
    public String kind() {
        return kind;
    }

    @Override
    public Class<?> implementation() {
        try {
            return Class.forName(classFqn);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
