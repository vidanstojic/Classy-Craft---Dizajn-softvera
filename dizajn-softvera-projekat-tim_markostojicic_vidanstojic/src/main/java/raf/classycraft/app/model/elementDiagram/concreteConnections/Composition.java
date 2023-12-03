package raf.classycraft.app.model.elementDiagram.concreteConnections;

import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.Interclass;

import java.awt.*;

public class Composition extends Connection {


    public Composition(Color color, int stroke, Interclass from, Interclass to) {
        super(color, stroke, from, to);
    }
}
