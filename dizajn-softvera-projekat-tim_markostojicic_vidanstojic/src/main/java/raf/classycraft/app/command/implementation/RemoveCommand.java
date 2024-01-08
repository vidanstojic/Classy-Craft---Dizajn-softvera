package raf.classycraft.app.command.implementation;

import raf.classycraft.app.command.AbstractCommand;
import raf.classycraft.app.gui.tree.ClassyTreeImplementation;
import raf.classycraft.app.gui.tree.model.ClassyTreeItem;
import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.gui.view.paint.ElementPainter;
import raf.classycraft.app.model.elementDiagram.DiagramElement;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RemoveCommand extends AbstractCommand {
    private DiagramView diagramView;
    private List<ElementPainter> paintersToRemove;

    private List<DiagramElement> elementsToRemove;

    private ClassyTreeImplementation classyTreeImplementation;

    private List<ElementPainter> startingPainters = new ArrayList<>();

    private List<DiagramElement> startingModels = new ArrayList<>();

    public RemoveCommand(DiagramView diagramView, List<ElementPainter> paintersToRemove, List<DiagramElement> elementsToRemove,ClassyTreeImplementation classyTreeImplementation) {
        this.diagramView = diagramView;
        this.paintersToRemove = paintersToRemove;
        this.elementsToRemove = elementsToRemove;
        this.classyTreeImplementation = classyTreeImplementation;
        this.startingPainters.addAll(paintersToRemove);
        this.startingModels.addAll(elementsToRemove);
    }

    @Override
    public void doCommand() {
        if(diagramView == null || paintersToRemove == null || elementsToRemove == null || classyTreeImplementation == null) return;

        for(ElementPainter elementPainter : paintersToRemove){
            diagramView.getListOfPainters().remove(elementPainter);
        }
        for(DiagramElement elementToRemove : elementsToRemove){
            if(elementToRemove == null) continue;
            ClassyTreeItem itemToRemove = this.classyTreeImplementation.findTreeItem( (ClassyTreeItem)classyTreeImplementation.getTreeModel().getRoot() ,elementToRemove);
            this.classyTreeImplementation.removeChild(itemToRemove);
        }
    }

    @Override
    public void undoCommand() {
        if(diagramView == null || paintersToRemove == null || elementsToRemove == null || classyTreeImplementation == null) return;

        diagramView.getListOfPainters().addAll(startingPainters);

        for (DiagramElement elementToRemove : startingModels) {
            if(elementToRemove == null) continue;
            ClassyTreeItem parentForDiagramElement = classyTreeImplementation.findTreeItem((ClassyTreeItem)classyTreeImplementation.getTreeModel().getRoot(), diagramView.getDiagram());
            classyTreeImplementation.addDiagramElement(parentForDiagramElement, new ClassyTreeItem(elementToRemove));
        }


    }
}
