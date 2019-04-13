package it.polimi.isw2019.Model.AmmoTile;

import it.polimi.isw2019.Model.Cube;

import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public interface AmmoTile {

    public int getId();

    public ArrayList<Cube> getCubes();

    public int getIdPowerUpCard();

    public StateCard getCheckState();

}
