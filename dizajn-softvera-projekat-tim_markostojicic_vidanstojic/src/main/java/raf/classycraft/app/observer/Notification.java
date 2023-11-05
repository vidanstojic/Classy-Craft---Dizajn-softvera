package raf.classycraft.app.observer;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import raf.classycraft.app.model.messageGenerator.EventTypes;
import raf.classycraft.app.model.messageGenerator.Type;


@Data
public class Notification{
    private String message;

    private Type type;
    private EventTypes eventTypes;
    private String messageText;

    public Notification(String message, EventTypes eventTypes, Type type,String messageText) {
        this.message = message;
        this.eventTypes = eventTypes;
        this.type = type;
        this.messageText = messageText;
    }

}
