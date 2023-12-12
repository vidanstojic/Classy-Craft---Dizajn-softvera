package raf.classycraft.app.model.elementDiagram.concreteConnections;

import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.Interclass;

import java.awt.*;
import java.awt.geom.Line2D;

public class Composition extends Connection {


    public Composition(Color color, int stroke, Interclass from, Interclass to, Line2D line2D, ConnectionInfo connectionInfo) {
        super(color, stroke, from, to, line2D, connectionInfo);
    }

    public Composition(Color color, int stroke, Line2D line2D) {
        super(color, stroke, line2D);
    }

    @Override
    public Interclass getClassFrom() {
        return super.getClassFrom();
    }

    @Override
    public void setClassFrom(Interclass classFrom) {
        super.setClassFrom(classFrom);
    }

    @Override
    public Interclass getClassTo() {
        return super.getClassTo();
    }

    @Override
    public void setClassTo(Interclass classTo) {
        super.setClassTo(classTo);
    }
    @Override
    public Line2D getLine2D() {
        return super.getLine2D();
    }

    @Override
    public void setLine2D(Line2D line2D) {
        super.setLine2D(line2D);
    }

    @Override
    public ConnectionInfo getConnectionInfo() {
        return super.getConnectionInfo();
    }

    @Override
    public void setConnectionInfo(ConnectionInfo connectionInfo) {
        super.setConnectionInfo(connectionInfo);
    }
}
