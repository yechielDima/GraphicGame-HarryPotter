

import javax.swing.*;
import java.awt.*;

public class Wall { // שרביט חוסם

    private int x;
    public static final int HOW_MUCH_TO_MOVE = 150;
    public static final int WAND_HEIGHT = 132;
    public static final int WAND_WIDTH = 14;
    public static final int Y= Window.WINDOW_HEIGHT - WAND_HEIGHT - 30;
    public static final int START_LINE_FOR_WALL = (Window.WINDOW_WIDTH / 3) + 210;
    public static final int END_LINE_FOR_WALL = Window.WINDOW_WIDTH - 20;



    public Wall(){
        this.x = START_LINE_FOR_WALL;
    }
    public void gutBonus(){
        if (this.x < END_LINE_FOR_WALL){
            this.x += HOW_MUCH_TO_MOVE;
        }
    }
    public void died(boolean isHarryAlive){
        if (isHarryAlive){
            this.x -= HOW_MUCH_TO_MOVE / 2;
        }
    }

    public void paint(Graphics graphics){
        ImageIcon wandImage = new ImageIcon("resources\\images\\Wand2.0.png");
        wandImage.paintIcon(null,graphics,this.x,Y);

        graphics.setColor(Color.GREEN);
        graphics.fillRect(this.x + (WAND_WIDTH / 2) - 2, 0, (WAND_WIDTH / 4) + 1,Window.WINDOW_HEIGHT - Wall.WAND_HEIGHT - 30);
    }

    public boolean isWallStartedToMove(){
        return this.x != START_LINE_FOR_WALL;
    }
    public void reset(){
        this.x = START_LINE_FOR_WALL;
    }

    public int getX() {
        return x;
    }
}
