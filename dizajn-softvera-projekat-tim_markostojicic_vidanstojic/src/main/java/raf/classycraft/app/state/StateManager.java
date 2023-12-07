package raf.classycraft.app.state;

import raf.classycraft.app.state.concrete.*;

public class StateManager {
    private State currentState;
    private MoveState moveState;
    private RemoveState removeState;
    private EditClassState editClassState;
    private AddConnectionState addConnectionState;
    private AddClassState addClassState;


    public StateManager(){
        initStates();
    }

    private void initStates(){
        moveState = new MoveState();
        removeState = new RemoveState();
        editClassState = new EditClassState();
        addConnectionState = new AddConnectionState();
        addClassState = new AddClassState();
        currentState = addClassState;
    }

    public State getCurrentState(){
        return currentState;
    }

    public void setSelectionState() {
        this.currentState = moveState;
    }

    public void setRemoveState() {
        this.currentState = removeState;
    }

    public void setEditClassState() {
        this.currentState = editClassState;
    }

    public void setAddConnectionState() {
        this.currentState = addConnectionState;
    }

    public void setAddClassState() {this.currentState = addClassState;}
}
