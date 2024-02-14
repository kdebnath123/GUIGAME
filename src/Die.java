import javax.swing.*;
import java.awt.*;

public class Die {
    /** Instance Variables **/

    public static final int DICE_SIZE = 50, EXTRA_OFFSET = 25,
            ROLL_X_START = DiceGameView.X_OFFSET + EXTRA_OFFSET,
            ROLL_X_SIZE = DiceGameView.WIDTH - ROLL_X_START - DICE_SIZE - EXTRA_OFFSET - DiceGameView.X_OFFSET,
            ROLL_Y_START = DiceGameView.Y_OFFSET + 2 * Box.BOX_HEIGHT + EXTRA_OFFSET,
            ROLL_Y_SIZE = DiceGameView.HEIGHT - ROLL_Y_START - DICE_SIZE - EXTRA_OFFSET - DiceGameView.Y_OFFSET;

    public static final int NUM_SIDES = 6;


    private int Xcorner;
    private int Ycorner;
    private Image[] diceImage;

    private int currentRoll;



    /** Constructors **/
    public Die() {
        diceImage = new Image[NUM_SIDES];

        currentRoll = NUM_SIDES;

        for(int i = 0; i < NUM_SIDES; i++){
            diceImage[i] = new ImageIcon("Resources/dice/" + (i + 1) +".png").getImage();
        }


    }

    public  void draw(Graphics g, DiceGameView d){

        Xcorner = (int)(Math.random() * (ROLL_X_SIZE) + ROLL_X_START);
        Ycorner = (int)(Math.random() * (ROLL_Y_SIZE) + ROLL_Y_START);

        g.drawImage(diceImage[currentRoll - 1], Xcorner, Ycorner, d);
    }

    //public static void NoOvelap(Die one, Die two, Graphics g, DiceGameView d){





    //}



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
     * Returns a String in the following form:
     * "This is an n-sided die."
     */
    @Override
    public String toString() {
        return "This is a " + NUM_SIDES + "-sided die";
    }
}
