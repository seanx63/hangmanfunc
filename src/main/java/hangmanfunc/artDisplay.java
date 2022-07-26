package hangmanfunc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public class artDisplay {
    public String word;
    String[] artArray = new String[6];

    public artDisplay(Word word) {
        this.word = word.getWord();
    }

    public void mainDisplay(HashSet<String> guesses, ArrayList<String> wrongGuesses) throws IOException {
        String artStream = Files.readString(Path.of("src/main/java/hangmanfunc/art.txt"));
        artArray = artStream.split("\n\n");
        switch (wrongGuesses.size()) {
            case 1 ->
                System.out.println(artArray[1]);
            case 2 ->
                System.out.println(artArray[2]);
            case 3 ->
                System.out.println(artArray[3]);
            case 4 ->
                System.out.println(artArray[4]);
            case 5 ->
                System.out.println(artArray[5]);
            case 6 ->
                System.out.println(artArray[6]);
            default ->
                System.out.println(artArray[0]);
        }
        if (wrongGuesses.size() > 0) {
            System.out.println("Missed Letters: " + String.join("", wrongGuesses));
            wordDisplay(guesses);
        } else {
            System.out.println("Missed Letters: ");
            wordDisplay(guesses);
        }
    }

    public String wordDisplay(HashSet<String> guesses) {
        ArrayList<String> wordList = (ArrayList<String>) word.chars().mapToObj(n -> String.valueOf(n)).collect(Collectors.toList());
        String display = wordList.stream().map(n -> {
            if (guesses.contains(String.valueOf(n)))
                return String.valueOf(n);
            return ("_");
        }).collect(Collectors.joining());
        System.out.println(display+"\n");
        return display;
    }

}
