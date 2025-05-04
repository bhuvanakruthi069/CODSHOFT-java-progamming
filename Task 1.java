//NUMBER GUESSING GAME//
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int roundsWon = 0;
        
        // Game loop for multiple rounds
        do {
            int attemptsLeft = 10; // Limit the number of attempts
            int targetNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
            boolean guessedCorrectly = false;

            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("I have generated a number between 1 and 100. Try to guess it!");
            System.out.println("You have " + attemptsLeft + " attempts to guess the number.");

            // Start the guessing loop
            while (attemptsLeft > 0 && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess < 1 || userGuess > 100) {
                    System.out.println("Please guess a number between 1 and 100.");
                    continue;
                }

                if (userGuess < targetNumber) {
                    System.out.println("Your guess is too low.");
                } else if (userGuess > targetNumber) {
                    System.out.println("Your guess is too high.");
                } else {
                    System.out.println("Congratulations! You've guessed the number correctly.");
                    guessedCorrectly = true;
                }
                
                attemptsLeft--;
                if (attemptsLeft > 0 && !guessedCorrectly) {
                    System.out.println("You have " + attemptsLeft + " attempts left.");
                } else if (!guessedCorrectly) {
                    System.out.println("Sorry, you've run out of attempts. The correct number was " + targetNumber + ".");
                }
            }

            // Track score (rounds won)
            if (guessedCorrectly) {
                roundsWon++;
            }

            // Ask if the player wants to play another round
            System.out.print("Do you want to play another round? (y/n): ");
            char playAgain = scanner.next().charAt(0);

            if (playAgain == 'n' || playAgain == 'N') {
                break;
            }

        } while (true);

        // Display final score
        System.out.println("You won " + roundsWon + " rounds. Thanks for playing!");
        scanner.close();
    }
}
