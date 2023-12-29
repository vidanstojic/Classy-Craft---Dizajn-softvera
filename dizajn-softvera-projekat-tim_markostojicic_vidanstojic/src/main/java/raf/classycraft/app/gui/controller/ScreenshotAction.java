package raf.classycraft.app.gui.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ScreenshotAction extends AbstractClassyAction{
    public ScreenshotAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/screenshots.png"));
        putValue(NAME, "Screenshot");
        putValue(SHORT_DESCRIPTION, "Screenshot diagram");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
