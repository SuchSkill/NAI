package task_03;

import java.util.ArrayList;

public class Perceptrone {
    private String language;
    public ArrayList<Double> weights;
    private double treshhold;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Perceptrone(String language, ArrayList<Double> weights, double treshhold) {
        this.language = language;
        this.weights = weights;
        this.treshhold = treshhold;
    }

    public ArrayList<Double> getWeights() {
        return weights;
    }

    @Override
    public String toString() {
        return "Perceptrone{" +
                "language='" + language + '\'' +
                ", weights=" + weights +
                ", treshhold=" + treshhold +
                '}' + '\n';
    }

    public void setWeights(ArrayList<Double> weights) {
        this.weights = weights;
    }

    public double getTreshhold() {
        return treshhold;
    }

    public void setTreshhold(double treshhold) {
        this.treshhold = treshhold;
    }
}
