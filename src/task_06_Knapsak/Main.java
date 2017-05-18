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

/**
 * Created by Eugene on 11-May-17.
 */
public class Main {
    static List<Item> list = new ArrayList<>();
    static int limit = 0;
    static int maxWeignt = 0;
    static int maxValue = 0;
    static String maxItem;

    public static void main(String[] args) throws IOException {
        initData();
        LocalDateTime timeStart = LocalDateTime.now(
        );
        int items = list.size();
        double pow = Math.pow(2, items);
        System.out.println(pow);
        for (int i = 0; i < pow; i++) {
            if (i%100_000_000==0){
                System.out.println(i);
            }
            String binStr = intToBinaryShifting(i, items);
//            System.out.println(binStr);
            int j = 0;
            int currentWeight=0;
            int currentValue=0;
            for (char c : binStr.toCharArray()) {
                if (c==1){
                    Item item = list.get(j);
                    currentValue += item.value;
                    currentWeight += item.weight;
                }
                j++;
            }
            if (currentWeight<limit) {
                continue;
            }
            if(maxValue<currentValue){
                maxValue=currentValue;
                maxItem=binStr;
            }
        }
        System.out.println(maxItem);
        LocalDateTime timeend = LocalDateTime.now(
        );
//        LocalDateTime minus = timeend.();


    }

    private static void initData() throws IOException {
        String pathToFolder = "C:\\Users\\Eugene\\IdeaProjects\\NAI\\src\\task_06_Knapsak\\data\\2";
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

    public static String intToBinary(int n, int numOfBits) {
        String binary = "";
        for (int i = 0; i < numOfBits; ++i, n /= 2) {
            int j = n % 2;
            switch (j) {
                case 0:
                    binary = "0" + binary;
                    break;
                case 1:
                    binary = "1" + binary;
                    break;
            }
        }

        return binary;
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
}
