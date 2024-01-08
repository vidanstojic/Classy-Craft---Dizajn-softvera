package raf.classycraft.app.model.elementDiagram.classContent;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Attribute.class, name = "Attribute"),
        @JsonSubTypes.Type(value = Method.class, name = "Method")
})
public abstract class ClassContent {
    private String name;
    private Visibility visibility;
    private String returnType;

    private String abstractContentOrNot = "";
    private String staticContentOrNot = "";

    public ClassContent(String name, Visibility visibility, String returnType) {
        this.name = name;
        this.visibility = visibility;
        this.returnType = returnType;
    }

    public ClassContent(String name, Visibility visibility, String returnType, String abstractContentOrNot, String staticContentOrNot) {
        this.name = name;
        this.visibility = visibility;
        this.returnType = returnType;
        this.abstractContentOrNot = abstractContentOrNot;
        this.staticContentOrNot = staticContentOrNot;
    }

    public ClassContent(String name) {
        this.name = name;
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

    public String getAbstractContentOrNot() {
        return abstractContentOrNot;
    }

    public void setAbstractContentOrNot(String abstractContentOrNot) {
        this.abstractContentOrNot = abstractContentOrNot;
    }

    public String getStaticContentOrNot() {
        return staticContentOrNot;
    }

    public void setStaticContentOrNot(String staticContentOrNot) {
        this.staticContentOrNot = staticContentOrNot;
    }
}
