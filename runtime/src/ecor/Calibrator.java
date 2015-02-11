package ecor;

public interface Calibrator {
    public int getMode(int max);

    public Object calibrate(Object input);

    public void adjust();
    
    public int supply();
}
