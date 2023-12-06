package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.classContent.Attribute;
import raf.classycraft.app.model.elementDiagram.classContent.Method;
import raf.classycraft.app.model.elementDiagram.classContent.Visibility;
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
        Point point1 = new Point();
        Stroke stroke = new BasicStroke(diagramElement.getStroke());
        String nameOfClass = classInterClass.getName();
        graphics2D.setColor(classInterClass.getColor());
        graphics2D.setStroke(stroke);
        point1.y = classInterClass.getPoint().y + 35;

        int length = graphics2D.getFontMetrics().stringWidth(nameOfClass);
        for(Attribute attribute : classInterClass.getAttributes()){
            String visibilityChar;
            if(attribute.getVisibility() == Visibility.PUBLIC){
                visibilityChar = "+ ";
            }
            else if(attribute.getVisibility() == Visibility.PRIVATE){
                visibilityChar = "- ";
            }
            else if(attribute.getVisibility() == Visibility.PROTECTED){
                visibilityChar = "# ";
            }
            else{
                visibilityChar = " ";
            }
            String attributeFullName = visibilityChar+attribute.getName()+":"+attribute.getReturnType();
            if(length < graphics2D.getFontMetrics().stringWidth(attributeFullName)){
                length  = graphics2D.getFontMetrics().stringWidth(attributeFullName);
            }
            graphics2D.drawString(attributeFullName,classInterClass.getPoint().x+5, point1.y);
            point1.y = point1.y + 15;

        }
        Point pointForLine = new Point(classInterClass.getPoint().x, point1.y);
        point1.y = point1.y + 15;


        for(Method method : classInterClass.getMethods()){
            String visibilityChar;
            if(method.getVisibility() == Visibility.PUBLIC){
                visibilityChar = "+ ";
            }
            else if(method.getVisibility() == Visibility.PRIVATE){
                visibilityChar = "- ";
            }
            else if(method.getVisibility() == Visibility.PROTECTED){
                visibilityChar = "# ";
            }
            else{
                visibilityChar = " ";
            }
            String methodeFullName = visibilityChar+method.getName()+":"+method.getReturnType();
            if(length < graphics2D.getFontMetrics().stringWidth(methodeFullName)){
                length  = graphics2D.getFontMetrics().stringWidth(methodeFullName);
            }
            graphics2D.drawString(methodeFullName,classInterClass.getPoint().x+5, point1.y);
            point1.y = point1.y + 15;
        }
        if(classInterClass.getAttributes().size() >= 1){
            graphics2D.drawLine(pointForLine.x, pointForLine.y, pointForLine.x+ (int) (15 + length + 15), pointForLine.y);
        }


        this.rectangle = new Rectangle(classInterClass.getPoint().x, classInterClass.getPoint().y, (int) (15 + length + 15),120);
        graphics2D.drawRect((int)rectangle.getX(),(int) rectangle.getY(),(int) rectangle.getWidth(),(int) rectangle.getHeight());
        graphics2D.drawString("C", classInterClass.getPoint().x + 5, classInterClass.getPoint().y + 15);
        graphics2D.drawString(classInterClass.getName(), classInterClass.getPoint().x + 20, classInterClass.getPoint().y + 15);
        graphics2D.drawLine(classInterClass.getPoint().x, classInterClass.getPoint().y + 20, classInterClass.getPoint().x + (int) (15 + length + 15), classInterClass.getPoint().y + 20);
        point1.y = classInterClass.getPoint().y + 30;
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
