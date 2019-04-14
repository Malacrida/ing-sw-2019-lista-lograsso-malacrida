package it.polimi.isw2019.Model.AmmoTile;

import it.polimi.isw2019.Model.Cube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public abstract class AbstractAmmoTile implements AmmoTile {

    protected int id;
    protected ArrayList<Cube> cubes;
    protected int idPowerUpCard;
    protected StateCard checkState;


    /* Methods */

    public int getId() {
        return id;
    }

    public ArrayList<Cube> getCubes(){
        return cubes;
    }

    public int getIdPowerUpCard(){
        return idPowerUpCard;
    }

    public StateCard getCheckState(){
        return checkState;
    }


}
