package raf.classycraft.app.gui.view;


import javax.swing.*;

public class MyToolBar extends JToolBar {

    public MyToolBar(){
        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getEa());
        add (MainFrame.getInstance().getActionManager().getNewItemAction());
        add (MainFrame.getInstance().getActionManager().getRemoveItemAction());
        add (MainFrame.getInstance().getActionManager().getProjectAuthorAction());
    }

}
