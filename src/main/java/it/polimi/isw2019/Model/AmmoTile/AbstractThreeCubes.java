package it.polimi.isw2019.Model.AmmoTile;

import it.polimi.isw2019.Model.Cube;

public abstract class AbstractThreeCubes implements AmmoTile {

    private int id;
    private Cube[] cubes = new Cube[3];

    public int getId() {
        return id;
    }

    /*public Cube getCube() {
        return cubes;
    }*/


}
