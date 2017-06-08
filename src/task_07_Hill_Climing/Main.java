package task_07_Hill_Climing;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    static int[][] matrix;
    private static int size;
    static List<Integer> visitedCitys = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        initData();
        size = matrix[0].length;

        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        int startCity = new Random().nextInt(size);
        System.out.println(startCity);
        visitedCitys.add(startCity);
        int currentCity=startCity;
        while (visitedCitys.size()<size) {
            int lowestDistance = Integer.MAX_VALUE;
            int lowestIndex = 0;
            for (int i = 0; i < size; i++) {
                if (visitedCitys.contains(i)) continue;
                int current = matrix[currentCity][i];
                System.out.print(current + " ");
                if (current < lowestDistance) {
                    lowestDistance = current;
                    lowestIndex = i;
                }
            }
            System.out.println();
            System.out.println("lowestDistance = " + lowestDistance);
            System.out.println("lowestIndex = " + lowestIndex);
            System.out.println();
            currentCity=lowestIndex;
            visitedCitys.add(lowestIndex);
        }
        System.out.println(visitedCitys);

    }

    private static void initData() throws IOException {
        String pathToFolder = "C:\\Users\\Eugene\\IdeaProjects\\NAI\\src\\task_07_Hill_Climing\\tsp_data\\tsp_data2.txt";
        readFile(Paths.get(pathToFolder));
    }


    private static void readFile(Path filePath) throws IOException {


        List<String> allDocLines = new ArrayList<>();
        System.err.println(filePath);
        try {
            allDocLines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 0;
        int j = 0;
        for (String line : allDocLines) {
//            System.out.println(line);
            String[] split = line.split(" ");
            for (String s : split) {
//                System.out.print(s);
                if (i == 0 && j == 0) {
                    matrix = new int[split.length][split.length];
                }
                matrix[i][j] = Integer.parseInt(s);
                i++;
            }
            j++;
            i = 0;
        }

    }
}
