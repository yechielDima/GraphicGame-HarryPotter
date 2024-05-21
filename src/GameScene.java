import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScene extends JPanel implements KeyListener {

    private Harry harry;
    private final int timeForHarryToRevive = 2600;
    private int waitBeforeReviveCounter = 0;
    private int timeToChange = 300;
    private final int toAdd = 100;

    private Snitch snitch;
    private int snitchToCatch;
    private int waitBeforeNextSnitch = 0;
    private Background background;
    private Boost[] boosts;
    private int waitBeforeNextBoost = 0;
    private FreezeBall freezeBall;
    private int waitBeforeNextFreezeBall = 0;
    private int waitBeforeCanUseNextFreezeBall = 0;
    private Wall wall;
    private int waitBeforeMoveWallBack = 0;
    private Bludger[] bludgers;
    private boolean[] pressedKeys;
    private EndPanel endPanel;

    public static final int HARRY_SPEED = 1;
    public static final int TOTAL_BLUDGERS = 3;
    public static final int TOTAL_BOOSTS = 1;
    public static final int TOTAL_KEYS = 5;
    public static final int DOWN = 0;
    public static final int UP = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int SPACE = 4;


    public GameScene(){

        this.background = new Background();
        this.background.start();

        this.wall = new Wall();

        this.pressedKeys = new boolean[TOTAL_KEYS];

        this.harry = new Harry(Harry.HARRY_START_X, Harry.HARRY_START_Y);

        this.snitchToCatch = 3;

        this.snitch = new Snitch(this.snitchToCatch);
        this.snitch.start();

        this.boosts = new Boost[TOTAL_BOOSTS];
        for (int i = 0; i < this.boosts.length; i++) {
            Boost boost = new Boost(Window.WINDOW_WIDTH + 50, 1, Utils.seconds(3));
            boosts[i] = boost;
            boosts[i].start();
        }

        this.freezeBall = new FreezeBall(1, 7000);
        this.freezeBall.start();

        this.bludgers = new Bludger[TOTAL_BLUDGERS];
        for (int i = 0; i < this.bludgers.length; i++) {
            Bludger bludger = new Bludger(Bludger.START_LINE_FOR_BLUDGER, 1, i, (i) * 5000, this.snitch.getSnitchsToCatch());
            bludgers[i] = bludger;
            bludger.start();
        }

        this.endPanel = new EndPanel();

        this.mainGameLoop();

        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);
    }


    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        this.background.paint(graphics);

        this.wall.paint(graphics);

        this.harry.paint(graphics);

        for (int i = 0; i < TOTAL_BLUDGERS; i++) {
            bludgers[i].paint(graphics);
        }

        this.snitch.paint(graphics);

        for (Boost boost : this.boosts) {
            boost.paint(graphics);
        }

        this.freezeBall.paint(graphics);

        if (this.snitch.getSnitchsToCatch() == 0) {
            endPanel.paintComponent(graphics);
        }

    }


    public void mainGameLoop() {
        new Thread(() -> {

            //calculate harry movement section
            while (true) {

                // calculate the end of the game section
                if (this.snitch.getSnitchsToCatch() == 0) {
                    Utils.sleep(3000);
                    System.exit(74);
                }

                //calculate harry movement section
                int dx = 0;
                int dy = 0;

                if (this.pressedKeys[DOWN]) {
                    if (this.harry.getY() + this.harry.getHeight() < Window.WINDOW_HEIGHT - 50) {
                        dy++;
                    }
                }

                if (this.pressedKeys[UP]) {
                    if (harry.getY() > 0) {
                        dy--;
                    }
                }

                if (this.pressedKeys[RIGHT]) {
                    if (harry.getX() + harry.getWidth() < Window.WINDOW_WIDTH && this.harry.getX() + this.harry.getWidth() < wall.getX() + 6) {
                        dx++;
                    }
                }

                if (this.pressedKeys[LEFT]) {
                    if (harry.getX() > 0) {
                        dx--;
                    }
                }

                this.harry.move(dx, dy);

                // calculate if can freeze bludgers section
                if (this.freezeBall.canFreeze()) {
                    if (this.pressedKeys[SPACE]) {
                        if (this.waitBeforeCanUseNextFreezeBall == 800) {
                            this.waitBeforeCanUseNextFreezeBall = 0;
                            this.freezeBall.useFreezeBall();
                            for (int i = 0; i < TOTAL_BLUDGERS; i++) {
                                this.bludgers[i].freeze();
                            }
                        }
                    }
                }


                // calculate harry vs snitch section
                if (Utils.checkCollision(this.harry.calculateRectangle2(), this.snitch.calculateRectangle())) {
                    if (this.waitBeforeNextSnitch == 200) {
                        this.waitBeforeNextSnitch = 0;
                        this.snitch.speedUp();
                        this.snitch.harryCatchTheSnitch();
                        this.background.speedUp(this.snitch.getSnitchsToCatch());
                        this.harry.reset();
                        this.wall.reset();
                    }
                }


                // calculate harry vs boost section
                for (Boost boost : this.boosts) {
                    if (Utils.checkCollision(this.harry.calculateRectangle1(), boost.calculateRectangle()) || Utils.checkCollision(this.harry.calculateRectangle2(), boost.calculateRectangle())) {
                        if (this.waitBeforeNextBoost == 3000) {
                            this.wall.gutBonus();
                            boost.setToHideOrNotBoost(false);
                            this.waitBeforeNextBoost = 0;
                        }
                    }
                }

                // calculate harry vs freeze ball section
                if (Utils.checkCollision(this.harry.calculateRectangle1(), this.freezeBall.calculateRectangle()) || Utils.checkCollision(this.harry.calculateRectangle2(), this.freezeBall.calculateRectangle())) {
                    if (this.waitBeforeNextFreezeBall == 3000) {
                        this.freezeBall.gotFreezeBall();
                        this.freezeBall.setToHideOrNotFreezeBall(false);
                        this.waitBeforeNextFreezeBall = 0;
                    }
                }


                // calculate harry vs bludger section
                for (int i = 0; i < TOTAL_BLUDGERS; i++) {
                    if (Utils.checkCollision(this.harry.calculateRectangle1(), this.bludgers[i].calculateRectangle()) || Utils.checkCollision(this.harry.calculateRectangle2(), this.bludgers[i].calculateRectangle())) {
                        if (this.wall.isWallStartedToMove()) {
                            if (this.waitBeforeMoveWallBack == 1000) {
                                this.wall.died(this.harry.isAlive());
                                this.waitBeforeMoveWallBack = 0;
                            }
                        }
                        this.harry.kill();
                    }
                }

                // calculate the next boost and move wall section
                if (this.waitBeforeNextBoost < 3000) {
                    this.waitBeforeNextBoost++;
                }
                if (this.waitBeforeMoveWallBack < 1000) {
                    this.waitBeforeMoveWallBack++;
                }
                //calculate the next freeze ball section
                if (this.waitBeforeNextFreezeBall < 3000) {
                    this.waitBeforeNextFreezeBall++;
                }
                if (this.waitBeforeCanUseNextFreezeBall < 800) {
                    this.waitBeforeCanUseNextFreezeBall++;
                }
                if (this.waitBeforeNextSnitch < 200) {
                    this.waitBeforeNextSnitch++;
                }

                // calculate harry time after dying section
                if (!this.harry.isAlive()) {
                    waitBeforeReviveCounter++;
                    if (waitBeforeReviveCounter == timeForHarryToRevive) {
                        timeToChange = toAdd;
                        harry.revive();
                        waitBeforeReviveCounter = 0;

                    } else if (waitBeforeReviveCounter == timeToChange) {
                        timeToChange += toAdd;
                        harry.toHideOrNot();
                    }
                }
                repaint();

                Utils.sleep(HARRY_SPEED);
            }
        }).start();
    }


    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        Integer toPress = null;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            toPress = RIGHT;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            toPress = LEFT;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            toPress = UP;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            toPress = DOWN;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            toPress = SPACE;
        }
        if (toPress != null) {
            this.pressedKeys[toPress] = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        Integer toRelease = null;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            toRelease = RIGHT;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            toRelease = LEFT;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            toRelease = UP;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            toRelease = DOWN;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            toRelease = SPACE;
        }

        if (toRelease != null) {
            this.pressedKeys[toRelease] = false;
        }
    }
}