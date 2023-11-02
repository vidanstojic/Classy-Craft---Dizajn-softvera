package raf.classycraft.app.model.simpleFactory;

import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.model.simpleFactory.Logger;
import raf.classycraft.app.observer.Notification;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {


    public  FileLogger(){
        ApplicationFramework.getInstance().getMessageGenerator().addSubscriber(this);

    }

    @Override
    public void update(Notification notification) {
        this.log(notification.getMessage());
    }

    @Override
    public void log(String message) {
        String filePath = "resources/txtFiles/log.txt";

        File file = new File(filePath);

        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

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
