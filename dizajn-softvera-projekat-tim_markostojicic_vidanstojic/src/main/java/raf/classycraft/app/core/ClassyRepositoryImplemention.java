package raf.classycraft.app.core;

import raf.classycraft.app.model.compositeImplement.ProjectExplorer;

public class ClassyRepositoryImplemention implements ClassyRepository {

    private ProjectExplorer root;

    public ClassyRepositoryImplemention() {
        this.root = new ProjectExplorer();
    }

    @Override
    public ProjectExplorer getRoot() {
        return this.root;
    }


}
