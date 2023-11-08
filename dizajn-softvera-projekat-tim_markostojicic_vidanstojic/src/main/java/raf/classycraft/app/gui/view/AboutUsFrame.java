package raf.classycraft.app.gui.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class AboutUsFrame extends JFrame {
    public AboutUsFrame(){
        setTitle("AboutUs");
        setLocationRelativeTo(null);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel title = new JLabel("AboutUs");
        titlePanel.add(title);

        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel text = new JLabel("Na ovom projektu rade Vidan Stojić i Marko Stojičić.");
        textPanel.add(text);

        panel.add(titlePanel);
        panel.add(textPanel);

        URL vidanURL = getClass().getResource("/images/Vidan Stojic.jpg");
        URL markoURL = getClass().getResource("/images/Marko Stojicic.jpeg");
        ImageIcon icon1 = null;

        if(vidanURL != null){
            icon1 = new ImageIcon(vidanURL);
        }
        else {
            System.err.println("Resource not found: " + vidanURL);
        }

        ImageIcon icon2 = null;

        if(markoURL != null){
            icon2 = new ImageIcon(markoURL);
        }
        else {
            System.err.println("Resource not found: " + markoURL);
        }

        JPanel imagePanel = new JPanel(new FlowLayout());

        JLabel imageLabel1 = new JLabel(icon1);
        JLabel imageLabel2 = new JLabel(icon2);
        imagePanel.add(imageLabel1);
        imagePanel.add(imageLabel2);



        panel.add(titlePanel);
        panel.add(textPanel);
        panel.add(imagePanel);

        JLabel textMarko = new JLabel("Marko(kontakt: mstojicic12923rn@raf.rs)");
        JLabel textVidan = new JLabel("Vidan(kontakt: vstojic8223rn@raf.rs)");

        JPanel namesPanel = new JPanel(new FlowLayout());
        namesPanel.add(textVidan);
        namesPanel.add(textMarko);
        textMarko.setBorder(new EmptyBorder(10, 30, 15, 50));
        textVidan.setBorder(new EmptyBorder(10,70,15,50));

        panel.add(imagePanel);
        panel.add(namesPanel);




        this.add(panel);
    }
}
