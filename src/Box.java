import javax.swing.*;
import java.awt.*;

public class Box {

    public static final int BOX_HEIGHT = 150;
    public static final int BOX_WIDTH = 100;

    private int number;
    private boolean isOpen;

    private int Xcorner;
    private int Ycorner;

    private Image openImage, closedImage;

    public Box(int number){
        this.number = number;
        this.isOpen = true;

        Xcorner = DiceGameView.X_OFFSET + BOX_WIDTH*(number - 1);
        Ycorner = DiceGameView.Y_OFFSET;

        closedImage = new ImageIcon("Resources/boxes/0.png").getImage();
        openImage = new ImageIcon("Resources/boxes/" + number + ".png").getImage();
    }

    public int getNumber() {
        return number;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void close() {
        isOpen = false;
    }
    public void open(){
        isOpen = true;
    }

    public  void draw(Graphics g, DiceGameView d){

        if(isOpen){
            g.drawImage(openImage, Xcorner, Ycorner, d);
        }
        else{
            g.drawImage(closedImage, Xcorner, Ycorner + BOX_HEIGHT, d);
        }

    }




}
