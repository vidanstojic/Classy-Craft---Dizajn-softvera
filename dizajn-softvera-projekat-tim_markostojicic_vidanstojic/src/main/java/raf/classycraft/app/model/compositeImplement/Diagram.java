package raf.classycraft.app.model.compositeImplement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import raf.classycraft.app.gui.view.MainFrame;
import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeAbstract.ClassyNodeComposite;
import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.DiagramElement;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.observer.*;

import java.util.ArrayList;
import java.util.List;

public class Diagram extends ClassyNodeComposite implements IPublisher {

    @JsonIgnore
    private List<ISubscriber> subscribers = new ArrayList<>();
    public Diagram(String name, ClassyNode parent){
        super.setName(name);
        super.setParent(parent);
    }
    public Diagram(){

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
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
    public ClassyNode getParent() {
        return super.getParent();
    }

    @Override
    public void setParent(ClassyNode parent) {
        super.setParent(parent);
    }

    @Override
    public void addSubscriber(ISubscriber iSubscriber) {
        this.subscribers.add(iSubscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber iSubscriber) {
        this.subscribers.remove(iSubscriber);
    }

    @Override
    public void notifySub(Object notify) {
        if(notify instanceof NotificationDiagramView){
            NotificationDiagramView notificationDiagramView = (NotificationDiagramView) notify;
            for(ISubscriber subscriberView : this.subscribers){
                subscriberView.update(notificationDiagramView);
            }
        }

    }

    public Project findProject() {
        ClassyNode currentNode = this;

        while (!(currentNode instanceof Project)) {
            ClassyNode parent = currentNode.getParent();
            if (parent == null) {
                return null;
            }
            currentNode = parent;
        }
        Project project = (Project) currentNode;
        return project;
    }


    @Override
    public void addChild(ClassyNode child) {
        if(child != null && child instanceof DiagramElement) {
            DiagramElement diagramElement = (DiagramElement) child;
            if (!this.getChildren().contains(diagramElement)){
                this.getChildren().add(diagramElement);
                NotificationDiagramView notificationDiagramView = new NotificationDiagramView(TypeDiagramView.ADD_DIAGRAM_ELEMENT,diagramElement);
                notifySub(notificationDiagramView);
            }

        }
    }

    @Override
    public void removeChild(ClassyNode child) {
        if(child != null && getChildren().contains(child)){
            DiagramElement diagramElement = (DiagramElement) child;
            this.getChildren().remove(child);
            NotificationDiagramView notificationDiagramView = new NotificationDiagramView(TypeDiagramView.REMOVE_DIAGRAM_ELEMENT,diagramElement);
            notifySub(notificationDiagramView);

            if(child instanceof Interclass){
                Interclass interclass = (Interclass) child;
                ClassyNode childToRemove = null;
                for(ClassyNode children :this.getChildren()){
                    if(children instanceof Connection){
                        Connection tempConnection = (Connection) children;
                        if(tempConnection.getClassFrom().equals(interclass) || tempConnection.getClassTo().equals(interclass)){
                            childToRemove = children;
                        }
                    }
                }
                removeChild(childToRemove);
            }

        }


    }
}
