package ir.piana.dev.jpos.qp.core.security.role;

import ir.piana.dev.jpos.qp.core.error.QPException;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
public enum QPRoleManagementType {
    IN_MEMORY("in-memory"),
    DATABASE("database");

    private String code;

    QPRoleManagementType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static QPRoleManagementType fromCode(String code)
            throws QPException {
        for(QPRoleManagementType type : QPRoleManagementType.values()) {
            if(type.code.equalsIgnoreCase(code))
                return type;
        }
        throw new QPException(code + " not defined");
    }
}
