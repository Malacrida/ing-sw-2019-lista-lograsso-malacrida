package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public abstract class AbstractWeaponCard{
    protected int id;
    protected String name;
    protected ColorCube color;
    protected ArrayList<String> infoEffect;
    protected ArrayList<ColorCube> rechargeCube;
    protected StateCard stateCard = StateCard.DECK;

    public AbstractWeaponCard(int id, String name, ColorCube color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    //Methods
    public int getId() {
        return id;
    }

    public abstract int getID();

    public String getName(){
        return name;
    }

    public abstract String getEffect();

    public abstract ColorCube getRechargecube();

    public ColorCube getColor(){
        return color;
    }

    public ArrayList<String> getInfoEffect() {return infoEffect; }

    public ArrayList<ColorCube> getRechargeCube() {return rechargeCube; }

    public StateCard checkState(){
        return this.stateCard;
    }
}