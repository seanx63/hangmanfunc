package hangmanfunc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Hangman {
    Word word = new Word();
    private final Input input = new Input();
    private final artDisplay artDisplay = new artDisplay(word);
    private boolean gameOver = false;
    HashSet<String> playerGuesses = new HashSet<String>();
    ArrayList<String> wrongGuesses = new ArrayList<>();
    String currentGuess, name;

    public void start() throws IOException {
        name = input.getName();
        System.out.println(word.getWord());
        artDisplay.mainDisplay(playerGuesses, wrongGuesses);
        while (!gameOver) {
            currentGuess = input.getUserChoice();
            if (playerGuesses.contains(currentGuess)) {
                System.out.println("That has been guessed already, try again.");
            }
            if (!word.getWord().contains(currentGuess)) {
                wrongGuesses.add(currentGuess);
            }
            playerGuesses.add(currentGuess);
            if (word.getWord().equals(artDisplay.wordDisplay(playerGuesses))) {
                System.out.println("CONGRATS! YOU WIN!");
                if (checkHighScore()) {
                    System.out.println("YOU GOT THE HIGH SCORE!");
                }
                gameOver = true;
                break;
            }
            artDisplay.mainDisplay(playerGuesses, wrongGuesses);
            gameOver = isGameOver();
        }
        if (wrongGuesses.size() > 5) {
            System.out.println("GAME OVER, the word was: " + artDisplay.word);
        }
    }

    public boolean isGameOver() {
        if (wrongGuesses.size() > 5) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkHighScore() throws IOException {
        ArrayList<Integer> scoresList = new ArrayList<>();
        String scoresString = Files.readString(Path.of("src/main/java/hangmanfunc/scores.txt"));
        String[] scoresArray = scoresString.split("\n");
        scoresList = (ArrayList<Integer>) Arrays.stream(scoresArray).map(n -> {
            String[] tmpArray = n.split(", |\n");
            return Integer.valueOf(tmpArray[1]);
        }).collect(Collectors.toList());
        return (Collections.max(scoresList) <= (100 - wrongGuesses.size()));
    }

    public void saveHighScores() throws IOException {
        Writer writer = new FileWriter("src/main/java/hangmanfunc/scores.txt", true);
        BufferedWriter bufWriter = new BufferedWriter(writer);
        bufWriter.write("\n" + name + ", " + (100 - wrongGuesses.size()));
        System.out.println("\n" + name + ", " + (100 - wrongGuesses.size()));
        bufWriter.close();

    }
}
