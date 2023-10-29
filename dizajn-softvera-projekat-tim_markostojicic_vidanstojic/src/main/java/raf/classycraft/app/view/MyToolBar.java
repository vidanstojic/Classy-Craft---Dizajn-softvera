package raf.classycraft.app.view;

import raf.classycraft.app.controller.ExitAction;

import javax.swing.*;

public class MyToolBar extends JToolBar {

    public MyToolBar(){
        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getEa());
    }

}
