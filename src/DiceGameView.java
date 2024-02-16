import javax.swing.*;
import java.awt.*;

public class DiceGameView extends JFrame {

    /** Window attributes **/
    public static final int HEIGHT = 800, WIDTH = 1000,
                            X_OFFSET = 50, Y_OFFSET = 75,
                            LINE_HEIGHT = 25;
    public static final String TITLE = "SHUT THE BOX";

    /** Background image **/
    private Image background;

    /** Shared data **/
    private DiceGame d;
    private Box[] boxes;
    private Die diceOne, diceTwo;

    /** Constructor **/
    public DiceGameView(DiceGame d) {

        // Get need data from front end
        this.d = d;
        boxes = d.getBoxes();
        this.diceOne = d.getDiceOne();
        this.diceTwo = d.getDiceTwo();

        // Loads background image
        background = new ImageIcon("Resources/background.png").getImage();

        // Initializes window
        this.setSize(WIDTH, HEIGHT);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /** Paints window **/
    public void paint(Graphics g) {

        // Resets background
        g.setColor(Color.WHITE);
        g.drawImage(background, 0, 0, this);

        // Draws instructions once
        if(d.isFirstTime()) {
            g.drawString("Welcome to close the box.", X_OFFSET, Y_OFFSET );
            g.drawString("Each turn roll 2 dice, and sum the numbers.", X_OFFSET, Y_OFFSET + LINE_HEIGHT);
            g.drawString("Then 'shut' any combination of boxes that adds to the sum.", X_OFFSET, Y_OFFSET + 2* LINE_HEIGHT);
            g.drawString("Try to get the lowest score.", X_OFFSET, Y_OFFSET + 3* LINE_HEIGHT);
            g.drawString("Input to confirm.", X_OFFSET, Y_OFFSET + 4* LINE_HEIGHT);
            return;
        }

        // Draw dice
        diceOne.draw(g, this);
        diceTwo.draw(g, this);

        // Informs score


        // Draw each box
        for (Box b: boxes) {
            b.draw(g, this);
        }

        // Won/Loss screen
        if(d.hasWon()) {
            g.drawString("YOU CLEARED THE BOARD GOOD JOB!", X_OFFSET, Y_OFFSET);
            g.drawString("YOU WON!!!!", X_OFFSET, Y_OFFSET + LINE_HEIGHT);
            return;
        }
        if(d.hasLost()) {
            g.drawString("No More Possible moves :(", (int)(WIDTH / 3.0), HEIGHT - Y_OFFSET + LINE_HEIGHT);
            g.drawString("FINAL SCORE: " + d.getScore(), WIDTH - 4*(X_OFFSET), HEIGHT - Y_OFFSET + LINE_HEIGHT);
            return;
        }

        // Only displays if sum/score game is in progress
        g.drawString("SUM: "+ d.getSum(), X_OFFSET, HEIGHT - Y_OFFSET + LINE_HEIGHT);
        g.drawString("SCORE: "+ d.getScore(), WIDTH - 3*X_OFFSET, HEIGHT - Y_OFFSET + LINE_HEIGHT);



    }
}