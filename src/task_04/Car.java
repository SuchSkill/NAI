package task_04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Eugene on 27-Apr-17.
 */
public class Car {
    public static List<Map<String,Integer>> countMapList = new ArrayList<>();
    public static List<String> attrList = new ArrayList<>();
    public static Map<String, Integer> difBuyPrice = new HashMap<>();
    public String buyPrice;
    public static Map<String, Integer> difMaintainancePrice = new HashMap<>();
    public String maintainancePrice;
    public static Map<String, Integer> difNumDoors = new HashMap<>();
    public String numDoors;
    public static Map<String, Integer> difSpace = new HashMap<>();
    public String space;
    public static Map<String, Integer> difLaggage = new HashMap<>();
    public String laggage;
    public static Map<String, Integer> difSafty = new HashMap<>();
    public String safty;
    public static Map<String, Integer> difCarClass = new HashMap<>();
    public String carClass;

    public static void print(){
        for (Map<String, Integer> stringIntegerMap : countMapList) {
            print(stringIntegerMap);
        }
    }
    public static void print(Map<String, Integer> m){
        for (String s : m.keySet()) {
            Integer i = m.get(s);
            System.out.print(s + " ");
            System.out.println(i);
        }
        System.out.println();
    }
    @Override
    public String toString() {
        return "Car{" +
                "buyPrice='" + buyPrice + '\'' +
                ", maintainancePrice='" + maintainancePrice + '\'' +
                ", numDoors='" + numDoors + '\'' +
                ", space='" + space + '\'' +
                ", laggage='" + laggage + '\'' +
                ", safty='" + safty + '\'' +
                ", carClass='" + carClass + '\'' +
                '}'+'\n';
    }


}
