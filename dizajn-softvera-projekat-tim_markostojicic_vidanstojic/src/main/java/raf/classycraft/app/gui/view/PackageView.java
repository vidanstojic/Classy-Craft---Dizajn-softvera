package raf.classycraft.app.gui.view;

import javax.swing.*;
import java.awt.*;

public class PackageView extends JPanel{

    private JTabbedPane tabbedPane;
    private Label author;
    private Label nameOfProject;

    public PackageView(){
        tabbedPaneInit();
    }

    public void tabbedPaneInit(){
        this.tabbedPane = new JTabbedPane();
        this.setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
    }

    public void addTab(String title, Component component ){
        tabbedPane.addTab(title, component);
    }


}
