package raf.classycraft.app.model.messageGenerator;

import lombok.Getter;
import lombok.Setter;
import raf.classycraft.app.observer.IPublisher;
import raf.classycraft.app.observer.ISubscriber;
import raf.classycraft.app.observer.Notification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class MessageGenerator implements IPublisher {



    private List<ISubscriber> subscribers = new ArrayList<>();

    public MessageGenerator(){

    }

    public void generateMessage(EventTypes eventType,Type type){
        String message = "";

        if(type == Type.ERROR && eventType == EventTypes.NAME_CANNOT_BE_EMPTY){
            //[ERROR][12.11.2022. 22:56] ProjectExplorer ne može biti obrisan.
            message = "["+Type.ERROR+"]"+"["+ LocalDateTime.now()+"]"+" Name can not be empty.";
        }
        else if(type == Type.ERROR && eventType == EventTypes.NODE_CANNOT_BE_DELETED){
            message = "["+Type.ERROR+"]"+"["+ LocalDateTime.now()+"]"+" Node can not be deleted.";
        }
        else if(type == Type.ERROR && eventType == EventTypes.PROJECTEXPLORER_CANNOT_BE_DELETED){
            message = "["+Type.ERROR+"]"+"["+ LocalDateTime.now()+"]"+" ProjectExplorer can not be deleted.";
        }
        else if(type == Type.WARNING && eventType == EventTypes.NODE_MUST_BE_SELECTED){
            message = "["+Type.WARNING+"]"+"["+ LocalDateTime.now()+"]"+" Node must be deleted.";
        }
        else if(type == Type.WARNING && eventType == EventTypes.NODE_WILL_BE_DELETED){
            message = "["+Type.WARNING+"]"+"["+ LocalDateTime.now()+"]"+" Node will be deleted.";
        }
        else if(type == Type.INFORMATION && eventType == EventTypes.NODE_SUCCESSFULLY_RENAMED){
            message = "["+Type.INFORMATION+"]"+"["+ LocalDateTime.now()+"]"+" Node is successfully renamed.";
        }
        else if(type == Type.INFORMATION && eventType == EventTypes.NODE_SUCCESSFULLY_DELETED){
            message = "["+Type.INFORMATION+"]"+"["+ LocalDateTime.now()+"]"+" Node is successfully deleted.";
        }

        

        Notification notification = new Notification(message, eventType, type);
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


}
