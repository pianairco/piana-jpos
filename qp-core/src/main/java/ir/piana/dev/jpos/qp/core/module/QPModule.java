package ir.piana.dev.jpos.qp.core.module;

import org.jpos.util.NameRegistrar;

/**
 * @author Mohammad Rahmati, 1/20/2019
 */
public interface QPModule {
    String KIND_CHANNEL_ADAPTOR = "channel-adaptor";
    String KIND_QSERVER = "server";
    String KIND_QBEAN = "qbean";
    String KIND_LOGGER = "logger";
    String KIND_MUX = "mux";
    String KIND_TRANSACTION_MANAGER = ""/*"txnmgr"*/;
    String DEFAULT_QUEUE_SUFFIX = "-queue";

    @SuppressWarnings("unchecked")
    default <T> T getInstance() {
        try {
            return (T) (KIND_QBEAN.equals(kind()) ? NameRegistrar.get(nameId())
                    : NameRegistrar.get((!"".equals(kind())
                    ? kind() + "." : "") + nameId()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    default <T> T tryInstance() {
        return (T) (KIND_QBEAN.equals(kind())
                ? NameRegistrar.getIfExists(nameId())
                : NameRegistrar.getIfExists(kind() + "." + nameId()));
    }

    //    String inputQueue();

    String nameId();

    String kind();

    Class<?> implementation();
}
