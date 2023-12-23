package raf.classycraft.app.gui.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class OpenProjectAction extends AbstractClassyAction{
    public OpenProjectAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/open project.png"));
        putValue(NAME, "Open");
        putValue(SHORT_DESCRIPTION, "Open project");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
