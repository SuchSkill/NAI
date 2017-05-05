package task_04_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Table {
    List<Car> cars = new ArrayList<>();
    //accurances of each column
    List<Map<String, Integer>> AttributeAccuranceList = new ArrayList<>();

    @Override
    public String toString() {
        return "Table{" +
                "cars=" + cars +
                ", AttributeAccuranceList=" + AttributeAccuranceList +
                '}'+'\n';
    }
}
