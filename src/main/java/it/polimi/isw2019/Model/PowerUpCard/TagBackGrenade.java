package it.polimi.isw2019.Model.PowerUpCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.PowerUpCard.AbstractPowerUpCard;
import it.polimi.isw2019.Model.StateCard;

public class TagBackGrenade extends AbstractPowerUpCard {

    public TagBackGrenade(){
        this.name = "Tag Back Grenade";
        this.infoEffect = " You may play this card\n" +
                "  when you receive damage\n" +
                "  from a player you can see.\n" +
                "  Give that player 1 mark.\n";
    }

}