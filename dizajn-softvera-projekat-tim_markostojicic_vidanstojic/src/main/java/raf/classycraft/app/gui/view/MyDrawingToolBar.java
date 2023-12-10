package raf.classycraft.app.gui.view;

import javax.swing.*;

public class MyDrawingToolBar extends JToolBar {

    public MyDrawingToolBar(){
        super(VERTICAL);
        setFloatable(false);

        add (MainFrame.getInstance().getActionManager().getAddClassAction());
        add (MainFrame.getInstance().getActionManager().getAddConnectionAction());
        add (MainFrame.getInstance().getActionManager().getMoveAction());
        add (MainFrame.getInstance().getActionManager().getEditAction());
        add (MainFrame.getInstance().getActionManager().getSelectionAction());
        add (MainFrame.getInstance().getActionManager().getEraserAction());
        add(MainFrame.getInstance().getActionManager().getDuplicateAction());

    }
}
