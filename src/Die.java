import javax.swing.*;
import java.awt.*;

public class Die {

    public static final int DICE_SIZE = 50, EXTRA_OFFSET = 25,

            /** Magic numbers for where the dice can be randomly drawn in the green pad **/
            ROLL_X_START = DiceGameView.X_OFFSET + EXTRA_OFFSET,
            ROLL_X_SIZE = DiceGameView.WIDTH - ROLL_X_START - DICE_SIZE - EXTRA_OFFSET - DiceGameView.X_OFFSET,
            ROLL_Y_START = DiceGameView.Y_OFFSET + 2 * Box.BOX_HEIGHT + EXTRA_OFFSET,
            ROLL_Y_SIZE = DiceGameView.HEIGHT - ROLL_Y_START - DICE_SIZE - EXTRA_OFFSET - DiceGameView.Y_OFFSET;

    public static final int NUM_SIDES = 6;

    /** Coordinates of dice image **/
    private int Xcorner, Ycorner;

    /** Contains the possible faces of dice to be displayed **/
    private Image[] diceImage;

    /** currently rolled side **/
    private int currentRoll;


    /** Constructors **/
    public Die() {
        // Creates an array to contain the images of each side of the dice
        diceImage = new Image[NUM_SIDES];

        // Saves each side of the dice as an image
        for(int i = 0; i < NUM_SIDES; i++) {
            diceImage[i] = new ImageIcon("Resources/dice/" + (i + 1) +".png").getImage();
        }
    }



    /** Draws face of currently rolled side at prevoisuly randomly generated posistion **/
    public void draw(Graphics g, DiceGameView d) {
        g.drawImage(diceImage[currentRoll - 1], Xcorner, Ycorner, d);
    }

    /**
     * Rolls the dice, generating a new face up side, returns rolled side.
     *  Also randomly generates new dice position to imatate a real roll.
     */
    public int roll() {
        // Randomly generates new cordniates within predetermined ranges
        Xcorner = (int)(Math.random() * (ROLL_X_SIZE) + ROLL_X_START);
        Ycorner = (int)(Math.random() * (ROLL_Y_SIZE) + ROLL_Y_START);

        // Generates random number from 1 to NUM_SIDES to get new dice face
        currentRoll = (int)((Math.random() * (NUM_SIDES)) + 1);
        return currentRoll;


    }

    /**
     * Overloaded verision of roll() which takes in a (X, Y) cordniate
     * Uses given cordniate to ensure the dice don't overlap when drawn
     *
     */
    public int roll(int otherX, int otherY) {

        // Randomly generates new cordniates within predetermined ranges
        Xcorner = (int)(Math.random() * (ROLL_X_SIZE) + ROLL_X_START);
        Ycorner = (int)(Math.random() * (ROLL_Y_SIZE) + ROLL_Y_START);

        // Continuous to re-generate the second location until the dice are non-overlapping
        while (Math.abs(otherX - Xcorner) < DICE_SIZE || Math.abs(Xcorner - Ycorner) < DICE_SIZE) {

            Xcorner = (int)(Math.random() * (ROLL_X_SIZE) + ROLL_X_START);
            Ycorner = (int)(Math.random() * (ROLL_Y_SIZE) + ROLL_Y_START);
        }

        // Generates random number from 1 to NUM_SIDES to get new dice face
        currentRoll = (int)((Math.random() * (NUM_SIDES)) + 1);
        return currentRoll;


    }

    /******************** Getters ********************/
    public int getXcorner() {
        return Xcorner;
    }

    public int getYcorner() {
        return Ycorner;
    }
}
