package hangmanfunc;

import java.util.Scanner;

public class Input {
    Scanner input = new Scanner(System.in);

    public String getUserChoice() {
        System.out.println("Guess a letter: ");
        String choice = input.nextLine();
        if (choice.length() > 1) {
            System.out.println("Sorry please choose only one letter character");
            return getUserChoice();
        }
        if (choice.matches("[a-z]|[A-Z]")) {
            return choice;
        } else {
            System.out.println("Please only choose a letter: ");
            return getUserChoice();
        }
    }

    public String getName() {
        System.out.println("Please enter your name: ");
        return input.nextLine();
    }
}
