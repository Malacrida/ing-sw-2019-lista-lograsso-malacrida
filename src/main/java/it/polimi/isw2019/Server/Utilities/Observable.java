package it.polimi.isw2019.Server.Utilities;

import java.util.ArrayList;
import java.util.List;
import it.polimi.isw2019.Server.Message.*;

public abstract class Observable {

    private List<Observer> observers;

    public Observable(){
        observers=new ArrayList<Observer>();
    }

    public void registerObserver(Observer o){
        observers.add(o);
    }

    public void unregisterObserver(Observer o){
        this.observers.remove(o);
    }

    /*public void notifyObservers(){
        for(Observer o: this.observers){
            o.update();
        }
    }*/

    //togliere diamont
    public <T> void notifyObservers(T message){
        for(Observer o: this.observers){
            o.update(message);
        }
    }
}
