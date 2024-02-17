import java.util.InputMismatchException;
import java.util.Scanner;

public class DiceGame {

    private Scanner input;
    public static final int BOX_NUMBER = 9;

    /** Game data **/
    private int sum;
    private int score;

    /** Objects **/
    private Box[] boxes;
    private Die diceOne, diceTwo;
    private DiceGameView window;

    /** Tracks game progression **/
    private boolean isFirstTime, hasWon, hasLost;


    /** Main function **/
    public static void main(String[] args) {

        DiceGame game = new DiceGame();
        game.playGame();

    }

    /** Constructor **/
    public DiceGame() {

        // Creates Scanner
        this.input = new Scanner(System.in);

        this.sum = 0;
        // Initial score is sum of numbers 1 to 9
        this.score = 45;

        // Initial game status
        this.isFirstTime = true;
        this.hasWon = false;
        this.hasLost = false;

        // Create two six sided dice
        diceOne = new Die();
        diceTwo = new Die();

        // Creates an array that holds 9 boxes
        boxes = new Box[BOX_NUMBER];

        for (int i = 0; i < BOX_NUMBER; i++){
            boxes[i] = new Box(i + 1);
        }

        // Creates the viewer's window
        this.window = new DiceGameView(this);
    }

    /** Controls overview of game round **/
    public void playGame() {

        // Waits for user to read and confirm instructions
        System.out.print("Enter to start: ");
        input.nextLine();
        isFirstTime = false;

        //Loops until game is over
        while(true) {

            // Roll dice and sum them
            sum = diceOne.roll() + diceTwo.roll(diceOne.getXcorner(), diceOne.getYcorner());

            //Check win/loss exit if it is
            if(GameIsOver()){
                break;
            }

            window.repaint();

            //Controls Gameplay for each dice roll
            //Continue to re-prompt and close boxes until Non-possible
            while(!GameIsOver() && closeBox() != 0) {

                // Update screen
                window.repaint();
            }
        }
    }

    /** Reprompts until valid number is inputed, then closes the corresponding box **/
    public int closeBox() {

        int close;

        // Forces user to input a number between 1 and 9
        while (true) {
            // Try/Catch learned from Stack Overflow
            try {
                System.out.print("Close box: ");
                close = input.nextInt();

                if (close > 0 && close <= 9) {
                    input.nextLine();
                    break;
                }
            }
            catch (InputMismatchException e) {
                input.next();
            }
        }

        // Returns -1 if box cannot be close
        if (close > sum || !(boxes[close - 1].isOpen())) {
            return - 1;
        }
        // Closes box, updates score, and returns the remaining sum if box can be closed
        else {
            boxes[close - 1].close();
            sum -= close;
            score -= close;
            return sum;
        }
    }

    /**
     * Checks if the game has been won/lost or in progress
     * Then updates accordingly
     * Returns true if game is won or lost
     * Returns false if game is in progress
     **/
    public boolean GameIsOver() {

        // Checks if user has won
        if(score == 0) {
            hasWon = true;
            window.repaint();
            return true;
        }

        // Checks if any box can be closed
        for (int i = 0; i < 9; i++) {
            if (boxes[i].isOpen() && boxes[i].getNumber() <= sum) {
                return false;
            }
        }

        // If user has not won, but can't close any boxes they must have lost.
        hasLost = true;
        window.repaint();
        return true;
    }

    /******************** Getters ********************/
    public Die getDiceOne() {
        return diceOne;
    }

    public Die getDiceTwo() {
        return diceTwo;
    }

    public boolean isFirstTime() {
        return isFirstTime;
    }

    public boolean hasLost() {
        return hasLost;
    }

    public boolean hasWon() {
        return hasWon;
    }

    public int getSum() {
        return sum;
    }

    public int getScore() {
        return score;
    }

    public Box[] getBoxes() {
        return boxes;
    }
}