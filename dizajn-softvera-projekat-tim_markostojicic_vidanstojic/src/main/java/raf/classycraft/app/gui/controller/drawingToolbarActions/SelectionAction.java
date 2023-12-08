package raf.classycraft.app.gui.controller.drawingToolbarActions;

import raf.classycraft.app.gui.controller.AbstractClassyAction;
import raf.classycraft.app.gui.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SelectionAction extends AbstractClassyAction {

    public SelectionAction(){

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("/images/selection.png"));
        putValue(NAME, "Selection");
        putValue(SHORT_DESCRIPTION, "Select");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().startSelectionState();
    }
}
