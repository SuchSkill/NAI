package task_04_2;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Eugene on 27-Apr-17.
 */
public class Main {
    private static Table trainTable = new Table();
    private static Table testTable = new Table();

    public static void main(String[] args) throws IOException {
        initData();

        calcTest();

        if (args!=null){
            int size = trainTable.AttributeAccuranceList.size();
            Map<String, Integer> stringIntegerMapLastColunm = trainTable.AttributeAccuranceList.get(size - 1);
            Car car = new Car();
            for (String arg : args) {
                car.attributes.add(arg);
            }
            String maxName = "";
            double maxPobability=0;
            for (String s : stringIntegerMapLastColunm.keySet()) {
                double totalProbability = 1;
                for (int i = 0; i < car.attributes.size()-1; i++) {
                    String attribute = car.attributes.get(i);
                    double probability = calcProbability(i, attribute, s);
                    totalProbability *= probability;
//                    System.out.println(probability + " " + attribute + " " + s);
                }
                if (totalProbability > maxPobability){
                    maxName = s;
                    maxPobability = totalProbability;
                }
//            System.out.println("----------");
//            System.out.println(totalProbability);
//            System.out.println();
            }
//        System.out.println("maxName = " + maxName);
//        System.out.println("maxProbability = " + maxPobability);
            String lastAttribute = car.attributes.get(car.attributes.size()-1);
            if (maxName.equals(lastAttribute)){
                System.out.println("user input match");
            }else{
                System.out.println("user input do not match");

            }

        }

    }

    private static void calcTest() {
        int size = trainTable.AttributeAccuranceList.size();
        Map<String, Integer> stringIntegerMapLastColunm = trainTable.AttributeAccuranceList.get(size - 1);
        double totalMatch = 0;
        for (Car car : testTable.cars) {
            String maxName = "";
            double maxPobability=0;
            for (String s : stringIntegerMapLastColunm.keySet()) {
                double totalProbability = 1;
                for (int i = 0; i < car.attributes.size()-1; i++) {
                    String attribute = car.attributes.get(i);
                    double probability = calcProbability(i, attribute, s);
                    totalProbability *= probability;
//                    System.out.println(probability + " " + attribute + " " + s);
                }
                if (totalProbability > maxPobability){
                    maxName = s;
                    maxPobability = totalProbability;
                }
//            System.out.println("----------");
//            System.out.println(totalProbability);
//            System.out.println();
            }
//        System.out.println("maxName = " + maxName);
//        System.out.println("maxProbability = " + maxPobability);
            String lastAttribute = car.attributes.get(car.attributes.size()-1);
            if (maxName.equals(lastAttribute)){
                totalMatch++;
            }
        }
        System.out.println();
        System.out.println("Accuracy " + totalMatch / testTable.cars.size());
    }

    private static double calcProbability(int i, String attribute, String result){
        double fullMatch = 0;
        int lastCol = 0;
        for (Car car : trainTable.cars) {
            lastCol = car.attributes.size()-1;
            if (car.attributes.get(i).equals(attribute) && car.attributes.get(lastCol).equals(result)){
                fullMatch++;
            }
        }
        double resultVariety = trainTable.AttributeAccuranceList.get(lastCol).get(result);
        double typesOfAttribute = trainTable.AttributeAccuranceList.get(i).keySet().size();
        return (fullMatch+1)/(resultVariety + typesOfAttribute);
    }

    private static void initData() throws IOException {
        String pathToTrainFolder = "C:\\Users\\Eugene\\IdeaProjects\\NAI\\src\\task_04_2\\training";
        String pathToTestFolder = "C:\\Users\\Eugene\\IdeaProjects\\NAI\\src\\task_04_2\\test";

        readFile(Paths.get(pathToTrainFolder), trainTable);
        readFile(Paths.get(pathToTestFolder), testTable);

        populateTable(trainTable);
        populateTable(testTable);


    }

    private static void populateTable(Table table) {
        int attrCount = table.cars.get(0).attributes.size();
        for (int i = 0; i < attrCount; i++) {
            Map<String, Integer> AttrAccurance = new HashMap<>();
            table.AttributeAccuranceList.add(AttrAccurance);
        }

        Integer count = 0;
        int newCount = 0;
        for (Car car : table.cars) { // go through table rows
                for (int j = 0; j < car.attributes.size(); j++) {
                    String attribute = car.attributes.get(j);
                    Map<String, Integer> stringIntegerMap = table.AttributeAccuranceList.get(j);
                    count = stringIntegerMap.get(attribute);
                    newCount = (count == null ? 1 : count + 1);
                    stringIntegerMap.put(attribute, newCount);
            }

        }
    }

    private static void readFile(Path filePath, Table table) throws IOException {


        List<String> allDocLines = new ArrayList<>();
        System.err.println(filePath);
        try {
            allDocLines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String line : allDocLines) {
            String[] split = line.split(",");
            Car car = new Car();
            for (String s : split) {
                car.attributes.add(s);

            }
            table.cars.add(car);
        }
    }
}
