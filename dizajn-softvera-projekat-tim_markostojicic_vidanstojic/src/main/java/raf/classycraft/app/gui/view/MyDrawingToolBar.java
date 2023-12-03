package raf.classycraft.app.gui.view;

import javax.swing.*;
import java.awt.*;

public class MyDrawingToolBar extends JToolBar {

    public MyDrawingToolBar(){
        super(VERTICAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getAddAction());
        add (MainFrame.getInstance().getActionManager().getAddConnectionAction());
        add (MainFrame.getInstance().getActionManager().getSelectAction());
        add (MainFrame.getInstance().getActionManager().getEditAction());
        add (MainFrame.getInstance().getActionManager().getEraserAction());

    }
}
