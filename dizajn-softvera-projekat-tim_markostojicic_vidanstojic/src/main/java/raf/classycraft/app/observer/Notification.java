package raf.classycraft.app.observer;


import raf.classycraft.app.model.messageGenerator.EventTypes;
import raf.classycraft.app.model.messageGenerator.Type;



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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public EventTypes getEventTypes() {
        return eventTypes;
    }

    public void setEventTypes(EventTypes eventTypes) {
        this.eventTypes = eventTypes;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
