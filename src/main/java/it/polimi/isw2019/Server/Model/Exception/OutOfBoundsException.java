package it.polimi.isw2019.Server.Model.Exception;

public class OutOfBoundsException extends Exception {

    String info;
    public OutOfBoundsException(){

    }

    public OutOfBoundsException(String info){
        this.info= info;
    }

    public String getInfo() {
        return info;
    }
}
