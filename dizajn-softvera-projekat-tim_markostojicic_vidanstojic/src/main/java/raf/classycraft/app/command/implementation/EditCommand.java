package raf.classycraft.app.command.implementation;

import raf.classycraft.app.command.AbstractCommand;
import raf.classycraft.app.gui.tree.ClassyTreeImplementation;
import raf.classycraft.app.gui.view.DiagramView;
import raf.classycraft.app.model.elementDiagram.EditClassMode;
import raf.classycraft.app.model.elementDiagram.Interclass;
import raf.classycraft.app.model.elementDiagram.classContent.Attribute;
import raf.classycraft.app.model.elementDiagram.classContent.ClassContent;
import raf.classycraft.app.model.elementDiagram.classContent.Method;
import raf.classycraft.app.model.elementDiagram.classContent.Visibility;

import javax.swing.*;
import java.util.List;

public class EditCommand extends AbstractCommand {

    private Attribute attribute;

    private Method method;

    private DiagramView diagramView;

    private Interclass interclass;

    private EditClassMode editClassMode;

    private List<ClassContent> listForRemoveContent;

    private String newName;
    private String oldName;

    private ClassContent contentForRename;

    private ClassyTreeImplementation classyTreeImplementation;

    private String abstractElement;

    private String staticElement;

    private String oldStatic;
    private String oldAbstract;

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

    public EditCommand(DiagramView diagramView, Interclass interclass, String newName, EditClassMode editClassMode, ClassyTreeImplementation classyTreeImplementation){
        this.diagramView = diagramView;
        this.interclass = interclass;
        this.newName = newName;
        this.editClassMode = editClassMode;
        this.classyTreeImplementation = classyTreeImplementation;
    }
    public EditCommand(DiagramView diagramView, ClassContent classContent, String newName, EditClassMode editClassMode){
        this.diagramView = diagramView;
        this.contentForRename = classContent;
        this.newName = newName;
        this.editClassMode = editClassMode;
    }

    public EditCommand(DiagramView diagramView, ClassContent classContent, String abstractElement,String staticElement,EditClassMode editClassMode){
        this.diagramView = diagramView;
        this.contentForRename = classContent;
        this.staticElement = staticElement;
        this.abstractElement = abstractElement;
        this.editClassMode = editClassMode;
    }



    @Override
    public void doCommand() {
        if(diagramView == null) return;

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
        else if(editClassMode == EditClassMode.RENAME_CLASS_NAME){
            this.oldName = this.interclass.getName();
            interclass.setName(newName);
            SwingUtilities.updateComponentTreeUI(this.classyTreeImplementation.getTreeView());
            diagramView.repaint();
        }
        else if(editClassMode == EditClassMode.RENAME_ELEMENT){
            this.oldName = contentForRename.getName();
            contentForRename.setName(newName);
            diagramView.repaint();
        }
        else if(editClassMode == EditClassMode.CHANGE_ELEMENT_TYPE){
            this.oldName = contentForRename.getReturnType();
            contentForRename.setReturnType(newName);
            diagramView.repaint();
        }
        else if(editClassMode == EditClassMode.CHANGE_ELEMENT_VISIBILITY){
            this.oldName = contentForRename.getVisibility().toString();
            contentForRename.setVisibility(Enum.valueOf(Visibility.class, newName));
            diagramView.repaint();
        }
        else if(editClassMode == EditClassMode.MODIFY_STATIC_ABSTRACT){
            this.oldAbstract = contentForRename.getAbstractContentOrNot();
            this.oldStatic = contentForRename.getStaticContentOrNot();
            contentForRename.setAbstractContentOrNot(abstractElement);
            contentForRename.setStaticContentOrNot(staticElement);
            diagramView.repaint();
        }


    }

    @Override
    public void undoCommand() {
        if(diagramView == null) return;

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
        else if(editClassMode == EditClassMode.RENAME_CLASS_NAME){
            interclass.setName(oldName);
            SwingUtilities.updateComponentTreeUI(this.classyTreeImplementation.getTreeView());
            diagramView.repaint();
        }
        else if(editClassMode == EditClassMode.RENAME_ELEMENT){
            contentForRename.setName(oldName);
            diagramView.repaint();
        }
        else if(editClassMode == EditClassMode.CHANGE_ELEMENT_TYPE){
            contentForRename.setReturnType(oldName);
            diagramView.repaint();
        }
        else if(editClassMode == EditClassMode.CHANGE_ELEMENT_VISIBILITY){
            contentForRename.setVisibility(Enum.valueOf(Visibility.class, oldName));
            diagramView.repaint();
        }
        else if(editClassMode == EditClassMode.MODIFY_STATIC_ABSTRACT){
            contentForRename.setAbstractContentOrNot(oldAbstract);
            contentForRename.setStaticContentOrNot(oldStatic);
            diagramView.repaint();
        }

    }
}
