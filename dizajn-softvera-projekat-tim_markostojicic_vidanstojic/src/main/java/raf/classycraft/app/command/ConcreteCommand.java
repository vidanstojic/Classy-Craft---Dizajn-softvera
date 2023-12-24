package raf.classycraft.app.command;

import raf.classycraft.app.command.implementation.*;

public class ConcreteCommand extends AbstractCommand{

    private AddClassCommand addClassCommand;
    private AddConnectionCommand addConnectionCommand;
    private DuplicateCommand duplicateCommand;
    private EditCommand editCommand;
    private MoveCommand moveCommand;
    private RemoveCommand removeCommand;

    public ConcreteCommand(){
        initialize();
    }
    private void initialize(){
        addClassCommand = new AddClassCommand();
        addConnectionCommand = new AddConnectionCommand();
        duplicateCommand = new DuplicateCommand();
        editCommand = new EditCommand();
        moveCommand = new MoveCommand();
        removeCommand = new RemoveCommand();
    }
    @Override
    public void doCommand() {

    }

    @Override
    public void undoCommand() {

    }

    public AddClassCommand getAddClassCommand() {
        return addClassCommand;
    }
    public AddConnectionCommand getAddConnectionCommand() {
        return addConnectionCommand;
    }
    public DuplicateCommand getDuplicateCommand() {
        return duplicateCommand;
    }
    public EditCommand getEditCommand() {
        return editCommand;
    }
    public MoveCommand getMoveCommand() {
        return moveCommand;
    }
    public RemoveCommand getRemoveCommand() {
        return removeCommand;
    }
}
