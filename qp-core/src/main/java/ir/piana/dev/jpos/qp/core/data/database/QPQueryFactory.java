package ir.piana.dev.jpos.qp.core.data.database;

import ir.piana.dev.jpos.qp.core.error.QPException;
import org.jdom2.DataConversionException;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammad Rahmati, 1/22/2019
 */
public class QPQueryFactory {
    public static QPQueryStruct createQueryStruct(
            Element queryStructConfig)
            throws QPException {
        try {
            String query = queryStructConfig.getChildText("query");
            List<QPQueryParamStruct> paramStructList = new ArrayList<>();
            for(Element paramElement : queryStructConfig.getChildren("param")) {
                int order = paramElement.getAttribute("order").getIntValue();
                String type = paramElement.getAttribute("type").getValue();
                String name = paramElement.getValue();
                paramStructList.add(new QPQueryParamStruct(order,type, name));
            }
            return new QPQueryStruct(query, paramStructList);
        } catch (DataConversionException e) {
            e.printStackTrace();
            throw new QPException();
        }
    }
}
