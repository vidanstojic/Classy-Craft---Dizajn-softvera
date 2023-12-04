package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.concreteInterclass.ClassInterClass;

import java.awt.*;

public class ClassPainter extends InterClassPainter{
    public ClassInterClass classInterClass; // recept za pravljenje paintera

    public ClassPainter(Point point, ClassInterClass classInterClass) {
        super(point);
        this.classInterClass = classInterClass;
    }

    @Override
    public void paint(Graphics2D graphics2D, DiagramElement diagramElement) {
        Stroke stroke = new BasicStroke(diagramElement.getStroke());
        String string = classInterClass.getName();
        Font font = new Font("Arial", Font.PLAIN, 10);
        FontMetrics fontMetrics = graphics2D.getFontMetrics(font);
        int length = fontMetrics.stringWidth(string);
        graphics2D.setStroke(stroke);
        graphics2D.drawRect(point.x, point.y, (int) (10 + length*1.25 + 10),120);
        graphics2D.drawString("C", point.x + 5, point.y + 15);
        graphics2D.drawString(classInterClass.getName(), point.x + 20, point.y + 15);
        graphics2D.drawLine(point.x, point.y + 20, point.x + (int) (10 + length*1.25 + 10), point.y + 20);
        //graphics2D.setBackground(Color.WHITE);
        Rectangle rectangle = new Rectangle();
        
    }

    @Override
    public void elementAt() {

    }
}
