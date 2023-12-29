package raf.classycraft.app.model.compositeAbstract;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import raf.classycraft.app.model.compositeImplement.Diagram;
import raf.classycraft.app.model.compositeImplement.MyPackage;
import raf.classycraft.app.model.compositeImplement.Project;
import raf.classycraft.app.model.compositeImplement.ProjectExplorer;
import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.model.elementDiagram.concreteConnections.Aggregation;
import raf.classycraft.app.model.elementDiagram.concreteConnections.Composition;
import raf.classycraft.app.model.elementDiagram.concreteConnections.Dependency;
import raf.classycraft.app.model.elementDiagram.concreteConnections.Generalization;

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
        @JsonSubTypes.Type(value = DiagramElement.class, name = "DiagramElement"),
        @JsonSubTypes.Type(value = Interclass.class, name = "Class"),
        @JsonSubTypes.Type(value = Aggregation.class, name = "Aggregation"),
        @JsonSubTypes.Type(value = Composition.class, name = "Composition"),
        @JsonSubTypes.Type(value = Dependency.class, name = "Dependency"),
        @JsonSubTypes.Type(value = Generalization.class, name = "Generalization")
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
