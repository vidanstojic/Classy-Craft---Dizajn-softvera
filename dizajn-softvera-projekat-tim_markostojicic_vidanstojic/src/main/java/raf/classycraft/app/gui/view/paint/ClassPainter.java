package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;

import javax.swing.*;
import java.awt.*;

public class ClassPainter extends InterClassPainter{
    public ClassInterClass classInterClass; // recept za pravljenje paintera

    public ClassPainter(Point point, ClassInterClass classInterClass) {
        super(point);
        this.classInterClass = classInterClass;
    }

    @Override
    public void paint(Graphics2D graphics2D, DiagramElement diagramElement) {
        Stroke stroke = new BasicStroke(diagramElement.getStroke());
        graphics2D.setStroke(stroke);
        graphics2D.drawRect(point.x, point.y, 100,120);
        graphics2D.drawString("C", point.x + 5, point.y + 15);
        graphics2D.drawString(classInterClass.getName(), point.x + 20, point.y + 15);
        graphics2D.drawLine(point.x, point.y + 20, point.x + 100, point.y + 20);
        //graphics2D.setBackground(Color.WHITE);
        
    }

    @Override
    public void elementAt() {

    }
}
