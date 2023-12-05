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
        String string = interfaceInterclass.getName();
        int lentgh = graphics2D.getFontMetrics().stringWidth(string);
        graphics2D.setStroke(stroke);
        this.rectangle = new Rectangle(point.x, point.y, (int) (15 + lentgh + 15),120);

        graphics2D.drawRect((int)rectangle.getX(),(int) rectangle.getY(),(int) rectangle.getWidth(),(int) rectangle.getHeight());
        graphics2D.drawString("I", point.x + 5, point.y + 15);
        graphics2D.drawString(interfaceInterclass.getName(), point.x + 20, point.y + 15);
        graphics2D.drawLine(point.x, point.y + 20, point.x + (int) (15 + lentgh + 15), point.y + 20);
    }

    @Override
    public boolean elementAt(Point pos) {
        return (rectangle != null && rectangle.contains(pos));
    }
}
