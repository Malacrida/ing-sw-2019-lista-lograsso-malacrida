package it.polimi.isw2019.utilities;


import java.util.ArrayList;
import java.util.List;

public abstract class Observable<T> {

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

    public void notifyObservers(T message){
        for(Observer o: this.observers){
            o.update(message);
        }
    }
}