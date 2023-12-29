package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.concreteConnections.Dependency;
import raf.classycraft.app.model.elementDiagram.concreteConnections.Generalization;

import java.awt.*;
import java.awt.geom.Line2D;

public class DependancyPainter extends ConnectionPainter {

    private Line2D line2D;

    public DependancyPainter(Dependency connection) {
        super(connection);
    }
    public DependancyPainter(Dependency dependency, Line2D line2D){
        super(dependency);
        this.line2D = line2D;
        dependency.setLine2D(line2D);
    }
    @Override
    public void paint(Graphics2D graphics2D, DiagramElement diagramElement) {
        Connection connection = super.getConnection();
        if (connection.getLine2D() == null) return;
        if (connection.getClassTo() == null && connection.getClassFrom() == null) return;

        connection.lineUpdate();
        this.line2D = connection.getLine2D();

        // Ovo postavlja isprekidanu liniju
        Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        graphics2D.setStroke(dashed);

        graphics2D.drawLine((int) line2D.getX1(), (int) line2D.getY1(), (int) line2D.getX2(), (int) line2D.getY2());

        // ovde se resetuje stroke kako bi nacrtali obicnu strelicu
        graphics2D.setStroke(new BasicStroke());

        drawArrow(graphics2D, (int) line2D.getX1(), (int) line2D.getY1(),
                (int) line2D.getX2(), (int) line2D.getY2());
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

        g.drawLine(x2, y2, (int) arrowPoint1X, (int) arrowPoint1Y);
        g.drawLine(x2, y2, (int) arrowPoint2X, (int) arrowPoint2Y);
    }

    @Override
    public boolean elementAt(Point pos) {
        return (line2D != null && line2D.contains(pos));
    }
}
