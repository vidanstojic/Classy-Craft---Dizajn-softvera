package raf.classycraft.app.observer;


import raf.classycraft.app.model.messageGenerator.EventTypes;
import raf.classycraft.app.model.messageGenerator.Type;



public class NotificationMessageGenerator {
    private String message;
    private Type type;
    private EventTypes eventTypes;
    private String messageText;

    public NotificationMessageGenerator(String message, EventTypes eventTypes, Type type, String messageText) {
        this.message = message;
        this.eventTypes = eventTypes;
        this.type = type;
        this.messageText = messageText;
    }

    public String getMessage() {
        return message;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    public String getMessageText() {
        return messageText;
    }
}
