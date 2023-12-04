package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.DiagramElement;

import java.awt.*;

public abstract class ElementPainter {
    // potrebne su metode paint i elementAt
    public abstract void paint(Graphics g, DiagramElement diagramElement);
    public abstract void elementAt();

}
