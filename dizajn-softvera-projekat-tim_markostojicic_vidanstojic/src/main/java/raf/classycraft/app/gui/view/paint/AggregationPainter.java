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


        drawDiamond(graphics2D, (int) connection.getLine2D().getX1(), (int) connection.getLine2D().getY1(),
                (int) connection.getLine2D().getX2(), (int) connection.getLine2D().getY2());



        this.setLine2D(line2D);
    }

    private void drawDiamond(Graphics2D g, int x1, int y1, int x2, int y2) {
        int diamondSize = 15;

        double angle = Math.atan2(y2 - y1, x2 - x1);
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);

        double centerX = x1;
        double centerY = y1;

        double diamondPoint1X = centerX - diamondSize * cos * 0.35;
        double diamondPoint1Y = centerY - diamondSize * sin * 0.35;

        double diamondPoint2X = centerX + diamondSize * sin * 0.35;
        double diamondPoint2Y = centerY + diamondSize * cos * 0.35;

        double diamondPoint3X = centerX + diamondSize * cos * 0.35;
        double diamondPoint3Y = centerY + diamondSize * sin * 0.35;

        double diamondPoint4X = centerX - diamondSize * sin * 0.35;
        double diamondPoint4Y = centerY - diamondSize * cos * 0.35;

        GeneralPath path = new GeneralPath();
        path.moveTo(diamondPoint4X, diamondPoint4Y);
        path.lineTo(diamondPoint3X, diamondPoint3Y);
        path.lineTo(diamondPoint2X, diamondPoint2Y);
        path.lineTo(diamondPoint1X, diamondPoint1Y);
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
