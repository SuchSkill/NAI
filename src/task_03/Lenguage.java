package task_03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Eugene on 06-Apr-17.
 */
public class Lenguage {
    public String name;
    public List<String> exapmles = new ArrayList<>();
    public Map<Character, Integer> charFreq = new HashMap<>();

    public Lenguage(String name) {
        this.name = name;
    }
}
