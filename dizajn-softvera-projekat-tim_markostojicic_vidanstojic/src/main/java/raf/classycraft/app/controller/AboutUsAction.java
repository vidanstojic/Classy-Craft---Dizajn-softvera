package raf.classycraft.app.controller;

import raf.classycraft.app.view.AboutUsFrame;

import java.awt.event.ActionEvent;

public class AboutUsAction extends AbstractClassyAction{

    public AboutUsAction(){
        putValue(NAME, "AboutUs");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AboutUsFrame aboutUsFrame = new AboutUsFrame();
        aboutUsFrame.setVisible(true);
    }
}
