package raf.classycraft.app.gui.controller;

import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.model.compositeImplement.Diagram;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
        if (MainFrame.getInstance().getCurrentDiagramView() == null)return;
        if (!(MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode() instanceof Diagram)) return;
        DiagramView diagramView = MainFrame.getInstance().getCurrentDiagramView();
        BufferedImage bufferedImage = new BufferedImage(diagramView.getWidth(), diagramView.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        diagramView.printAll(g2d);
        g2d.dispose();
        try {
            ImageIO.write(bufferedImage, "png", new File("src/main/resources" + diagramView.getDiagram().getName() + ".png"));
        } catch (IOException ex) {
            System.out.println("lose ucitavanje");
            throw new RuntimeException(ex);
        }
    }
}
