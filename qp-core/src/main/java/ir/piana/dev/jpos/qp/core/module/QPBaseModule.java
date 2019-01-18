package ir.piana.dev.jpos.qp.core.module;

import ir.piana.dev.jpos.qp.core.error.QPException;
import org.jpos.q2.QBeanSupport;
import org.jpos.space.LocalSpace;
import org.jpos.space.Space;
import org.jpos.space.SpaceFactory;
import org.jpos.space.SpaceListener;

import java.util.Arrays;

public abstract class QPBaseModule extends QBeanSupport {
    private Space space;
    private String spaceName;
    private String inQueue;
    private String outQueue;

    @Override
    protected final void initService() throws Exception {
        spaceName = cfg.get("qp-space", "tspace:default");
        String defaultQueue = "";
        for (String name : this.getClass().getSimpleName()
                .split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])")) {
            defaultQueue = defaultQueue
                    .concat(name.toLowerCase())
                    .concat("-");
        }
        inQueue = cfg.get("qp-in", defaultQueue.concat("in"));
        outQueue = cfg.get("qp-out", defaultQueue.concat("out"));
        space = SpaceFactory.getSpace(spaceName);
        configQPModule();
        initQPModule();
    }

    protected abstract void configQPModule() throws Exception;

    protected abstract void initQPModule() throws Exception;

    protected final void out(Object object) {
        space.out(outQueue, object);
    }

    protected final void setSpaceListener(SpaceListener spaceListener) {
        ((LocalSpace)space).addListener(inQueue, spaceListener);
    }

    protected Object in() {
        return space.in(inQueue);
    }

    protected <T> T in(Class objType)
            throws QPException {
        Object in = space.in(inQueue);
        if(objType.isInstance(in))
            return (T) in;
        throw new QPException("type conversion error");
    }
}
