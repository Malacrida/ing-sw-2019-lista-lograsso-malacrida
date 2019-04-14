package it.polimi.isw2019.Model.PowerUpCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

public abstract class AbstractPowerUpCard implements PowerUpCard {

    /* Attributes */

    protected int id;
    protected String name;
    protected ColorCube color;
    protected String infoEffect;
    protected StateCard checkState;


    /* Methods */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ColorCube getColor() {
        return color;
    }

    public String getInfoEffect(){
        return infoEffect;
    }

    public StateCard getCheckState() {
        return checkState;
    }

}
