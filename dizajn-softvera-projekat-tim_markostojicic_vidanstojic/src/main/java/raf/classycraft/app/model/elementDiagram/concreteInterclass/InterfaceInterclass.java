package raf.classycraft.app.model.elementDiagram.concreteInterclass;

import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.model.elementDiagram.classContent.Attribute;
import raf.classycraft.app.model.elementDiagram.classContent.ClassContent;
import raf.classycraft.app.model.elementDiagram.classContent.Method;

import java.awt.*;
import java.util.List;

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

    @Override
    public String getVisibility() {
        return super.getVisibility();
    }

    @Override
    public void setVisibility(String visibility) {
        super.setVisibility(visibility);
    }

    @Override
    public java.util.List<Attribute> getAttributes() {
        return super.getAttributes();
    }

    @Override
    public void setAttributes(java.util.List<Attribute> attributes) {
        super.setAttributes(attributes);
    }

    @Override
    public java.util.List<Method> getMethods() {
        return super.getMethods();
    }

    @Override
    public void setMethods(List<Method> methods) {
        super.setMethods(methods);
    }
    @Override
    public List<ClassContent> getClassContents() {
        return super.getClassContents();
    }

    @Override
    public List<Point> getConnectionDots() {
        return super.getConnectionDots();
    }
}
