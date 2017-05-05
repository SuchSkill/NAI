package task_03;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;


public class Main {

    private static List<Language> trainListLenguages;
    private static List<Language> testListLenguages;
    private static List<Perceptrone> percList;
    private static double alpha = 0.1;

    public static void main(String[] args) throws IOException {
        initData(args);

//        for (int i = 0; i < 100; i++) {
//            trainPerceptrones();
//            double accuracy = calcAccutacy();
//            System.out.println("accuracy = " + accuracy);
//        }
        double accuracy = 0;
        int i = 0;
        while (accuracy < 95) {
            i++;
            trainPerceptrones(trainListLenguages);
            accuracy = calcAccutacy(trainListLenguages);
        }
        System.out.println("accuracy = " + accuracy);
        System.out.println("after " + i + " iterations");

        System.out.println();
        trainPerceptrones(testListLenguages);
        accuracy = calcAccutacy(testListLenguages);
        System.out.println("accuracy in test example = " + accuracy);



    }

    private static double calcAccutacy(List<Language> listLenguages) {
        int correct = 0;
        double total = 0;
        for (Perceptrone perceptrone : percList) {
            for (Language language : listLenguages) {
                for (Document doc : language.docs) {
                    total++;
                    Map<Character, Double> charMapAccurance = doc.charMapAccurance;
                    boolean activated = isActivated(perceptrone, doc);
                    boolean match = perceptrone.getLanguage().equals(language.name);
                    if (activated) {
                        if (match) {
                            //ok
                            correct++;
                        } else {
                            //wrong
                        }
                    } else {
                        if (match) {
                            //wrong
                        } else {
                            //ok
                            correct++;
                        }
                    }
                }
            }
        }
        return (correct / total) * 100;
    }

    private static double trainPerceptrones(List<Language> listLenguages) {
        int correct = 0;
        double total = 0;
        for (Perceptrone perceptrone : percList) {
            for (Language language : listLenguages) {
                for (Document doc : language.docs) {
                    total++;
                    Map<Character, Double> charMapAccurance = doc.charMapAccurance;
                    boolean activated = isActivated(perceptrone, doc);
                    boolean match = perceptrone.getLanguage().equals(language.name);
                    if (activated) {
                        if (match) {
                            //ok
                            correct++;
                        } else {
                            //wrong
                            updateWeights(-1, perceptrone, charMapAccurance);
                        }
                    } else {
                        if (match) {
                            //wrong
                            updateWeights(1, perceptrone, charMapAccurance);
                        } else {
                            //ok
                            correct++;
                        }
                    }
                }
            }
        }
        return (correct / total) * 100;
    }

    private static void updateWeights(int coef, Perceptrone perceptrone, Map<Character, Double> charMapAccurance) {
        for (int i = 0; i < perceptrone.weights.size(); i++) {
            Double weight = perceptrone.weights.get(i);
            char[] key = Character.toChars(i + 97);
            Double charAcourance = charMapAccurance.get(key[0]);
            perceptrone.weights.set(i, weight + (coef * charAcourance * alpha));
            perceptrone.setTreshhold(perceptrone.getTreshhold() - (coef * alpha));
        }
    }

    private static boolean isActivated(Perceptrone perceptrone, Document doc) {
        Map<Character, Double> charMapAccurance = doc.charMapAccurance;
        double result = 0;
        int i = 0;
        for (char ch = 'a'; ch <= 'z'; ++ch, i++) {
            Double charOccur = charMapAccurance.get(ch);
            Double weight = perceptrone.getWeights().get(i);
            result += charOccur * weight;
        }
        return (result - perceptrone.getTreshhold()) > 0;
    }

    private static void initData(String[] args) throws IOException {
        trainListLenguages = new ArrayList<>();
        testListLenguages = new ArrayList<>();
        percList = new ArrayList<>();
        String pathToTrainFolder = "C:\\Users\\Eugene\\IdeaProjects\\NAI\\src\\task_03\\trainingset";
        String pathToTestFolder = "C:\\Users\\Eugene\\IdeaProjects\\NAI\\src\\task_03\\testset";
        readFile(pathToTrainFolder, trainListLenguages);
        readFile(pathToTestFolder, testListLenguages);


        for (Language language : trainListLenguages) {
            System.out.println(language.name);

            initPerceptrone(language.name);

            calculateCharAccurance(language);
        }
        for (Language language : testListLenguages) {
            System.out.println(language.name);
            calculateCharAccurance(language);

        }
    }

    private static void calculateCharAccurance(Language language) {
        for (Document doc : language.docs) {
            Map<Character, Integer> charFreqLocal = doc.charMap;
            double sumOfChars = doc.sumOfChars;
            System.out.println("doc = " + doc.name);
            System.out.println("sumOfChars = " + sumOfChars);
            for (char ch = 'a'; ch <= 'z'; ++ch) {
                Integer count = charFreqLocal.get(ch);
                if (count == null) count = 1;
                double charOcurrance = (count / sumOfChars);
                doc.charMapAccurance.put(ch, charOcurrance);
            }
        }
    }


    private static void initPerceptrone(String name) {
        Perceptrone p = new Perceptrone(name, new ArrayList<>(), getRandom());
        for (int i = 0; i < 26; i++) {
            p.getWeights().add(getRandom());
        }
        System.out.println(p.getWeights().size());
        percList.add(p);
    }

    private static double getRandom() {
        return ThreadLocalRandom.current().nextDouble(0.01, 0.99);
    }

    private static void readFile(String file, List<Language> listLenguages) throws IOException {
        File[] directories = new File(file).listFiles(File::isDirectory);

        for (File directory : directories) {
            System.out.println(directory);
            int length = directory.toString().length();
            String substring = directory.toString().substring(length - 2, length);
            System.out.println("substring = " + substring);
            Language language = new Language(substring);
            listLenguages.add(language);

            walkDirectory(directory.toString(), language);


        }
    }

    private static void walkDirectory(String path, Language language) {
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    //file to string
                    List<String> allDocLines = new ArrayList<>();
                    System.err.println(filePath);
                    try {
                        allDocLines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Document document = new Document(filePath.toString());
                    language.docs.add(document);
                    getCharFreq(document, allDocLines);

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getCharFreq(Document document, List<String> allDocLines) {
        int countAll = 0;
        for (String docLine : allDocLines) {
            if (docLine != null) {
                for (Character c : docLine.toCharArray()) {
                    if ((c + "").matches("[a-zA-Z]")) {
                        countAll++;
//                        Integer count = language.charFreq.get(c);
                        Integer count = document.charMap.get(c);
                        int newCount = (count == null ? 1 : count + 1);
//                        language.charFreq.put(c, newCount);
                        document.charMap.put(c, newCount);
                    }

                }
            }
        }
        document.sumOfChars = countAll;
    }
}
