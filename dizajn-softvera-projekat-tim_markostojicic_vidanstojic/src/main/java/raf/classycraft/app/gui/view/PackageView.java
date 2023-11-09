package raf.classycraft.app.gui.view;

import javax.swing.*;
import java.awt.*;

public class PackageView {
    private Label author;
    private Label nameOfProject;

    public PackageView(){
        tabbedPaneInit();
    }

    public void tabbedPaneInit(){
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("PROBA",new DiagramView());
    }
}
