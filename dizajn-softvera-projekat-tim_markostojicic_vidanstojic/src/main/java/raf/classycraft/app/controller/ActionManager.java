package raf.classycraft.app.controller;

import raf.classycraft.app.controller.ExitAction;

public class ActionManager {
    private ExitAction ea;

    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions(){
        ea = new ExitAction();
    }

    public ExitAction getEa() {
        return ea;
    }
}
