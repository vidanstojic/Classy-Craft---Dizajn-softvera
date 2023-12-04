package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;

import java.awt.*;

public class ClassPainter extends InterClassPainter{
    public ClassInterClass classInterClass; // recept za pravljenje paintera

    public ClassPainter(Point point, ClassInterClass classInterClass) {
        super(point);
        this.classInterClass = classInterClass;
    }

    @Override
    public void paint(Graphics2D graphics2D, DiagramElement diagramElement) {
        graphics2D.setColor(diagramElement.getColor());
        Stroke stroke = new BasicStroke(diagramElement.getStroke());
        graphics2D.setStroke(stroke);
        graphics2D.drawRect(point.x, point.y, 20,10);
        
    }

    @Override
    public void elementAt() {

    }
}
