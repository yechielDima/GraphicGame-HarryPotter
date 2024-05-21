

import javax.swing.*;
import java.awt.*;

public class Background extends Thread{

    private int backgroundSpeed;
    private int backgroundImage1X;
    private int BackgroundImage2X;
    public static final int ALL_THE_BACKGROUNDS_Y = 0;
    public static final int BACKGROUND1_START_x = 0;
    public static final int BACKGROUND2_START_x = Window.WINDOW_WIDTH;
    public static final int BACKGROUND_WIDTH = Window.WINDOW_WIDTH;


    public Background(){
        this.backgroundSpeed = 10;
        this.backgroundImage1X = 0;
        this.BackgroundImage2X = BACKGROUND_WIDTH;
    }

    public void paint(Graphics graphics){
        ImageIcon backgroundImage3 = new ImageIcon( "resources\\images\\Background1.2.4.jpg");
        backgroundImage3.paintIcon(null, graphics, BACKGROUND1_START_x, ALL_THE_BACKGROUNDS_Y);
        ImageIcon backgroundImage1 = new ImageIcon( "resources\\images\\Background1.2.4.jpg");
        backgroundImage1.paintIcon(null, graphics, backgroundImage1X, ALL_THE_BACKGROUNDS_Y);
        ImageIcon backgroundImage2 = new ImageIcon( "resources\\images\\Background2.2.4.jpg");
        backgroundImage2.paintIcon(null, graphics, BackgroundImage2X, ALL_THE_BACKGROUNDS_Y);


    }

    public void run(){
        while (true){
            this.backgroundImage1X -= 2;
            this.BackgroundImage2X -= 2;
            if (this.backgroundImage1X == - BACKGROUND_WIDTH){
                this.backgroundImage1X = BACKGROUND2_START_x;
            }
            if (this.BackgroundImage2X == - BACKGROUND_WIDTH){
                this.BackgroundImage2X = BACKGROUND2_START_x;
            }
            Utils.sleep(backgroundSpeed);
        }
    }
    public void speedUp(int addToSpeed){
        if (this.backgroundSpeed > 1) {
            this.backgroundSpeed -= (addToSpeed * 2);
        }
    }


}
