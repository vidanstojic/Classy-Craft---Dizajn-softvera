package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.DiagramElement;

import java.awt.*;
import java.awt.geom.Line2D;

public abstract class ElementPainter {
    // potrebne su metode paint i elementAt
    private Rectangle rectangle;
    private Line2D line2D;
    public abstract void paint(Graphics2D graphics2D, DiagramElement diagramElement);
    public abstract boolean elementAt(Point pos);

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Line2D getLine2D() {
        return line2D;
    }

    public void setLine2D(Line2D line2D) {
        this.line2D = line2D;
    }
}
