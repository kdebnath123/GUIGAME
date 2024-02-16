import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;

public class DiceGameView extends JFrame {

    public static final int HEIGHT = 800, WIDTH = 1000,
                            X_OFFSET = 50, Y_OFFSET = 75,
                            LINE_HIEGHT = 25;

    public static final String TITLE = "SHUT THE BOX";

    private Image background;

    private DiceGame d;
    private Box[] boxes;
    private Die diceOne, diceTwo;


    public DiceGameView(DiceGame d){

        this.d = d;
        boxes = d.getBoxes();
        this.diceOne = d.getDiceOne();
        this.diceTwo = d.getDiceTwo();

        background = new ImageIcon("Resources/background.png").getImage();

        this.setSize(WIDTH, HEIGHT);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void paint(Graphics g) {


        g.setColor(Color.WHITE);
        g.drawImage(background, 0, 0, this);

        // Draws instructions once
        if(d.isFirstTime()) {
            g.drawString("Welcome to close the box.", X_OFFSET, Y_OFFSET );
            g.drawString("Each turn roll 2 dice, and sum the numbers.", X_OFFSET, Y_OFFSET + LINE_HIEGHT);
            g.drawString("Then 'shut' the numbers until it adds up the same sum.", X_OFFSET, Y_OFFSET + 2*LINE_HIEGHT);
            g.drawString("Try to get the lowest score.", X_OFFSET, Y_OFFSET + 3*LINE_HIEGHT);
            g.drawString("Input to confirm.", X_OFFSET, Y_OFFSET + 4* LINE_HIEGHT);
            return;
        }

       diceOne.draw(g, this);
       diceTwo.draw(g, this);


       g.drawString("SUM: "+ d.getSum(), X_OFFSET, HEIGHT - Y_OFFSET + LINE_HIEGHT);
       g.drawString("SCORE: "+ d.getScore(), WIDTH - 3*X_OFFSET, HEIGHT - Y_OFFSET + LINE_HIEGHT);


        for (Box b: boxes) {
            b.draw(g, this);
        }

        if(d.hasWon()) {
            g.drawString("YOU CLEARED THE BOARD GOOD JOB!", X_OFFSET, Y_OFFSET);
            g.drawString("YOU WON!!!!", X_OFFSET, Y_OFFSET + LINE_HIEGHT);
        }

        if(d.hasLossed()) {
            g.drawString("No More Possible moves :(", (int)(WIDTH / 3.0), HEIGHT - Y_OFFSET + LINE_HIEGHT);
            g.drawString("FINAL ", WIDTH - 4*(X_OFFSET - 1), HEIGHT - Y_OFFSET + LINE_HIEGHT);
        }
    }
}