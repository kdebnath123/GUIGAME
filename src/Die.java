import java.awt.*;

public class Die {
    /** Instance Variables **/

    private int numSides;

    private Image[] diceImage;

    /** Constructors **/
    public Die() {
        this.numSides = 6;
        diceImage = new Image[numSides];

        // TODO: import the images

    }

    /** Methods **/

    /**
     * Returns the number of sides on the Die.
     */
    public int getSides() {
        return numSides;
    }

    /**
     * Returns a random int between 1 and
     * the number of sides on the Die
     */
    public int roll() {
        return (int)(Math.random() * (numSides) + 1);
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
        return "This is a " + numSides + "-sided die";
    }
}
