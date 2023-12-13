package raf.classycraft.app.gui.controller.drawingToolbarActions;

import raf.classycraft.app.gui.controller.AbstractClassyAction;
import raf.classycraft.app.gui.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ZoomInAction extends AbstractClassyAction {
    public ZoomInAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("/images/zoom in.png"));
        putValue(NAME, "Zoom in");
        putValue(SHORT_DESCRIPTION, "zoom in");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().startZoomInState();
    }
}
