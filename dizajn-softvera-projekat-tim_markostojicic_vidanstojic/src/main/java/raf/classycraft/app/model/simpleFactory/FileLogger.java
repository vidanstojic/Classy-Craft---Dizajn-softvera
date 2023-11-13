package raf.classycraft.app.model.simpleFactory;

import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.observer.NotificationMessageGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    private File file = new File("src/main/resources/log.txt");
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    public  FileLogger(){
        ApplicationFramework.getInstance().getMessageGenerator().addSubscriber(this);
        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public void update(Object notify) {
        NotificationMessageGenerator notification= (NotificationMessageGenerator) notify;
        this.log(notification.getMessage());
    }

    @Override
    public void log(String message) {

        try {
            fileWriter = new FileWriter(this.file, true);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(message);
            bufferedWriter.newLine();

            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("Podaci se nisu pravilno upisali u fajl");
            throw new RuntimeException(e);
        }

    }
}
