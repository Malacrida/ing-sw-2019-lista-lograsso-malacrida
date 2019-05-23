package it.polimi.isw2019.model.exception;

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
