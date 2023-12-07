package raf.classycraft.app.model.elementDiagram.concreteConnections;

import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.Interclass;

import java.awt.*;
import java.awt.geom.Line2D;

public class Aggregation extends Connection {

    public Aggregation(Color color, int stroke, Interclass from, Interclass to, Line2D line2D) {
        super(color, stroke, from, to, line2D);
    }

    @Override
    public Interclass getFrom() {
        return super.getFrom();
    }

    @Override
    public void setFrom(Interclass from) {
        super.setFrom(from);
    }

    @Override
    public Interclass getTo() {
        return super.getTo();
    }

    @Override
    public void setTo(Interclass to) {
        super.setTo(to);
    }
    @Override
    public Line2D getLine2D() {
        return super.getLine2D();
    }

    @Override
    public void setLine2D(Line2D line2D) {
        super.setLine2D(line2D);
    }
}
