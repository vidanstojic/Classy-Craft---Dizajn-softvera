package raf.classycraft.app.view;

import javax.swing.*;
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

        JPanel panel = new JPanel(new BorderLayout());

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel title = new JLabel("AboutUs");
        titlePanel.add(title);

        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel text = new JLabel("Na ovom projektu rade Vidan Stojic i Marko Stojicic.");
        textPanel.add(text);

        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(textPanel, BorderLayout.CENTER);

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

        JPanel imagesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel imagesLabel1 = new JLabel();
        imagesLabel1.setIcon(icon1);
        JLabel imagesLabel2 = new JLabel();
        imagesLabel2.setIcon(icon2);
        imagesPanel.add(imagesLabel1,imagesLabel2);
        panel.add(imagesPanel);

        this.add(panel);
    }
}
