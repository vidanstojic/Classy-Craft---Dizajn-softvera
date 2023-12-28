package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.model.elementDiagram.classContent.Attribute;
import raf.classycraft.app.model.elementDiagram.classContent.Method;
import raf.classycraft.app.model.elementDiagram.classContent.Visibility;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ClassPainter extends InterClassPainter{
    private Rectangle rectangle;


    public ClassPainter(Point point, ClassInterClass classInterClass) {
        super(point, classInterClass);
    }

    private void calculateConnectionDots() {
        ClassInterClass classInterClass = (ClassInterClass) super.getInterclass();

        classInterClass.getConnectionDots().clear();


        int centerX = (int) rectangle.getCenterX();
        int centerY = (int) rectangle.getCenterY();

        // Gornja tacka
        classInterClass.getConnectionDots().add(new Point(centerX, (int) rectangle.getY()));
        // Donja tacka
        classInterClass.getConnectionDots().add(new Point(centerX, (int) rectangle.getMaxY()));
        // Leva tacka
        classInterClass.getConnectionDots().add(new Point((int) rectangle.getX(), centerY));
        // Desna tacka
        classInterClass.getConnectionDots().add(new Point((int) rectangle.getMaxX(), centerY));
    }

    @Override
    public void paint(Graphics2D graphics2D, DiagramElement diagramElement) {
        ClassInterClass classInterClass = (ClassInterClass) super.getInterclass();
        String abstractString = classInterClass.getAbstractClass();

        Point mainPoint = new Point();
        Point heightPoint = new Point();
        heightPoint.y = 35;
        //Stroke stroke = new BasicStroke(diagramElement.getStroke());
        Stroke stroke = new BasicStroke(3);
        String nameOfClass = classInterClass.getName();
        graphics2D.setColor(classInterClass.getColor());
        graphics2D.setStroke(stroke);
        mainPoint.y = classInterClass.getPoint().y + 35;

        int length = graphics2D.getFontMetrics().stringWidth(nameOfClass);
        int heightOfString = graphics2D.getFontMetrics().getMaxAscent();
        for (Attribute attribute : classInterClass.getAttributes()) {
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
            String attributeFullName = visibilityChar + attribute.getName()+" " +attribute.getStaticContentOrNot()+" "+attribute.getAbstractContentOrNot() +":" + attribute.getReturnType();
            if (length < graphics2D.getFontMetrics().stringWidth(attributeFullName)) {
                length = graphics2D.getFontMetrics().stringWidth(attributeFullName);
            }
            graphics2D.drawString(attributeFullName, classInterClass.getPoint().x + 5, mainPoint.y);
            mainPoint.y = mainPoint.y + 13;
            heightPoint.y += 13;
        }

        Point pointForLine = new Point(classInterClass.getPoint().x, mainPoint.y);
        mainPoint.y = mainPoint.y + 15;
        heightPoint.y += 15;

        for (Method method : classInterClass.getMethods()) {
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
            String methodeFullName = visibilityChar + method.getName()+" " +method.getStaticContentOrNot()+" "+method.getAbstractContentOrNot() + ":" + method.getReturnType();
            if (length < graphics2D.getFontMetrics().stringWidth(methodeFullName)) {
                length = graphics2D.getFontMetrics().stringWidth(methodeFullName);
            }
            graphics2D.drawString(methodeFullName, classInterClass.getPoint().x + 5, mainPoint.y - 10);
            mainPoint.y = mainPoint.y + 15;
            heightPoint.y += 15;
        }

        if (classInterClass.getAttributes().size() >= 1) {
            graphics2D.drawLine(pointForLine.x, pointForLine.y - 10, pointForLine.x + (int) (15 + length + 15), pointForLine.y - 10);
        }

        int heightOfRectangle = ((classInterClass.getClassContents().size()) == 0) ? (heightPoint.y) : (classInterClass.getClassContents().size() * heightOfString + 33);
        this.rectangle = new Rectangle(classInterClass.getPoint().x, classInterClass.getPoint().y, (int) (15 + length + 15), heightOfRectangle);
        graphics2D.drawRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
        if(abstractString.equals("Yes")) {
            graphics2D.drawString("Ⓐ", classInterClass.getPoint().x + 2, classInterClass.getPoint().y + 15);
            graphics2D.drawString("C", classInterClass.getPoint().x + 13, classInterClass.getPoint().y + 15);
            graphics2D.drawString(classInterClass.getName(), classInterClass.getPoint().x + 25, classInterClass.getPoint().y + 15);
            graphics2D.drawLine(classInterClass.getPoint().x, classInterClass.getPoint().y + 20, classInterClass.getPoint().x + (int) (15 + length + 15), classInterClass.getPoint().y + 20);
        }else {
            graphics2D.drawString("C", classInterClass.getPoint().x + 5, classInterClass.getPoint().y + 15);
            graphics2D.drawString(classInterClass.getName(), classInterClass.getPoint().x + 20, classInterClass.getPoint().y + 15);
            graphics2D.drawLine(classInterClass.getPoint().x, classInterClass.getPoint().y + 20, classInterClass.getPoint().x + (int) (15 + length + 15), classInterClass.getPoint().y + 20);
        }
        this.setRectangle(rectangle);
        classInterClass.setRectangle(rectangle);
        calculateConnectionDots();

    }

    @Override
    public boolean elementAt(Point pos) {
        return (rectangle != null && rectangle.contains(pos));
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }



    @Override
    public Interclass getInterclass() {
        return super.getInterclass();
    }

}
