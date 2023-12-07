package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.classContent.Attribute;
import raf.classycraft.app.model.elementDiagram.classContent.Method;
import raf.classycraft.app.model.elementDiagram.classContent.Visibility;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.EnumInterclass;

import java.awt.*;

public class EnumPainter extends InterClassPainter{

    private EnumInterclass enumInterclass;
    private Rectangle rectangle;
    public EnumPainter(Point point, EnumInterclass enumInterclass) {
        super(point);
        this.enumInterclass = enumInterclass;
    }

    @Override
    public void paint(Graphics2D graphics2D, DiagramElement diagramElement)  {
        Point mainPoint = new Point();
        Point heightPoint = new Point();
        heightPoint.y = 35;
        Stroke stroke = new BasicStroke(diagramElement.getStroke());
        String nameOfClass = enumInterclass.getName();
        graphics2D.setColor(enumInterclass.getColor());
        graphics2D.setStroke(stroke);
        mainPoint.y = enumInterclass.getPoint().y + 35;
        int length = graphics2D.getFontMetrics().stringWidth(nameOfClass);
        int heightOfString = graphics2D.getFontMetrics().getMaxAscent();
        for (Attribute attribute : enumInterclass.getAttributes()) {
            String visibilityChar = "";
            String attributeFullName = visibilityChar + attribute.getName() + ":" + attribute.getReturnType();
            if (length < graphics2D.getFontMetrics().stringWidth(attributeFullName)) {
                length = graphics2D.getFontMetrics().stringWidth(attributeFullName);
            }
            graphics2D.drawString(attributeFullName.toUpperCase(), enumInterclass.getPoint().x + 5, mainPoint.y);
            mainPoint.y = mainPoint.y + 13;
            heightPoint.y += 13;
        }
        mainPoint.y = mainPoint.y + 15;
        heightPoint.y += 15;
        int heightOfRectangle = ((enumInterclass.getClassContents().size()) == 0) ? (heightPoint.y) : (enumInterclass.getClassContents().size() * heightOfString + 33);
        this.rectangle = new Rectangle(enumInterclass.getPoint().x, enumInterclass.getPoint().y, (int) (15 + length + 15), heightOfRectangle);
        graphics2D.drawRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
        graphics2D.drawString("E", enumInterclass.getPoint().x + 5, enumInterclass.getPoint().y + 15);
        graphics2D.drawString(enumInterclass.getName(), enumInterclass.getPoint().x + 20, enumInterclass.getPoint().y + 15);
        graphics2D.drawLine(enumInterclass.getPoint().x, enumInterclass.getPoint().y + 20, enumInterclass.getPoint().x + (int) (15 + length + 15), enumInterclass.getPoint().y + 20);
        this.setRectangle(rectangle);
        enumInterclass.setRectangle(rectangle);
    }

    @Override
    public boolean elementAt(Point pos) {
        return (rectangle != null && rectangle.contains(pos));
    }

    public EnumInterclass getEnumInterclass() {
        return enumInterclass;
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
