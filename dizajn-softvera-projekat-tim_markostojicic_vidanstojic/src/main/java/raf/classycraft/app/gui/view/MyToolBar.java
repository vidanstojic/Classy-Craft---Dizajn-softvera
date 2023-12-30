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
        add(MainFrame.getInstance().getActionManager().getSaveAsAction());
        add(MainFrame.getInstance().getActionManager().getOpenProjectAction());
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        add(MainFrame.getInstance().getActionManager().getRedoAction());
        add(MainFrame.getInstance().getActionManager().getScreenshotAction());
        add(MainFrame.getInstance().getActionManager().getExportCodeAction());
    }

}
