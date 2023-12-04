package raf.classycraft.app.model.elementDiagram.concreteInterclass;

import raf.classycraft.app.model.elementDiagram.Interclass;

import java.awt.*;

public class EnumInterclass extends Interclass {

    private String name;
    public EnumInterclass(Color color, int stroke, String name, String visibility) {
        super(color, stroke, name, visibility);
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
}
