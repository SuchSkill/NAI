package task_01;

/**
 * Created by Eugene on 19-Mar-17.
 */
public class Buffer {
    public String type;
    public double dist;

    @Override
    public String toString() {
        return "task_01.Buffer{" +
                "type='" + type + '\'' +
                ", dist=" + dist +
                '}';
    }

    public Buffer(String type, double dist) {
        this.type = type;
        this.dist = dist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }
}
