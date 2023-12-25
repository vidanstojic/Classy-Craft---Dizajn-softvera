package raf.classycraft.app.command.implementation;

import raf.classycraft.app.command.AbstractCommand;
import raf.classycraft.app.gui.view.DiagramView;
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

    public EditCommand(Attribute attribute, Method method, DiagramView diagramView, Interclass interclass) {
        this.attribute= attribute;
        this.method = method;
        this.diagramView = diagramView;
        this.interclass = interclass;
    }




    @Override
    public void doCommand() {
        if(diagramView == null || interclass == null) return;

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

    @Override
    public void undoCommand() {
        if(diagramView == null || interclass == null) return;

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
}
