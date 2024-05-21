import javax.swing.*;
import java.awt.*;

public class Snitch extends Thread {// הכדור שצריך לתפוס

    private int x;
    private int y;
    private int xForRight;
    private int yForDown;
    private int speed;
    private int snitchsToCatch;
    private boolean snitchUpOrDown;
    //    up = true
    //    down = false
    private boolean SnitchRightOrLeft;
    //    Right = true
    //    Left = false
    public static final int SNITCH_WIDTH = 75;
    public static final int SNITCH_HEIGHT = 37;
    public static final int SNITCH_COUNTER_SYMBOL_X = Window.WINDOW_WIDTH  - (SNITCH_WIDTH / 3);
    public static final int SNITCH_UPPER_Y_LINE = (Window.WINDOW_HEIGHT / 7);
    public static final int SNITCH_BOTTOM_Y_LINE = 5 * (Window.WINDOW_HEIGHT / 7);
    public static final int SNITCH_START_X_LINE = Window.WINDOW_WIDTH - 40; // ->
    public static final int SNITCH_END_X_LINE = Window.WINDOW_WIDTH - 300; // <-


    public Snitch(int snitchToCatch){
        this.x = SNITCH_START_X_LINE;
        this.y = Window.THE_MIDDLE_HEIGHT_OF_THE_WINDOW - (SNITCH_HEIGHT / 2);
        this.snitchUpOrDown = true;
        this.SnitchRightOrLeft = false;
        this.snitchsToCatch = snitchToCatch;
        this.speed = 5;
    }

    public void paint (Graphics graphics){
        ImageIcon snitchImage = new ImageIcon( "resources\\images\\Snitch2.2.1.gif");
        snitchImage.paintIcon(null, graphics, this.x, this.y);

        for (int i = 0; i < this.snitchsToCatch; i++) {
            ImageIcon snitchCounterSymbol = new ImageIcon("resources\\images\\SnitchBlurPhoto.png");
            snitchCounterSymbol.paintIcon(null,graphics,SNITCH_COUNTER_SYMBOL_X - (SNITCH_WIDTH * (snitchsToCatch - i)) ,1);
        }
    }

    public void run () {
        while (true){

            this.xForRight = this.x + SNITCH_WIDTH;
            this.yForDown = this.y + SNITCH_HEIGHT;

            if (this.y == SNITCH_UPPER_Y_LINE){
                this.snitchUpOrDown = false;
            }
            if (this.yForDown == SNITCH_BOTTOM_Y_LINE){
                this.snitchUpOrDown = true;
            }
            if (this.xForRight == SNITCH_START_X_LINE){
                this.SnitchRightOrLeft = false;
            }
            if (this.x == SNITCH_END_X_LINE){
                this.SnitchRightOrLeft = true;
            }

            if (this.snitchUpOrDown){
                moveUp();
            }
            if (!this.snitchUpOrDown){
                moveDown();
            }
            if (this.SnitchRightOrLeft){
                moveRight();
            }
            if (!this.SnitchRightOrLeft){
                moveLeft();
            }

            Utils.sleep(speed);
        }
    }
    public Rectangle calculateRectangle () {
        return new Rectangle(this.x, this.y,SNITCH_WIDTH,SNITCH_HEIGHT);
    }
    public void harryCatchTheSnitch(){
        this.snitchsToCatch--;
    }

    public void moveUp(){
        this.y --;
    }
    public void moveDown(){
        this.y ++;
    }
    public void moveRight(){
        this.x ++;
    }
    public void moveLeft(){
        this.x --;
    }

    public void speedUp(){
        if (this.speed > 2) {
            this.speed ++;
        }
    }

    public int getSnitchsToCatch() {
        return snitchsToCatch;
    }
}
