import javax.swing.*;
import java.awt.*;

public class EndPanel extends JPanel {

    public void paintComponent(Graphics graphics){
        ImageIcon endHomePageImage = new ImageIcon( "resources\\images\\EndBackground.2.7.jpg.png");
        endHomePageImage.paintIcon(null, graphics, 0,0);


    }
}
