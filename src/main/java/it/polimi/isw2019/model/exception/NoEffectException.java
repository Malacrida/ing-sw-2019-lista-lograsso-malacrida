package it.polimi.isw2019.model.exception;

public class NoEffectException extends Exception {

    public NoEffectException(){
        super("This effect doesn't exist");
    }

}
