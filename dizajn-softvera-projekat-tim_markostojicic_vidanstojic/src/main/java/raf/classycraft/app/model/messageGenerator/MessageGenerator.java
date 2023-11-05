package raf.classycraft.app.model.messageGenerator;


import raf.classycraft.app.observer.IPublisher;
import raf.classycraft.app.observer.ISubscriber;
import raf.classycraft.app.observer.Notification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



public class MessageGenerator implements IPublisher {



    private List<ISubscriber> subscribers = new ArrayList<>();

    public MessageGenerator(){

    }

    public void generateMessage(EventTypes eventType,Type type){
        String message = "";
        String messageText = "";

        if(type == Type.ERROR && eventType == EventTypes.NAME_CANNOT_BE_EMPTY){
            //[ERROR][12.11.2022. 22:56] ProjectExplorer ne može biti obrisan.
            messageText = "Name can not be empty.";
            message = "["+Type.ERROR+"]"+"["+ LocalDateTime.now()+"]"+ messageText;
        }
        else if(type == Type.ERROR && eventType == EventTypes.NODE_CANNOT_BE_DELETED){
            messageText = "Node can not be deleted.";
            message = "["+Type.ERROR+"]"+"["+ LocalDateTime.now()+"]"+ messageText;
        }
        else if(type == Type.ERROR && eventType == EventTypes.PROJECTEXPLORER_CANNOT_BE_DELETED){
            messageText = "ProjectExplorer can not be deleted.";
            message = "["+Type.ERROR+"]"+"["+ LocalDateTime.now()+"]"+messageText;
        }
        else if(type == Type.WARNING && eventType == EventTypes.NODE_MUST_BE_SELECTED){
            messageText = "Node must be selected.";
            message = "["+Type.WARNING+"]"+"["+ LocalDateTime.now()+"]"+ messageText;
        }
        else if(type == Type.WARNING && eventType == EventTypes.NODE_WILL_BE_DELETED){
            messageText = "Node will be deleted.";
            message = "["+Type.WARNING+"]"+"["+ LocalDateTime.now()+"]"+ messageText;
        }
        else if(type == Type.INFORMATION && eventType == EventTypes.NODE_SUCCESSFULLY_RENAMED){
            messageText = "Node is successfully renamed.";
            message = "["+Type.INFORMATION+"]"+"["+ LocalDateTime.now()+"]"+ messageText;
        }
        else if(type == Type.INFORMATION && eventType == EventTypes.NODE_SUCCESSFULLY_DELETED){
            messageText = "Node is successfully deleted.";
            message = "["+Type.INFORMATION+"]"+"["+ LocalDateTime.now()+"]"+ messageText;
        }

        

        Notification notification = new Notification(message, eventType, type, messageText);
        notifySub(notification);

    }

    @Override
    public void addSubscriber(ISubscriber iSubscriber) {
        this.subscribers.add(iSubscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber iSubscriber) {
        this.subscribers.remove(iSubscriber);
    }

    @Override
    public void notifySub(Notification notification) {
        for(ISubscriber listener : subscribers){
            listener.update(notification);
        }
    }

    public List<ISubscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<ISubscriber> subscribers) {
        this.subscribers = subscribers;
    }
}
