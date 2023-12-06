package raf.classycraft.app.model.elementDiagram;

import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.classContent.Attribute;
import raf.classycraft.app.model.elementDiagram.classContent.Method;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Interclass extends DiagramElement {
    private String name;
    private String visibility;

    private Point point;

    private List<Attribute> attributes = new ArrayList<>();
    private List<Method> methods = new ArrayList<>();

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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }
}
