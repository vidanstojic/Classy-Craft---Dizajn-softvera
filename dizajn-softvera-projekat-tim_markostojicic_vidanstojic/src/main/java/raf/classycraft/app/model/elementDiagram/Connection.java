package raf.classycraft.app.model.elementDiagram;

import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.Interclass;

import java.awt.*;

public abstract class Connection extends DiagramElement {
    private Interclass from;
    private Interclass to;

    public Connection(Color color, int stroke, Interclass from, Interclass to) {
        super(color, stroke);
        this.from = from;
        this.to = to;
    }
}
