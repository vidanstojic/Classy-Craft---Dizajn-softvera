package raf.classycraft.app.gui.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ExportCodeAction extends AbstractClassyAction {

  public ExportCodeAction(){
      putValue(SMALL_ICON, loadIcon("/images/exportCode.png"));
      putValue(NAME, "Export code");
      putValue(SHORT_DESCRIPTION, "Export your Code");
  }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
