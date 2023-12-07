package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.DiagramElement;

import java.awt.*;

public abstract class ElementPainter {
    // potrebne su metode paint i elementAt
    private Rectangle rectangle;
    public abstract void paint(Graphics2D graphics2D, DiagramElement diagramElement);
    public abstract boolean elementAt(Point pos);

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
