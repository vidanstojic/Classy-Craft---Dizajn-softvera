package raf.classycraft.app.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import raf.classycraft.app.model.compositeImplement.Project;
import raf.classycraft.app.serializer.Serializer;

import java.io.File;
import java.io.IOException;

public class JacksonSerializer implements Serializer {

    private final ObjectMapper objectMapper = new ObjectMapper();
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
    public void saveProject(Project project) {
        try {
            objectMapper.writeValue(new File(String.valueOf(project.getFilepath())), project);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
