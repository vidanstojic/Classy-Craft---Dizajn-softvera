package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.DiagramElement;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

public class AggregationPainter extends ConnectionPainter {

    private Line2D line2D;
    public AggregationPainter(Connection connection) {
        super(connection);
    }

    @Override
    public void paint(Graphics2D graphics2D, DiagramElement diagramElement) {
        Connection connection = super.getConnection();
        if (connection.getLine2D() == null) return;
        if (connection.getClassTo() == null && connection.getClassFrom() == null) return;

        connection.lineUpdate();
        this.line2D = connection.getLine2D();
        graphics2D.drawLine((int) connection.getLine2D().getX1(), (int) connection.getLine2D().getY1(),
                (int) connection.getLine2D().getX2(), (int) connection.getLine2D().getY2());


        drawArrow(graphics2D, (int) connection.getLine2D().getX1(), (int) connection.getLine2D().getY1(),
                (int) connection.getLine2D().getX2(), (int) connection.getLine2D().getY2());

        this.setLine2D(line2D);
    }


    private void drawArrow(Graphics2D g, int x1, int y1, int x2, int y2) {
        int arrowSize = 5;

        double angle = Math.atan2(y2 - y1, x2 - x1);
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);

        double triangleX = x2;
        double triangleY = y2;

        double arrowPoint1X = triangleX - arrowSize * cos;
        double arrowPoint1Y = triangleY - arrowSize * sin;

        double arrowPoint2X = triangleX + arrowSize * sin;
        double arrowPoint2Y = triangleY - arrowSize * cos;

        double arrowPoint3X = triangleX + arrowSize * cos;
        double arrowPoint3Y = triangleY + arrowSize * sin;

        double arrowPoint4X = triangleX - arrowSize * sin;
        double arrowPoint4Y = triangleY + arrowSize * cos;

        GeneralPath path = new GeneralPath();
        path.moveTo(arrowPoint1X, arrowPoint1Y);
        path.lineTo(arrowPoint2X, arrowPoint2Y);
        path.lineTo(arrowPoint3X, arrowPoint3Y);
        path.lineTo(arrowPoint4X, arrowPoint4Y);
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
