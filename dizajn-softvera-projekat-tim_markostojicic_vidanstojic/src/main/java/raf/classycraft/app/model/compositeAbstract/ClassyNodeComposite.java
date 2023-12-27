package raf.classycraft.app.model.compositeAbstract;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import raf.classycraft.app.model.compositeImplement.MyPackage;
import raf.classycraft.app.model.compositeImplement.Project;
import raf.classycraft.app.model.compositeImplement.ProjectExplorer;

import java.util.ArrayList;
import java.util.List;
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Project.class, name = "Project"),
        @JsonSubTypes.Type(value = MyPackage.class, name = "MyPackage"),
        @JsonSubTypes.Type(value = ProjectExplorer.class, name = "ProjectExplorer")
})
public abstract class ClassyNodeComposite extends ClassyNode{

    protected List<ClassyNode> children = new ArrayList<>();

    public ClassyNodeComposite() {

    }

    ///Metode
    public abstract void addChild(ClassyNode child);
    public abstract void removeChild(ClassyNode child);

    public List<ClassyNode> getChildren() {
        return children;
    }

    public void setChildren(List<ClassyNode> children) {///Mislim da ovo ni ne treba
        this.children = children;
    }
}
