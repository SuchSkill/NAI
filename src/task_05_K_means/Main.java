package task_05_K_means;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static List<Flower5> irisList = new ArrayList<>();
    private static List<Centroid> centroids = new ArrayList<>();
    private static List<Integer> usedFlowers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        initData();

//        System.out.println(irisList);


        int i = Integer.parseInt(args[0]);
        initCentroids(i);
        List<Integer> oldL = new ArrayList<>();
        List<Integer> newL = new ArrayList<>();

        while(true){
            calcDistance();
            newL.clear();
            for (Centroid centroid : centroids) {
                centroid.recalculateCoordinates();
                System.out.println(centroid.name + " " + centroid.CentroidCoordinates);
                int size = centroid.content.size();
                System.out.println(size);
                newL.add(size);
            }
            System.out.println();
            if (oldL.size() < 1){
                oldL = new ArrayList<>(newL);
                continue;
            }
            if (!oldL.retainAll(newL)) {
                System.out.println("ok");
                break;
            }

            oldL = new ArrayList<>(newL);
        }



        Integer count = null;
        Integer newCount = null;

        for (Centroid centroid : centroids) {
            Map<String, Integer> map = new HashMap<>();
            for (Flower5 flower : centroid.content) {
                String name = flower.name;
                count = map.get(name);
                newCount = (count == null ? 1 : count + 1);
                map.put(name, newCount);
            }
            int max =0;
            String maxName="";
            for (String s : map.keySet()) {
                Integer integer = map.get(s);
                if (integer>max){
                    max = integer;
                    maxName=s;
                }
            }
            System.out.println(maxName + " " + max + " " + (double)max/centroid.content.size());
        }
    }

    private static void calcDistance() {
        for (Centroid centroid : centroids) {
            centroid.content.clear();
        }
        for (Flower5 flower : irisList) {
            Centroid minCentroid = null;
            double minDistance = Double.MAX_VALUE;
            for (Centroid centroid : centroids) {
                double v = calcDistance(flower, centroid);
//                System.out.println(centroid.name + " " + centroid.CentroidCoordinates);
//                System.out.println(flower);
//                System.out.println(v);
                if (v < minDistance) {
                    minDistance = v;
                    minCentroid = centroid;

                }
            }
//            System.out.println("min centroid "+minCentroid.name);
            minCentroid.content.add(flower);
        }
//        System.out.println();
    }

    private static double calcDistance(Flower5 f, Centroid c) {
        double distance = 0;
        for (int i = 0, j = 0; i < f.coordinates.size(); i++, j++) {
            double pow = Math.pow(f.coordinates.get(i) - c.CentroidCoordinates.get(i), 2);
            distance += pow;
        }
        distance = Math.sqrt(distance);
        return distance;
    }

    private static void initCentroids(int k) {

        for (int i = 0; i < k; i++) {
            Centroid centroid = new Centroid();
            Flower5 randomFlower = getRandomFlower();
            ArrayList<Double> doubles = new ArrayList<>(randomFlower.coordinates);
            centroid.CentroidCoordinates = doubles;
            centroid.name = "c" + i;
            centroids.add(centroid);
        }


    }

    private static int getRandom(int max) {
        return ThreadLocalRandom.current().nextInt(0, max);
    }

    private static Flower5 getRandomFlower() {
        int random;
        do {
            random = getRandom(irisList.size());
        } while (usedFlowers.contains(random));
        usedFlowers.add(random);
        return irisList.get(random);
    }

    private static void initData() throws IOException {
        String pathToFolder = "C:\\Users\\Eugene\\IdeaProjects\\NAI\\src\\task_05_K_means\\iris.txt";
        readFile(Paths.get(pathToFolder), irisList);


    }

    private static void readFile(Path filePath, List<Flower5> list) throws IOException {


        List<String> allDocLines = new ArrayList<>();
        System.err.println(filePath);
        try {
            allDocLines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int j = 0;
        for (String line : allDocLines) {
            String[] split = line.split(",");
            Flower5 flower = new Flower5();
            for (int i = 0; i < split.length - 1; i++) {
                double v = Double.parseDouble(split[i]);
                flower.buffer.add(v);
            }
            flower.name = split[split.length - 1];
            flower.number = j++;
            flower.lock();
            list.add(flower);
        }

    }
}
