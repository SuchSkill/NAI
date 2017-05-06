package task_05_K_means;

import java.util.ArrayList;
import java.util.List;

public class Centroid {
    String name;
    List<Double> CentroidCoordinates = new ArrayList<>();
    List<Flower5> content = new ArrayList<>();

    @Override
    public String toString() {
        return "Centroid{" +
                "name='" + name + '\'' +
                ", CentroidCoordinates=" + CentroidCoordinates +
                ", content=" + content +
                '}'+'\n';
    }

    public void recalculateCoordinates() {
        if(content.size()>0) {
            for (int i = 0; i < CentroidCoordinates.size(); i++) {
                CentroidCoordinates.set(i, 0d);
            }
        }else{
            return;
        }
        for (Flower5 flower : content) {
            for (int i = 0; i < flower.coordinates.size(); i++) {
                Double v = CentroidCoordinates.get(i);
                CentroidCoordinates.set(i, v+flower.coordinates.get(i));
            }
        }
        for (int i = 0; i < CentroidCoordinates.size(); i++) {
            Double aDouble = CentroidCoordinates.get(i);
            CentroidCoordinates.set(i, aDouble/content.size());
        }
    }
}
