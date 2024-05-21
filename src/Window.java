import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class Window extends JFrame {
    private GameScene gameScene;
    private StartHomePage startHomePage;
    private StartHomePage2 startHomePage2;
    private JButton switchPanelsButton;
    private JButton startGame;
    private Audio audio;
    private JButton audioControl;
    private boolean playOrPauseMusic;
    public static final int WINDOW_WIDTH = 1900;
    public static final int WINDOW_HEIGHT = 1050;
    public static final int THE_MIDDLE_HEIGHT_OF_THE_WINDOW = WINDOW_HEIGHT / 2;
    public static final int THE_MIDDLE_WIDTH_OF_THE_WINDOW = WINDOW_WIDTH / 2;




    public Window () throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        this.audio = new Audio();
        this.playOrPauseMusic = false;

        this.setTitle("QUIDDITCH");
        this.setLayout(null);
        this.setResizable(false);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.startHomePage2 = new StartHomePage2();
        this.startHomePage2.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.startHomePage2.setVisible(false);

        this.audioControl = new JButton(new ImageIcon("resources\\images\\pauseAudioButton.2.png"));
        this.audioControl.setFocusable(false);
        this.audioControl.setBounds(WINDOW_WIDTH - 150,WINDOW_HEIGHT - 140,70,60);
        this.audioControl.setVisible(false);
        this.audioControl.addActionListener(e -> {
            if (this.playOrPauseMusic){
                this.playOrPauseMusic = false;
                this.audioControl.setIcon(new ImageIcon("resources\\images\\pauseAudioButton.2.png"));
                this.audio.PlayMusic();
            }



            else {
                this.playOrPauseMusic = true;
                this.audioControl.setIcon(new ImageIcon( "resources\\images\\playAudioButton2.png"));
                this.audio.stopMusic();
            }
        });

        this.startHomePage = new StartHomePage();
        this.startHomePage.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);


        this.switchPanelsButton = new JButton(new ImageIcon("resources\\images\\StartButton2.2.4.3.jpg"));
        this.switchPanelsButton.setBounds(THE_MIDDLE_WIDTH_OF_THE_WINDOW  - (350 / 2) , THE_MIDDLE_HEIGHT_OF_THE_WINDOW + 150, 350,196);
        this.switchPanelsButton.setVisible(true);


        this.switchPanelsButton.addActionListener((e) -> {

            this.remove(startHomePage);
            this.startHomePage.setVisible(false);
            this.startHomePage2.setVisible(true);
            this.remove(switchPanelsButton);
            this.switchPanelsButton.setVisible(false);
            this.startGame.setVisible(true);
            this.audioControl.setVisible(true);
        });
        this.add(switchPanelsButton);
        this.add(startHomePage);


        this.startGame = new JButton(new ImageIcon("resources\\images\\HarryStartButton.2.2.gif"));
        this.startGame.setBounds(THE_MIDDLE_WIDTH_OF_THE_WINDOW - (217 / 2),WINDOW_HEIGHT - 250 , 217,149);
        this.startGame.setVisible(false);


        this.startGame.addActionListener((e) -> {
            this.remove(startHomePage2);
            this.remove(audioControl);

            this.gameScene = new GameScene();
            this.gameScene.setBounds(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
            this.add(gameScene);

            this.remove(startGame);
        });
        this.add(audioControl);
        this.add(startGame);
        this.add(startHomePage2);
    }


    public void showWindow () {
        this.setVisible(true);
    }
}