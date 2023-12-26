package raf.classycraft.app.command.implementation;

import raf.classycraft.app.command.AbstractCommand;
import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.model.elementDiagram.EditClassMode;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.model.elementDiagram.classContent.Attribute;
import raf.classycraft.app.model.elementDiagram.classContent.ClassContent;
import raf.classycraft.app.model.elementDiagram.classContent.Method;

import java.util.List;

public class EditCommand extends AbstractCommand {

    private Attribute attribute;

    private Method method;

    private DiagramView diagramView;

    private Interclass interclass;

    private EditClassMode editClassMode;

    private List<ClassContent> listForRemoveContent;

    public EditCommand(Attribute attribute, Method method, DiagramView diagramView, Interclass interclass, EditClassMode editClassMode) {
        this.attribute= attribute;
        this.method = method;
        this.diagramView = diagramView;
        this.interclass = interclass;
        this.editClassMode = editClassMode;
    }

    public EditCommand(DiagramView diagramView, Interclass interclass, List<ClassContent> listForRemoveContent, EditClassMode editClassMode){
        this.diagramView = diagramView;
        this.interclass = interclass;
        this.listForRemoveContent = listForRemoveContent;
        this.editClassMode = editClassMode;
    }



    @Override
    public void doCommand() {
        if(diagramView == null || interclass == null) return;

        if(editClassMode == EditClassMode.ADD_ELEMENT){
            if(method != null){
                interclass.getMethods().add(method);
                interclass.getClassContents().add(method);
            }
            if(attribute != null){
                interclass.getAttributes().add(attribute);
                interclass.getClassContents().add(attribute);
            }
            diagramView.repaint();
        }
        else if(editClassMode == EditClassMode.REMOVE_ELEMENT){
            for (ClassContent classContent : listForRemoveContent){
                if (classContent instanceof Attribute)
                    interclass.getAttributes().remove(classContent);
                else if (classContent instanceof Method) {
                    interclass.getMethods().remove(classContent);
                }
                interclass.getClassContents().remove(classContent);
                diagramView.repaint();
            }
        }
        else if(editClassMode == EditClassMode.RENAME_ELEMENT){

        }


    }

    @Override
    public void undoCommand() {
        if(diagramView == null || interclass == null) return;

        if(editClassMode == EditClassMode.ADD_ELEMENT){
            if(method != null){
                interclass.getMethods().remove(method);
                interclass.getClassContents().remove(method);
            }
            if(attribute != null){
                interclass.getAttributes().remove(attribute);
                interclass.getClassContents().remove(attribute);
            }
            diagramView.repaint();
        }
        else if(editClassMode == EditClassMode.REMOVE_ELEMENT){
            for (ClassContent classContent : listForRemoveContent){
                if (classContent instanceof Attribute)
                    interclass.getAttributes().add((Attribute) classContent);
                else if (classContent instanceof Method) {
                    interclass.getMethods().add((Method) classContent);
                }
                interclass.getClassContents().add(classContent);
                diagramView.repaint();
            }
        }
        else if(editClassMode == EditClassMode.RENAME_ELEMENT){

        }

    }
}
