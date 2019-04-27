package it.polimi.isw2019.Model.Exception;

import it.polimi.isw2019.Model.ColorPlayer;

public class OverKill extends Exception {

    private ColorPlayer colorPlayer;

    public OverKill (){

    }

    public OverKill (ColorPlayer colorPlayer){
        this.colorPlayer=colorPlayer;
    }

    public ColorPlayer getColorPlayer() {
        return colorPlayer;
    }
}
