import javax.swing.*;
import java.awt.*;

public class Box {

    /** Box size **/
    public static final int BOX_HEIGHT = 150;
    public static final int BOX_WIDTH = 100;

    /** Box number and open status **/
    private int number;
    private boolean isOpen;

    /** Corner of current box **/
    private int Xcorner;
    private int Ycorner;

    /** Images for open/closed boxes **/
    private Image openImage, closedImage;

    /** Constructor **/
    public Box(int number) {
        // Sets number and starts with open box
        this.number = number;
        this.isOpen = true;

        // Creates lined-up boxes
        Xcorner = DiceGameView.X_OFFSET + BOX_WIDTH*(number - 1);
        Ycorner = DiceGameView.Y_OFFSET;

        // Loads in images
        closedImage = new ImageIcon("Resources/boxes/0.png").getImage();
        openImage = new ImageIcon("Resources/boxes/" + number + ".png").getImage();
    }

    /** Draws box in opened or closed state **/
    public  void draw(Graphics g, DiceGameView d) {

        if(isOpen) {
            g.drawImage(openImage, Xcorner, Ycorner, d);
        }
        else {
            g.drawImage(closedImage, Xcorner, Ycorner + BOX_HEIGHT, d);
        }
    }

    /** Getters **/
    public int getNumber() {
        return number;
    }
    public boolean isOpen() {
        return isOpen;
    }

    /** Setter **/
    public void close() {
        isOpen = false;
    }
}
