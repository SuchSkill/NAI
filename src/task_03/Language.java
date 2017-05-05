package task_03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Eugene on 06-Apr-17.
 */
public class Language {
    public String name;
    public List<Document> docs = new ArrayList<>();
//    public Map<Character, Integer> charFreq = new HashMap<>();
//    public double sumOfChars = 0;


    public Language(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Language{" +
                "name='" + name + '\'' +
                ", docs=" + docs +
                '}';
    }
}
