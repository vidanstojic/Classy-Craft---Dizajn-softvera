package raf.classycraft.app.gui.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class AddConnectionAction extends AbstractClassyAction{

    public AddConnectionAction(){

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("/images/git.png"));
        putValue(NAME, "Add connection");
        putValue(SHORT_DESCRIPTION, "Add Connection");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
