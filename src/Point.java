/**
 * Created by Eugene on 19-Mar-17.
 */
public class Point {
    private float x;
    private float y;
    private float z;
    private float k;
    private String type;

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", k=" + k +
                ", type='" + type + '\'' +
                '}'+
                '\n';
    }

    public Point(float x, float y, float z, float k, String type) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.k = k;
        this.type = type;
    }

    public float getX() {

        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getK() {
        return k;
    }

    public void setK(float k) {
        this.k = k;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
