package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.InterfaceInterclass;

import java.awt.*;

public class InterfacePainter extends InterClassPainter{

    InterfaceInterclass interfaceInterclass;
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
        graphics2D.drawRect(point.x, point.y, (int) (15 + lentgh + 15),120);
        graphics2D.drawString("I", point.x + 5, point.y + 15);
        graphics2D.drawString(interfaceInterclass.getName(), point.x + 20, point.y + 15);
        graphics2D.drawLine(point.x, point.y + 20, point.x + (int) (15 + lentgh + 15), point.y + 20);
    }

    @Override
    public void elementAt() {

    }
}
