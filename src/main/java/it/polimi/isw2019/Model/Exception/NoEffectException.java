package it.polimi.isw2019.Model.Exception;

public class NoEffectException extends Exception {

    public NoEffectException(){
        super("This effect doesn't exist");
    }

}
