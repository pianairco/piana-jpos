package ir.piana.dev.jpos.qp.core.data.database;

import java.util.List;

/**
 * @author Mohammad Rahmati, 12/12/2018
 */
public class UniqueIndexInfo {
    private String name;
    private boolean isLocal;
    private List<Integer> fields;

    public UniqueIndexInfo(String name, List<Integer> fields, boolean isLocal) {
        this.name = name;
        this.fields = fields;
        this.isLocal = isLocal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getFields() {
        return fields;
    }

    public void setFields(List<Integer> fields) {
        this.fields = fields;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }
}
