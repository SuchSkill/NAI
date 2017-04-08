package task_02;

import POJO.Flower;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

/**
 * Created by Eugene on 25-Mar-17.
 */
public class Perc {
    private static List<Flower> testList;
    private static List<Flower> trainList;
    private static Perseptron perseptron;
    private static double alpha;
    private static double errorTreshhold = 100;
    private static List<Flower> userList;
    private static int iterations =100;

    public static void main(String[] args) {
        initData(args);
        for (int i = 0; i < iterations; i++) {
            trainPerceptron(trainList);
            double presition = checkAccuracy(testList);
            System.out.println("accuracy is " + presition);
        }
        if (userList.size()>0){
            double presition1 = checkAccuracy(userList);
            System.out.println("Accuracy on user list is" + presition1);
        }

    }

    private static double checkAccuracy(List<Flower> list) {
        double icorrectAttampts = 0;
        for (Flower flower : list) {
            double result = getPercetroneResult(flower);
            boolean isTriggered = result > 0;
            boolean isVersicolor = flower.getType().equals("Iris-versicolor");
            if (!isTriggered) {
                if (isVersicolor) {
                    icorrectAttampts++;

                }
            } else {
                if (!isVersicolor) {
                    icorrectAttampts++;
                }
            }

        }
        return (icorrectAttampts / list.size()) * 100;
    }

    private static void trainPerceptron(List<Flower> list) {
        double trainingError;
        do {
            double errors = 0;
            for (Flower flower : list) {
                while (true) {
                    double result = getPercetroneResult(flower);
                    boolean isTriggered = result > 0;
                    boolean isVersicolor = flower.getType().equals("Iris-versicolor");
                    if (isTriggered) {
                        if (isVersicolor) {
                            updateWeights(flower, -1);
                            errors++;
                            break;
                        } else {
                            break;
                        }

                    } else {
                        if (!isVersicolor) {
                            updateWeights(flower, 1);
                            errors++;
                            break;

                        } else {
                            break;
                        }
                    }
                }
            }
            trainingError = 0.5 * errors;
            System.out.println(trainingError);
        }while(errorTreshhold < trainingError);


    }

    private static double getPercetroneResult(Flower flower) {
        return ((flower.getX() * perseptron.getWx())
                + (flower.getY() * perseptron.getWy())
                + (flower.getK() * perseptron.getWk())
                + (flower.getZ() * perseptron.getWz())) - perseptron.getTreshhold();
    }

    private static void updateWeights(Flower flower, int coef) {
        perseptron.setWx(perseptron.getWx() + (coef * flower.getX() * alpha));
        perseptron.setWy(perseptron.getWy() + (coef * flower.getY() * alpha));
        perseptron.setWk(perseptron.getWk() + (coef * flower.getK() * alpha));
        perseptron.setWz(perseptron.getWz() + (coef * flower.getZ() * alpha));
        perseptron.setTreshhold(perseptron.getTreshhold() - (coef * alpha));
    }


    private static void initData(String[] args) {
        testList = new ArrayList<>();
        trainList = new ArrayList<>();
        userList = new ArrayList<>();
        final String TEST = "src/task_02/test.txt";
        final String TRAIN = "src/task_02/train.txt";
        if (args.length > 0){
            int iter = Integer.parseInt(args[0]);
            if(iter > 0){
                iterations = iter;
            } else{
                iterations = 10;
            }
        }
        if(args.length > 1){
            errorTreshhold = Double.parseDouble(args[1]);
        }
        if(args.length > 7) {
            perseptron = new Perseptron(Double.parseDouble(args[2]),
                    Double.parseDouble(args[3]),
                    Double.parseDouble(args[4]),
                    Double.parseDouble(args[5]),
                    Double.parseDouble(args[6]));
            alpha = Double.parseDouble(args[7]);
        }else{
            perseptron = new Perseptron(getRandom(), getRandom(), getRandom(), getRandom(), getRandom());
            alpha = getRandom();
        }
        if(args.length > 12) {

            userList.add(new Flower(
                    Float.parseFloat(args[8]),
                    Float.parseFloat(args[9]),
                    Float.parseFloat(args[10]),
                    Float.parseFloat(args[11]),
                    args[12]
            ));
        }
        readFile(TEST, testList);
        readFile(TRAIN, trainList);
    }

    private static double getRandom() {
        return ThreadLocalRandom.current().nextDouble(0.01, 0.99);
    }

    private static void readFile(String file, List<Flower> collection) {
        try (Stream<String> stream = Files.lines(Paths.get(file))) {
            stream
                    .map(line -> line.split(","))
                    .forEach(x -> collection.add(
                            new Flower(Float.parseFloat(x[0]), Float.parseFloat(x[1]),
                                    Float.parseFloat(x[2]), Float.parseFloat(x[3]), x[4])));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
