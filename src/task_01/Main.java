package task_01;

import POJO.Flower;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Main {

    private static List<Flower> testList;
    private static List<Flower> trainList;
    private static List<Buffer> buff = new ArrayList<>();
    private static float correctAttampts = 0;

    public static void main(String[] args) {
        readFiles();

        for (int i = 1; i < 100; i++) {
//            findNearestPoints(parseArgs(args[0]));
            findNearestPoints(i);
            System.out.println("i = " + i);
            calculateAccuracy();
            correctAttampts = 0;

        }
    }

    private static void calculateAccuracy() {
        System.out.println("Total accuracy = " + (correctAttampts/testList.size()) * 100);

    }

    private static void findNearestPoints(int firstN) {
        for (Flower p1 : testList) {

            calculateDistance(p1);
            findFirstNElements(firstN, p1);
        }
    }

    private static void calculateDistance(Flower p1) {
        for (Flower p2 : trainList) {
            double b = (Math.sqrt(
                    Math.pow(p2.getX()-p1.getX(), 2) + Math.pow(p2.getY()-p1.getY(), 2) +
                            Math.pow(p2.getZ()-p1.getZ(), 2) + Math.pow(p2.getK()-p1.getK(), 2))
            );
            buff.add(new Buffer(p2.getType(), b));
        }
    }

    private static void findFirstNElements(int firstN, Flower p1) {
//        buff.sort(new DistanceComparator());
        buff.sort((a,b) -> a.dist < b.dist ? -1 : a.dist == b.dist ? 0 : 1);
        Map<String, Long> map = buff
                .stream()
                .limit(firstN)
                .collect(groupingBy(Buffer::getType, counting()));
        String key = map.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();

        if(key.equals(p1.getType())){
            correctAttampts++;
        }
        buff.clear();
    }

    private static int parseArgs(String arg) {
        return Integer.parseInt(arg);
    }

    private static void readFiles() {
        testList = new ArrayList<>();
        trainList = new ArrayList<>();
        final String TEST = "src/task_01/test.txt";
        final String TRAIN = "src/task_01/train.txt";

        //read file into stream, try-with-resources
        initTest(TEST);
        initTrain(TRAIN);
    }

    private static void initTrain(String TRAIN) {
        try (Stream<String> stream = Files.lines(Paths.get(TRAIN))) {
            stream
                    .map(line -> line.split(","))
                    .forEach(x -> trainList.add(
                            new Flower(Float.parseFloat(x[0]), Float.parseFloat(x[1]),
                                    Float.parseFloat(x[2]),Float.parseFloat(x[3]), x[4])));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initTest(String TEST) {
        try (Stream<String> stream = Files.lines(Paths.get(TEST))) {
            stream
                    .map(line -> line.split(","))
                    .forEach(x -> testList.add(
                            new Flower(Float.parseFloat(x[0]), Float.parseFloat(x[1]),
                                    Float.parseFloat(x[2]),Float.parseFloat(x[3]), x[4])));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
