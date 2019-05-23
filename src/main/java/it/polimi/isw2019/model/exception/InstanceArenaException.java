package it.polimi.isw2019.model.exception;

public class InstanceArenaException extends Exception {

    private String info;

    public InstanceArenaException(){
        info="Arena is already instanced";
    }

    public String getInfo() {
        return info;
    }
}
