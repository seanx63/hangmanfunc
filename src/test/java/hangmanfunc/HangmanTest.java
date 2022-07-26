package hangmanfunc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HangmanTest {
    @Test
    void isGameEnd() {
        Hangman hangman = new Hangman();
        hangman.wrongGuesses.add("a");
        hangman.wrongGuesses.add("a");
        hangman.wrongGuesses.add("a");
        hangman.wrongGuesses.add("a");
        hangman.wrongGuesses.add("a");
        hangman.wrongGuesses.add("a");
        hangman.wrongGuesses.add("a");
        assertEquals(true, hangman.isGameOver());
    }
    @Test
    void isGameNotEnd() {
        Hangman hangman = new Hangman();
        hangman.wrongGuesses.add("a");
        assertEquals(false, hangman.isGameOver());
    }
}
