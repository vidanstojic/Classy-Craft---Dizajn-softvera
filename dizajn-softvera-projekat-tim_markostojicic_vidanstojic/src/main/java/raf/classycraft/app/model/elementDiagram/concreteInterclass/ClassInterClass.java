package raf.classycraft.app.model.elementDiagram.concreteInterclass;

import raf.classycraft.app.model.elementDiagram.Interclass;

import java.awt.*;

public class ClassInterClass extends Interclass {

    private String name;
    private Point point;
    public ClassInterClass(Point point, Color color, int stroke, String name, String visibility) {
        super(color, stroke, name, visibility);
        this.name = name;
        this.point = point;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}

