package raf.classycraft.app.gui.controller;

public class ActionManager {
    private ExitAction ea;
    private AboutUsAction aboutUs;

    private NewProjectAction newProjectAction;

    private RemoveItemAction removeItemAction;

    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions(){
        ea = new ExitAction();
        aboutUs = new AboutUsAction();
        newProjectAction = new NewProjectAction();
        removeItemAction = new RemoveItemAction();
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
}
