package task_02;

public class Perseptron {
    private double wx;
    private double wy;
    private double wz;
    private double wk;
    private double treshhold;

    public double getWx() {
        return wx;
    }

    public void setWx(double wx) {
        this.wx = wx;
    }

    public double getWy() {
        return wy;
    }

    public void setWy(double wy) {
        this.wy = wy;
    }

    public double getWz() {
        return wz;
    }

    public void setWz(double wz) {
        this.wz = wz;
    }

    @Override
    public String toString() {
        return "Perseptron{" +
                "wx=" + wx +
                ", wy=" + wy +
                ", wz=" + wz +
                ", wk=" + wk +
                ", treshhold=" + treshhold +
                '}';
    }

    public double getWk() {
        return wk;
    }

    public void setWk(double wk) {
        this.wk = wk;
    }

    public double getTreshhold() {
        return treshhold;
    }

    public void setTreshhold(double treshhold) {
        this.treshhold = treshhold;
    }

    public Perseptron(double wx, double wy, double wz, double wk, double treshhold) {
        this.wx = wx;
        this.wy = wy;
        this.wz = wz;
        this.wk = wk;
        this.treshhold = treshhold;
    }
}
