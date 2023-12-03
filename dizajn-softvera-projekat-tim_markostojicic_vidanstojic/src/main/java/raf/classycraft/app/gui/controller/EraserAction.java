package raf.classycraft.app.gui.controller;

import raf.classycraft.app.gui.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EraserAction extends AbstractClassyAction{

    public EraserAction(){

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/eraser.png"));
        putValue(NAME, "Eraser");
        putValue(SHORT_DESCRIPTION, "Eraser");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getPackageView().startRemoveState();
    }
}
