package it.polimi.isw2019.Server.Model.Exception;

public class InstanceArenaException extends Exception {

    private String info;

    public InstanceArenaException(){
        info="Arena is already instanced";
    }

    public String getInfo() {
        return info;
    }
}
