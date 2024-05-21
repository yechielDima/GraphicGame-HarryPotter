import java.awt.*;

public class Boost extends Thread{
    private int x;
    private int y;
    private boolean needUp;

    private int waitBeforeStart;
    private boolean toHideOrNot;
    public static final int BOOST_BALL_SIZE = 20;
    public static final int UPPER_LINE_FOR_BOOST = 0;
    public static final int BOTTOM_LINE_FOR_BOOST = Window.WINDOW_HEIGHT - BOOST_BALL_SIZE - 35;
    public static final int START_LINE_FOR_BOOST = Window.WINDOW_WIDTH + 50;
    public static final int END_LINE_FOR_BOOST = -BOOST_BALL_SIZE;
    public static final int BOOST_SPEED=6;


    public Boost(int x, int y, int waitBeforeStart){
        this.x = x;
        this.y = y;

        this.needUp = false;
        this.waitBeforeStart = waitBeforeStart;
        this.toHideOrNot = true;
    }

    public void paint(Graphics graphics){
        if (this.toHideOrNot){
            graphics.setColor(Color.YELLOW);
            graphics.fillOval(this.x, this.y, BOOST_BALL_SIZE, BOOST_BALL_SIZE);
        }
    }


    public void run(){
        Utils.sleep(waitBeforeStart);

        while (true){
           if (this.y >= BOTTOM_LINE_FOR_BOOST || this.y <= UPPER_LINE_FOR_BOOST) {
               this.needUp = !needUp;
           }
           if (needUp){
               moveUp();
           }
           else {
               moveDone();
           }

           if (this.x <= END_LINE_FOR_BOOST){
               this.x = START_LINE_FOR_BOOST;
               this.toHideOrNot = true;
               Utils.sleep(waitBeforeStart);
           }

            Utils.sleep(BOOST_SPEED);
        }
    }


    public Rectangle calculateRectangle(){
        return new Rectangle(this.x,this.y, BOOST_BALL_SIZE, BOOST_BALL_SIZE);
    }
    public void moveUp(){
        this.x--;
        this.y -= 3;
    }
    public void moveDone(){
        this.x--;
        this.y += 3;
    }

    public void setToHideOrNotBoost(boolean toHideOrNot){
        this.toHideOrNot = toHideOrNot;
    }


}
