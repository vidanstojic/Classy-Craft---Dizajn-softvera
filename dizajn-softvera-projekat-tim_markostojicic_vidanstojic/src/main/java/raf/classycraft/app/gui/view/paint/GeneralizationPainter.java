package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.concreteConnections.Generalization;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

public class GeneralizationPainter extends ConnectionPainter {

    private Line2D line2D;


    public GeneralizationPainter(Generalization connection) {
        super(connection);
    }

    @Override
    public void paint(Graphics2D graphics2D, DiagramElement diagramElement) {
            Connection connection = super.getConnection();
            if (connection.getLine2D() == null) return;
            if (connection.getTo() == null && connection.getFrom() == null) return;

            this.line2D = connection.getLine2D();
            graphics2D.drawLine((int) connection.getLine2D().getX1(), (int) connection.getLine2D().getY1(),
                    (int) connection.getLine2D().getX2(), (int) connection.getLine2D().getY2());


            drawArrow(graphics2D, (int) connection.getLine2D().getX1(), (int) connection.getLine2D().getY1(),
                    (int) connection.getLine2D().getX2(), (int) connection.getLine2D().getY2());

            this.setLine2D(line2D);
    }


    private void drawArrow(Graphics2D g, int x1, int y1, int x2, int y2) {
        int arrowSize = 15;

        double angle = Math.atan2(y2 - y1, x2 - x1);
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);

        double triangleX = x2;
        double triangleY = y2;

        double arrowPoint1X = triangleX - arrowSize * cos - arrowSize * 0.4 * sin;
        double arrowPoint1Y = triangleY - arrowSize * sin + arrowSize * 0.4 * cos;

        double arrowPoint2X = triangleX - arrowSize * cos + arrowSize * 0.4 * sin;
        double arrowPoint2Y = triangleY - arrowSize * sin - arrowSize * 0.4 * cos;


        GeneralPath path = new GeneralPath();
        path.moveTo(triangleX, triangleY);
        path.lineTo(arrowPoint1X, arrowPoint1Y);
        path.lineTo(arrowPoint2X, arrowPoint2Y);
        path.closePath();


        g.setColor(Color.WHITE);
        g.fill(path);


        g.setColor(Color.BLACK);
        g.draw(path);
    }

    @Override
    public boolean elementAt(Point pos) {
        return (line2D != null && line2D.contains(pos));

    }
}
