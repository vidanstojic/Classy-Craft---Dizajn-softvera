package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.DiagramElement;
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
    public void paint(Graphics2D graphics2D, DiagramElement diagramElement) {
        Stroke stroke = new BasicStroke(diagramElement.getStroke());
        String nameOfClass = enumInterclass.getName();
        int length = graphics2D.getFontMetrics().stringWidth(nameOfClass);
        graphics2D.setColor(enumInterclass.getColor());
        graphics2D.setStroke(stroke);
        this.rectangle = new Rectangle(enumInterclass.getPoint().x, enumInterclass.getPoint().y, (int) (15 + length + 15),120);
        graphics2D.drawRect((int)rectangle.getX(),(int) rectangle.getY(),(int) rectangle.getWidth(),(int) rectangle.getHeight());
        graphics2D.drawString("E", enumInterclass.getPoint().x + 5, enumInterclass.getPoint().y + 15);
        graphics2D.drawString(enumInterclass.getName(), enumInterclass.getPoint().x + 20, enumInterclass.getPoint().y + 15);
        graphics2D.drawLine(enumInterclass.getPoint().x, enumInterclass.getPoint().y + 20, enumInterclass.getPoint().x + (int) (15 + length + 15), enumInterclass.getPoint().y + 20);
    }

    @Override
    public boolean elementAt(Point pos) {
        return (rectangle != null && rectangle.contains(pos));
    }

    public EnumInterclass getEnumInterclass() {
        return enumInterclass;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
