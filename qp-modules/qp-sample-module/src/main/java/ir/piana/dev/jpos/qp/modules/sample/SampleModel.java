package ir.piana.dev.jpos.qp.modules.sample;

/**
 * @author Mohammad Rahmati, 1/19/2019
 */
public class SampleModel {
    private String name;
    private int average;

    public SampleModel() {
    }

    public SampleModel(String name, int average) {
        this.name = name;
        this.average = average;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAverage() {
        return average;
    }

    public void setAverage(int average) {
        this.average = average;
    }
}
