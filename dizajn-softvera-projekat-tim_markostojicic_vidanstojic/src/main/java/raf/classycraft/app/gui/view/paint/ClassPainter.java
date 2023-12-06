package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.classContent.Attribute;
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
        this.rectangle = new Rectangle(classInterClass.getPoint().x, classInterClass.getPoint().y, (int) (15 + length + 15),120);
        graphics2D.drawRect((int)rectangle.getX(),(int) rectangle.getY(),(int) rectangle.getWidth(),(int) rectangle.getHeight());
        graphics2D.drawString("C", classInterClass.getPoint().x + 5, classInterClass.getPoint().y + 15);
        graphics2D.drawString(classInterClass.getName(), classInterClass.getPoint().x + 20, classInterClass.getPoint().y + 15);
        graphics2D.drawLine(classInterClass.getPoint().x, classInterClass.getPoint().y + 20, classInterClass.getPoint().x + (int) (15 + length + 15), classInterClass.getPoint().y + 20);
        for(Attribute attribute : classInterClass.getAttributes()){
            classInterClass.getPoint().y = classInterClass.getPoint().y + 30;
            graphics2D.drawString(attribute.getName(),classInterClass.getPoint().x+5, classInterClass.getPoint().y);
        }
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
