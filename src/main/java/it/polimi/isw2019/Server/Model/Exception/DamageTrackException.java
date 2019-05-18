package it.polimi.isw2019.Server.Model.Exception;

import it.polimi.isw2019.Server.Model.ColorPlayer;

public class DamageTrackException extends Exception {

    private ColorPlayer colorPlayer;

    public DamageTrackException(){

    }

    public DamageTrackException(ColorPlayer colorPlayer){
            this.colorPlayer=colorPlayer;
    }

    public ColorPlayer getColorPlayer() {
        return colorPlayer;
    }
}
