import java.awt.*;

public class FreezeBall extends Thread{

    private int x;
    private int y;
    private boolean needUp;
    private boolean toHideOrNot;
    private int waitBeforeStart;
    private int currentAmountOfFreezeBalls;
    public static final int FREEZE_BALL_SIZE = 20;
    public static final int FREEZE_BALL_SPEED =6;
    public static final int FREEZE_BALL_IN_MAGAZINE_WIDTH = 40;
    public static final int FREEZE_BALL_IN_MAGAZINE_HEIGHT = 50;
    public static final int MAX_AMOUNT_OF_FREEZE_BALLS = 5;
    public static final int UPPER_LINE_FOR_FREEZE_BALL = 0;
    public static final int BOTTOM_LINE_FOR_FREEZE_BALL = Window.WINDOW_HEIGHT - FREEZE_BALL_SIZE - 35;
    public static final int START_LINE_FOR_FREEZE_BALL = Window.WINDOW_WIDTH + 75;
    public static final int END_LINE_FOR_FREEZE_BALL = -FREEZE_BALL_SIZE;
    public static final int MAGAZINE_WIDTH = (FREEZE_BALL_IN_MAGAZINE_WIDTH * MAX_AMOUNT_OF_FREEZE_BALLS) + 4;
    public static final int MAGAZINE_HEIGHT = FREEZE_BALL_IN_MAGAZINE_HEIGHT + 4;


    public FreezeBall(int y, int waitBeforeStart){
        this.x = START_LINE_FOR_FREEZE_BALL;
        this.y = y;
        this.needUp = false;
        this.waitBeforeStart = waitBeforeStart;
        this.toHideOrNot = true;
        this.currentAmountOfFreezeBalls = 0;

    }
    public void paint(Graphics graphics){
        if (currentAmountOfFreezeBalls < 5) {
            if (this.toHideOrNot) {
                graphics.setColor(Color.CYAN);
                graphics.fillOval(this.x, this.y, FREEZE_BALL_SIZE, FREEZE_BALL_SIZE);
            }
        }
        graphics.setColor(Color.RED);
        graphics.drawRect(2,2 , MAGAZINE_WIDTH, MAGAZINE_HEIGHT);

        graphics.setColor(Color.CYAN);
        graphics.fillRect(4,4,FREEZE_BALL_IN_MAGAZINE_WIDTH * currentAmountOfFreezeBalls, FREEZE_BALL_IN_MAGAZINE_HEIGHT);
    }

    public void run(){
        Utils.sleep(waitBeforeStart);

        while (true){
            if (this.y >= BOTTOM_LINE_FOR_FREEZE_BALL || this.y <= UPPER_LINE_FOR_FREEZE_BALL){
                this.needUp = !needUp;
            }
            if (needUp){
                moveUp();
            }
            else {
                moveDone();
            }

            if (this.x <= END_LINE_FOR_FREEZE_BALL){
                this.x = START_LINE_FOR_FREEZE_BALL;
                this.toHideOrNot = true;
                Utils.sleep(waitBeforeStart);
            }

            Utils.sleep(FREEZE_BALL_SPEED);
        }
    }

    public Rectangle calculateRectangle(){
        return new Rectangle(this.x , this.y , FREEZE_BALL_SIZE , FREEZE_BALL_SIZE);
    }
    public void gotFreezeBall(){
        if (currentAmountOfFreezeBalls < MAX_AMOUNT_OF_FREEZE_BALLS){
            this.currentAmountOfFreezeBalls++;
        }
    }
    public void useFreezeBall(){
        if (currentAmountOfFreezeBalls > 0){
            this.currentAmountOfFreezeBalls--;
        }
    }
    public boolean canFreeze(){
        return currentAmountOfFreezeBalls > 0;
    }

    public void moveUp(){
        this.x--;
        this.y -= 2;
    }
    public void moveDone(){
        this.x--;
        this.y += 2;
    }
    public void setToHideOrNotFreezeBall(boolean toHideOrNot){
        this.toHideOrNot = toHideOrNot;
    }

}