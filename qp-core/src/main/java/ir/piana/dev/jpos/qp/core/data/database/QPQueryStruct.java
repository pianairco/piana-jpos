package ir.piana.dev.jpos.qp.core.data.database;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Mohammad Rahmati, 1/22/2019
 */
public class QPQueryStruct {
    String query;
    List<QPQueryParamStruct> params;

    public QPQueryStruct(
            String query,
            List<QPQueryParamStruct> params) {
        this.query = query;
        this.params = params;
    }

    public String getParamName(int index) {
        return params.get(index).name;
    }
}
