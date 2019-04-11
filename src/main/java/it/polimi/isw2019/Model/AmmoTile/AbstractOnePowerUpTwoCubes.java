package it.polimi.isw2019.Model.AmmoTile;

import it.polimi.isw2019.Model.Cube;
import it.polimi.isw2019.Model.PowerUpCard.PowerUpCard;

public abstract class AbstractOnePowerUpTwoCubes implements AmmoTile{

    private int id;
    private Cube[] cubes = new Cube[2];
    private int powerUpCard;


    public int getId(){
        return id;
    }

   /*public Cube getCube(){
        return cubes;
    }*/

    public int getIdPowerUpCard(){
        return powerUpCard;
    }
}
