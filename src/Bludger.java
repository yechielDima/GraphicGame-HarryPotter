import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Bludger extends Thread { // הכדור שפוגע לך בראש


    private int x;
    private int y;
    private int angle;
    private boolean down;
    private boolean toFreeze;
    private int bludgerSleepSpeed;
    private int waitBeforeStartBeforeSet;
    private int waitBeforeStartAfterSet;
    private int heightOfStraightBludger;
    private int addToStraightBludgerSpeed;
    public static final int BLUDGER_SIZE = 45; // WIDTH = HEIGHT
    public static final int TIME_TO_STOP_WHILE_FROZEN = 2000;
    public static final int UPPER_LINE_FOR_BLUDGER = 0;
    public static final int BOTTOM_LINE_FOR_BLUDGER = Window.WINDOW_HEIGHT - 35 - BLUDGER_SIZE;
    public static final int START_LINE_FOR_BLUDGER = Window.WINDOW_WIDTH + BLUDGER_SIZE;
    public static final int END_LINE_FOR_BLUDGER = - BLUDGER_SIZE;






    public Bludger(int x,int y, int angle,int waitBeforeStart , int addToStraightBludgerSpeed){
        this.x = x;
        this.y= y;
        this.down = true;
        this.angle = angle;
        this.toFreeze = false;
        this.waitBeforeStartAfterSet = waitBeforeStart;
        this.waitBeforeStartBeforeSet = 2000;
        this.addToStraightBludgerSpeed = addToStraightBludgerSpeed;

    }
    public void paint(Graphics graphics){
        ImageIcon bludgerImage = new ImageIcon("resources\\images\\Bludger2.2.png");
        bludgerImage.paintIcon(null,graphics,this.x,this.y);
    }

    public void run () {
        Utils.sleep(waitBeforeStartBeforeSet);
        Random random = new Random();
        if (this.angle == 0){
            if(this.addToStraightBludgerSpeed > 1) {
                this.bludgerSleepSpeed = random.nextInt(3, 4);
            }else {
                this.bludgerSleepSpeed = random.nextInt(2, 3);
            }
        }else {
            this.bludgerSleepSpeed = random.nextInt(3, 6);
        }
        this.heightOfStraightBludger = 100 * (random.nextInt(1,9));


        Utils.sleep(this.waitBeforeStartAfterSet);

        while (true) {
            if (this.y >= BOTTOM_LINE_FOR_BLUDGER || this.y <= UPPER_LINE_FOR_BLUDGER){
                this.down = !down;
            }
            if (this.angle == 0){
                this.y = this.heightOfStraightBludger;
            }

            if (this.down){
                this.moveDown();
            }else {
                this.moveUp();
            }
            if (this.x == END_LINE_FOR_BLUDGER){
                this.x = START_LINE_FOR_BLUDGER;
                this.heightOfStraightBludger = 100 * (random.nextInt(1,9));
            }
            Utils.sleep(this.bludgerSleepSpeed);

            if (toFreeze){
                this.toFreeze = false;
                Utils.sleep(TIME_TO_STOP_WHILE_FROZEN);
            }
        }

    }
    public void moveUp(){
        this.x--;
        this.y -= this.angle;
    }
    public void moveDown(){
        this.x--;
        this.y += this.angle;
    }
    public void freeze(){
        this.toFreeze = true;
    }

    public Rectangle calculateRectangle () {
        return new Rectangle(this.x, this.y,BLUDGER_SIZE,BLUDGER_SIZE);
    }

}