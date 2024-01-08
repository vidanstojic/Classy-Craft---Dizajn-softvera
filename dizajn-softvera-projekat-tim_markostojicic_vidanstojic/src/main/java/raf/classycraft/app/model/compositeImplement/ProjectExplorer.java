package raf.classycraft.app.model.compositeImplement;

import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeAbstract.ClassyNodeComposite;

public class ProjectExplorer extends ClassyNodeComposite {
    public ProjectExplorer() {
        super.setName("ProjectExplorer");
    }


    @Override
    public void addChild(ClassyNode child) {
        if (child != null &&  child instanceof Project){
            Project project = (Project) child;
            if (!this.getChildren().contains(project)){
                this.getChildren().add(project);
            }
        }
    }

    @Override
    public void removeChild(ClassyNode child) {
        if(this.getChildren().contains(child)){
            this.getChildren().remove(child);
        }
    }
    @Override
    public ClassyNode getParent(){
        return null;
    }
}
