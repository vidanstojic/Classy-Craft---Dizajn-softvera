package raf.classycraft.app.model.elementDiagram;

import com.fasterxml.jackson.annotation.JsonTypeName;
import raf.classycraft.app.model.compositeAbstract.ClassyNode;

import java.awt.*;
@JsonTypeName("DiagramElement")
public abstract class DiagramElement extends ClassyNode {
    // cuva model/recept za crtanje
    private Color color;
    private int stroke;

    public DiagramElement(Color color, int stroke) {
        this.color = color;
        this.stroke = stroke;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getStroke() {
        return stroke;
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }
}
