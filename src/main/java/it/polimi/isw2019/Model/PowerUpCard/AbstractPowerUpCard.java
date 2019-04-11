package it.polimi.isw2019.Model.PowerUpCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

public abstract class AbstractPowerUpCard implements PowerUpCard {

    private int id;
    private String name;
    private ColorCube color;
    private StateCard stateCard;


    //Methods
    public String getName() {
        return name;
    }

    public void doEffect() {

    }

    public int getId() {
        return id;
    }

    public ColorCube getColor() {
        return color;
    }

    public StateCard checkState() {
        return stateCard;
    }

}
