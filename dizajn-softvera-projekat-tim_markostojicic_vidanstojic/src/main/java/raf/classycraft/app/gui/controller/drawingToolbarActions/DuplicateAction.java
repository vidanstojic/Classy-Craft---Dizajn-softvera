package raf.classycraft.app.gui.controller.drawingToolbarActions;

import raf.classycraft.app.gui.controller.AbstractClassyAction;
import raf.classycraft.app.gui.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class DuplicateAction extends AbstractClassyAction {

    public DuplicateAction(){

        putValue(SMALL_ICON, loadIcon("/images/duplicate.png"));
        putValue(NAME, "Duplicate Class");
        putValue(SHORT_DESCRIPTION, "Duplicate Class");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().startDuplicateState();
    }
}
