package raf.classycraft.app.gui.view;

import javax.swing.*;
import java.awt.*;

public class MyDrawingToolBar extends JToolBar {

    public MyDrawingToolBar(){
        super(VERTICAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getEa());
        add (MainFrame.getInstance().getActionManager().getNewProjectAction());
        add (MainFrame.getInstance().getActionManager().getRemoveItemAction());
        add (MainFrame.getInstance().getActionManager().getProjectAuthorAction());

    }
}
