package raf.classycraft.app.model.elementDiagram.concreteConnections;

import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.Interclass;

import java.awt.*;

public class Association extends Connection {
    public Association(Color color, int stroke, Interclass from, Interclass to) {
        super(color, stroke, from, to);
    }
}
