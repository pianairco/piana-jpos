package ir.piana.dev.jpos.qp.core.data.database;

/**
 * @author Mohammad Rahmati, 1/22/2019
 */
public class QPQueryParamStruct {
    int order;
    String type;
    String name;

    public QPQueryParamStruct(
            int order, String type, String name) {
        this.order = order;
        this.type = type;
        this.name = name;
    }
}
