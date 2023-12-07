package raf.classycraft.app.gui.view.paint;

import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.DiagramElement;

import java.awt.*;

public abstract class ConnectionPainter extends ElementPainter {

    private Connection connection;
    public ConnectionPainter(Connection connection){
        this.connection = connection;
    }

    public void paint(Graphics2D graphics2D, DiagramElement diagramElement) {

    }


    public boolean elementAt(Point pos) {
        return true;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
