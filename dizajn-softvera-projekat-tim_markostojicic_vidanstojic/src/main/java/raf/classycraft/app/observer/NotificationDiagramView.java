package raf.classycraft.app.observer;

import raf.classycraft.app.model.elementDiagram.DiagramElement;

public class NotificationDiagramView {
    private TypeDiagramView typeDiagramView;
    private DiagramElement diagramElement;

    public NotificationDiagramView(TypeDiagramView typeDiagramView, DiagramElement diagramElement) {
        this.typeDiagramView = typeDiagramView;
        this.diagramElement = diagramElement;
    }
    public NotificationDiagramView(TypeDiagramView typeDiagramView){
        this.typeDiagramView = typeDiagramView;
    }

    public TypeDiagramView getTypeDiagramView() {
        return typeDiagramView;
    }

    public void setTypeDiagramView(TypeDiagramView typeDiagramView) {
        this.typeDiagramView = typeDiagramView;
    }

    public DiagramElement getDiagramElement() {
        return diagramElement;
    }

    public void setDiagramElement(DiagramElement diagramElement) {
        this.diagramElement = diagramElement;
    }
}
