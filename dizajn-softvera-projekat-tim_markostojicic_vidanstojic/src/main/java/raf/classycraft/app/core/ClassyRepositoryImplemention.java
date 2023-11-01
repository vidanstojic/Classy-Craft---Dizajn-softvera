package raf.classycraft.app.core;

import raf.classycraft.app.model.CompositeImplement.ProjectExplorer;

public class ClassyRepositoryImplemention implements ClassyRepository {

    private ProjectExplorer root;

    public ClassyRepositoryImplemention(ProjectExplorer root) {
        this.root = root;
    }

    @Override
    public ProjectExplorer getRoot() {
        return this.root;
    }
}
