package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.DiagramElement;
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
    public void paint(Graphics2D graphics2D, DiagramElement diagramElement) {
        Stroke stroke = new BasicStroke(diagramElement.getStroke());
        String nameOfClass = interfaceInterclass.getName();
        int length = graphics2D.getFontMetrics().stringWidth(nameOfClass);
        graphics2D.setColor(interfaceInterclass.getColor());
        graphics2D.setStroke(stroke);
        this.rectangle = new Rectangle(interfaceInterclass.getPoint().x, interfaceInterclass.getPoint().y, (int) (15 + length + 15),120);
        graphics2D.drawRect((int)rectangle.getX(),(int) rectangle.getY(),(int) rectangle.getWidth(),(int) rectangle.getHeight());
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
