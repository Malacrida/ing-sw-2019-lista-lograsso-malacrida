package it.polimi.isw2019.Model.Exception;

import it.polimi.isw2019.Model.ColorPlayer;

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
