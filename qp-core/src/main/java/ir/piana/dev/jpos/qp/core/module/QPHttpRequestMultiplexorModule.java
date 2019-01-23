package ir.piana.dev.jpos.qp.core.module;

import ir.piana.dev.jpos.qp.core.error.QPException;
import ir.piana.dev.jpos.qp.core.http.QPHttpOperator;
import ir.piana.dev.jpos.qp.core.http.QPHttpHandler;
import org.glassfish.grizzly.http.Method;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import org.jpos.q2.QBean;
import org.jpos.space.SpaceListener;
import org.jpos.transaction.Context;
import org.jpos.util.LogEvent;
import org.jpos.util.Logger;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static ir.piana.dev.jpos.qp.core.http.QPDefaultRequestHandlerType.INTERNAL_ERROR;
import static ir.piana.dev.jpos.qp.core.http.QPDefaultRequestHandlerType.NOT_FOUND;

public class QPHttpRequestMultiplexorModule
        extends QPBaseModule
        implements SpaceListener<String, Context> {
    protected Map<String, QPHttpOperator> httpHandlerMap =
            new LinkedHashMap<>();
    protected ExecutorService listener;
    protected ExecutorService worker;

    @Override
    protected void configBeforeRegisterQPModule() throws Exception {
        getPersist().getChildren("qp-http-request")
                .parallelStream().forEach(httpRequest -> {
            try {
                String url = httpRequest.getChildText("qp-url");
                if(!url.startsWith("/"))
                    url = "/".concat(url);
                String service = httpRequest.getChildText("qp-service");
                QPHttpOperator handler = (QPHttpOperator)getLoader().loadClass(service).newInstance();
                httpHandlerMap.put(url, handler);
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
    protected void stopService() throws Exception {

    }

    @Override
    protected void destroyService() throws Exception {

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
            QPHttpOperator qpHttpHandler = httpHandlerMap.get(request.getRequestURI());
            if (qpHttpHandler == null)
                NOT_FOUND.handle(request, response);
            else {
                if(qpHttpHandler instanceof QPHttpHandler) {
                    invokeHttpOperator((QPHttpHandler) qpHttpHandler, request, response);
                } else {
                    qpHttpHandler.service(request, response);
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
            QPHttpHandler qpHttpOperator,
            Request request, Response response)
            throws Exception {
        if(request.getMethod().equals(Method.POST)) {
            qpHttpOperator.post(request, response);
        } else if(request.getMethod().equals(Method.GET)) {
            qpHttpOperator.get(request, response);
        } else if(request.getMethod().equals(Method.PUT)) {
            qpHttpOperator.put(request, response);
        } else if(request.getMethod().equals(Method.DELETE)) {
            qpHttpOperator.delete(request, response);
        } else if(request.getMethod().equals(Method.HEAD)) {
            qpHttpOperator.head(request, response);
        } else if(request.getMethod().equals(Method.OPTIONS)) {
            qpHttpOperator.options(request, response);
        } else if(request.getMethod().equals(Method.TRACE)) {
            qpHttpOperator.trace(request, response);
        }
    }
}
