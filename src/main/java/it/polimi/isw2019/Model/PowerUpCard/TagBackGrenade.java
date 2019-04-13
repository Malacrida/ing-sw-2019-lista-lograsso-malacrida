package it.polimi.isw2019.Model.PowerUpCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.PowerUpCard.AbstractPowerUpCard;
import it.polimi.isw2019.Model.StateCard;

public class TagBackGrenade extends AbstractPowerUpCard {

    private int id;
    private String name = "Tag Back Grenade";
    private ColorCube color;
    private String infoEffect = " You may play this card\n" +
            "  when you receive damage\n" +
            "  from a player you can see.\n" +
            "  Give that player 1 mark.\n";
    private StateCard checkState;

}