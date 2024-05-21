import javax.swing.*;
import java.awt.*;

public class Harry {// הארי בעצמו

    private int x;
    private int y;
    private ImageIcon harryImage;
    private boolean alive;
    public static final int HARRY_HEIGHT = 186;
    public static final int HARRY_WIDTH = 310;
    public static final int HARRY_START_X = Window.WINDOW_WIDTH - 1700;
    public static final int HARRY_START_Y = (Window.WINDOW_HEIGHT / 2) - 100;
    private int[] calculateRectangleOfHarry1;
    private int[] calculateRectangleOfHarry2;

    private boolean showHarry;


    public Harry(int x,int y){
        this.x = x;
        this.y = y;
        this.alive=true;
        this.showHarry=true;
        this.calculateRectangleOfHarry1 = new int[4];
        this.calculateRectangleOfHarry2 = new int[4];

    }
    public void paint (Graphics graphics) {
        if (this.showHarry){
            this.harryImage = new ImageIcon("resources\\images\\Harry2.1.png");
            harryImage.paintIcon(null,graphics,this.x,this.y);
        }

        this.calculateRectangleOfHarry1[0] = this.x + 50;
        this.calculateRectangleOfHarry1[1] = this.y +26;
        this.calculateRectangleOfHarry1[2] = 120;
        this.calculateRectangleOfHarry1[3] = 150;

        this.calculateRectangleOfHarry2[0] = this.x + 170;
        this.calculateRectangleOfHarry2[1] = this.y;
        this.calculateRectangleOfHarry2[2] = 140;
        this.calculateRectangleOfHarry2[3] = 100;
    }
    public void move (int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public int getX () {
        return this.x;
    }

    public int getY() {
        return y;
    }
    public int getHeight(){
        return this.harryImage.getIconHeight();
    }
    public int getWidth(){
        return this.harryImage.getIconWidth();
    }
    public void kill(){
        if (this.alive){
            showHarry = false;
            this.alive = false;
            reset();
        }
    }
    public Rectangle calculateRectangle1() {
        return new Rectangle(this.calculateRectangleOfHarry1[0],this.calculateRectangleOfHarry1[1],this.calculateRectangleOfHarry1[2],this.calculateRectangleOfHarry1[3]);
    }
    public Rectangle calculateRectangle2() {
        return new Rectangle(this.calculateRectangleOfHarry2[0],this.calculateRectangleOfHarry2[1],this.calculateRectangleOfHarry2[2],this.calculateRectangleOfHarry2[3]);
    }

    public void revive () {
        showHarry=true;
        this.alive = true;
    }
    public boolean isAlive () {
        return this.alive;
    }
    public void toHideOrNot(){
        this.showHarry = !this.showHarry;
    }
    public void reset(){
        this.x = HARRY_START_X;
        this.y = HARRY_START_Y;
    }

}