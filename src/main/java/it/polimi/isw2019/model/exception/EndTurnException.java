package it.polimi.isw2019.model.exception;

public class EndTurnException extends Exception {

    public EndTurnException(){

    }

    public EndTurnException(String info){
        super(info);
    }
}
