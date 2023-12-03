package raf.classycraft.app.gui.controller;

public class ActionManager {
    private ExitAction ea;
    private AboutUsAction aboutUs;
    private AddClassAction addAction;
    private NewProjectAction newProjectAction;
    private AddConnectionAction addConnectionAction;
    private RemoveItemAction removeItemAction;
    private EditAction editAction;
    private ProjectAuthorAction projectAuthorAction;
    private EraserAction eraserAction;
    private SelectAction selectAction;
    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions(){
        ea = new ExitAction();
        aboutUs = new AboutUsAction();
        newProjectAction = new NewProjectAction();
        removeItemAction = new RemoveItemAction();
        projectAuthorAction = new ProjectAuthorAction();
        addAction = new AddClassAction();
        addConnectionAction = new AddConnectionAction();
        editAction = new EditAction();
        eraserAction = new EraserAction();
        selectAction = new SelectAction();
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

    public AddClassAction getAddAction() {
        return addAction;
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

    public SelectAction getSelectAction() {
        return selectAction;
    }
}
