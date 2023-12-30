package raf.classycraft.app.serializer;

import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeImplement.Diagram;
import raf.classycraft.app.model.compositeImplement.Project;

import java.io.File;

public interface Serializer {
    Project loadProject(File file);
    Diagram loadDiagram(File file);
    void saveProject(ClassyNode node);
}
