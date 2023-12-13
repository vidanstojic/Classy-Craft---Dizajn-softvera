package raf.classycraft.app.state;

import raf.classycraft.app.state.concrete.*;

public class StateManager {
    private State currentState;
    private MoveState moveState;
    private RemoveState removeState;
    private EditClassState editClassState;
    private AddConnectionState addConnectionState;
    private AddClassState addClassState;
    private SelectionState selectionState;
    private ZoomOutState zoomOutState;
    private DuplicateState duplicateState;
    private ZoomInState zoomInState;
    public StateManager(){
        initStates();
    }

    private void initStates(){
        moveState = new MoveState();
        removeState = new RemoveState();
        editClassState = new EditClassState();
        addConnectionState = new AddConnectionState();
        addClassState = new AddClassState();
        selectionState = new SelectionState();
        duplicateState = new DuplicateState();
        zoomInState = new ZoomInState();
        zoomOutState = new ZoomOutState();
        currentState = addClassState;
    }

    public State getCurrentState(){
        return currentState;
    }

    public void setMoveState() {
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
    public void setZoomInState(){this.currentState = zoomInState;}
    public void setAddClassState() {this.currentState = addClassState;}
    public void setSelectionState() {
        this.currentState = selectionState;
    }
    public void setZoomOutState(){this.currentState = zoomOutState;}
    public void setDuplicateState(){this.currentState = duplicateState;}
}
