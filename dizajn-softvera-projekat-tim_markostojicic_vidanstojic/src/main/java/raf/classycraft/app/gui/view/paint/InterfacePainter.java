package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.classContent.Attribute;
import raf.classycraft.app.model.elementDiagram.classContent.Method;
import raf.classycraft.app.model.elementDiagram.classContent.Visibility;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.InterfaceInterclass;

import java.awt.*;

public class InterfacePainter extends InterClassPainter{

    private InterfaceInterclass interfaceInterclass;
    private Rectangle rectangle;
    public InterfacePainter(Point point, InterfaceInterclass interfaceInterclass) {
        super(point);
        this.interfaceInterclass = interfaceInterclass;
    }

    @Override
    public void paint(Graphics2D graphics2D, DiagramElement diagramElement)  {
        Point mainPoint = new Point();
        Point heightPoint = new Point();
        heightPoint.y = 35;
        Stroke stroke = new BasicStroke(diagramElement.getStroke());
        String nameOfClass = interfaceInterclass.getName();
        graphics2D.setColor(interfaceInterclass.getColor());
        graphics2D.setStroke(stroke);
        mainPoint.y = interfaceInterclass.getPoint().y + 35;
        int length = graphics2D.getFontMetrics().stringWidth(nameOfClass);
        int heightOfString = graphics2D.getFontMetrics().getMaxAscent();
        for (Attribute attribute : interfaceInterclass.getAttributes()) {
            String visibilityChar;
            if (attribute.getVisibility() == Visibility.PUBLIC) {
                visibilityChar = "+ ";
            } else if (attribute.getVisibility() == Visibility.PRIVATE) {
                visibilityChar = "- ";
            } else if (attribute.getVisibility() == Visibility.PROTECTED) {
                visibilityChar = "# ";
            } else {
                visibilityChar = " ";
            }
            String attributeFullName = visibilityChar + attribute.getName() + ":" + attribute.getReturnType();
            if (length < graphics2D.getFontMetrics().stringWidth(attributeFullName)) {
                length = graphics2D.getFontMetrics().stringWidth(attributeFullName);
            }
            graphics2D.drawString(attributeFullName, interfaceInterclass.getPoint().x + 5, mainPoint.y);
            mainPoint.y = mainPoint.y + 13;
            heightPoint.y += 13;
        }
        Point pointForLine = new Point(interfaceInterclass.getPoint().x, mainPoint.y);
        mainPoint.y = mainPoint.y + 15;
        heightPoint.y += 15;
        for (Method method : interfaceInterclass.getMethods()) {
            String visibilityChar;
            if (method.getVisibility() == Visibility.PUBLIC) {
                visibilityChar = "+ ";
            } else if (method.getVisibility() == Visibility.PRIVATE) {
                visibilityChar = "- ";
            } else if (method.getVisibility() == Visibility.PROTECTED) {
                visibilityChar = "# ";
            } else {
                visibilityChar = " ";
            }
            String methodeFullName = visibilityChar + method.getName() + ":" + method.getReturnType();
            if (length < graphics2D.getFontMetrics().stringWidth(methodeFullName)) {
                length = graphics2D.getFontMetrics().stringWidth(methodeFullName);
            }
            graphics2D.drawString(methodeFullName, interfaceInterclass.getPoint().x + 5, mainPoint.y - 10);
            mainPoint.y = mainPoint.y + 15;
            heightPoint.y += 15;
        }
        if (interfaceInterclass.getAttributes().size() >= 1) {
            graphics2D.drawLine(pointForLine.x, pointForLine.y - 10, pointForLine.x + (int) (15 + length + 15), pointForLine.y - 10);
        }
        int heightOfRectangle = ((interfaceInterclass.getClassContents().size()) == 0) ? (heightPoint.y) : (interfaceInterclass.getClassContents().size() * heightOfString + 33);
        this.rectangle = new Rectangle(interfaceInterclass.getPoint().x, interfaceInterclass.getPoint().y, (int) (15 + length + 15), heightOfRectangle);
        graphics2D.drawRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
        graphics2D.drawString("I", interfaceInterclass.getPoint().x + 5, interfaceInterclass.getPoint().y + 15);
        graphics2D.drawString(interfaceInterclass.getName(), interfaceInterclass.getPoint().x + 20, interfaceInterclass.getPoint().y + 15);
        graphics2D.drawLine(interfaceInterclass.getPoint().x, interfaceInterclass.getPoint().y + 20, interfaceInterclass.getPoint().x + (int) (15 + length + 15), interfaceInterclass.getPoint().y + 20);
    }

    @Override
    public boolean elementAt(Point pos) {
        return (rectangle != null && rectangle.contains(pos));
    }

    public InterfaceInterclass getInterfaceInterclass() {
        return interfaceInterclass;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
