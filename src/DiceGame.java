import java.util.InputMismatchException;
import java.util.Scanner;

public class DiceGame {

    //Instance variables    
    private Scanner input;
    public static final int BOX_NUMBER = 9;
    private int sum;
    private Box[] boxes;
    private int roundNumber;
    private int close;

    private Die diceOne, diceTwo;

    private DiceGameView window;


    //Main function
    public static void main(String[] args){

        DiceGame game = new DiceGame();
        game.playGame();

    }

    //Constructor
    public DiceGame() {

        this.input = new Scanner(System.in);
        this.roundNumber = 0;
        this.sum = 0;

        diceOne = new Die();
        diceTwo = new Die();


        boxes = new Box[BOX_NUMBER];

        for (int i = 0; i < BOX_NUMBER; i++){
            boxes[i] = new Box(i + 1);
        }

        this.window = new DiceGameView(this);

    }

    public Box[] getBoxes(){
        return boxes;
    }

    //Controls overview of game rounds
    public void playGame() {

        window.repaint();



        //print instructions
        printInsructions();

        //Loops forever, controls each round of game
        while(true) {

            //Roll dice and sum them
            sum = diceOne.roll() + diceTwo.roll();;

            //Check win/loss
            checkWinLoss();

            //Inform
            System.out.println("You rolled a " + diceOne.getCurrentRoll() + " and a " + diceTwo.getCurrentRoll()
                    + " for a sum of " + sum);
            printOpenBoxes();

            //Controls Gameplay for each dice roll
            //Countinue to reprompt and close boxes until Nonpossible
            while (closeBox() != 0) {

                //Clear the screen -- from javapoint
                System.out.print("\033[H\033[2J");

                //Print board and inform
                printOpenBoxes();
                System.out.println("Your remaining sum is " + sum);

                //Check win/loss
                checkWinLoss();

            }

        }

    }


    //Print games instructions
    public void printInsructions(){

        System.out.println("Welcome to close the box.");
        System.out.println("Each turn roll 2 dice, and sum the numbers.");
        System.out.println("Then 'shut' the numbers" +
                "until it adds up the same sum.");
        System.out.println("Try to get the lowest score.\n");

    }

    //Print the open boxes
    public void printOpenBoxes() {
        window.repaint();
    }

    //Prompts for next int
    //If input is valid then close it
    public int closeBox() {

        //Prompt
        int close;

        // Gets only valid int inputs;
        while (true) {
            try
            {
                System.out.print("Close box: ");
                close = input.nextInt();

                if (close > 0 && close <= 9){
                    input.nextLine();
                    break;
                }
            }
            catch (InputMismatchException e)
            {
                input.next();
            }
        }


        //Valid check
        if (close > sum || !(boxes[close - 1].isOpen())) {

            return -1;
        }
        else {
            boxes[close - 1].close();
            sum -= close;

            return sum;
        }

    }

    public void checkWinLoss() {


        int score = 0;
        boolean hasLossed = true;

        // Check if any box can be closed
        for (int i = 0; i < 9; i++) {

            if(boxes[i].isOpen()){
                score += boxes[i].getNumber();
            }

            //If any open box is smaller than sum, it can be closed
            //Thus player countinues to play
            if (boxes[i].isOpen() && boxes[i].getNumber() <= sum) {
                return;
            }

        }

        //If sum is zero, all boxes are closed, so they won
        //Win screen
        if (score == 0){

            System.out.print("\033[H\033[2J");
            System.out.println("YOU CLEARED THE BOARD GOOD JOB!");
            System.out.println("YOU WON!!!!");

        }

        //If they cant close any box and didn't win, they lost
        //Loss screen
        System.out.print("\033[H\033[2J");
        System.out.println("No More Possible moves :(");
        System.out.println("Good job your score was " + score);

    }
}