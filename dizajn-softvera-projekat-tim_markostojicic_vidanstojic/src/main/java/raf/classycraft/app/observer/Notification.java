package raf.classycraft.app.observer;

import lombok.Getter;
import lombok.Setter;
import raf.classycraft.app.model.messageGenerator.EventTypes;
import raf.classycraft.app.model.messageGenerator.Type;

@Getter
@Setter
public class Notification{
    private String message;

    private Type type;
    private EventTypes eventTypes;

    public Notification(String message, EventTypes eventTypes, Type type) {
        this.message = message;
        this.eventTypes = eventTypes;
        this.type = type;
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
}
