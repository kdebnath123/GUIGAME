import javax.swing.*;
import java.awt.*;

public class DiceGameView extends JFrame {

    public static final int HEIGHT = 800, WIDTH = 1000,
            BOX_SIZE = 100,
            X_OFFSET = 50,
            Y_OFFSET = 75;
    public static final String TITLE = "SHUT THE BOX";

    private Image background;

    private DiceGame d;
    private Box[] boxes;


    public DiceGameView(DiceGame d){

        this.d = d;
        boxes = d.getBoxes();

        background = new ImageIcon("Resources/background.png").getImage();

        this.setSize(WIDTH, HEIGHT);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void paint(Graphics g){

        g.drawImage(background, 0, 0, this);

        for(int i = 0; i < 9; i++){
            boxes[i].draw(g, this);
        }

        for (Box b: boxes) {
            b.draw(g, this);
        }


    }

}