package ir.piana.dev.jpos.qp.core.security.role;

import ir.piana.dev.jpos.qp.core.error.QPException;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
public enum RoleManagementType {
    IN_MEMORY("in-memory"),
    DATABASE("database");

    private String code;

    RoleManagementType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static RoleManagementType fromCode(String code)
            throws QPException {
        for(RoleManagementType type : RoleManagementType.values()) {
            if(type.code.equalsIgnoreCase(code))
                return type;
        }
        throw new QPException(code + " not defined");
    }
}
