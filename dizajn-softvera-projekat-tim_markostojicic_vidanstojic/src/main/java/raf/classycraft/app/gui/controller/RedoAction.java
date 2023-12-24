package raf.classycraft.app.gui.controller;

import raf.classycraft.app.gui.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RedoAction extends AbstractClassyAction{
    public RedoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_R, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/redo.png"));
        putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "Redo");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getDiagramView().getCommandManager().doCommand();///BACA NULL
    }
}
