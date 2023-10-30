package raf.classycraft.app.view;

import javax.swing.*;
import java.awt.*;

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


        this.add(panel);
    }
}
