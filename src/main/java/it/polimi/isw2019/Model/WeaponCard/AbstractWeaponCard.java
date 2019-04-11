package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

public abstract class AbstractWeaponCard implements WeaponCard {
    private int id;
    private String name;
    private ColorCube color;
    private ColorCube[] rechargeCube;
    private StateCard stateCard;

    //Methods
    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public ColorCube getColor(){
        return color;
    }

    /*public ColorCube[] getRechargecube() {
        return rechargeCube;
    }*/

    public StateCard checkState(){
        return stateCard;
    }

    //aggiungere metodo is allowed
}
