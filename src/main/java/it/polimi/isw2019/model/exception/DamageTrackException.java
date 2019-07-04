package it.polimi.isw2019.model.exception;

import it.polimi.isw2019.model.ColorPlayer;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class DamageTrackException extends Exception {

    private ColorPlayer colorPlayer;

    private ArrayList<Player> players;

    public DamageTrackException(){

    }

    public DamageTrackException(ColorPlayer colorPlayer){
            this.colorPlayer=colorPlayer;
    }

    public ColorPlayer getColorPlayer() {
        return colorPlayer;
    }
}
