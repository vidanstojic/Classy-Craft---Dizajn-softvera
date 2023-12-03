package raf.classycraft.app.gui.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class AddAction extends AbstractClassyAction{

    public AddAction(){

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("/images/add.png"));
        putValue(NAME, "Add class/interface");
        putValue(SHORT_DESCRIPTION, "Add Class/Interface");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
