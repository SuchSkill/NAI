package task_05_K_means;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Flower5 {
    String name;
    int number;
    List<Double> coordinates = null;
    List<Double> buffer = new ArrayList<>();

    public void lock(){
        coordinates = Collections.unmodifiableList(buffer);
    }
    @Override
    public String toString() {
        return "Flower5{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", coordinates=" + coordinates +
                '}' + '\n';
    }
}
