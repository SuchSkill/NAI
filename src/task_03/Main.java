package task_03;

import POJO.Flower;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


public class Main {

    private static List<String> testList;
    private static List<Lenguage> trainList;

    public static void main(String[] args) throws IOException {
        initData(args);
    }

    private static void initData(String[] args) throws IOException {
        testList = new ArrayList<>();
        trainList = new ArrayList<>();


        readFile("a", testList);
    }

    private static void readFile(String file, List<String> collection) throws IOException {
        File[] directories = new File("C:\\Users\\Eugene\\IdeaProjects\\NAI\\src\\task_03\\trainingset").listFiles(File::isDirectory);

        for (File directory : directories) {
            System.out.println(directory);
            int length = directory.toString().length();
            String substring = directory.toString().substring(length - 2, length);
            System.out.println("substring = " + substring);
            Lenguage lenguage = new Lenguage(substring);
            trainList.add(lenguage);

            walkDirectory(directory.toString(), lenguage);


        }
        for (Lenguage lenguage : trainList) {
            System.out.println(lenguage.name);
            Map<Character, Integer> charFreq = lenguage.charFreq;
            for (char ch = 'a'; ch <= 'z'; ++ch) {
                Integer integer = charFreq.get(String.valueOf(ch));
                System.out.println("char " + ch + " is met " + integer);
            }
//            charFreq.keySet().stream().filter(x-> x.toString().matches("[a-z|A-Z]")).
            System.out.println(lenguage.charFreq);
        }


    }

    private static void walkDirectory(String path, Lenguage lenguage) {
        try(Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    System.out.println(filePath);
                    //file to string
                    List<String> lines = new ArrayList<>();
                    System.out.println(filePath);
                    try {
                        lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    getCharFreq(lines, lenguage);

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getCharFreq(List<String> s, Lenguage lenguage) {
        for (String s1 : s) {

            if (s1 != null) {
                for (Character c : s1.toCharArray()) {
                    System.out.println(c);
                    Integer count = lenguage.charFreq.get(c);
                    int newCount = (count == null ? 1 : count + 1);
                    lenguage.charFreq.put(c, newCount);
                }
            }
        }
    }
}
