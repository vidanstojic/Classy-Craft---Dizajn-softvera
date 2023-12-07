package raf.classycraft.app.model.elementDiagram;

import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.Interclass;

import javax.sound.sampled.Line;
import java.awt.*;
import java.awt.geom.Line2D;

public abstract class Connection extends DiagramElement {
    private Interclass from;
    private Interclass to;

    private Line2D line2D;
    public Connection(Color color, int stroke, Interclass from, Interclass to, Line2D line2D) {
        super(color, stroke);
        this.from = from;
        this.to = to;
        this.line2D = line2D;
    }

    public Interclass getFrom() {
        return from;
    }

    public void setFrom(Interclass from) {
        this.from = from;
    }

    public Interclass getTo() {
        return to;
    }

    public void setTo(Interclass to) {
        this.to = to;
    }

    public Line2D getLine2D() {
        return line2D;
    }

    public void setLine2D(Line2D line2D) {
        this.line2D = line2D;
    }
}
