package raf.classycraft.app.gui.controller;

import raf.classycraft.app.gui.controller.drawingToolbarActions.*;

public class ActionManager {
    private ExitAction ea;
    private AboutUsAction aboutUs;
    private AddClassAction addClassAction;
    private NewItemAction newItemAction;
    private AddConnectionAction addConnectionAction;
    private RemoveItemAction removeItemAction;
    private EditAction editAction;
    private ProjectAuthorAction projectAuthorAction;
    private EraserAction eraserAction;
    private MoveAction moveAction;
    private SelectionAction selectionAction;
    private ZoomOutAction zoomOutAction;
    private ZoomInAction zoomInAction;
    private SaveAsAction saveAsAction;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private DuplicateAction duplicateAction;
    private OpenProjectAction openProjectAction;
    private ScreenshotAction screenshotAction;
    private MyMouseListener myMouseListener;

    private ExportCodeAction exportCodeAction;
    public ActionManager(){
        initialiseActions();
    }

    private void initialiseActions(){
        ea = new ExitAction();
        aboutUs = new AboutUsAction();
        newItemAction = new NewItemAction();
        removeItemAction = new RemoveItemAction();
        projectAuthorAction = new ProjectAuthorAction();
        addClassAction = new AddClassAction();
        addConnectionAction = new AddConnectionAction();
        editAction = new EditAction();
        eraserAction = new EraserAction();
        moveAction = new MoveAction();
        selectionAction = new SelectionAction();
        duplicateAction = new DuplicateAction();
        zoomInAction = new ZoomInAction();
        zoomOutAction = new ZoomOutAction();
        saveAsAction = new SaveAsAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        openProjectAction = new OpenProjectAction();
        screenshotAction = new ScreenshotAction();
        exportCodeAction = new ExportCodeAction();
    }

    public ExitAction getEa() {
        return ea;
    }

    public AboutUsAction getAboutUs() {
        return aboutUs;
    }

    public NewItemAction getNewItemAction() {
        return newItemAction;
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
    public OpenProjectAction getOpenProjectAction(){return openProjectAction;}
    public SelectionAction getSelectionAction(){return selectionAction;}
    public UndoAction getUndoAction() {return undoAction;}
    public DuplicateAction getDuplicateAction() {
        return duplicateAction;
    }
    public RedoAction getRedoAction(){return redoAction;}
    public ZoomOutAction getZoomOutAction() {
        return zoomOutAction;
    }
    public SaveAsAction getSaveAsAction(){return saveAsAction;}
    public ZoomInAction getZoomInAction() {
        return zoomInAction;
    }

    public ScreenshotAction getScreenshotAction() {
        return screenshotAction;
    }

    public ExportCodeAction getExportCodeAction(){return  exportCodeAction;}
}
