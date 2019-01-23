package ir.piana.dev.jpos.qp.core.data.database;

/**
 * @author Mohammad Rahmati, 12/12/2018
 */
public class PartitionInfo {
    private String name;
    private int interval;
    private String type;
    private String columnType;
    private int emptyValue;

    public PartitionInfo(String name, int interval, String type, String columnType, int emptyValue) {
        this.name = name;
        this.interval = interval;
        this.type = type;
        this.columnType = columnType;
        this.emptyValue = emptyValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEmptyValue() {
        return emptyValue;
    }

    public void setEmptyValue(int emptyValue) {
        this.emptyValue = emptyValue;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }
}
