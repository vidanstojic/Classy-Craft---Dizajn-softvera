package raf.classycraft.app.gui.controller;

import raf.classycraft.app.gui.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends AbstractClassyAction{
    public UndoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_U, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/undo.png"));
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getDiagramView().getCommandManager().undoCommand();///BACA NULL
    }
}
