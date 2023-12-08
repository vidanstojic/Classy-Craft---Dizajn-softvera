package raf.classycraft.app.gui.controller;

import raf.classycraft.app.gui.controller.drawingToolbarActions.*;

public class ActionManager {
    private ExitAction ea;
    private AboutUsAction aboutUs;
    private AddClassAction addClassAction;
    private NewProjectAction newProjectAction;
    private AddConnectionAction addConnectionAction;
    private RemoveItemAction removeItemAction;
    private EditAction editAction;
    private ProjectAuthorAction projectAuthorAction;
    private EraserAction eraserAction;
    private MoveAction moveAction;
    private SelectionAction selectionAction;
    private MyMouseListener myMouseListener;
    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions(){
        ea = new ExitAction();
        aboutUs = new AboutUsAction();
        newProjectAction = new NewProjectAction();
        removeItemAction = new RemoveItemAction();
        projectAuthorAction = new ProjectAuthorAction();
        addClassAction = new AddClassAction();
        addConnectionAction = new AddConnectionAction();
        editAction = new EditAction();
        eraserAction = new EraserAction();
        moveAction = new MoveAction();
        selectionAction = new SelectionAction();
    }

    public ExitAction getEa() {
        return ea;
    }

    public AboutUsAction getAboutUs() {
        return aboutUs;
    }

    public NewProjectAction getNewProjectAction() {
        return newProjectAction;
    }

    public RemoveItemAction getRemoveItemAction() {
        return removeItemAction;
    }

    public ProjectAuthorAction getProjectAuthorAction() {
        return projectAuthorAction;
    }

    public AddClassAction getAddClassAction() {
        return addClassAction;
    }

    public AddConnectionAction getAddConnectionAction() {
        return addConnectionAction;
    }

    public EditAction getEditAction() {
        return editAction;
    }

    public EraserAction getEraserAction() {
        return eraserAction;
    }

    public MoveAction getMoveAction() {
        return moveAction;
    }

    public SelectionAction getSelectionAcstion(){return selectionAction;}
}
