package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.DiagramElement;

import java.awt.*;

public class CompositionPainter extends ConnectionPainter{

    public CompositionPainter(Connection connection) {
        super(connection);
    }

    @Override
    public void paint(Graphics2D graphics2D, DiagramElement diagramElement) {

    }

    @Override
    public boolean elementAt(Point pos) {
        return true;
    }
}
