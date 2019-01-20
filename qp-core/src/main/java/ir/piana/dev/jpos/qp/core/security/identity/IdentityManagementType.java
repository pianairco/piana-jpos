package ir.piana.dev.jpos.qp.core.security.identity;

import ir.piana.dev.jpos.qp.core.error.QPException;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
public enum IdentityManagementType {
    IN_MEMORY("in-memory"),
    DATABASE("database");

    private String code;

    IdentityManagementType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static IdentityManagementType fromCode(String code)
            throws QPException {
        for(IdentityManagementType type : IdentityManagementType.values()) {
            if(type.code.equalsIgnoreCase(code))
                return type;
        }
        throw new QPException(code + " not defined");
    }
}
