package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.DiagramElement;

import java.awt.*;
import java.awt.geom.Line2D;

public class GeneralizationPainter extends ConnectionPainter {


    public GeneralizationPainter(Connection connection) {
        super(connection);
    }

    @Override
    public void paint(Graphics2D graphics2D, DiagramElement diagramElement) {
        if(diagramElement instanceof Connection){
            Connection connection = (Connection) diagramElement;
            graphics2D.drawLine((int) connection.getLine2D().getX1(), (int) connection.getLine2D().getY1(), (int) connection.getLine2D().getX2(), (int) connection.getLine2D().getY2());
        }
    }

    @Override
    public boolean elementAt(Point pos) {
        return true;
    }
}
