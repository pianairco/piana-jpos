package ir.piana.dev.jpos.qp.core.security.identity;

import ir.piana.dev.jpos.qp.core.error.QPException;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
public enum QPIdentityManagementType {
    IN_MEMORY("in-memory"),
    DATABASE("database");

    private String code;

    QPIdentityManagementType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static QPIdentityManagementType fromCode(String code)
            throws QPException {
        for(QPIdentityManagementType type : QPIdentityManagementType.values()) {
            if(type.code.equalsIgnoreCase(code))
                return type;
        }
        throw new QPException(code + " not defined");
    }
}
