import javax.swing.*;
import java.awt.*;

public class StartHomePage2 extends JPanel {

    JLabel information;
    Font informationFont;
    private String englishText;
    private String hebrewText;
    private JButton changTheLanguageButton;
    private boolean hebrewOrEnglish;
    public static final int START_HOME_PAGE2_X = 0;
    public static final int START_HOME_PAGE2_Y = 0;


    public StartHomePage2(){
        this.setLayout(null);

        this.hebrewOrEnglish=true;

        this.hebrewText = "<html>"
                + "שעת לילה מאוחרת, חבורה קטנה מתלמידי שנה חמישית חומקים למחסן ציוד הקווידץ' (לא, ללא רשות)"
                + "<br/>"
                + "הסידור הנוכחי של המחסן לא היה לטעמם והם החליטו לעשות לו \"עיצוב מחדש\""
                + "<br/>"
                + "לצערם, כעבור כמה רגעים פילץ' השרת תפס אותם ודרש שהסדר יוחזר במהירות לקדמותו"
                + "<br/>"
                + "לבסוף כל הציוד הונח בחזרה במקומו...ובכן, כמעט..."
                + "<br/>"
                + " כמה מרביצנים וכדור אחד הלא הוא הסניץ' המוזהב חמקו מעיניהם וכעת הם מרחפים להם אי שם בשמי הטירה "
                + "<br/>"
                + "\"שימוס, חייבים להחזיר את הסניץ' לפני שמישהו יגלה\" "
                + "<br/>"
                + "\"אתה צודק, רק הארי יכול לחלץ אותנו מזה\""
                + "<br/>"
                + "<br/>"
                + "עזרו להארי לתפוס את הסניץ' המוזהב, רק אל תשכחו את המרביצנים שמסתובבים חופשי"
                + "<br/>"
                + "<br/>"
                + "השתמשו בחיצי המקלדת כדי לנוע ברחבי המסך ולהתחמק מהמרביצנים"
                + "<br/>"
                + "מדי פעם תקבלו הזדמנות לתפוס כדור זהוב שתפיסתו תקרב אתכם לתפיסת הסניץ'"
                + "<br/>"
                + "בדומה לכדור הזהוב ישנו גם כדור כחול שתפיסתו תאפשר לכם להקפיא את תנועת המרביצנים"
                + "<br/>"
                + " לזמן קצר באמצעות לחיצה על מקש הרווח"
                + "<br/>"
                + "<br/>"
                + " בהצלחה!"
                + "</html>";


        this.englishText = "<html>"
                + "Late at night, a small group of fifth-grade students sneaks into the Quidditch equipment storage room (and no, without permission)."
                + "<br/>"
                + "They were not satisfied with the current arrangement of the storage room and decided to give it a \"makeover\""
                + "<br/>"
                + "Unfortunately, after a few moments, the caretaker detected them and demanded that the order be quickly restored to its original order."
                + "<br/>"
                + "In the end, all the equipment was placed back in its proper place... well, almost..."
                + "<br/>"
                + "Some Bludgers and one sneaky Snitch escaped their sight and are now floating somewhere in the castle's skies."
                + "\"Seamus, We have to return the snitch back in is place before anyone finds out.\""
                + "<br/>"
                + "\"You're right, only Harry can get us out of this.\""
                + "<br/>"
                + "<br/>"
                + "Help Harry catch the golden Snitch, but don't forget the freely roaming Bludgers"
                + "<br/>"
                + "Use the arrow keys to move around the screen and dodge the Bludgers"
                + "<br/>"
                + "Once in a while, you will have the opportunity to catch a golden ball. Capturing it will bring you closer to capturing the Snitch."
                + "<br/>"
                + "Similarly, there is also a blue ball that, when caught, will freeze the movement of the Bludgers."
                + "<br/>"
                + "you can Press the space to activate it for a short period of time."
                + "<br/>"
                + "<br/>"
                + "Good luck! "
                + "<html/>";



        this.informationFont = new Font("Ariel", Font.PLAIN , 30);

        this.information = new JLabel();
        this.information.setFont(informationFont);
        this.information.setText(hebrewText);
        this.information.setBounds(400,100,1600,620);
        this.add(information);

        this.changTheLanguageButton = new JButton(new ImageIcon("resources\\images\\English&Flag.2.png"));
        this.changTheLanguageButton.setBounds(50,Window.WINDOW_HEIGHT - 150,120,35);

        this.changTheLanguageButton.addActionListener((event)->{
            this.changTheLanguage();
        });
        this.changTheLanguageButton.setVisible(true);
        this.add(changTheLanguageButton);
    }


    public void paintComponent (Graphics graphics) {
        super.paintComponent(graphics);

        ImageIcon startHomePageImage = new ImageIcon( "resources\\images\\StartBackground2.2.4.2.jpg");
        startHomePageImage.paintIcon(null, graphics, START_HOME_PAGE2_X, START_HOME_PAGE2_Y);
    }
    public void changTheLanguage(){
        this.hebrewOrEnglish = !this.hebrewOrEnglish;
        if (this.hebrewOrEnglish){
            this.information.setText(hebrewText);
            this.information.setBounds(400,70,1600,620);
            this.changTheLanguageButton.setIcon(new ImageIcon( "resources\\images\\English&Flag.2.png"));
        }else {
            this.information.setText(englishText);
            this.information.setBounds(220,70,1600,690);
            this.changTheLanguageButton.setIcon(new ImageIcon( "resources\\images\\Hebrew&Flag.png"));

        }
    }
}