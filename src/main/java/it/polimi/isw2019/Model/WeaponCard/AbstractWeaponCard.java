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


    public abstract int getID();

    public abstract String getName();

    public abstract ArrayList<ColorCube> getRechargecube();

    public abstract ColorCube getColor();

    public abstract ArrayList<String> getInfoEffect();

    public abstract StateCard checkState();

    public abstract boolean firstEffect();

    public abstract boolean secondEffect();

    public abstract boolean thirdEffect();


    public static void doOneDamage(){ //prende in ingresso 1 giocatore
        //Inserire il fai 1 danno
    }

    public static void moveOneSquare(){
        //inserire il movimento
    }

    public static void putOneMark(){
        //mettere un marchio
    }


}