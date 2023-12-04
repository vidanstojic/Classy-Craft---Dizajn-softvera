package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.EnumInterclass;

import java.awt.*;

public class EnumPainter extends InterClassPainter{
    EnumInterclass enumInterclass;
    public EnumPainter(Point point, EnumInterclass enumInterclass) {
        super(point);
        this.enumInterclass = enumInterclass;
    }

    @Override
    public void paint(Graphics2D graphics2D, DiagramElement diagramElement) {
        Stroke stroke = new BasicStroke(diagramElement.getStroke());
        String string = enumInterclass.getName();
        int lentgh = graphics2D.getFontMetrics().stringWidth(string);
        graphics2D.setStroke(stroke);
        graphics2D.drawRect(point.x, point.y, (int) (15 + lentgh + 15),120);
        graphics2D.drawString("E", point.x + 5, point.y + 15);
        graphics2D.drawString(enumInterclass.getName(), point.x + 20, point.y + 15);
        graphics2D.drawLine(point.x, point.y + 20, point.x + (int) (15 + lentgh + 15), point.y + 20);
    }

    @Override
    public void elementAt() {

    }
}
