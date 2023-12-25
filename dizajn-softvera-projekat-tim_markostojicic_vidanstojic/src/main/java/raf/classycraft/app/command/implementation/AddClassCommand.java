package raf.classycraft.app.command.implementation;

import raf.classycraft.app.command.AbstractCommand;
import raf.classycraft.app.gui.tree.ClassyTreeImplementation;
import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.InterClassPainter;
import raf.classycraft.app.model.elementDiagram.Interclass;

public class AddClassCommand extends AbstractCommand {

    private DiagramView diagramView;
    private Interclass interclass;
    private InterClassPainter interClassPainter;
    private ClassyTreeItem parentTreeItem;
    private ClassyTreeItem childTreeItem;
    private  ClassyTreeImplementation classyTreeImplementation;


    public AddClassCommand(DiagramView diagramView, Interclass interclass, InterClassPainter interClassPainter, ClassyTreeItem parentTreeItem, ClassyTreeItem childTreeItem, ClassyTreeImplementation classyTreeImplementation) {
        this.diagramView = diagramView;
        this.interclass = interclass;
        this.interClassPainter = interClassPainter;
        this.parentTreeItem = parentTreeItem;
        this.childTreeItem = childTreeItem;
        this.classyTreeImplementation = classyTreeImplementation;

    }

    @Override
    public void doCommand() {
        if(interclass == null || interClassPainter == null || diagramView == null || parentTreeItem == null || childTreeItem == null) return;

        diagramView.getListOfPainters().add(interClassPainter);
        diagramView.getDiagram().addChild(interclass);
        this.classyTreeImplementation.addDiagramElement(parentTreeItem, childTreeItem);
    }

    @Override
    public void undoCommand() {
        if(interclass == null || interClassPainter == null || diagramView == null) return;

        diagramView.getListOfPainters().remove(interClassPainter);
        diagramView.getDiagram().removeChild(interclass);
        this.classyTreeImplementation.removeChild(childTreeItem);
    }
}
