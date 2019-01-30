package ir.piana.dev.jpos.qp.ext.http.module;

import com.google.gson.Gson;
import ir.piana.dev.jpos.qp.core.error.QPException;
import ir.piana.dev.jpos.qp.core.error.QPHttpResponseException;
import ir.piana.dev.jpos.qp.core.http.QPHttpAuthorizable;
import ir.piana.dev.jpos.qp.core.http.QPHttpRoleManageable;
import ir.piana.dev.jpos.qp.core.http.enums.HttpMethodType;
import ir.piana.dev.jpos.qp.core.http.enums.QPDefaultRequestHandlerType;
import ir.piana.dev.jpos.qp.core.module.QPAuthorizationProviderModule;
import ir.piana.dev.jpos.qp.core.http.enums.HttpMediaType;
import ir.piana.dev.jpos.qp.core.module.QPBaseModule;
import ir.piana.dev.jpos.qp.core.security.identity.QPIdentityProvider;
import ir.piana.dev.jpos.qp.core.security.role.QPRoleManager;
import org.glassfish.grizzly.http.Method;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import org.jdom2.Element;
import org.jpos.q2.QBean;
import org.jpos.space.SpaceListener;
import org.jpos.transaction.Context;
import org.jpos.util.LogEvent;
import org.jpos.util.Logger;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static ir.piana.dev.jpos.qp.core.http.enums.QPDefaultRequestHandlerType.INTERNAL_ERROR;
import static ir.piana.dev.jpos.qp.core.http.enums.QPDefaultRequestHandlerType.NOT_FOUND;

