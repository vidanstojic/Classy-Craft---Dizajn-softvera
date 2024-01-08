package raf.classycraft.app.observer;

public class ProjectNotificationType {
    private String name;
    private String author;

    public ProjectNotificationType(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }
}
