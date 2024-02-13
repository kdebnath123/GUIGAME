import javax.swing.*;
import java.awt.*;

public class Die {
    /** Instance Variables **/

    public static final int NUM_SIDES = 6;
    public static final int DICE_SIZE = 50;
    private Image[] diceImage;

    private int currentRoll;



    /** Constructors **/
    public Die() {
        diceImage = new Image[NUM_SIDES];

        currentRoll = -1;

        for(int i = 0; i < NUM_SIDES; i++){
            diceImage[i] = new ImageIcon("Resources/dice/" + (i + 1) +".png").getImage();
        }


    }

    public  void draw(Graphics g, DiceGameView d){

        for (int i = 0; i< NUM_SIDES; i++){
            g.drawImage(diceImage[i], 400 + DICE_SIZE*i, 400 + DICE_SIZE*i, d);
        }

    }

    /** Methods **/

    /**
     * Returns a random int between 1 and
     * the number of sides on the Die
     */
    public int roll() {
        currentRoll = (int)(Math.random() * (NUM_SIDES) + 1);
        return currentRoll;
    }

    public int getCurrentRoll(){
        return currentRoll;
    }

    /**
     * Rolls the dice the numRolls times
     * and returns the max value of the rolls
     */
    public int getMaxRoll(int numRolls) {

        int rollMax = 0;

        for (int i = 0; i < numRolls; i++) {

            rollMax = Math.max(this.roll(), rollMax);

        }

        return rollMax;

    }

    /**
     * Returns a String in the following form:
     * "This is an n-sided die."
     */
    public String toString() {
        return "This is a " + NUM_SIDES + "-sided die";
    }
}
