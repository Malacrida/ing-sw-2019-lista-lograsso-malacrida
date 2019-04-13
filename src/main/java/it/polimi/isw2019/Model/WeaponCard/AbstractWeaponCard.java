package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public abstract class AbstractWeaponCard implements WeaponCard {
    protected int id;
    protected String name;
    protected ColorCube color;
    protected ArrayList<String> effect;
    protected ArrayList<ColorCube> rechargeCube;
    protected StateCard stateCard;

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
