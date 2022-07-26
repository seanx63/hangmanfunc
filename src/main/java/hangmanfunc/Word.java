package hangmanfunc;


import java.util.Random;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Word {
    String word;

    public String getWord() {
        return word;
    }

    public Word() {
        ArrayList<String> words = new ArrayList<String>();
        try {
            words = (ArrayList<String>) Files.lines(Paths.get("./src/main/java/hangmanfunc/randomwords.txt"))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Random rand = new Random();
        word = words.get(rand.nextInt(words.size()));
    }

    public Word(String newWord) {
        this.word = newWord;
    }

}
