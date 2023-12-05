package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;

import java.awt.*;

public class ClassPainter extends InterClassPainter{
    private ClassInterClass classInterClass; // recept za pravljenje paintera
    private Rectangle rectangle;

    public ClassPainter(Point point, ClassInterClass classInterClass) {
        super(point);
        this.classInterClass = classInterClass;
    }

    @Override
    public void paint(Graphics2D graphics2D, DiagramElement diagramElement) {
        Stroke stroke = new BasicStroke(diagramElement.getStroke());
        String nameOfClass = classInterClass.getName();
        int length = graphics2D.getFontMetrics().stringWidth(nameOfClass);
        graphics2D.setColor(classInterClass.getColor());
        graphics2D.setStroke(stroke);
        this.rectangle = new Rectangle(point.x, point.y, (int) (15 + length + 15),120);

        graphics2D.drawRect((int)rectangle.getX(),(int) rectangle.getY(),(int) rectangle.getWidth(),(int) rectangle.getHeight());
        graphics2D.drawString("C", point.x + 5, point.y + 15);
        graphics2D.drawString(classInterClass.getName(), point.x + 20, point.y + 15);
        graphics2D.drawLine(point.x, point.y + 20, point.x + (int) (15 + length + 15), point.y + 20);
        
    }

    @Override
    public boolean elementAt(Point pos) {
        return (rectangle != null && rectangle.contains(pos));
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public ClassInterClass getClassInterClass() {
        return classInterClass;
    }
}
