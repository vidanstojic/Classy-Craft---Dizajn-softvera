package raf.classycraft.app.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import raf.classycraft.app.model.compositeAbstract.ClassyNode;
import raf.classycraft.app.model.compositeImplement.Diagram;
import raf.classycraft.app.model.compositeImplement.MyPackage;
import raf.classycraft.app.model.compositeImplement.Project;
import raf.classycraft.app.serializer.Serializer;

import java.io.File;
import java.io.IOException;

public class JacksonSerializer implements Serializer {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private Diagram diagram;
    private Project p;

    @Override
    public Project loadProject(File file) {
        try {
            return p = objectMapper.readValue(file, Project.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Diagram loadDiagram(File file) {
        try {
            return diagram = objectMapper.readValue(file, Diagram.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveProject(ClassyNode classyNode) {
        try {
            if (classyNode instanceof Project) {
                Project project = (Project) classyNode;
                objectMapper.writeValue(new File(String.valueOf(project.getFilepath())), project);
            } else if (classyNode instanceof Diagram) {
                Diagram diagram = (Diagram) classyNode;
                objectMapper.writeValue(new File(String.valueOf(diagram.findProject().getFilepath())), diagram);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
