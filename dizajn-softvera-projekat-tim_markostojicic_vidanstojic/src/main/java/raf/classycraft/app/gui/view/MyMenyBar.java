package raf.classycraft.app.gui.view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenyBar extends JMenuBar {

    public MyMenyBar(){
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        fileMenu.add(MainFrame.getInstance().getActionManager().getEa());
        add(fileMenu);

        JMenu editMenu = new JMenu("Edit");
        editMenu.add(MainFrame.getInstance().getActionManager().getAboutUs());
        add(editMenu);
    }

}
