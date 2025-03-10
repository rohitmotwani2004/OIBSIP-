import java.util.Scanner;
import java.util.Random;

class GuessingGame {
    private int secretNumber;
    private int maxAttempts;
    private int attempts;
    private boolean gameWon;
    
    public GuessingGame(int min, int max, int maxAttempts) {
        Random rand = new Random();
        this.secretNumber = rand.nextInt(max - min + 1) + min;
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
        this.gameWon = false;
    }
    
    public boolean makeGuess(int guess) {
        attempts++;
        if (guess == secretNumber) {
            gameWon = true;
            System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
            return true;
        } else if (guess < secretNumber) {
            System.out.println("Too low! Try again.");
        } else {
            System.out.println("Too high! Try again.");
        }
        
        if (attempts >= maxAttempts) {
            System.out.println("Game Over! The correct number was: " + secretNumber);
            return true;
        }
        return false;
    }
    
    public boolean isGameWon() {
        return gameWon;
    }
}

public class  RohitNumberGuessingGameV2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        System.out.println("Welcome to the Number Guessing Game!");
        
        do {
            System.out.println("Select Difficulty:");
            System.out.println("1. Easy (1-20, 6 attempts)");
            System.out.println("2. Medium (1-50, 5 attempts)");
            System.out.println("3. Hard (1-100, 4 attempts)");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            int min = 1, max = 20, maxAttempts = 6;
            if (choice == 2) {
                max = 50;
                maxAttempts = 5;
            } else if (choice == 3) {
                max = 100;
                maxAttempts = 4;
            }
            
            GuessingGame game = new GuessingGame(min, max, maxAttempts);
            boolean gameEnded = false;
            
            System.out.println("Try to guess the number between " + min + " and " + max + "!");
            while (!gameEnded) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                gameEnded = game.makeGuess(guess);
            }
            
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        } while (playAgain);
        
        System.out.println("Thanks for playing! Goodbye!");
        scanner.close();
    }
}