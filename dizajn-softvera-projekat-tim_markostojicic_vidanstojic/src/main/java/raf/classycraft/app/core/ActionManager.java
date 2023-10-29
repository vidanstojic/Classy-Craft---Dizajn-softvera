package raf.classycraft.app.core;

import raf.classycraft.app.controller.ExitAction;

public class ActionManager {
    private ExitAction ea;

    public ActionManager(){
        initialize();
    }

    public void initialize(){
        ea = new ExitAction();
    }

    public ExitAction getEa() {
        return ea;
    }

    public void setEa(ExitAction ea) {
        this.ea = ea;
    }
}
