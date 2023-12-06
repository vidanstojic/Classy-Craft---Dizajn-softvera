package raf.classycraft.app.model.elementDiagram;

import raf.classycraft.app.model.elementDiagram.DiagramElement;

import java.awt.*;

public abstract class Interclass extends DiagramElement {
    private String name;
    private String visibility;

    private Point point;

    public Interclass(Point point,Color color, int stroke, String name, String visibility) {
        super(color, stroke);
        this.name = name;
        this.visibility = visibility;
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

}
