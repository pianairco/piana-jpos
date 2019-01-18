package ir.piana.dev.jpos.qp.core.participant;

import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

import java.io.IOException;
import java.io.Serializable;

public class HttpParticipant implements TransactionParticipant{

    @Override
    public int prepare(long l, Serializable serializable) {
        try {
            Response response = (Response)((Context)serializable).get("response");
            Request request = (Request) ((Context)serializable).get("request");
            response.getWriter().write("HelloWorld!");
            response.getWriter().flush();
            response.resume();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void commit(long l, Serializable serializable) {

    }

    @Override
    public void abort(long l, Serializable serializable) {

    }
}
