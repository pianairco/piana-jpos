package ir.piana.dev.jpos.qp.core.module;

import ir.piana.dev.jpos.qp.core.http.DefaultRequestHandlerType;
import org.glassfish.grizzly.http.server.*;
import org.jpos.transaction.Context;

public class QPHttpServerModule extends QPBaseModule {
    private String host;
    private int port;
    private HttpServer server;


    @Override
    protected void configQPModule() throws Exception {
        host = cfg.get("host", "localhost");
        port = cfg.getInt("port", 9090);
    }

    @Override
    protected void initQPModule() throws Exception {
        server = new HttpServer();
        final ServerConfiguration config = server.getServerConfiguration();
        final NetworkListener listener =
                new NetworkListener("grizzly",
                        host,
                        port);
        server.addListener(listener);
        server.getServerConfiguration().addHttpHandler(
                new HttpHandler() {
                    public void service(Request request, Response response) throws Exception {
                        response.suspend();
                        Context context = new Context();
                        context.put("request", request);
                        context.put("response", response);

                        new Thread(() -> {
                            try {
                                Thread.sleep(10000l);
                                DefaultRequestHandlerType.INTERNAL_ERROR.handle(request, response);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }).start();
                        out(context);
                    }
                });
    }

    @Override
    protected void startService() throws Exception {
        try {
            server.start();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    protected void stopService() throws Exception {
    }

    @Override
    protected void destroyService() throws Exception {
    }
}