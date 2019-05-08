package it.polimi.isw2019.Server.Model.Exception;

import it.polimi.isw2019.Server.Model.ColorPlayer;

public class KillShotException extends Exception {

    private ColorPlayer colorPlayer;

    public KillShotException(){

    }

    public KillShotException(ColorPlayer colorPlayer){
            this.colorPlayer=colorPlayer;
    }

    public ColorPlayer getColorPlayer() {
        return colorPlayer;
    }
}
