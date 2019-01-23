package ir.piana.dev.jpos.qp.core.data.database;

/**
 * @author Mohammad Rahmati, 12/12/2018
 */
public class FieldInfo {
    private String name;
    private int number;
    private boolean isString;
    private String sqlType;
    private int length;
    private boolean mandatory;

    public FieldInfo(
            String name,
            int number,
            boolean isString,
            String sqlType,
            int length,
            boolean mandatory) {
        this.name = name;
        this.number = number;
        this.isString = isString;
        this.sqlType = sqlType;
        this.length = length;
        this.mandatory = mandatory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isString() {
        return isString;
    }

    public void setString(boolean isString) {
        this.isString = isString;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }
}
