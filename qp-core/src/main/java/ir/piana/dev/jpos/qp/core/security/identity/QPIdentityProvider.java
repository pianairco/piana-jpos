package ir.piana.dev.jpos.qp.core.security.identity;

import ir.piana.dev.jpos.qp.core.error.QPException;

import java.util.Map;

/**
 * @author Mohammad Rahmati, 1/20/2019
 */
public interface QPIdentityProvider {
    String provide(
            Map<String, Object> subjectMap)
            throws QPException;
}