public class QPHttpRequestMultiplexorExtModule
        extends QPBaseModule
        implements SpaceListener<String, Context> {
    protected Map<String, QPHttpHandlerInfo> httpHandlerMap =
            new LinkedHashMap<>();
    protected ExecutorService listener;
    protected ExecutorService worker;
    protected Gson gson = new Gson();
//    protected HttpAuthorizationType httpAuthorizationType;

    protected String authorizationProviderModuleName;
//    protected String roleManagementModuleName;

    @Override
    protected void configBeforeRegisterQPModule() throws Exception {
        authorizationProviderModuleName = getPersist()
                .getChildText("authorization-provider-module");
//        roleManagementModuleName = authElement
//                .getChildText("role-management-module");

        getPersist().getChildren("qp-http-request")
                .parallelStream().forEach(httpRequest -> {
            try {
                String url = httpRequest.getChildText("qp-url");
                if(!url.startsWith("/"))
                    url = "/".concat(url);
                String service = httpRequest.getChildText("qp-service");
                QPHttpHandlerExt handler = (QPHttpHandlerExt)getLoader()
                        .loadClass(service).newInstance();
                Element qpRoleElement = httpRequest.getChild("qp-role");
                String defaultRoles = qpRoleElement.getChildText("default");
                String getRoles = qpRoleElement.getChildText("get");
                String postRoles = qpRoleElement.getChildText("post");
                String putRoles = qpRoleElement.getChildText("put");
                String deleteRoles = qpRoleElement.getChildText("delete");
                String headRoles = qpRoleElement.getChildText("head");
                String optionsRoles = qpRoleElement.getChildText("options");
                String traceRoles = qpRoleElement.getChildText("trace");

                QPHttpRole httpRole = new QPHttpRole(defaultRoles);
                httpRole.setRole(HttpMethodType.GET, getRoles);
                httpRole.setRole(HttpMethodType.POST, postRoles);
                httpRole.setRole(HttpMethodType.PUT, putRoles);
                httpRole.setRole(HttpMethodType.DELETE, deleteRoles);
                httpRole.setRole(HttpMethodType.HEAD, headRoles);
                httpRole.setRole(HttpMethodType.OPTIONS, optionsRoles);
                httpRole.setRole(HttpMethodType.TRACE, traceRoles);

                httpHandlerMap.put(url, new QPHttpHandlerInfo(url, handler, httpRole));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void initBeforeRegisterQPModule() throws Exception {
        listener = Executors.newSingleThreadExecutor();
        worker = Executors.newSingleThreadExecutor();
//        authorizationProvider = AuthorizationProviderFactory
//                .createAuthorizationProvider(
//                        httpAuthorizationType, getPersist());
    }

    @Override
    protected void initAfterRegisterQPModule() throws Exception {

    }

    @Override
    protected void configAfterRegisterQPModule() throws Exception {

    }

    @Override
    protected void startQPModule() throws Exception {
        listener.submit(() -> {
            while (getState() == QBean.STARTED) {
                try {
                    Context context = in(Context.class);
                    worker.execute(() -> {
                        processRequest(context);
                    });
                } catch (QPException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void stopQPModule() throws Exception {

    }

    @Override
    protected void destroyQPModule() throws Exception {

    }

    @Override
    public void notify(String s, Context context) {
        processRequest(context);
    }

    protected void processRequest(Context context) {
        Request request = null;
        Response response = null;
        try {
            request = context.get("request");
            response = context.get("response");
            QPHttpHandlerInfo handlerInfo = httpHandlerMap
                    .get(request.getRequestURI());
            if (handlerInfo == null || handlerInfo.getHttpHandlerExt() == null)
                NOT_FOUND.handle(request, response);
            else {
                invokeHttpOperator(
                        handlerInfo,
                        request, response);
            }
        } catch (QPHttpResponseException e) {
            try {
                e.sendResponse(request, response);
            } catch (Exception e1) {
                try {
                    INTERNAL_ERROR.handle(request, response);
                } catch (Exception e2) {
                    LogEvent logEvent = new LogEvent();
                    logEvent.addMessage(e2.getMessage());
                    Logger.log(logEvent);
                }
            }
        } catch (Exception e) {
            try {
                INTERNAL_ERROR.handle(request, response);
            } catch (Exception e1) {
                LogEvent logEvent = new LogEvent();
                logEvent.addMessage(e1.getMessage());
                Logger.log(logEvent);
            }
        }
    }

    protected void invokeHttpOperator(
            QPHttpHandlerInfo handlerInfo,
            Request request, Response response)
            throws Exception {
        QPHttpAuthorizable authorizable = QPBaseModule
                .getModule(authorizationProviderModuleName);
        QPHttpRequest httpRequest = new QPHttpRequest(request);
//        QPHttpRoleManageable roleManageable = authorizable
//                .authorize(httpRequest);

//        QPAuthorizationProviderModule authorizationProvider = QPBaseModule
//                .getModule(authorizationProviderModuleName);
//        QPRoleManager roleManager = QPBaseModule
//                .getModule(roleManagementModuleName);

        QPHttpHandlerExt httpHandlerExt = handlerInfo.getHttpHandlerExt();
        boolean b = true;

//        boolean b = roleManager.hasAnyRole(
//                identity, handlerInfo.getRoles()
//                        .getRole(HttpMethodType.fromCode(
//                                request.getMethod().getMethodString())));
        if(!b)
            QPDefaultRequestHandlerType.UNAUTHORIZED
                    .handle(request, response);
        if(request.getMethod().equals(Method.POST)) {
            makeResponse(response,
                    httpHandlerExt.post(new QPHttpRequest(request)));

        } else if(request.getMethod().equals(Method.GET)) {
            makeResponse(response,
                        httpHandlerExt.get(new QPHttpRequest(request)));
        } else if(request.getMethod().equals(Method.PUT)) {
            makeResponse(response,
                    httpHandlerExt.put(new QPHttpRequest(request)));
        } else if(request.getMethod().equals(Method.DELETE)) {
            makeResponse(response,
                    httpHandlerExt.delete(new QPHttpRequest(request)));
        } else if(request.getMethod().equals(Method.HEAD)) {
            makeResponse(response,
                    httpHandlerExt.head(new QPHttpRequest(request)));
        } else if(request.getMethod().equals(Method.OPTIONS)) {
            makeResponse(response,
                    httpHandlerExt.options(new QPHttpRequest(request)));
        } else if(request.getMethod().equals(Method.TRACE)) {
            makeResponse(response,
                    httpHandlerExt.trace(new QPHttpRequest(request)));
        }
    }

    protected final void makeResponse(
            Response response,
            QPHttpResponse qpHttpResponse) {
        response.setStatus(qpHttpResponse.httpStatus.getStatusCode());

        String entityString = null;
        if(!(qpHttpResponse.entity instanceof String)) {
            if (qpHttpResponse.mediaType == HttpMediaType.APPLICATION_JSON) {
                entityString = gson.toJson(qpHttpResponse.entity);
            }
        } else {
            entityString = (String)qpHttpResponse.entity;
        }
        response.setHeader("content-type",
                qpHttpResponse.mediaType.getCode()
                        .concat(";charset=")
                        .concat(qpHttpResponse.charset));
        try {
            response.getWriter().write(
                    entityString);
            response.getWriter().flush();
            response.resume();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
