import java.util.Random;
import java.util.Scanner;

/** A number guessing game. */
public class Game {
  public static void main(String[] args) {
    System.out.println("Welcome to the Guessing Game");

    Scanner input = new Scanner(System.in);

    // Get user's name
    System.out.println("Enter your name:");
    String name = input.nextLine();
    
    //clear the screen
    System.out.print("\033[H\033[2J");
    System.out.printf("Hi, %s, I'm thinking of a number between 0 and 100.", name);
        
    boolean play = true;
    
    String highScore = "false";
    while (play) {

      int tries = playGame(name, input);
      if (tries < 11) {
        highScore = checkHighScore(highScore, tries);
      }

      play = playAgain(name, input);

    }
    
    input.close();
    System.out.printf("Your best score for today was: %s tries", highScore);
  
  }
  

  private static int playGame(String name, Scanner input) {
    
    Random rand = new Random();
    int number = rand.nextInt(101);
    
    System.out.println("Try to guess the number.");
    
    int tries = 0;
    int guess = number + 1;
    
    while (guess != number) {
      guess = getGuess(input);
      tries++;
      if (tries == 11) {
        System.out.println("Sorry, you're out of guesses.");
        break;
      }

      if (guess == number) {
        System.out.printf("\nCongratulations %s, you got it in %d tries!", name, tries);
      }

      else{
        System.out.printf("%s Guess again. \n", guess < number ? "Too Low." : "Too High.");
      }
    }
    return tries;
  }


  private static String checkHighScore(String highScore, int tries) {
    
    if (highScore.equals("false") || tries < Integer.parseInt(highScore)) {
      highScore = Integer.toString(tries);
      System.out.println("That's a new high score!");
    }
    
    else {
      System.out.printf("The current high score is %s tries. Can you beat that?", highScore);
    }
    return highScore;
  }
  

  private static boolean valid(String s) {
    try {
      int n = Integer.parseInt(s);
      if (n < 0 || n > 100) {
        return false;
      }
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
  

  private static int getGuess(Scanner input) {
    String guess = input.nextLine();
    while (!valid(guess)) {
      System.out.println("Please enter a number between 0 and 100.");
      guess = input.nextLine();
    }
    return Integer.parseInt(guess);
  }
  
  private static boolean playAgain(String name, Scanner input) {
    boolean play = true;
    System.out.println("Would you like to play again? (y/n)");
    String answer = input.nextLine();
    System.out.print("\033[H\033[2J");
    System.out.flush();
    if (answer.equals("n")) {
      play = false;
      System.out.println("Thanks for playing!");
    }else{
      System.out.println("Okay, " + name + ", now I'm thinking of a new number between 0 and 100.");
    }
    return play;
  }
}
