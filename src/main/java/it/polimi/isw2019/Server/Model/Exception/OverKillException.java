package it.polimi.isw2019.Server.Model.Exception;

import it.polimi.isw2019.Server.Model.ColorPlayer;

public class OverKillException extends Exception {

    private ColorPlayer colorPlayer;

    public OverKillException(){

    }

    public OverKillException(ColorPlayer colorPlayer){
        this.colorPlayer=colorPlayer;
    }

    public ColorPlayer getColorPlayer() {
        return colorPlayer;
    }
}
