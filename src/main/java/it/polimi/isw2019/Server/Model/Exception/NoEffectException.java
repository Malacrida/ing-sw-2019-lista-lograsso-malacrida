package it.polimi.isw2019.Server.Model.Exception;

public class NoEffectException extends Exception {

    public NoEffectException(){
        super("This effect doesn't exist");
    }

}
