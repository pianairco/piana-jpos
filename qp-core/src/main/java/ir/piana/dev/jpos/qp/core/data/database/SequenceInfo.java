package ir.piana.dev.jpos.qp.core.data.database;

/**
 * @author Mohammad Rahmati, 12/12/2018
 */
public class SequenceInfo {
    private String name;
    private long minValue;
    private long maxValue;
    private long startWith;
    private int incrementBy;
    private int cacheSize;

    public SequenceInfo(
            String name,
            long minValue, long maxValue,
            long startWith, int incrementBy, int cacheSize) {
        this.name = name;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.startWith = startWith;
        this.incrementBy = incrementBy;
        this.cacheSize = cacheSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMinValue() {
        return minValue;
    }

    public void setMinValue(long minValue) {
        this.minValue = minValue;
    }

    public long getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(long maxValue) {
        this.maxValue = maxValue;
    }

    public long getStartWith() {
        return startWith;
    }

    public void setStartWith(long startWith) {
        this.startWith = startWith;
    }

    public int getIncrementBy() {
        return incrementBy;
    }

    public void setIncrementBy(int incrementBy) {
        this.incrementBy = incrementBy;
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }
}
