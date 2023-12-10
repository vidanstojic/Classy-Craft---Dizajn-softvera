package raf.classycraft.app.model.elementDiagram.classContent;

public abstract class ClassContent {
    private String name;
    private Visibility visibility;
    private String returnType;

    public ClassContent(String name, Visibility visibility, String returnType) {
        this.name = name;
        this.visibility = visibility;
        this.returnType = returnType;
    }

    public ClassContent(String name, String returnType){
        this.name = name;
        this.returnType = returnType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
}
