package raf.classycraft.app.gui.controller;

public class ActionManager {
    private ExitAction ea;
    private AboutUsAction aboutUs;

    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions(){
        ea = new ExitAction();
        aboutUs = new AboutUsAction();
    }

    public ExitAction getEa() {
        return ea;
    }

    public AboutUsAction getAboutUs() {
        return aboutUs;
    }
}
