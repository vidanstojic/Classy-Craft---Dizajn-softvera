package raf.classycraft.app.model.compositeAbstract;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import raf.classycraft.app.model.compositeImplement.Diagram;
import raf.classycraft.app.model.compositeImplement.MyPackage;
import raf.classycraft.app.model.compositeImplement.Project;
import raf.classycraft.app.model.compositeImplement.ProjectExplorer;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Project.class, name = "Project"),
        @JsonSubTypes.Type(value = MyPackage.class, name = "MyPackage"),
        @JsonSubTypes.Type(value = ProjectExplorer.class, name = "ProjectExplorer"),
        @JsonSubTypes.Type(value = Diagram.class, name = "Diagram"),
})
public abstract class ClassyNode {

    private String name;
    @JsonIgnore
    private ClassyNode parent;

    public ClassyNode() {
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public ClassyNode getParent() {
        return parent;
    }

    public void setParent(ClassyNode parent) {
        this.parent = parent;
    }


}
