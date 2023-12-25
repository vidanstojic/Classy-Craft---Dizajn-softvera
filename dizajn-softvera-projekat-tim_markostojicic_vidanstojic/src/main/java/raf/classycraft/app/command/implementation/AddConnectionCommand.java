package raf.classycraft.app.command.implementation;

import raf.classycraft.app.command.AbstractCommand;
import raf.classycraft.app.gui.tree.ClassyTreeImplementation;
import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.ConnectionPainter;
import raf.classycraft.app.gui.view.paint.InterClassPainter;
import raf.classycraft.app.model.elementDiagram.Connection;
import raf.classycraft.app.model.elementDiagram.Interclass;

public class AddConnectionCommand extends AbstractCommand {

    private DiagramView diagramView;
    private Connection connectionModel;

    private ConnectionPainter connectionPainter;

    private ClassyTreeItem parentTreeItem;
    private ClassyTreeItem childTreeItem;

    private ClassyTreeImplementation classyTreeImplementation;

    public AddConnectionCommand(DiagramView diagramView, Connection connectionModel, ConnectionPainter connectionPainter, ClassyTreeItem parentTreeItem, ClassyTreeItem childTreeItem, ClassyTreeImplementation classyTreeImplementation) {
        this.diagramView = diagramView;
        this.connectionModel = connectionModel;
        this.connectionPainter = connectionPainter;
        this.parentTreeItem = parentTreeItem;
        this.childTreeItem = childTreeItem;
        this.classyTreeImplementation = classyTreeImplementation;
    }

    @Override
    public void doCommand() {
        if(diagramView == null || connectionModel == null || connectionPainter == null || parentTreeItem == null || childTreeItem == null || classyTreeImplementation == null){
            return;
        }
        if(!diagramView.getListOfPainters().contains(connectionPainter)){
            diagramView.getListOfPainters().add(connectionPainter);
        }
        this.classyTreeImplementation.addDiagramElement(parentTreeItem, childTreeItem);
    }

    @Override
    public void undoCommand() {
        if(diagramView == null || connectionModel == null || connectionPainter == null || parentTreeItem == null || childTreeItem == null || classyTreeImplementation == null){
            return;
        }
        diagramView.getListOfPainters().remove(connectionPainter);
        this.classyTreeImplementation.removeChild(childTreeItem);
    }
}
