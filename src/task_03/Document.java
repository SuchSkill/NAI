package task_03;

import java.util.HashMap;
import java.util.Map;

public class Document {
    public String name;
    public Map<Character, Integer> charMap = new HashMap<>();
    public Map<Character, Double> charMapAccurance = new HashMap<>();
    public double sumOfChars = 0;

    public Document(String name) {
        this.name = name;
    }
}
