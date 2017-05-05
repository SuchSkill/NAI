package task_04;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Eugene on 27-Apr-17.
 */
public class Main {
    private static List<Car> trainList = new ArrayList<>();
    private static List<Car> testList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        initData();

        int i = 1;

        double trainClassCarCount = Car.countMapList.get(6).keySet().size();
        System.out.println(trainClassCarCount);
        for (Car testCar : testList) {
            double fullMatch = 0;
            String s = "";
            Integer numberOfSecondAttr = 0;
            for (Car trainCar : trainList) {
                if (testCar.buyPrice.equals(trainCar.buyPrice)) {
                    fullMatch++;
                }
                s = testCar.carClass;
                numberOfSecondAttr = Car.difCarClass.get(s);

            }
            int buyPriceAttributeVarayety = Car.difBuyPrice.size();
            System.out.println(i+ "   " +fullMatch);
            System.out.println(numberOfSecondAttr);
            double result1 = (fullMatch+1)/(buyPriceAttributeVarayety + numberOfSecondAttr);
            System.out.println(testCar.buyPrice + " | " + testCar.carClass + " " + result1);


            double fullMatch2 = 0;
            for (Car trainCar : trainList) {
                if (testCar.maintainancePrice.equals(trainCar.maintainancePrice) &&
                        testCar.carClass.equals(trainCar.carClass)) {
                    fullMatch2++;
                }

            }
            int buyPriceAttributeVarayety2 = Car.difMaintainancePrice.size();
            double result2 = (fullMatch2+1)/(buyPriceAttributeVarayety2 + numberOfSecondAttr);
            System.out.println(testCar.maintainancePrice + " | " + testCar.carClass + " " + result2);



            double fullMatch3 = 0;
            for (Car trainCar : trainList) {
                if (testCar.numDoors.equals(trainCar.numDoors) &&
                        testCar.carClass.equals(trainCar.carClass)) {
                    fullMatch3++;
                }

            }
            int buyPriceAttributeVarayety3 = Car.difNumDoors.size();
            double result3 = (fullMatch3+1)/(buyPriceAttributeVarayety3 + numberOfSecondAttr);
            System.out.println(testCar.numDoors + " | " + testCar.carClass + " " + result3);


            double fullMatch4 = 0;
            for (Car trainCar : trainList) {
                if (testCar.space.equals(trainCar.space) &&
                        testCar.carClass.equals(trainCar.carClass)) {
                    fullMatch4++;
                }

            }
            int buyPriceAttributeVarayety4 = Car.difSpace.size();
            double result4 = (fullMatch4+1)/(buyPriceAttributeVarayety4 + numberOfSecondAttr);
            System.out.println(testCar.space + " | " + testCar.carClass + " " + result4);


            double fullMatch5 = 0;
            for (Car trainCar : trainList) {
                if (testCar.laggage.equals(trainCar.laggage) &&
                        testCar.carClass.equals(trainCar.carClass)) {
                    fullMatch5++;
                }

            }
            int buyPriceAttributeVarayety5 = Car.difLaggage.size();
            double result5 = (fullMatch5+1)/(buyPriceAttributeVarayety5 + numberOfSecondAttr);
            System.out.println(testCar.laggage + " | " + testCar.carClass + " " + result5);


            double fullMatch6 = 0;
            for (Car trainCar : trainList) {
                if (testCar.safty.equals(trainCar.safty) &&
                        testCar.carClass.equals(trainCar.carClass)) {
                    fullMatch6++;
                }

            }
            int buyPriceAttributeVarayety6 = Car.difSafty.size();
            double result6 = (fullMatch6+1)/(buyPriceAttributeVarayety6 + numberOfSecondAttr);
            System.out.println(testCar.safty + " | " + testCar.carClass + " " + result6);


            double summ = 0;
            for (String s1 : Car.difCarClass.keySet()) {
                summ += Car.difCarClass.get(s1);
            }
            Integer integer = Car.difCarClass.get(testCar.carClass);
            double result7 = integer/summ;
            System.out.println(result7);

            System.out.println(result1*result2*result3*result4*result5*result6*result7);
            i++;
        }
    }

    private static void initData() throws IOException {
        String pathToTrainFolder = "C:\\Users\\Eugene\\IdeaProjects\\NAI\\src\\task_04\\training";
        String pathToTestFolder = "C:\\Users\\Eugene\\IdeaProjects\\NAI\\src\\task_04\\test";

        readFile(Paths.get(pathToTrainFolder), trainList);
        readFile(Paths.get(pathToTestFolder), testList);


        Car.countMapList.add(Car.difBuyPrice);
        Car.countMapList.add(Car.difMaintainancePrice);
        Car.countMapList.add(Car.difNumDoors);
        Car.countMapList.add(Car.difSpace);
        Car.countMapList.add(Car.difLaggage);
        Car.countMapList.add(Car.difSafty);
        Car.countMapList.add(Car.difCarClass);
//        Car.countMapList.add();
        Car.print();
    }

    private static void readFile(Path filePath, List<Car> list) throws IOException {


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

            car.buyPrice = split[0];
            car.maintainancePrice = split[1];
            car.numDoors = split[2];
            car.space = split[3];
            car.laggage = split[4];
            car.safty = split[5];
            car.carClass = split[6];

            car.attrList.add(car.buyPrice);
            car.attrList.add(car.maintainancePrice);
            car.attrList.add(car.numDoors);
            car.attrList.add(car.space);
            car.attrList.add(car.laggage);
            car.attrList.add(car.safty);
            car.attrList.add(car.carClass);

            Integer count = Car.difBuyPrice.get(split[0]);
            int newCount = (count == null ? 1 : count + 1);
            Car.difBuyPrice.put(split[0], newCount);

            Integer count1 = Car.difMaintainancePrice.get(split[1]);
            int newCount1 = (count1 == null ? 1 : count1 + 1);
            Car.difMaintainancePrice.put(split[1], newCount1);

            count = Car.difNumDoors.get(split[2]);
            newCount = (count == null ? 1 : count + 1);
            Car.difNumDoors.put(split[2], newCount);

            count = Car.difSpace.get(split[3]);
            newCount = (count == null ? 1 : count + 1);
            Car.difSpace.put(split[3], newCount);

            count = Car.difLaggage.get(split[4]);
            newCount = (count == null ? 1 : count + 1);
            Car.difLaggage.put(split[4], newCount);

            count = Car.difSafty.get(split[5]);
            newCount = (count == null ? 1 : count + 1);
            Car.difSafty.put(split[5], newCount);

            count = Car.difCarClass.get(split[6]);
            newCount = (count == null ? 1 : count + 1);
            Car.difCarClass.put(split[6], newCount);




            list.add(car);
        }
    }
}
