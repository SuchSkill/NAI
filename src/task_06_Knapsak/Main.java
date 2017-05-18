package task_06_Knapsak;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.time.temporal.ChronoUnit;

/**
 * Created by Eugene on 11-May-17.
 */
public class Main {
    static List<Item> list = new ArrayList<>();
    static int limit = 0;
    static int maxValue = 0;
    static String maxItem;
    static boolean[] maxb;


    public static void main(String[] args) throws IOException {
        initData();
        LocalDateTime timeStart = LocalDateTime.now();
        int items = list.size();
        double pow = Math.pow(2, items);
        System.out.println(pow);
        for (int i = 0; i < pow; i++) {
            int percent = i % 10_000_000;
            if (percent ==0){
                System.out.println(i/10_000_000);
            }
//            String binStr = intToBinaryShifting(i, items);
            boolean[] booleans = intToBinaryShiftingBoolean(i, items);
//            System.out.println(binStr);
            int j = 0;
            int currentWeight=0;
            int currentValue=0;
//            for (char c : binStr.toCharArray()) {
//                if (c==49){//askii num of 1
//                    Item item = list.get(j);
//                    currentValue += item.value;
//                    currentWeight += item.weight;
//                }
//                j++;
//            }
            for (boolean b : booleans) {
                if (b){//askii num of 1
                    Item item = list.get(j);
                    currentValue += item.value;
                    currentWeight += item.weight;
                }
                j++;
            }
            if (currentWeight>limit) {
                continue;
            }
            if(maxValue<currentValue){
                maxValue=currentValue;
//                maxItem=binStr;
                maxb = booleans;

            }
        }
        for (boolean b : maxb) {
            if (b){
                System.out.print("1");
            }else
                System.out.print("0");
        }
        System.out.println();

        LocalDateTime timeEnd = LocalDateTime.now();
        long seconds = ChronoUnit.SECONDS.between(timeStart, timeEnd);
        System.out.println("Duration " + seconds);



    }

    private static void initData() throws IOException {
        String pathToFolder = "C:\\Users\\Eugene\\IdeaProjects\\NAI\\src\\task_06_Knapsak\\data\\15";
        readFile(Paths.get(pathToFolder), list);
    }


    private static void readFile(Path filePath, List<Item> list) throws IOException {


        List<String> allDocLines = new ArrayList<>();
        System.err.println(filePath);
        try {
            allDocLines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int j = 0;
        limit = Integer.parseInt(allDocLines.get(0));
        allDocLines.remove(0);
        for (String line : allDocLines) {
            String[] split = line.split(" ");
            int value = Integer.parseInt(split[0]);
            int weight = Integer.parseInt(split[1]);
            Item item = new Item(value, weight);
            list.add(item);
        }

    }

    public static String intToBinaryShifting(int n, int numOfBits) {
        int bitmask = 1;
        String binary = "";
//        while (n>0){
        for (int i = 0; i < numOfBits; ++i) {
            int j = n & bitmask;
            binary = j + binary;
            n = n >> 1;
        }
//        }
        return binary;
    }
    private static boolean[] intToBinaryShiftingBoolean(int number, int base) {
        final boolean[] ret = new boolean[base];
        for (int i = 0; i < base; i++) {
            ret[base - 1 - i] = (1 << i & number) != 0;
        }
        return ret;
    }
}
