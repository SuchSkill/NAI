package task_03;

public class PercBuffer {
    public Perceptrone perceptrone;
    public double activation;
    public boolean isMatch;

    public PercBuffer(Perceptrone perceptrone, double activation, boolean isMatch) {
        this.perceptrone = perceptrone;
        this.activation = activation;
        this.isMatch = isMatch;
    }
}
