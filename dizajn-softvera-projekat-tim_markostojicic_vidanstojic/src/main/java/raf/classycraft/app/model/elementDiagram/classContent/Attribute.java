package raf.classycraft.app.model.elementDiagram.classContent;

import raf.classycraft.app.model.elementDiagram.classContent.ClassContent;

public class Attribute extends ClassContent {
    public Attribute(String name, Visibility visibility, String returnType) {
        super(name, visibility, returnType);
    }
    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public Visibility getVisibility() {
        return super.getVisibility();
    }

    @Override
    public void setVisibility(Visibility visibility) {
        super.setVisibility(visibility);
    }

    @Override
    public String getReturnType() {
        return super.getReturnType();
    }

    @Override
    public void setReturnType(String returnType) {
        super.setReturnType(returnType);
    }
}
