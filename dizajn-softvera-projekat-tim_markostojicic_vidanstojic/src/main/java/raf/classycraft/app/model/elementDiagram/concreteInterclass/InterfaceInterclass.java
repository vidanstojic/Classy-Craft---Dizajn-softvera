package raf.classycraft.app.model.elementDiagram.concreteInterclass;

import raf.classycraft.app.model.elementDiagram.Interclass;

import java.awt.*;

public class InterfaceInterclass extends Interclass {
    public String name;
    public InterfaceInterclass(Point point,Color color, int stroke, String name, String visibility) {
        super(point,color, stroke, name, visibility);
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Point getPoint() {
        return super.getPoint();
    }

    @Override
    public void setPoint(Point point) {
        super.setPoint(point);
    }
}
