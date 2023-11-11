package raf.classycraft.app.model.simpleFactory;

import raf.classycraft.app.core.ApplicationFramework;
import raf.classycraft.app.observer.NotificationMessageGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class FileLogger implements Logger {


    public  FileLogger(){
        ApplicationFramework.getInstance().getMessageGenerator().addSubscriber(this);

    }

    @Override
    public void update(Object notify) {
        NotificationMessageGenerator notification= (NotificationMessageGenerator) notify;
        this.log(notification.getMessage());
    }

    @Override
    public void log(String message) {
        URL filePath = getClass().getResource("resources/log.txt");

        File file = new File(String.valueOf(filePath));

        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(message);
            bufferedWriter.newLine();

            bufferedWriter.close();
            fileWriter.close();
            System.out.println("Mare doktro");

        } catch (IOException e) {
            System.out.println("Podaci se nisu pravilno upisali u fajl");
            throw new RuntimeException(e);
        }

    }
}
