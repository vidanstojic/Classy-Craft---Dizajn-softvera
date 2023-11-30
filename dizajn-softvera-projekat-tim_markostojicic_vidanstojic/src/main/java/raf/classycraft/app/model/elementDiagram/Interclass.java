package raf.classycraft.app.model.elementDiagram;

import raf.classycraft.app.model.elementDiagram.DiagramElement;

import java.awt.*;

public abstract class Interclass extends DiagramElement {
    private String name;
    private String visibility;


    public Interclass(Color color, int stroke, String name, String visibility) {
        super(color, stroke);
        this.name = name;
        this.visibility = visibility;
    }
}
