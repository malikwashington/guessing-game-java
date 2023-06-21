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
    System.out.println("Hi, " + name + ", I'm thinking of a number between 0 and 100.");
    
    
    boolean play = true;
    
    while (play) {
      String highScore = "false";
      int tries = playGame(name, input);
      if (tries < 11) {
        highScore = checkHighScore(highScore, tries);
      }
      play = playAgain(name, input);
    }
    input.close();
  }
  

  private static int playGame(String name, Scanner input){
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
        System.out.println("\nCongratulations " + name + ", you got it in " + tries + " tries!");
      }
      else{
        String response = guess < number ? "Too Low." : "Too High.";
        System.out.println(response + " Guess again. \n");
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
      System.out.println("The current high score is " + highScore + " tries. Can you beat that?");
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
