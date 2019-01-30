package ir.piana.dev.jpos.qp.ext.http.module;

import com.google.gson.Gson;
import ir.piana.dev.jpos.qp.core.error.QPException;
import ir.piana.dev.jpos.qp.core.http.enums.HttpMediaType;
import ir.piana.dev.jpos.qp.core.http.enums.HttpMethodType;
import org.glassfish.grizzly.http.server.Request;

import java.util.*;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
public class QPHttpRequest {
    private Request request;

    HttpMethodType httpMethodType;
    String body;
    HttpMediaType contentType;
    Map<String, String[]> queryParams;
    Object bodyObject;

    QPHttpRequest(Request request)
            throws QPException {
        this.request = request;
        httpMethodType = HttpMethodType.fromCode(
                request.getMethod().getMethodString());
        if(httpMethodType == HttpMethodType.POST ||
                httpMethodType == HttpMethodType.PUT) {
            contentType = HttpMediaType
                    .fromCode((String) request
                            .getHeader("content-type"));
        }
        queryParams = Collections.unmodifiableMap(
                request.getParameterMap());
    }

    private void readBody() {
        if(body == null && httpMethodType.canHasBody()) {
            body = "";
            Scanner scanner = new Scanner(request.getInputStream());
            while(scanner.hasNextLine()) {
                body = body
                        .concat(scanner.nextLine());
            }
        }
    }

    public <T> T getBodyAs(Class classType)
            throws QPException {
        return getBodyAs(classType, false);
    }

    public <T> T getBodyAs(Class classType, boolean isClone)
            throws QPException {
        if(!isClone && bodyObject != null)
            return (T)bodyObject;
        if(!httpMethodType.canHasBody())
            throw new QPException("not have body");
        T bodyObject;
        readBody();
        try {
            switch (contentType) {
                case APPLICATION_JSON:
                    bodyObject = (T) new Gson().fromJson(body, classType);
                    break;
                case APPLICATION_XML:
                case APPLICATION_FORM_URLENCODED:
                case TEXT_PLAIN:
                case TEXT_HTML:
                    throw new QPException("not supported yet!");
                default:
                    throw new QPException("not supported yet!");

            }
        } catch (Exception e) {
            throw new QPException("body of json is incorrect!");
        }
        if(!isClone)
            this.bodyObject = bodyObject;
        return bodyObject;
    }

    public String getHeader(String name) {
        return request.getHeader(name);
    }

    public String[] getParam(String name) {
        return queryParams.get(name);
    }

    public String getFirstParam(String name) {
        String[] strings = queryParams.get(name);
        return  strings != null && strings.length > 0 ?
                strings[0] : null;
    }

    public Set<String> getNameOfParams() {
        return queryParams.keySet();
    }
}
