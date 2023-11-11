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
        File file = new File("dizajn-softvera-projekat-tim_markostojicic_vidanstojic/dizajn-softvera-projekat-tim_markostojicic_vidanstojic/src/main/resources/log.txt");

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
